package ir.infra;

import com.codahale.metrics.MetricRegistry;
import ir.infra.tables.EmsInfo;

import java.io.IOException;

/**
 * Defines the necessary methods for each objects which should be persisted into the nosql database.
 */
public interface NoSqlClient {

    final MetricRegistry metrics = new MetricRegistry();

    void add(EmsInfo emsInfo) throws IOException;

    EmsInfo getEmsInfo(long id) throws IOException;
}
