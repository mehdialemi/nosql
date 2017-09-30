package ir.infra.tables;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mehdi on 9/30/17.
 */
public class EmsInfoSerializer extends JsonSerializer<EmsInfo> {

    @Override
    public void serialize(EmsInfo emsInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("EmsInfoId", emsInfo.EmsInfoId);
        jsonGenerator.writeNumberField("DeviceId", emsInfo.DeviceId);
        jsonGenerator.writeNumberField("DeviceCompanySystemId", emsInfo.DeviceCompanySystemId);
        jsonGenerator.writeNumberField("CompanyId", emsInfo.CompanyId);
        jsonGenerator.writeNumberField("Line", emsInfo.Line);
        jsonGenerator.writeStringField("PassDatetime", emsInfo.PassDatetime.toString());
        jsonGenerator.writeStringField("ReceiveDateTime", emsInfo.ReceiveDateTime.toString());
        jsonGenerator.writeNumberField("ImageScore", emsInfo.ImageScore);
        jsonGenerator.writeNumberField("InvalidInfo", emsInfo.InvalidInfo);
        jsonGenerator.writeNumberField("MasterPlateNumber", emsInfo.MasterPlateNumber);
        jsonGenerator.writeNumberField("SystemId", emsInfo.SystemId);
        jsonGenerator.writeNumberField("CarSpeed", emsInfo.CarSpeed);
        jsonGenerator.writeStringField("RFIDNumber", emsInfo.RFIDNumber);
        jsonGenerator.writeNumberField("ParkometerId", emsInfo.ParkometerId);
        jsonGenerator.writeNumberField("CrimeCode", emsInfo.CrimeCode);
        jsonGenerator.writeNumberField("Year", emsInfo.Year);
        jsonGenerator.writeObjectField("Month", emsInfo.Month);
        jsonGenerator.writeNumberField("Date", emsInfo.Date);
        jsonGenerator.writeObjectField("Inout", emsInfo.Inout);
        jsonGenerator.writeStringField("ImagePath", emsInfo.ImagePath);
        jsonGenerator.writeStringField("PlateImagePath", emsInfo.PlateImagePath);
        jsonGenerator.writeBooleanField("Allowed", emsInfo.Allowed);
        jsonGenerator.writeNumberField("AllowReasonId", emsInfo.AllowReasonId);
        jsonGenerator.writeBooleanField("SymfaAllowed", emsInfo.SymfaAllowed);
        jsonGenerator.writeStringField("SymfaEndDate", emsInfo.SymfaEndDate.toString());
        jsonGenerator.writeNumberField("WrongDirection", emsInfo.WrongDirection);
        jsonGenerator.writeBooleanField("IsSendToNaja", emsInfo.IsSendToNaja);
        jsonGenerator.writeBooleanField("ValidForSms", emsInfo.ValidForSms);
        jsonGenerator.writeEndObject();
    }
}

