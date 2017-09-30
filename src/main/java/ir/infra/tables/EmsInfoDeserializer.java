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

        TreeNode emsInfoId = treeNode.get("EmsInfoId");
        if (emsInfoId != null)
            emsInfo.EmsInfoId = (Long) ((LongNode) emsInfoId).numberValue();


        TreeNode deviceId = treeNode.get("DeviceId");
        if (deviceId != null)
            emsInfo.DeviceId = (Integer) ((IntNode) deviceId).numberValue();


        TreeNode deviceCompanySystemId = treeNode.get("DeviceCompanySystemId");
        if (deviceCompanySystemId != null)
            emsInfo.DeviceCompanySystemId = (Integer) ((IntNode) deviceCompanySystemId).numberValue();


        TreeNode companyId = treeNode.get("CompanyId");
        if (companyId != null)
            emsInfo.CompanyId = (Integer) ((IntNode) companyId).numberValue();


        TreeNode line = treeNode.get("Line");
        if (line != null)
            emsInfo.Line = (Integer) ((IntNode) line).numberValue();


        TreeNode passDatetime = treeNode.get("PassDatetime");
        if (passDatetime != null)
            emsInfo.PassDatetime = Instant.parse(passDatetime.asToken().asString());


        TreeNode receiveDateTime = treeNode.get("ReceiveDateTime");
        if (receiveDateTime != null)
            emsInfo.ReceiveDateTime = Instant.parse(receiveDateTime.asToken().asString());


        TreeNode imageScore = treeNode.get("ImageScore");
        if (imageScore != null)
            emsInfo.ImageScore = (Integer) ((IntNode) imageScore).numberValue();


        TreeNode invalidInfo = treeNode.get("InvalidInfo");
        if (invalidInfo != null)
            emsInfo.InvalidInfo = (Integer) ((IntNode) invalidInfo).numberValue();

        TreeNode masterPlateNumber = treeNode.get("MasterPlateNumber");
        if (masterPlateNumber != null)
            emsInfo.MasterPlateNumber = (Long) ((LongNode) masterPlateNumber).numberValue();

        TreeNode systemId = treeNode.get("SystemId");
        if (systemId != null)
            emsInfo.SystemId = (Integer) ((IntNode) systemId).numberValue();

        TreeNode carSpeed = treeNode.get("CarSpeed");
        if(carSpeed != null)
            emsInfo.CarSpeed = (Integer) ((IntNode) carSpeed).numberValue();

        TreeNode rfidNumber = treeNode.get("RFIDNumber");
        if (rfidNumber != null)
            emsInfo.RFIDNumber = rfidNumber.asToken().asString();

        TreeNode parkometerId = treeNode.get("ParkometerId");
        if (parkometerId != null)
            emsInfo.ParkometerId = (Integer) ((IntNode) parkometerId).numberValue();

        TreeNode crimeCode = treeNode.get("CrimeCode");
        if (crimeCode != null)
            emsInfo.CrimeCode = (Integer) ((IntNode) crimeCode).numberValue();

        TreeNode year = treeNode.get("Year");
        if (year != null)
            emsInfo.Year = (Integer) ((IntNode) year).numberValue();

        TreeNode month = treeNode.get("Month");
        if (month != null)
            emsInfo.Month = (Byte) ((IntNode) month).numberValue();

        TreeNode date = treeNode.get("Date");
        if (date != null)
            emsInfo.Date = (Long) ((LongNode) date).numberValue();


        TreeNode inout = treeNode.get("Inout");
        if (inout != null)
            emsInfo.Inout = (Short) ((ShortNode) inout).numberValue();

        TreeNode imagePath = treeNode.get("ImagePath");
        if (imagePath != null)
            emsInfo.ImagePath = imagePath.asToken().asString();

        TreeNode plateImagePath = treeNode.get("PlateImagePath");
        if (plateImagePath != null)
            emsInfo.PlateImagePath = plateImagePath.asToken().asString();

        TreeNode imageBWPath = treeNode.get("ImageBWPath");
        if (imageBWPath != null)
            emsInfo.ImageBWPath = imageBWPath.asToken().asString();

        TreeNode allowed = treeNode.get("Allowed");
        if (allowed != null)
            emsInfo.Allowed = ((BooleanNode) allowed).asBoolean();

        TreeNode allowReasonId = treeNode.get("AllowReasonId");
        if (allowReasonId != null)
            emsInfo.AllowReasonId = (Integer) ((IntNode) allowReasonId).numberValue();

        TreeNode symfaAllowed = treeNode.get("SymfaAllowed");
        if (symfaAllowed != null)
            emsInfo.SymfaAllowed = ((BooleanNode) symfaAllowed).asBoolean();

        TreeNode symfaEndDate = treeNode.get("SymfaEndDate");
        if (symfaEndDate != null)
            emsInfo.SymfaEndDate = Instant.parse(symfaEndDate.asToken().asString());

        TreeNode wrongDirection = treeNode.get("WrongDirection");
        if (wrongDirection != null)
            emsInfo.WrongDirection = (Integer) ((IntNode) wrongDirection).numberValue();

        TreeNode isSendToNaja = treeNode.get("IsSendToNaja");
        if (isSendToNaja != null)
            emsInfo.IsSendToNaja = ((BooleanNode) isSendToNaja).asBoolean();

        TreeNode validForSms = treeNode.get("ValidForSms");
        if (validForSms != null)
            emsInfo.ValidForSms = ((BooleanNode) validForSms).asBoolean();

        return emsInfo;
    }
}

