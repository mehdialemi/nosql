package ir.infra.cassandra;


import com.codahale.metrics.Meter;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import ir.infra.NoSqlClient;
import ir.infra.tables.EmsInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.UnknownHostException;

@Path("cassandra")
@Produces(MediaType.APPLICATION_JSON)
public class CassandraAPIs implements NoSqlClient {

    private CassandraClient cassandraClient;

    public CassandraAPIs(String coordinator) throws UnknownHostException {
        cassandraClient = new CassandraClient(coordinator);
    }

    public CassandraClient getCassandraClient() {
        return cassandraClient;
    }

    @GET
    @Path("/ping")
    public String ping(@QueryParam("input") String input) {
        return input;
    }

    @Metered
    @Timed
    @POST
    @Path("/add/emsInfo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(EmsInfo emsInfo) throws IOException {
        cassandraClient.add(emsInfo);
    }

    @Metered
    @Timed
    @GET
    @Path("/get/emsInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public EmsInfo getEmsInfo(@QueryParam("id") long id) {
        return cassandraClient.getEmsInfo(id);
    }
}
