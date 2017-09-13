package ir.infra.hbase;

import ir.infra.tables.EmsInfo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class EmsInfoDAO {


    private final Connection connection;
    private final Table table;

    /**
     * Constructs a data access object. Classes extending this class <strong>must</strong> call this constructor using <code>super</code>
     *
     * @param configuration Hadoop configuration
     * @throws IOException           Exceptions thrown by HBase
     * @throws IllegalStateException Annotation(s) on base entity may be incorrect
     */
    public EmsInfoDAO(Configuration configuration) throws IOException {
        connection = ConnectionFactory.createConnection(configuration);
        table = connection.getTable(TableName.valueOf("EmsInfo"));
    }

    public boolean isConnected() {
        return !connection.isClosed();
    }

    public void persist(EmsInfo emsInfo) throws IOException {
        table.put(EmsInfo.createPut(emsInfo));
    }

    public EmsInfo get(long emsInfoId) throws IOException {
        Get get = new Get(Bytes.toBytes(emsInfoId));
        Result result = table.get(get);
        return EmsInfo.load(result);
    }
}
