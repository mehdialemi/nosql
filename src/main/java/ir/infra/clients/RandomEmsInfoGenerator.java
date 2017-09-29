package ir.infra.clients;

import com.google.gson.Gson;
import ir.infra.tables.EmsInfo;
import sun.misc.BASE64Encoder;

import java.util.Random;

/**
 * Generate random {@link EmsInfo} objects
 */
public class RandomEmsInfoGenerator {

    static Random random = new Random();
    static BASE64Encoder encoder = new BASE64Encoder();
    static byte[] bytes = new byte[200 * 1024];
    static Gson gson = new Gson();

    public static EmsInfo randomEmsInfo(long id) {
        EmsInfo emsInfo = new EmsInfo();
        emsInfo.EmsInfoId = id;
        random.nextBytes(bytes);
        emsInfo.ImagePath =  encoder.encode(bytes);
        return emsInfo;
    }

    public static String toJson(EmsInfo emsInfo) {
        return gson.toJson(emsInfo);
    }

    public static String randomEmsInfoJson() {
        return toJson(randomEmsInfo(random.nextLong()));
    }
}
