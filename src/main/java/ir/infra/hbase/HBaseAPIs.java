package ir.infra.hbase;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import ir.infra.NoSqlClient;
import ir.infra.tables.EmsInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("hbase")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HBaseAPIs implements NoSqlClient {

    public final static String ZK_QUORUM = "127.0.0.1:2181";

    private HBaseClient hbaseClient;
    public HBaseAPIs(String zkAddress) throws IOException {
        hbaseClient = new HBaseClient(ZK_QUORUM);
    }

    public HBaseClient getHbaseClient() {return hbaseClient;}

    @GET
    @Path("/ping")
    public String ping(@QueryParam("input") String input) {
        return input;
    }

    @Timed
    @Metered
    @POST
    @Path("/add/emsInfo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(EmsInfo emsInfo) throws IOException {
        hbaseClient.add(emsInfo);
    }

    @Timed
    @Metered
    @GET
    @Path("/get/emsInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public EmsInfo getEmsInfo(long id) throws IOException {
        return hbaseClient.getEmsInfo(id);
    }
}
