package ir.infra.core;

import com.codahale.metrics.MetricRegistry;
import org.codehaus.jackson.annotate.JsonProperty;

public class ClusterConf {

    private String zkAddress = "localhost:2181";

    private String coordinator = "localhost";

    private boolean enable_hbase = true;

    private boolean enable_cassandra = true;

    private int reportPeriod = 10;

    private boolean enable_test = true;

    private MetricRegistry metricRegistry;

    private int old_allowed_sec;

    private int threadPerHost;

    @JsonProperty
    public String getZkAddress() {
        return zkAddress;
    }

    @JsonProperty
    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    @JsonProperty
    public String getCoordinator() {
        return coordinator;
    }

    @JsonProperty
    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }


    @JsonProperty
    public boolean isEnable_hbase() {
        return enable_hbase;
    }

    @JsonProperty
    public void setEnable_hbase(boolean enable_hbase) {
        this.enable_hbase = enable_hbase;
    }

    @JsonProperty
    public boolean isEnable_cassandra() {
        return enable_cassandra;
    }

    @JsonProperty
    public void setEnable_cassandra(boolean enable_cassandra) {
        this.enable_cassandra = enable_cassandra;
    }

    public int getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(int reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }

    public void setMetricRegistry(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    public boolean isEnable_test() {
        return enable_test;
    }

    public void setEnable_test(boolean enable_test) {
        this.enable_test = enable_test;
    }

    public int getOld_allowed_sec() {
        return old_allowed_sec;
    }

    public void setOld_allowed_sec(int old_allowed_sec) {
        this.old_allowed_sec = old_allowed_sec;
    }

    public int getThreadPerHost() {
        return threadPerHost;
    }

    public void setThreadPerHost(int threadPerHost) {
        this.threadPerHost = threadPerHost;
    }
}
