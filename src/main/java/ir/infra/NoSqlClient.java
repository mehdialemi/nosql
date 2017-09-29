package ir.infra;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.Timer;
import ir.infra.core.ClusterConf;
import ir.infra.tables.EmsInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Defines the necessary methods for each objects which should be persisted into the nosql database.
 */
public abstract class NoSqlClient {

    protected Timer addTimer;
    protected Timer getTimer;


    private final ConsoleReporter reporter;

    public NoSqlClient(ClusterConf conf) {
        addTimer = conf.getMetricRegistry().timer("add_duration");
        getTimer = conf.getMetricRegistry().timer("get_duration");

        reporter = ConsoleReporter.forRegistry(conf.getMetricRegistry())
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter((s, metric) -> s.endsWith("duration"))
                .build();
        reporter.start(conf.getReportPeriod(), TimeUnit.SECONDS);
    }

    protected abstract void add(EmsInfo emsInfo) throws IOException;

    protected abstract EmsInfo getEmsInfo(long id) throws IOException;
}
