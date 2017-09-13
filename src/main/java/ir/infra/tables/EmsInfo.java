package ir.infra.tables;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import com.flipkart.hbaseobjectmapper.*;
import com.flipkart.hbaseobjectmapper.codec.BestSuitCodec;
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
    public Integer DeviceId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "DeviceCompanySystemId")
    public Integer DeviceCompanySystemId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "CompanyId")
    public Integer CompanyId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Line")
    public Integer Line;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "PassDatetime",
            codecFlags = {@Flag(name = BestSuitCodec.SERIALISE_AS_STRING, value = "true")})
    public Instant PassDatetime;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ReceiveDateTime",
            codecFlags = {@Flag(name = BestSuitCodec.SERIALISE_AS_STRING, value = "true")})
    public Instant ReceiveDateTime;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ImageScore")
    public Integer ImageScore;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "InvalidInfo")
    public Integer InvalidInfo;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "MasterPlateNumber")
    public Long MasterPlateNumber;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "SystemId")
    public Integer SystemId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "CarSpeed")
    public Integer CarSpeed;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "RFIDNumber")
    public String RFIDNumber;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ParkometerId")
    public Integer ParkometerId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "CrimeCode")
    public Integer CrimeCode;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Year")
    public Integer Year;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Month")
    public Byte Month;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Date")
    public Long Date;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "Inout")
    public Short Inout;

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
    public Boolean Allowed;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "AllowReasonId")
    public Integer AllowReasonId;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "SymfaAllowed")
    public Boolean SymfaAllowed;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "SymfaEndDate", codecFlags = {@Flag(name = BestSuitCodec.SERIALISE_AS_STRING, value = "true")})
    public Instant SymfaEndDate;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "WrongDirection")
    public Integer WrongDirection;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "IsSendToNaja")
    public Boolean IsSendToNaja;

    @JsonProperty
    @HBColumn(family = FAMILY, column = "ValidForSms")
    public Boolean ValidForSms;

    public EmsInfo() {
    }

    public Long composeRowKey() {
        return EmsInfoId;
    }

    public void parseRowKey(Long rowKey) {
        EmsInfoId = rowKey;
    }
}
