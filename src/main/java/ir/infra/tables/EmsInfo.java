package ir.infra.tables;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import com.flipkart.hbaseobjectmapper.*;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import static ir.infra.core.Constants.KEY_SPACE;
import static ir.infra.core.Constants.FAMILY;

@Table(keyspace = KEY_SPACE, name = "EmsInfo")
@HBTable(name = "EmsInfo", families = {@Family(name = FAMILY)})
public class EmsInfo implements HBRecord<Long> {

    public EmsInfo() {}

    @NotNull
    @PartitionKey
    @HBRowKey
    private long EmsInfoId;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "DeviceId")
    private int DeviceId;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "DeviceCompanySystemId")
    private int DeviceCompanySystemId;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "CompanyId")
    private int CompanyId;

    @Null
    @Column
    @HBColumn(family = FAMILY, column = "Line")
    private int Line;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "PassDatetime")
    private DateTime PassDatetime;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "ReceiveDateTime")
    private DateTime ReceiveDateTime;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "ImageScore")
    private int ImageScore;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "InvalidInfo")
    private int InvalidInfo;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "MasterPlateNumber")
    private long MasterPlateNumber;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "SystemId")
    private int SystemId;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "CarSpeed")
    private int CarSpeed;

    @Null
    @Column
    @HBColumn(family = FAMILY, column = "RFIDNumber")
    private String RFIDNumber;

    @Null
    @Column
    @HBColumn(family = FAMILY, column = "ParkometerId")
    private int ParkometerId;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "CrimeCode")
    private int CrimeCode;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "Year")
    private int Year;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "Month")
    private short Month;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "Date")
    private long Date;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "Inout")
    private short Inout;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "ImagePath")
    private String ImagePath;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "PlateImagePath")
    private String PlateImagePath;

    @Null
    @Column
    @HBColumn(family = FAMILY, column = "ImageBWPath")
    private String ImageBWPath;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "Allowed")
    private boolean Allowed;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "AllowReasonId")
    private int AllowReasonId;

    @NotNull
    @Column
    @HBColumn(family = FAMILY, column = "SymfaAllowed")
    private boolean SymfaAllowed;

    @Null
    @Column
    @HBColumn(family = FAMILY, column = "SymfaEndDate")
    private DateTime SymfaEndDate;

    @Null
    @Column
    @HBColumn(family = FAMILY, column = "WrongDirection")
    private int WrongDirection;

    @Null
    @Column
    @HBColumn(family = FAMILY, column = "IsSendToNaja")
    private boolean IsSendToNaja;

    @Null
    @Column
    @HBColumn(family = FAMILY, column = "ValidForSms")
    private boolean ValidForSms;

    public int getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(int deviceId) {
        DeviceId = deviceId;
    }

    public int getDeviceCompanySystemId() {
        return DeviceCompanySystemId;
    }

    public void setDeviceCompanySystemId(int deviceCompanySystemId) {
        DeviceCompanySystemId = deviceCompanySystemId;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public DateTime getPassDatetime() {
        return PassDatetime;
    }

    public void setPassDatetime(DateTime passDatetime) {
        PassDatetime = passDatetime;
    }

    public DateTime getReceiveDateTime() {
        return ReceiveDateTime;
    }

    public void setReceiveDateTime(DateTime receiveDateTime) {
        ReceiveDateTime = receiveDateTime;
    }

    public int getImageScore() {
        return ImageScore;
    }

    public void setImageScore(int imageScore) {
        ImageScore = imageScore;
    }

    public int getInvalidInfo() {
        return InvalidInfo;
    }

    public void setInvalidInfo(int invalidInfo) {
        InvalidInfo = invalidInfo;
    }

    public long getMasterPlateNumber() {
        return MasterPlateNumber;
    }

    public void setMasterPlateNumber(long masterPlateNumber) {
        MasterPlateNumber = masterPlateNumber;
    }

    public int getSystemId() {
        return SystemId;
    }

    public void setSystemId(int systemId) {
        SystemId = systemId;
    }

    public int getCarSpeed() {
        return CarSpeed;
    }

    public void setCarSpeed(int carSpeed) {
        CarSpeed = carSpeed;
    }

    public String getRFIDNumber() {
        return RFIDNumber;
    }

    public void setRFIDNumber(String RFIDNumber) {
        this.RFIDNumber = RFIDNumber;
    }

    public int getCrimeCode() {
        return CrimeCode;
    }

    public void setCrimeCode(int crimeCode) {
        CrimeCode = crimeCode;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public short getMonth() {
        return Month;
    }

    public void setMonth(short month) {
        Month = month;
    }

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public short getInout() {
        return Inout;
    }

    public void setInout(short inout) {
        Inout = inout;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getPlateImagePath() {
        return PlateImagePath;
    }

    public void setPlateImagePath(String plateImagePath) {
        PlateImagePath = plateImagePath;
    }

    public String getImageBWPath() {
        return ImageBWPath;
    }

    public void setImageBWPath(String imageBWPath) {
        ImageBWPath = imageBWPath;
    }

    public boolean getAllowed() {
        return Allowed;
    }

    public void setAllowed(boolean allowed) {
        Allowed = allowed;
    }

    public int getAllowReasonId() {
        return AllowReasonId;
    }

    public void setAllowReasonId(int allowReasonId) {
        AllowReasonId = allowReasonId;
    }

    public boolean getSymfaAllowed() {
        return SymfaAllowed;
    }

    public void setSymfaAllowed(boolean symfaAllowed) {
        SymfaAllowed = symfaAllowed;
    }

    public DateTime getSymfaEndDate() {
        return SymfaEndDate;
    }

    public void setSymfaEndDate(DateTime symfaEndDate) {
        SymfaEndDate = symfaEndDate;
    }

    public int getWrongDirection() {
        return WrongDirection;
    }

    public void setWrongDirection(int wrongDirection) {
        WrongDirection = wrongDirection;
    }

    public boolean getIsSendToNaja() {
        return IsSendToNaja;
    }

    public void setIsSendToNaja(boolean sendToNaja) {
        IsSendToNaja = sendToNaja;
    }

    public boolean getValidForSms() {
        return ValidForSms;
    }

    public void setValidForSms(boolean validForSms) {
        ValidForSms = validForSms;
    }

    public int getParkometerId() {
        return ParkometerId;
    }

    public void setParkometerId(int parkometerId) {
        ParkometerId = parkometerId;
    }

    public long getEmsInfoId() {
        return EmsInfoId;
    }

    public void setEmsInfoId(long emsInfoId) {
        EmsInfoId = emsInfoId;
    }

    public int getLine() {
        return Line;
    }

    public void setLine(int line) {
        Line = line;
    }

    public Long composeRowKey() {
        return EmsInfoId;
    }

    public void parseRowKey(Long rowKey) {
        EmsInfoId = rowKey;
    }
}
