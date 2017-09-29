package ir.infra.clients;


import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultRoutePlanner;
import org.apache.http.impl.conn.DefaultSchemePortResolver;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Writes EmsInfo objects into the database by calling the provided REST services.
 */
public class EmsInfoWriter {

//    final static String URL = "http://localhost:8080/cassandra/add/emsInfo";
//    public static final String HOSTNAME = "localhost";

    public static void main(String[] args) throws Exception {
        final String hostname = args[0];
        final String path = args[1];
        int num = Integer.parseInt(args[2]);


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

        CloseableHttpClient client = HttpClients.custom()
                .setRoutePlanner(rp)
                .setKeepAliveStrategy(keepAliveStrategy)
                .build();

        long sum = 0;
        String url = "http://" + hostname + ":8080/" + path;
        for (int i = 0; i < num; i++) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("content-type", "application/json");
            String json = RandomEmsInfoGenerator.randomEmsInfoJson(i);
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            long t1 = System.nanoTime();
            HttpResponse response = client.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != 204)
                throw new Exception("Wrong status code: " + response.getStatusLine().getStatusCode());

            long t2 = System.nanoTime();
            long duration = (t2 - t1) / 1000000;
            System.out.println("duration: " + duration);
            sum += duration;
        }

        System.out.println("Average Insert Duration: " + sum / 1000 + "");

        client.close();
//
//        Client client = Client.create(clientConf);
//
//        WebResource webResource = client.resource(URL);
//
//        EmsInfo emsInfo = new EmsInfo();
//        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
//                .post(ClientResponse.class, emsInfo);
//
//        if (response.getStatus() != 204) {
//            System.out.println("Error");
//        }
    }
}
