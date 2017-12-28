package ir.infra.tables;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.joda.time.Instant;

import java.nio.ByteBuffer;

import static ir.infra.core.Constants.FAMILY;
import static org.apache.hadoop.hbase.util.Bytes.toBytes;

@Table(name = "EmsInfo")
@JsonSerialize(using = EmsInfoSerializer.class)
@JsonDeserialize(using = EmsInfoDeserializer.class)
public class EmsInfo {

    public static final byte[] FAMILY_BYTES = toBytes(FAMILY);
    public static final byte[] DEVICE_IDS_BYTES = toBytes("DeviceId");
    public static final byte[] DEVICE_COMPANY_SYSTEM_IDS = toBytes("DeviceCompanySystemId");
    public static final byte[] COMPANY_IDS = toBytes("CompanyId");
    public static final byte[] LINES = toBytes("Line");
    public static final byte[] PASS_DATETIMES = toBytes("PassDatetime");
    public static final byte[] RECEIVE_DATE_TIMES = toBytes("ReceiveDateTime");
    public static final byte[] IMAGE_SCORES = toBytes("ImageScore");
    public static final byte[] INVALID_INFOS = toBytes("InvalidInfo");
    public static final byte[] MASTER_PLATE_NUMBERS = toBytes("MasterPlateNumber");
    public static final byte[] SYSTEM_IDS = toBytes("SystemId");
    public static final byte[] CAR_SPEEDS = toBytes("CarSpeed");
    public static final byte[] RFID_NUMBERS = toBytes("RFIDNumber");
    public static final byte[] PARKOMETER_IDS = toBytes("ParkometerId");
    public static final byte[] CRIM_CODES = toBytes("CrimeCode");
    public static final byte[] YEARS = toBytes("Year");
    public static final byte[] MONTHS = toBytes("Month");
    public static final byte[] DATES = toBytes("Date");
    public static final byte[] INOUTS = toBytes("Inout");
    public static final byte[] IMAGE = toBytes("Image");
    public static final byte[] PLATE_IMAGE_PATHS = toBytes("PlateImagePath");
    public static final byte[] IMAGE_BW_PATHS = toBytes("ImageBWPath");
    public static final byte[] ALLOWEDS = toBytes("Allowed");
    public static final byte[] ALLOW_REASON_IDS = toBytes("AllowReasonId");
    public static final byte[] SYMFA_ALLOWEDS = toBytes("SymfaAllowed");
    public static final byte[] SYMFA_END_DATES = toBytes("SymfaEndDate");
    public static final byte[] WRONG_DIRECTIONS = toBytes("WrongDirection");
    public static final byte[] IS_SEND_TO_NAJAS = toBytes("IsSendToNaja");
    public static final byte[] VALID_FOR_SMS = toBytes("ValidForSms");

    @JsonProperty
    @PartitionKey
    public long EmsInfoId;

    @JsonProperty
    public Integer DeviceId;

    @JsonProperty
    public Integer DeviceCompanySystemId;

    @JsonProperty
    public Integer CompanyId;

    @JsonProperty
    public Integer Line;

    @JsonProperty
    public Instant PassDatetime;

    @JsonProperty
    public Instant ReceiveDateTime;

    @JsonProperty
    public Integer ImageScore;

    @JsonProperty
    public Integer InvalidInfo;

    @JsonProperty
    public Long MasterPlateNumber;

    @JsonProperty
    public Integer SystemId;

    @JsonProperty
    public Integer CarSpeed;

    @JsonProperty
    public String RFIDNumber;

    @JsonProperty
    public Integer ParkometerId;

    @JsonProperty
    public Integer CrimeCode;

    @JsonProperty
    public Integer Year;

    @JsonProperty
    public Byte Month;

    @JsonProperty
    public Long Date;

    @JsonProperty
    public Short Inout;

    @JsonProperty
    public ByteBuffer Image;

    @JsonProperty
    public String PlateImagePath;

    @JsonProperty
    public String ImageBWPath;

    @JsonProperty
    public Boolean Allowed;

    @JsonProperty
    public Integer AllowReasonId;

    @JsonProperty
    public Boolean SymfaAllowed;

    @JsonProperty
    public Instant SymfaEndDate;

    @JsonProperty
    public Integer WrongDirection;

    @JsonProperty
    public Boolean IsSendToNaja;

    @JsonProperty
    public Boolean ValidForSms;

    public EmsInfo() {
    }

    public Long composeRowKey() {
        return EmsInfoId;
    }

    public void parseRowKey(Long rowKey) {
        EmsInfoId = rowKey;
    }

