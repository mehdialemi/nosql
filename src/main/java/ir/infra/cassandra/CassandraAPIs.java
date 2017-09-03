package ir.infra.cassandra;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("cassandra")
@Produces(MediaType.APPLICATION_JSON)
public class CassandraAPIs {

    @GET
    @Path("/ping")
    public String ping(@QueryParam("input") String input) {
        return input;
    }
}
