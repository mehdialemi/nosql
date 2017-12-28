package ir.infra.cassandra;

import ir.infra.core.ClusterConf;

public class DeleteOldAllowed {

    public static void main(String[] args) throws Exception {
        int old_allowed_sec = Integer.parseInt(args[0]);
        ClusterConf conf = new ClusterConf();
        conf.setOld_allowed_sec(old_allowed_sec);
        conf.setEnable_test(false);
        CassandraClient cassandraClient = new CassandraClient(conf);

        cassandraClient.deleteOldAllowed();

        cassandraClient.close();
    }
}
