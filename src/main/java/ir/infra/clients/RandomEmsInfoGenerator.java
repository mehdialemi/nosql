package ir.infra.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import ir.infra.tables.EmsInfo;
import org.joda.time.Instant;
import sun.misc.BASE64Encoder;

import java.util.Random;

/**
 * Generate random {@link EmsInfo} objects
 */
public class RandomEmsInfoGenerator {

    static Random random = new Random();
    static BASE64Encoder encoder = new BASE64Encoder();
    static byte[] bytes = new byte[200 * 1024];

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(randomEmsInfoJson());
    }

    public static EmsInfo randomEmsInfo(long id) {
        EmsInfo emsInfo = new EmsInfo();
        emsInfo.EmsInfoId = id;
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
        emsInfo.ImagePath =  encoder.encode(bytes);

        emsInfo.PlateImagePath = "plate33222";
        emsInfo.Allowed = random.nextBoolean();
        emsInfo.AllowReasonId = random.nextInt();
        emsInfo.SymfaAllowed = random.nextBoolean();
        emsInfo.SymfaEndDate = Instant.now();
        emsInfo.WrongDirection = random.nextInt();
        emsInfo.IsSendToNaja = random.nextBoolean();
        emsInfo.ValidForSms = random.nextBoolean();

        return emsInfo;
    }

    public static String toJson(EmsInfo emsInfo) throws JsonProcessingException {
        ObjectMapper mapper = Jackson.newObjectMapper();
        return mapper.writeValueAsString(emsInfo);
    }

    public static String randomEmsInfoJson() throws JsonProcessingException {
        return toJson(randomEmsInfo(random.nextLong()));
    }
}
