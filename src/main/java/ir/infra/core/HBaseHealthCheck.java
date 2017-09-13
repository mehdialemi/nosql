package ir.infra.core;

import com.codahale.metrics.health.HealthCheck;
import ir.infra.hbase.HBaseClient;

public class HBaseHealthCheck extends HealthCheck {

    private HBaseClient client;

    public HBaseHealthCheck(HBaseClient client) {
        this.client = client;
    }

    protected Result check() throws Exception {
        if (client.isConnected())
            return Result.healthy();
        return Result.unhealthy("Client connection is closed to hbase");
    }
}
