package ir.infra.tables;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import com.flipkart.hbaseobjectmapper.*;
import org.joda.time.Instant;

import static ir.infra.core.Constants.KEY_SPACE;
import static ir.infra.core.Constants.FAMILY;

@Table(keyspace = KEY_SPACE, name = "EmsInfo")
@HBTable(name = "EmsInfo", families = {@Family(name = FAMILY)})
public class EmsInfo implements HBRecord<Long> {

    @PartitionKey
    @HBRowKey
    public long EmsInfoId;

    @HBColumn(family = FAMILY, column = "DeviceId")
    public int DeviceId;

    @HBColumn(family = FAMILY, column = "DeviceCompanySystemId")
    public int DeviceCompanySystemId;

    @HBColumn(family = FAMILY, column = "CompanyId")
    public int CompanyId;

    @HBColumn(family = FAMILY, column = "Line")
    public int Line;

    @HBColumn(family = FAMILY, column = "PassDatetime")
    public Instant PassDatetime;

    @HBColumn(family = FAMILY, column = "ReceiveDateTime")
    public Instant ReceiveDateTime;

    @HBColumn(family = FAMILY, column = "ImageScore")
    public int ImageScore;

    @HBColumn(family = FAMILY, column = "InvalidInfo")
    public int InvalidInfo;

    @HBColumn(family = FAMILY, column = "MasterPlateNumber")
    public long MasterPlateNumber;

    @HBColumn(family = FAMILY, column = "SystemId")
    public int SystemId;

    @HBColumn(family = FAMILY, column = "CarSpeed")
    public int CarSpeed;

    @HBColumn(family = FAMILY, column = "RFIDNumber")
    public String RFIDNumber;

    @HBColumn(family = FAMILY, column = "ParkometerId")
    public int ParkometerId;

    @HBColumn(family = FAMILY, column = "CrimeCode")
    public int CrimeCode;

    @HBColumn(family = FAMILY, column = "Year")
    public int Year;

    @HBColumn(family = FAMILY, column = "Month")
    public byte Month;

    @HBColumn(family = FAMILY, column = "Date")
    public long Date;

    @HBColumn(family = FAMILY, column = "Inout")
    public short Inout;

    @HBColumn(family = FAMILY, column = "ImagePath")
    public String ImagePath;

    @HBColumn(family = FAMILY, column = "PlateImagePath")
    public String PlateImagePath;

    @HBColumn(family = FAMILY, column = "ImageBWPath")
    public String ImageBWPath;

    @HBColumn(family = FAMILY, column = "Allowed")
    public boolean Allowed;

    @HBColumn(family = FAMILY, column = "AllowReasonId")
    public int AllowReasonId;

    @HBColumn(family = FAMILY, column = "SymfaAllowed")
    public boolean SymfaAllowed;

    @HBColumn(family = FAMILY, column = "SymfaEndDate")
    public Instant SymfaEndDate;

    @HBColumn(family = FAMILY, column = "WrongDirection")
    public int WrongDirection;

    @HBColumn(family = FAMILY, column = "IsSendToNaja")
    public boolean IsSendToNaja;

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
}
