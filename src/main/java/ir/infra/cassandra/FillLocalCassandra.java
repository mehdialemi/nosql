package ir.infra.cassandra;

import ir.infra.clients.RandomEmsInfoGenerator;
import ir.infra.core.ClusterConf;

public class FillLocalCassandra {

    public static void main(String[] args) throws Exception {
        ClusterConf conf = new ClusterConf();
        conf.setCoordinator("localhost");
        conf.setEnable_test(false);
        CassandraClient cassandraClient = new CassandraClient(conf);

        for (int i = 0 ; i < 10; i ++) {
            System.out.println("Writing item: " + (i + 1));
            cassandraClient.add(RandomEmsInfoGenerator.randomEmsInfo());
            Thread.sleep(300);
        }

        cassandraClient.close();
    }
}
