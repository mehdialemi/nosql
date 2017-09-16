package ir.infra.core;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClusterConf {

    private String zkAddress = "localhost:2181";

    private String coordinator = "localhost";

    private boolean enable_hbase = true;

    private boolean enable_cassandra = true;

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
}
