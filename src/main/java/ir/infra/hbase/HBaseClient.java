package ir.infra.hbase;

import ir.infra.tables.EmsInfo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;

import java.io.IOException;

/**
 * HBase client to insert objects into the hbase cluster.
 */
public class HBaseClient {

    EmsInfoDAO emsInfoDAO;

    public HBaseClient(String zkAddress) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set(HConstants.ZOOKEEPER_QUORUM, zkAddress);
        emsInfoDAO = new EmsInfoDAO(conf);
    }

    public boolean isConnected() {
        return emsInfoDAO.isConnected();
    }

    public void add(EmsInfo emsInfo) throws IOException {
        emsInfoDAO.persist(emsInfo);
    }

    public EmsInfo getEmsInfo(long id) throws IOException {
        return emsInfoDAO.get(id);
    }
}
