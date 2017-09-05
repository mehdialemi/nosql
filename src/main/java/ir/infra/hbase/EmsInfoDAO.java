package ir.infra.hbase;

import com.flipkart.hbaseobjectmapper.AbstractHBDAO;
import ir.infra.tables.EmsInfo;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;

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
}
