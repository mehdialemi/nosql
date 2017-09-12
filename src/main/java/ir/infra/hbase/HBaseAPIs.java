package ir.infra.hbase;

import ir.infra.NoSqlClient;
import ir.infra.tables.EmsInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("hbase")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HBaseAPIs implements NoSqlClient {

    public final static String ZK_QUORUM = "127.0.0.1";

    private HBaseClient HBaseClient;
    public HBaseAPIs() throws IOException {
        HBaseClient = new HBaseClient(ZK_QUORUM);
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
        HBaseClient.add(emsInfo);
    }

    @GET
    @Path("/get/emsIndo")
    @Produces(MediaType.APPLICATION_JSON)
    public EmsInfo getEmsInfo(long id) throws IOException {
        return HBaseClient.getEmsInfo(id);
    }
}
