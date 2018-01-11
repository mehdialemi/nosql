package ir.infra.cassandra;

import ir.infra.core.ClusterConf;

public class DeleteOldAllowed {

    public static void main(String[] args) throws Exception {
        String hostname = args[0];
        int old_allowed_sec = Integer.parseInt(args[1]);
        int threadPerHost = Integer.parseInt(args[2]);
        ClusterConf conf = new ClusterConf();
        conf.setCoordinator(hostname);
        conf.setOld_allowed_sec(old_allowed_sec);
        conf.setThreadPerHost(threadPerHost);
        conf.setEnable_test(false);
        CassandraClient cassandraClient = new CassandraClient(conf);

        cassandraClient.deleteOldAllowedParallel();

        cassandraClient.close();
    }
}
