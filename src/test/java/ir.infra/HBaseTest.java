package ir.infra;

import ir.infra.hbase.HBaseClient;
import ir.infra.tables.EmsInfo;
import org.joda.time.Instant;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
public class HBaseTest {

    @Test
    public void testInsert() throws IOException {
        String zkQuorum = "localhost:2181";
        HBaseClient client = new HBaseClient(zkQuorum);

        EmsInfo emsInfo = new EmsInfo();
        emsInfo.EmsInfoId = 5;
        emsInfo.DeviceId = 10;
        emsInfo.PassDatetime = Instant.parse("2017-10-13T02:31:24.691Z");

        client.add(emsInfo);

        EmsInfo result = client.getEmsInfo(5);
        assertEquals(5, result.EmsInfoId);
        assertEquals(10, (int)result.DeviceId);
        assertEquals(emsInfo.PassDatetime, result.PassDatetime);
    }
}
