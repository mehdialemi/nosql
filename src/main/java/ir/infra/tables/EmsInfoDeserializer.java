package ir.infra.tables;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import org.joda.time.Instant;

import java.io.IOException;

/**
 *
 */
public class EmsInfoDeserializer extends JsonDeserializer<EmsInfo> {

    @Override
    public EmsInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EmsInfo emsInfo = new EmsInfo();
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode root = mapper.readTree(jsonParser);

        JsonNode emsInfoId = root.findValue("EmsInfoId");
        if (emsInfoId != null)
            emsInfo.EmsInfoId = emsInfoId.asLong();


        JsonNode deviceId = root.get("DeviceId");
        if (deviceId != null)
            emsInfo.DeviceId = deviceId.asInt();


        JsonNode deviceCompanySystemId = root.get("DeviceCompanySystemId");
        if (deviceCompanySystemId != null)
            emsInfo.DeviceCompanySystemId = deviceCompanySystemId.asInt();


        JsonNode companyId = root.get("CompanyId");
        if (companyId != null)
            emsInfo.CompanyId = companyId.asInt();


        JsonNode line = root.get("Line");
        if (line != null)
            emsInfo.Line = line.asInt();


        JsonNode passDatetime = root.get("PassDatetime");
        if (passDatetime != null)
            emsInfo.PassDatetime = Instant.parse(passDatetime.asText());


        JsonNode receiveDateTime = root.get("ReceiveDateTime");
        if (receiveDateTime != null)
            emsInfo.ReceiveDateTime = Instant.parse(receiveDateTime.asText());


        JsonNode imageScore = root.get("ImageScore");
        if (imageScore != null)
            emsInfo.ImageScore = imageScore.asInt();


        JsonNode invalidInfo = root.get("InvalidInfo");
        if (invalidInfo != null)
            emsInfo.InvalidInfo = invalidInfo.asInt();

        JsonNode masterPlateNumber = root.get("MasterPlateNumber");
        if (masterPlateNumber != null)
            emsInfo.MasterPlateNumber = masterPlateNumber.asLong();

        JsonNode systemId = root.get("SystemId");
        if (systemId != null)
            emsInfo.SystemId = systemId.asInt();

        JsonNode carSpeed = root.get("CarSpeed");
        if(carSpeed != null)
            emsInfo.CarSpeed = carSpeed.asInt();

        JsonNode rfidNumber = root.get("RFIDNumber");
        if (rfidNumber != null)
            emsInfo.RFIDNumber = rfidNumber.asText();

        JsonNode parkometerId = root.get("ParkometerId");
        if (parkometerId != null)
            emsInfo.ParkometerId = parkometerId.asInt();

        JsonNode crimeCode = root.get("CrimeCode");
        if (crimeCode != null)
            emsInfo.CrimeCode = crimeCode.asInt();

        JsonNode year = root.get("Year");
        if (year != null)
            emsInfo.Year = year.asInt();

        JsonNode month = root.get("Month");
        if (month != null)
            emsInfo.Month = (byte) month.asInt();

        JsonNode date = root.get("Date");
        if (date != null)
            emsInfo.Date = date.asLong();


        JsonNode inout = root.get("Inout");
        if (inout != null)
            emsInfo.Inout = (short) inout.asInt();

        JsonNode imagePath = root.get("ImagePath");
        if (imagePath != null)
            emsInfo.ImagePath = imagePath.asText();

        JsonNode plateImagePath = root.get("PlateImagePath");
        if (plateImagePath != null)
            emsInfo.PlateImagePath = plateImagePath.asText();

        JsonNode imageBWPath = root.get("ImageBWPath");
        if (imageBWPath != null)
            emsInfo.ImageBWPath = imageBWPath.asText();

        JsonNode allowed = root.get("Allowed");
        if (allowed != null)
            emsInfo.Allowed = allowed.asBoolean();

        JsonNode allowReasonId = root.get("AllowReasonId");
        if (allowReasonId != null)
            emsInfo.AllowReasonId = allowReasonId.asInt();

        JsonNode symfaAllowed = root.get("SymfaAllowed");
        if (symfaAllowed != null)
            emsInfo.SymfaAllowed = symfaAllowed.asBoolean();

        JsonNode symfaEndDate = root.get("SymfaEndDate");
        if (symfaEndDate != null)
            emsInfo.SymfaEndDate = Instant.parse(symfaEndDate.asText());

        JsonNode wrongDirection = root.get("WrongDirection");
        if (wrongDirection != null)
            emsInfo.WrongDirection = wrongDirection.asInt();

        JsonNode isSendToNaja = root.get("IsSendToNaja");
        if (isSendToNaja != null)
            emsInfo.IsSendToNaja = isSendToNaja.asBoolean();

        JsonNode validForSms = root.get("ValidForSms");
        if (validForSms != null)
            emsInfo.ValidForSms = validForSms.asBoolean();

        return emsInfo;
    }
}

