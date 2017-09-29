package ir.infra.clients;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultRoutePlanner;
import org.apache.http.impl.conn.DefaultSchemePortResolver;
import org.apache.http.protocol.HttpContext;
import org.slf4j.LoggerFactory;

import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Writes EmsInfo objects into the database by calling the provided REST services.
 */
public class RandomEmsInfoWriter {

    public static final int MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        final String hostname = args[0];
        final String path = args[1];
        int threads = Integer.parseInt(args[2]);
        int reportPeriodSec = Integer.parseInt(args[3]);
        long testDuration = Integer.parseInt(args[4]);

        Logger logger = (Logger) LoggerFactory.getLogger("org.apache.http");
        logger.setLevel(Level.INFO);

        ConnectionKeepAliveStrategy keepAliveStrategy = new DefaultConnectionKeepAliveStrategy() {

            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAliveDuration = super.getKeepAliveDuration(response, context);
                if (keepAliveDuration == -1) {
                    keepAliveDuration = 45 * 1000; // 45 seconds
                }
                return keepAliveDuration;
            }
        };


        HttpRoutePlanner rp = new DefaultRoutePlanner(DefaultSchemePortResolver.INSTANCE) {

            @Override
            public HttpRoute determineRoute(
                    final HttpHost host,
                    final HttpRequest request,
                    final HttpContext context) throws HttpException {
                HttpHost target = host != null ? host : new HttpHost(hostname, 8080);
                return super.determineRoute(target, request, context);
            }
        };

        final SocketConfig socketConfig = SocketConfig.custom()
                .setSndBufSize(30 * MB)
                .setTcpNoDelay(true)
                .setSoKeepAlive(true)
                .build();

        HttpClientBuilder clientBuilder = HttpClients.custom()
                .setRoutePlanner(rp)
                .setKeepAliveStrategy(keepAliveStrategy)
                .setDefaultSocketConfig(socketConfig)
                .setConnectionManagerShared(true)
                .setConnectionTimeToLive(10, TimeUnit.SECONDS);

        MetricRegistry registry = new MetricRegistry();
        Timer timer = registry.timer("duration");

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(reportPeriodSec, TimeUnit.SECONDS);

        String url = "http://" + hostname + ":8080/" + path;

        ClientWriters clientWriters = new ClientWriters(clientBuilder, timer, url, threads);
        clientWriters.start();

        Thread.sleep(testDuration * 1000);

        clientWriters.stop();
    }
}