    public static Put createPut(EmsInfo emsInfo) {
        Put put = new Put(toBytes(emsInfo.EmsInfoId));

        if (emsInfo.DeviceId != null)
        put.addColumn(FAMILY_BYTES, DEVICE_IDS_BYTES, toBytes(emsInfo.DeviceId));

        if (emsInfo.DeviceCompanySystemId != null)
            put.addColumn(FAMILY_BYTES, DEVICE_COMPANY_SYSTEM_IDS, toBytes(emsInfo.DeviceCompanySystemId));

        if (emsInfo.CompanyId != null)
            put.addColumn(FAMILY_BYTES, COMPANY_IDS, toBytes(emsInfo.CompanyId));

        if (emsInfo.Line != null)
        put.addColumn(FAMILY_BYTES, LINES, toBytes(emsInfo.Line));

        if (emsInfo.PassDatetime != null)
        put.addColumn(FAMILY_BYTES, PASS_DATETIMES, toBytes(emsInfo.PassDatetime.toString()));

        if (emsInfo.ReceiveDateTime != null)
        put.addColumn(FAMILY_BYTES, RECEIVE_DATE_TIMES, toBytes(emsInfo.ReceiveDateTime.toString()));

        if (emsInfo.ImageScore != null)
        put.addColumn(FAMILY_BYTES, IMAGE_SCORES, toBytes(emsInfo.ImageScore));

        if (emsInfo.InvalidInfo != null)
        put.addColumn(FAMILY_BYTES, INVALID_INFOS, toBytes(emsInfo.InvalidInfo));

        if (emsInfo.MasterPlateNumber != null)
        put.addColumn(FAMILY_BYTES, MASTER_PLATE_NUMBERS, toBytes(emsInfo.MasterPlateNumber));

        if (emsInfo.SystemId != null)
        put.addColumn(FAMILY_BYTES, SYSTEM_IDS, toBytes(emsInfo.SystemId));

        if (emsInfo.CarSpeed != null)
        put.addColumn(FAMILY_BYTES, CAR_SPEEDS, toBytes(emsInfo.CarSpeed));

        if (emsInfo.RFIDNumber != null)
        put.addColumn(FAMILY_BYTES, RFID_NUMBERS, toBytes(emsInfo.RFIDNumber));

        if (emsInfo.ParkometerId != null)
        put.addColumn(FAMILY_BYTES, PARKOMETER_IDS, toBytes(emsInfo.ParkometerId));

        if (emsInfo.CrimeCode != null)
            put.addColumn(FAMILY_BYTES, CRIM_CODES, toBytes(emsInfo.CrimeCode));

        if (emsInfo.Year != null)
        put.addColumn(FAMILY_BYTES, YEARS, toBytes(emsInfo.Year));

        if (emsInfo.Month != null)
        put.addColumn(FAMILY_BYTES, MONTHS, toBytes(emsInfo.Month));

        if (emsInfo.Date != null)
        put.addColumn(FAMILY_BYTES, DATES, toBytes(emsInfo.Date));

        if (emsInfo.Inout != null)
        put.addColumn(FAMILY_BYTES, INOUTS, toBytes(emsInfo.Inout));

        if (emsInfo.Image != null)
        put.addColumn(FAMILY_BYTES, IMAGE, emsInfo.Image.array());

        if (emsInfo.PlateImagePath != null)
        put.addColumn(FAMILY_BYTES, PLATE_IMAGE_PATHS, toBytes(emsInfo.PlateImagePath));

        if (emsInfo.ImageBWPath != null)
        put.addColumn(FAMILY_BYTES, IMAGE_BW_PATHS, toBytes(emsInfo.ImageBWPath));

        if (emsInfo.Allowed != null)
        put.addColumn(FAMILY_BYTES, ALLOWEDS, toBytes(emsInfo.Allowed));

        if (emsInfo.AllowReasonId != null)
        put.addColumn(FAMILY_BYTES, ALLOW_REASON_IDS, toBytes(emsInfo.AllowReasonId));

        if (emsInfo.SymfaAllowed != null)
        put.addColumn(FAMILY_BYTES, SYMFA_ALLOWEDS, toBytes(emsInfo.SymfaAllowed));

        if (emsInfo.SymfaEndDate != null)
        put.addColumn(FAMILY_BYTES, SYMFA_END_DATES, toBytes(emsInfo.SymfaEndDate.toString()));

        if (emsInfo.WrongDirection != null)
        put.addColumn(FAMILY_BYTES, WRONG_DIRECTIONS, toBytes(emsInfo.WrongDirection));

        if (emsInfo.IsSendToNaja != null)
        put.addColumn(FAMILY_BYTES, IS_SEND_TO_NAJAS, toBytes(emsInfo.IsSendToNaja));

        if (emsInfo.IsSendToNaja != null)
        put.addColumn(FAMILY_BYTES, VALID_FOR_SMS, toBytes(emsInfo.IsSendToNaja));

        return put;
    }

