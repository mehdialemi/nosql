package ir.infra.tables;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import com.flipkart.hbaseobjectmapper.*;
import com.google.gson.Gson;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.Instant;

import static ir.infra.core.Constants.KEY_SPACE;
import static ir.infra.core.Constants.FAMILY;


@Table(keyspace = KEY_SPACE, name = "EmsInfo")
@HBTable(name = "EmsInfo", families = {@Family(name = FAMILY)})
public class EmsInfo implements HBRecord<Long> {

    @JsonProperty
    @PartitionKey
    @HBRowKey
    public long EmsInfoId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "DeviceId")
    public int DeviceId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "DeviceCompanySystemId")
    public int DeviceCompanySystemId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "CompanyId")
    public int CompanyId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Line")
    public int Line;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "PassDatetime")
    public Instant PassDatetime;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ReceiveDateTime")
    public Instant ReceiveDateTime;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ImageScore")
    public int ImageScore;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "InvalidInfo")
    public int InvalidInfo;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "MasterPlateNumber")
    public long MasterPlateNumber;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "SystemId")
    public int SystemId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "CarSpeed")
    public int CarSpeed;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "RFIDNumber")
    public String RFIDNumber;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ParkometerId")
    public int ParkometerId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "CrimeCode")
    public int CrimeCode;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Year")
    public int Year;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Month")
    public byte Month;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Date")
    public long Date;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Inout")
    public short Inout;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ImagePath")
    public String ImagePath;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "PlateImagePath")
    public String PlateImagePath;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ImageBWPath")
    public String ImageBWPath;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Allowed")
    public boolean Allowed;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "AllowReasonId")
    public int AllowReasonId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "SymfaAllowed")
    public boolean SymfaAllowed;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "SymfaEndDate")
    public Instant SymfaEndDate;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "WrongDirection")
    public int WrongDirection;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "IsSendToNaja")
    public boolean IsSendToNaja;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ValidForSms")
    public boolean ValidForSms;

    public EmsInfo() {
    }

    public Long composeRowKey() {
        return EmsInfoId;
    }

    public void parseRowKey(Long rowKey) {
        EmsInfoId = rowKey;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        EmsInfo emsInfo = new EmsInfo();
        emsInfo.EmsInfoId = 100;
        emsInfo.PassDatetime = Instant.now();
        emsInfo.DeviceId = 20;
        emsInfo.Allowed = true;
        emsInfo.CarSpeed = 130;
        emsInfo.AllowReasonId = 22;
        emsInfo.CompanyId = 320;
        emsInfo.CrimeCode = 41;
        emsInfo.Date = System.currentTimeMillis();
        emsInfo.DeviceCompanySystemId = 32;
        emsInfo.ImageBWPath = "bwPath";
        emsInfo.ImagePath = "imgPath";
        emsInfo.ImageScore = 2;
        emsInfo.Inout = 20;
        emsInfo.InvalidInfo = 64;
        emsInfo.IsSendToNaja = true;
        emsInfo.Line = 4;
        emsInfo.MasterPlateNumber = 53;
        emsInfo.PlateImagePath = "platePath";
        emsInfo.Month = 3;
        emsInfo.ReceiveDateTime = Instant.now();
        emsInfo.ParkometerId = 54;
        emsInfo.RFIDNumber = "24";
        emsInfo.SymfaAllowed = false;
        emsInfo.SymfaEndDate = Instant.now();
        emsInfo.SystemId = 35;
        emsInfo.ValidForSms = false;
        emsInfo.WrongDirection = 2;
        emsInfo.Year = 1397;
        String json = gson.toJson(emsInfo);
        System.out.println(json);
    }
}
