package ir.infra.tables;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ShortNode;
import org.joda.time.Instant;

import java.io.IOException;

/**
 *
 */
public class EmsInfoDeserializer extends JsonDeserializer<EmsInfo> {

    @Override
    public EmsInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EmsInfo emsInfo = new EmsInfo();
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        emsInfo.EmsInfoId = (Long) ((LongNode) treeNode.get("EmsInfoId")).numberValue();
        emsInfo.DeviceId = (Integer) ((IntNode) treeNode.get("DeviceId")).numberValue();
        emsInfo.DeviceCompanySystemId = (Integer) ((IntNode) treeNode.get("DeviceCompanySystemId")).numberValue();
        emsInfo.CompanyId = (Integer) ((IntNode) treeNode.get("CompanyId")).numberValue();
        emsInfo.Line = (Integer) ((IntNode) treeNode.get("Line")).numberValue();

        String passDatetime = treeNode.get("PassDatetime").asToken().asString();
        emsInfo.PassDatetime = Instant.parse(passDatetime);

        String receiveDateTime = treeNode.get("ReceiveDateTime").asToken().asString();
        emsInfo.ReceiveDateTime = Instant.parse(receiveDateTime);

        emsInfo.ImageScore = (Integer) ((IntNode) treeNode.get("ImageScore")).numberValue();
        emsInfo.InvalidInfo = (Integer) ((IntNode) treeNode.get("InvalidInfo")).numberValue();

        emsInfo.MasterPlateNumber = (Long) ((LongNode) treeNode.get("MasterPlateNumber")).numberValue();


        emsInfo.SystemId = (Integer) ((IntNode) treeNode.get("SystemId")).numberValue();
        emsInfo.CarSpeed = (Integer) ((IntNode) treeNode.get("CarSpeed")).numberValue();

        emsInfo.RFIDNumber = treeNode.get("RFIDNumber").asToken().asString();

        emsInfo.ParkometerId = (Integer) ((IntNode) treeNode.get("ParkometerId")).numberValue();
        emsInfo.CrimeCode = (Integer) ((IntNode) treeNode.get("CrimeCode")).numberValue();
        emsInfo.Year = (Integer) ((IntNode) treeNode.get("Year")).numberValue();
        emsInfo.Month = (Byte) ((IntNode) treeNode.get("Month")).numberValue();

        emsInfo.Date = (Long) ((LongNode) treeNode.get("Date")).numberValue();
        emsInfo.Inout = (Short) ((ShortNode) treeNode.get("Inout")).numberValue();

        emsInfo.ImagePath = treeNode.get("ImagePath").asToken().asString();
        emsInfo.PlateImagePath = treeNode.get("PlateImagePath").asToken().asString();
        emsInfo.ImageBWPath = treeNode.get("ImageBWPath").asToken().asString();

        emsInfo.Allowed = ((BooleanNode) treeNode.get("Allowed")).asBoolean();

        emsInfo.AllowReasonId = (Integer) ((IntNode) treeNode.get("AllowReasonId")).numberValue();

        emsInfo.SymfaAllowed = ((BooleanNode) treeNode.get("SymfaAllowed")).asBoolean();

        String symfaEndDate = treeNode.get("SymfaEndDate").asToken().asString();
        emsInfo.SymfaEndDate = Instant.parse(symfaEndDate);

        emsInfo.WrongDirection = (Integer) ((IntNode) treeNode.get("WrongDirection")).numberValue();

        emsInfo.IsSendToNaja = ((BooleanNode) treeNode.get("IsSendToNaja")).asBoolean();
        emsInfo.ValidForSms = ((BooleanNode) treeNode.get("ValidForSms")).asBoolean();

        return emsInfo;
    }
}

