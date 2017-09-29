package ir.infra.hbase;

import ir.infra.NoSqlAPI;
import ir.infra.core.ClusterConf;
import ir.infra.tables.EmsInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Path("hbase")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HBaseAPIs extends NoSqlAPI {

    private HBaseClient hbaseClient;
    public HBaseAPIs(ClusterConf conf) throws IOException {
        super(conf);
        hbaseClient = new HBaseClient(conf.getZkAddress());
    }

    public HBaseClient getHbaseClient() {return hbaseClient;}

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
        hbaseClient.add(emsInfo);
        long duration = System.nanoTime() - t1;
        addTimer.update(duration, TimeUnit.NANOSECONDS);
    }

    @GET
    @Path("/get/emsInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public EmsInfo getEmsInfo(long id) throws IOException {
        long t1 = System.nanoTime();
        EmsInfo result = hbaseClient.getEmsInfo(id);
        long duration = System.nanoTime() - t1;
        getTimer.update(duration, TimeUnit.NANOSECONDS);
        return result;
    }
}
