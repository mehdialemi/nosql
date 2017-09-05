package ir.infra.tables;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import org.joda.time.DateTime;

import static ir.infra.core.Constants.KEY_SPACE;

@Table(keyspace = KEY_SPACE, name = "ImageProcessingPlate")
public class ImageProcessingPlate {

    @PartitionKey
    private
    long ID;

    @Column
    private
    long Plate;

    @Column
    private
    int CodeTypeId;

    @Column
    private
    int PlateQuality;

    @Column
    private
    int PlateSize;

    @Column
    private
    DateTime StartTime;

    @Column
    private
    DateTime endTime;

    @Column
    private
    int DeviceId;

    @Column
    private
    int ErrorId;

    @Column
    private
    DateTime CreateDate;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getPlate() {
        return Plate;
    }

    public void setPlate(long plate) {
        Plate = plate;
    }

    public int getCodeTypeId() {
        return CodeTypeId;
    }

    public void setCodeTypeId(int codeTypeId) {
        CodeTypeId = codeTypeId;
    }

    public int getPlateQuality() {
        return PlateQuality;
    }

    public void setPlateQuality(int plateQuality) {
        PlateQuality = plateQuality;
    }

    public int getPlateSize() {
        return PlateSize;
    }

    public void setPlateSize(int plateSize) {
        PlateSize = plateSize;
    }

    public DateTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(DateTime startTime) {
        StartTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public int getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(int deviceId) {
        DeviceId = deviceId;
    }

    public int getErrorId() {
        return ErrorId;
    }

    public void setErrorId(int errorId) {
        ErrorId = errorId;
    }

    public DateTime getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(DateTime createDate) {
        CreateDate = createDate;
    }
}