    public static EmsInfo load(Result result) {

        EmsInfo emsInfo = new EmsInfo();
        emsInfo.EmsInfoId = Bytes.toLong(result.getRow());

        byte[] deviceId = result.getValue(FAMILY_BYTES, DEVICE_IDS_BYTES);
        if (deviceId != null)
            emsInfo.DeviceId = Bytes.toInt(deviceId);


        byte[] deviceCompanySystemId = result.getValue(FAMILY_BYTES, DEVICE_COMPANY_SYSTEM_IDS);
        if (deviceCompanySystemId != null)
            emsInfo.DeviceCompanySystemId = Bytes.toInt(deviceCompanySystemId);


        byte[] companyId = result.getValue(FAMILY_BYTES, COMPANY_IDS);
        if (companyId != null)
            emsInfo.CompanyId = Bytes.toInt(companyId);


        byte[] line = result.getValue(FAMILY_BYTES, LINES);
        if (line != null)
            emsInfo.Line = Bytes.toInt(line);


        byte[] passDateTime = result.getValue(FAMILY_BYTES, PASS_DATETIMES);
        if (passDateTime != null)
            emsInfo.PassDatetime = Instant.parse(Bytes.toString(passDateTime));


        byte[] receiveDateTime = result.getValue(FAMILY_BYTES, RECEIVE_DATE_TIMES);
        if (receiveDateTime != null)
            emsInfo.ReceiveDateTime = Instant.parse(Bytes.toString(receiveDateTime));


        byte[] imageScore = result.getValue(FAMILY_BYTES, IMAGE_SCORES);
        if (imageScore != null)
            emsInfo.ImageScore = Bytes.toInt(imageScore);


        byte[] invalidInfo = result.getValue(FAMILY_BYTES, INVALID_INFOS);
        if (invalidInfo != null)
            emsInfo.InvalidInfo = Bytes.toInt(invalidInfo);


        byte[] masterPlateNumber = result.getValue(FAMILY_BYTES, MASTER_PLATE_NUMBERS);
        if (masterPlateNumber != null)
            emsInfo.MasterPlateNumber = Bytes.toLong(masterPlateNumber);


        byte[] systemId = result.getValue(FAMILY_BYTES, SYSTEM_IDS);
        if (systemId != null)
            emsInfo.SystemId = Bytes.toInt(systemId);


        byte[] carSpeed = result.getValue(FAMILY_BYTES, CAR_SPEEDS);
        if (carSpeed != null)
            emsInfo.CarSpeed = Bytes.toInt(carSpeed);


        byte[] rfidNumber = result.getValue(FAMILY_BYTES, RFID_NUMBERS);
        if (rfidNumber != null)
            emsInfo.RFIDNumber = Bytes.toString(rfidNumber);


        byte[] parkometerId = result.getValue(FAMILY_BYTES, PARKOMETER_IDS);
        if (parkometerId != null)
            emsInfo.ParkometerId = Bytes.toInt(parkometerId);


        byte[] year = result.getValue(FAMILY_BYTES, YEARS);
        if (year != null)
            emsInfo.Year = Bytes.toInt(year);


        byte[] month = result.getValue(FAMILY_BYTES, MONTHS);
        if (month != null)
            emsInfo.Month = month[0];


        byte[] date = result.getValue(FAMILY_BYTES, DATES);
        if (date != null)
            emsInfo.Date = Bytes.toLong(date);


        byte[] inout = result.getValue(FAMILY_BYTES, INOUTS);
        if (inout != null)
            emsInfo.Inout = Bytes.toShort(inout);


        emsInfo.Image = ByteBuffer.wrap(result.getValue(FAMILY_BYTES, IMAGE));

        byte[] platePath = result.getValue(FAMILY_BYTES, PLATE_IMAGE_PATHS);
        if (platePath != null)
            emsInfo.PlateImagePath = Bytes.toString(platePath);


        byte[] imageBWPath = result.getValue(FAMILY_BYTES, IMAGE_BW_PATHS);
        if (imageBWPath != null)
            emsInfo.ImageBWPath = Bytes.toString(imageBWPath);


        byte[] allowed = result.getValue(FAMILY_BYTES, ALLOWEDS);
        if (allowed != null)
            emsInfo.Allowed = Bytes.toBoolean(allowed);


        byte[] allowedReasonId = result.getValue(FAMILY_BYTES, ALLOW_REASON_IDS);
        if (allowedReasonId != null)
             emsInfo.AllowReasonId = Bytes.toInt(allowedReasonId);


        byte[] symfaAllowed = result.getValue(FAMILY_BYTES, SYMFA_ALLOWEDS);
        if (symfaAllowed != null)
            emsInfo.SymfaAllowed = Bytes.toBoolean(symfaAllowed);


        byte[] symfaEndDate = result.getValue(FAMILY_BYTES, SYMFA_END_DATES);
        if (symfaEndDate != null)
            emsInfo.SymfaEndDate = Instant.parse(Bytes.toString(symfaEndDate));


        byte[] wrongDirection = result.getValue(FAMILY_BYTES, WRONG_DIRECTIONS);
        if (wrongDirection != null)
            emsInfo.WrongDirection = Bytes.toInt(wrongDirection);

        byte[] isSendToNaja = result.getValue(FAMILY_BYTES, IS_SEND_TO_NAJAS);
        if (isSendToNaja != null)
            emsInfo.IsSendToNaja = Bytes.toBoolean(isSendToNaja);


        byte[] validForSms = result.getValue(FAMILY_BYTES, VALID_FOR_SMS);
        if (validForSms != null)
            emsInfo.ValidForSms = Bytes.toBoolean(validForSms);

        return emsInfo;
    }
}
