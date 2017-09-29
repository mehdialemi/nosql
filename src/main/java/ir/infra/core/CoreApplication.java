package ir.infra.core;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ir.infra.cassandra.CassandraAPIs;
import ir.infra.hbase.HBaseAPIs;

public class CoreApplication extends Application<CoreConfiguration> {

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void initialize(Bootstrap<CoreConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(CoreConfiguration conf, Environment environment) throws Exception {

        conf.getClusterConf().setMetricRegistry(environment.metrics());

        if (conf.getClusterConf().isEnable_cassandra()) {
            final CassandraAPIs cResource = new CassandraAPIs(conf.getClusterConf());
            environment.jersey().register(cResource);
            environment.healthChecks().register("cassandra",
                    new CassandraHealthCheck(cResource.getCassandraClient()));
        }

        if (conf.getClusterConf().isEnable_hbase()) {
            final HBaseAPIs hResource = new HBaseAPIs(conf.getClusterConf());
            environment.jersey().register(hResource);
            environment.healthChecks().register("hbase",
                    new HBaseHealthCheck(hResource.getHbaseClient()));
        }
    }

    public static void main(String[] args) throws Exception {
        new CoreApplication().run(args);
    }
}
