package ir.infra;


import ir.infra.cassandra.Client;
import ir.infra.tables.EmsInfo;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class CassandraTest {

    private final static String COORDINATOR = "127.0.0.1";

    @Test
    public void testEmsInfo() throws UnknownHostException {
        Client client = new Client(COORDINATOR);
        EmsInfo emsInfo = new EmsInfo();
        emsInfo.setEmsInfoId(1);
        emsInfo.setAllowed(true);
        emsInfo.setCarSpeed(10);
        client.add(emsInfo);
        EmsInfo result = client.getEmsInfo(1);
        assertEquals(emsInfo.getEmsInfoId(), result.getEmsInfoId());
        assertEquals(emsInfo.getAllowed(), result.getAllowed());
        assertEquals(emsInfo.getCarSpeed(), result.getCarSpeed());
    }
}
