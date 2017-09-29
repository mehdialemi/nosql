package ir.infra.cassandra;


import ir.infra.NoSqlAPI;
import ir.infra.core.ClusterConf;
import ir.infra.tables.EmsInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Path("cassandra")
@Produces(MediaType.APPLICATION_JSON)
public class CassandraAPIs extends NoSqlAPI {

    private CassandraClient cassandraClient;

    public CassandraAPIs(ClusterConf conf) throws UnknownHostException {
        super(conf);
        cassandraClient = new CassandraClient(conf);
    }

    public CassandraClient getCassandraClient() {
        return cassandraClient;
    }

    @GET
    @Path("/ping")
    public String ping(@QueryParam("input") String input) {
        return input;
    }

    @POST
    @Path("/add/emsInfo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(EmsInfo emsInfo) throws IOException {
        long t1 = System.nanoTime();
        cassandraClient.add(emsInfo);
        long duration = System.nanoTime() - t1;
        addTimer.update(duration, TimeUnit.NANOSECONDS);
    }

    @GET
    @Path("/get/emsInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public EmsInfo getEmsInfo(@QueryParam("id") long id) {
        long t1 = System.nanoTime();
        EmsInfo result = cassandraClient.getEmsInfo(id);
        long duration = System.nanoTime() - t1;
        getTimer.update(duration, TimeUnit.NANOSECONDS);
        return result;
    }
}
