package ir.infra.core;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClusterConf {

    private String zkAddress = "localhost:2181";

    private String coordinator = "localhost";

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
}
