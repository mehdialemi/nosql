package ir.infra.cassandra;


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

    @POST
    @Path("/add/emsInfo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(EmsInfo emsInfo) throws IOException {
        System.out.println("Got new emsInfo: " + emsInfo);
        cassandraClient.add(emsInfo);
    }

    @GET
    @Path("/get/emsIndo")
    @Produces(MediaType.APPLICATION_JSON)
    public EmsInfo getEmsInfo(@QueryParam("id") long id) {
        System.out.println("Getting emsInfo for id: " + id);
        return cassandraClient.getEmsInfo(id);
    }
}
