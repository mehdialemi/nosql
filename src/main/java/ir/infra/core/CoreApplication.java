package ir.infra.core;

import ir.infra.cassandra.CassandraAPIs;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    public void run(CoreConfiguration coreConfiguration, Environment environment) throws Exception {
        final CassandraAPIs cResource = new CassandraAPIs();
        environment.jersey().register(cResource);
    }

    public static void main(String[] args) throws Exception {
        new CoreApplication().run(args);
    }
}
