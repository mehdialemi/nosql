package ir.infra.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import ir.infra.tables.EmsInfo;
import org.joda.time.Instant;
import sun.misc.BASE64Encoder;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Generate random {@link EmsInfo} objects
 */
public class RandomEmsInfoGenerator {

    static Random random = new Random();
    static BASE64Encoder encoder = new BASE64Encoder();
    static byte[] bytes = new byte[200 * 1024];

    public static void main(String[] args) throws JsonProcessingException {
        long t1 = System.nanoTime();
        for(int i = 0 ; i < 1000; i ++) {
            randomEmsInfoJson();
        }

        System.out.println("duration: " + (System.nanoTime() - t1) / 1000000);
    }

    static EmsInfo emsInfo = new EmsInfo();
    static String emsInfoJson;

    static {
        emsInfo.DeviceId = random.nextInt();
        emsInfo.DeviceCompanySystemId = random.nextInt();
        emsInfo.CompanyId = random.nextInt();
        emsInfo.Line = random.nextInt();
        emsInfo.PassDatetime = Instant.now();
        emsInfo.ReceiveDateTime = Instant.now();
        emsInfo.ImageScore = random.nextInt();
        emsInfo.InvalidInfo = random.nextInt();
        emsInfo.MasterPlateNumber = random.nextLong();
        emsInfo.SystemId = random.nextInt();
        emsInfo.CarSpeed = random.nextInt();
        emsInfo.RFIDNumber = "RF4223333";
        emsInfo.ParkometerId = random.nextInt();
        emsInfo.CrimeCode = random.nextInt();
        emsInfo.Year = random.nextInt(4444);
        emsInfo.Month = (byte) random.nextInt(127);
        emsInfo.Date = random.nextLong();
        emsInfo.Inout = (short) random.nextInt(4000);

        random.nextBytes(bytes);
        emsInfo.Image = ByteBuffer.wrap(bytes);

        emsInfo.PlateImagePath = "plate33222";
        emsInfo.Allowed = random.nextBoolean();
        emsInfo.AllowReasonId = random.nextInt();
        emsInfo.SymfaAllowed = random.nextBoolean();
        emsInfo.SymfaEndDate = Instant.now();
        emsInfo.WrongDirection = random.nextInt();
        emsInfo.IsSendToNaja = random.nextBoolean();
        emsInfo.ValidForSms = random.nextBoolean();

//        try {
//            emsInfoJson = toJson(emsInfo);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

    private static String toJson(EmsInfo emsInfo) throws JsonProcessingException {
        ObjectMapper mapper = Jackson.newObjectMapper();
        return mapper.writeValueAsString(emsInfo);
    }

    public static synchronized String randomEmsInfoJson() throws JsonProcessingException {
        StringBuilder sb = new StringBuilder(emsInfoJson);
        sb.insert(13, random.nextInt());
        return sb.toString();
    }

    public static synchronized EmsInfo randomEmsInfo() throws JsonProcessingException {
        emsInfo.EmsInfoId = random.nextLong();
        emsInfo.Allowed = random.nextBoolean();
        return emsInfo;
    }
}
