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

    public static final String COORDINATOR = "127.0.0.1";

    private CassandraClient cassandraClient;

    public CassandraAPIs() throws UnknownHostException {
        cassandraClient = new CassandraClient(COORDINATOR);
    }

    @GET
    @Path("/ping")
    public String ping(@QueryParam("input") String input) {
        return input;
    }

    @POST
    @Path("/put/emsInfo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(EmsInfo emsInfo) throws IOException {
        cassandraClient.add(emsInfo);
    }

    @GET
    @Path("/get/emsIndo")
    @Produces(MediaType.APPLICATION_JSON)
    public EmsInfo getEmsInfo(@QueryParam("id") long id) {
        return cassandraClient.getEmsInfo(id);
    }


}
