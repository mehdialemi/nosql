package ir.infra.clients;

import com.codahale.metrics.Timer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ClientWriters {

    private final Timer timer;
    private final String url;
    private boolean stop = false;
    private ExecutorService executorService;
    private List<Writer> writers;

    public ClientWriters(HttpClientBuilder clientBuilder, Timer timer, String url, int threads) {
        this.timer = timer;
        this.url = url;
        executorService = Executors.newFixedThreadPool(threads);
        writers = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            writers.add(new Writer(clientBuilder));
        }
    }

    public void start() throws InterruptedException {
        stop = false;
        executorService.invokeAll(writers);
    }

    public void stop() {
        stop = true;
        executorService.shutdown();
    }

    class Writer implements Callable<Boolean> {

        HttpPost httpPost = new HttpPost(url);
        private final CloseableHttpClient client;

        public Writer(HttpClientBuilder clientBuilder) {
            httpPost.addHeader("content-type", "application/json");
            client = clientBuilder.build();
        }

        @Override
        public Boolean call() throws JsonProcessingException {
            while (!stop) {
                String json = RandomEmsInfoGenerator.randomEmsInfoJson();
                StringEntity entity = null;
                try {
                    entity = new StringEntity(json);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    continue;
                }
                httpPost.setEntity(entity);
                long t1 = System.nanoTime();
                HttpResponse response = null;
                try {
                    response = client.execute(httpPost);
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                if (response.getStatusLine().getStatusCode() != 204) {
                    System.out.println("Wrong status code: " + response.getStatusLine().getStatusCode());
                    continue;
                }

                long t2 = System.nanoTime();
                long duration = t2 - t1;
                timer.update(duration, TimeUnit.NANOSECONDS);
            }

            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
}
