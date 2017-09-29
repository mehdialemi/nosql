package ir.infra;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.Timer;
import ir.infra.core.ClusterConf;
import ir.infra.tables.EmsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Defines the necessary methods for each objects which should be persisted into the nosql database.
 */
public abstract class NoSqlAPI {

    protected Timer addTimer;
    protected Timer getTimer;


    public NoSqlAPI(ClusterConf conf) {
        addTimer = conf.getMetricRegistry().timer("add_duration");
        getTimer = conf.getMetricRegistry().timer("get_duration");

        Slf4jReporter.forRegistry(conf.getMetricRegistry())
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter((s, metric) -> s.endsWith("duration"))
                .outputTo(LoggerFactory.getLogger("metric"))
                .build()
                .start(conf.getReportPeriod(), TimeUnit.SECONDS);
    }

    protected abstract void add(EmsInfo emsInfo) throws IOException;

    protected abstract EmsInfo getEmsInfo(long id) throws IOException;
}
