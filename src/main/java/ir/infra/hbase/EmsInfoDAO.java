package ir.infra.hbase;

import com.flipkart.hbaseobjectmapper.AbstractHBDAO;
import com.flipkart.hbaseobjectmapper.HBObjectMapper;
import com.flipkart.hbaseobjectmapper.HBRecord;
import ir.infra.tables.EmsInfo;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;
import java.util.List;

public class EmsInfoDAO extends AbstractHBDAO<Long, EmsInfo> {


    /**
     * Constructs a data access object. Classes extending this class <strong>must</strong> call this constructor using <code>super</code>
     *
     * @param configuration Hadoop configuration
     * @throws IOException           Exceptions thrown by HBase
     * @throws IllegalStateException Annotation(s) on base entity may be incorrect
     */
    protected EmsInfoDAO(Configuration configuration) throws IOException {
        super(configuration);
    }

    public boolean isConnected() {
        return !connection.isClosed();
    }

    @Override
    public List<Long> persist(List<? extends HBRecord<Long>> hbRecords) throws IOException {

        return super.persist(hbRecords);
    }
}
