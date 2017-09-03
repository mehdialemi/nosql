package ir.infra;


import org.joda.time.DateTime;

public class EmsInfo {
    long Identity;
    int DeviceId;
    int DeviceCompanySystemId;
    int CompanyId;
    DateTime PassDatetime;
    DateTime ReceiveDateTime;
    int ImageScore;
    int InvalidInfo;
    long MasterPlateNumber;
    int SystemId;
    int CarSpeed;
    String RFIDNumber;
    int ParkometerId;
    int CrimeCode;
    int Year;
    short Month;
    long Date;
    short Inout;
    String ImagePath;
    String PlateImagePath;
    String ImageBWPath;
    boolean Allowed;
    int AllowReasonId;
    boolean SymfaAllowed;
    DateTime SymfaEndDate;
    int WrongDirection;
    boolean IsSendToNaja;
    boolean ValidForSms;
}
