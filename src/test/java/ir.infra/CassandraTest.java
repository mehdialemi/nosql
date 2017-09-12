package ir.infra;

import ir.infra.cassandra.CassandraClient;
import ir.infra.tables.EmsInfo;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class CassandraTest {

    private final static String COORDINATOR = "127.0.0.1";

    @Test
    public void testEmsInfo() throws UnknownHostException {
        CassandraClient cassandraClient = new CassandraClient(COORDINATOR);
        EmsInfo emsInfo = new EmsInfo();
        emsInfo.EmsInfoId = 4;
        emsInfo.DeviceId = 10;
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-DD HH:mm:ss");
        emsInfo.PassDatetime = Instant.parse("2017-09-12 21:57:39", dateTimeFormatter);
        cassandraClient.add(emsInfo);
        EmsInfo result = cassandraClient.getEmsInfo(2);
        assertEquals(2, result.EmsInfoId);
        assertEquals(10, result.DeviceId);
        assertEquals(emsInfo.PassDatetime, result.PassDatetime);
    }
}
