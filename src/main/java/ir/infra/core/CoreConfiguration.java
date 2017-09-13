package ir.infra.core;

import io.dropwizard.Configuration;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CoreConfiguration extends Configuration {

    @Valid
    @NotNull
    private ClusterConf clusterConf = new ClusterConf();

    @JsonProperty("clusterConf")
    public ClusterConf getClusterConf() {
        return clusterConf;
    }

    @JsonProperty("clusterConf")
    public void setClusterConf(ClusterConf conf) {
        this.clusterConf = conf;
    }
}
