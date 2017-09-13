package ir.infra.core;

import com.codahale.metrics.health.HealthCheck;
import ir.infra.cassandra.CassandraClient;

public class CassandraHealthCheck extends HealthCheck {

    private CassandraClient client;

    public CassandraHealthCheck(CassandraClient client) {
        this.client = client;
    }

    protected Result check() throws Exception {
        if (client.isConnected())
            return Result.healthy();
        return Result.unhealthy("Client connection is closed to cassandra");
    }
}
