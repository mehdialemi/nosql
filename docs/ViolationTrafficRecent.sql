USE [ViolationTrafficRecent]
GO
/****** Object:  Table [dbo].[_ImageProcessingPlate]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[_ImageProcessingPlate](
	[ID] [bigint] NOT NULL,
	[Plate] [bigint] NOT NULL,
	[CodeTypeId] [int] NOT NULL,
	[PlateQuality] [int] NOT NULL,
	[PlateSize] [int] NOT NULL,
	[StartTime] [time](7) NOT NULL,
	[EndTime] [time](7) NOT NULL,
	[DeviceId] [int] NOT NULL,
	[ErrorId] [int] NOT NULL,
	[CreateDate] [datetime] NOT NULL CONSTRAINT [_DF_new_ImageProcessingPlate_CreateDate]  DEFAULT (getdate())
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Arka_Class]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Arka_Class](
	[Id] [int] NOT NULL,
	[Title] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Arka_Class] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Arka_Color]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Arka_Color](
	[Id] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Arka_Color] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Arka_Proccess]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Arka_Proccess](
	[EmsInfoId] [bigint] NOT NULL,
	[SubClassId] [int] NOT NULL,
	[Plate] [bigint] NULL,
	[ColorId] [int] NULL,
	[SubClass_Acc] [int] NULL,
	[Plate_Acc] [int] NULL,
	[Color_Acc] [int] NULL,
	[CreateDate] [datetime] NOT NULL,
 CONSTRAINT [PK_Arka_Proccess] PRIMARY KEY CLUSTERED 
(
	[EmsInfoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Arka_SubClass]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Arka_SubClass](
	[ID] [int] NOT NULL,
	[ClassId] [int] NOT NULL,
	[SubClass] [nvarchar](200) NOT NULL,
 CONSTRAINT [PK_Arka_VehicleType] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CarType]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CarType](
	[TypeId] [int] IDENTITY(1,1) NOT NULL,
	[Type] [nvarchar](50) NULL,
 CONSTRAINT [PK_CarType] PRIMARY KEY CLUSTERED 
(
	[TypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CodeType]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CodeType](
	[Id] [int] NOT NULL,
	[Description] [nvarchar](500) NOT NULL,
	[Behavior] [nvarchar](500) NULL,
	[Percentage] [float] NULL,
 CONSTRAINT [PK_CodeType] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[EmsInfo]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmsInfo](
	[EmsInfoId] [bigint] IDENTITY(1,1) NOT NULL,
	[DeviceId] [int] NOT NULL,
	[DeviceCompanySystemId] [int] NOT NULL,
	[CompanyId] [int] NOT NULL,
	[Line] [int] NULL,
	[PassDatetime] [datetime] NOT NULL,
	[ReceiveDateTime] [datetime] NOT NULL,
	[ImageScore] [int] NOT NULL,
	[InvalidInfo] [int] NOT NULL,
	[MasterPlateNumber] [bigint] NOT NULL,
	[SystemId] [int] NOT NULL,
	[CarSpeed] [int] NOT NULL,
	[RFIDNumber] [nvarchar](50) NULL,
	[ParkometerId] [int] NULL,
	[CrimeCode] [int] NOT NULL,
	[Year] [int] NOT NULL,
	[Month] [tinyint] NOT NULL,
	[Date] [bigint] NOT NULL,
	[Inout] [smallint] NOT NULL,
	[ImagePath] [nvarchar](500) NOT NULL,
	[PlateImagePath] [nvarchar](500) NOT NULL,
	[ImageBWPath] [nvarchar](500) NULL,
	[Allowed] [bit] NOT NULL,
	[AllowReasonId] [int] NOT NULL,
	[SymfaAllowed] [bit] NOT NULL,
	[SymfaEndDate] [datetime] NULL,
	[WrongDirection] [int] NULL,
	[IsSendToNaja] [bit] NULL,
	[ValidForSms] [bit] NULL,
 CONSTRAINT [PK_EmsInfoRecent] PRIMARY KEY CLUSTERED 
(
	[EmsInfoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[EmsInfo_500]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmsInfo_500](
	[EmsInfoId] [bigint] NOT NULL,
	[DeviceId] [int] NOT NULL,
	[DeviceCompanySystemId] [int] NOT NULL,
	[CompanyId] [int] NOT NULL,
	[Line] [int] NULL,
	[PassDatetime] [datetime] NOT NULL,
	[ReceiveDateTime] [datetime] NOT NULL,
	[ImageScore] [int] NOT NULL,
	[InvalidInfo] [int] NOT NULL,
	[MasterPlateNumber] [bigint] NOT NULL,
	[SystemId] [int] NOT NULL,
	[CarSpeed] [int] NOT NULL,
	[RFIDNumber] [nvarchar](50) NULL,
	[ParkometerId] [int] NULL,
	[CrimeCode] [int] NOT NULL,
	[Year] [int] NOT NULL,
	[Month] [tinyint] NOT NULL,
	[Date] [bigint] NOT NULL,
	[Inout] [smallint] NOT NULL,
	[ImagePath] [nvarchar](500) NOT NULL,
	[PlateImagePath] [nvarchar](500) NOT NULL,
	[Allowed] [bit] NOT NULL,
	[AllowReasonId] [int] NOT NULL,
	[SymfaAllowed] [bit] NOT NULL,
	[SymfaEndDate] [datetime] NULL,
	[WrongDirection] [int] NULL,
	[ViolationId] [bigint] NULL,
	[ViolationAllowed] [bit] NULL,
	[ViolationSendDate] [datetime] NULL,
	[ImageBWPath] [nvarchar](500) NULL,
 CONSTRAINT [PK_EmsInfo_500] PRIMARY KEY CLUSTERED 
(
	[EmsInfoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[EmsInfoArchive]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmsInfoArchive](
	[EmsInfoId] [bigint] IDENTITY(687238125,1) NOT NULL,
	[DeviceId] [int] NOT NULL,
	[DeviceCompanySystemId] [int] NOT NULL,
	[CompanyId] [int] NOT NULL,
	[Line] [int] NULL,
	[PassDatetime] [datetime] NOT NULL,
	[ReceiveDateTime] [datetime] NOT NULL,
	[ImageScore] [int] NOT NULL,
	[InvalidInfo] [int] NOT NULL,
	[MasterPlateNumber] [bigint] NOT NULL,
	[SystemId] [int] NOT NULL,
	[CarSpeed] [int] NOT NULL,
	[RFIDNumber] [nvarchar](50) NULL,
	[ParkometerId] [int] NULL,
	[CrimeCode] [int] NOT NULL,
	[Year] [int] NOT NULL,
	[Month] [tinyint] NOT NULL,
	[Date] [bigint] NOT NULL,
	[Inout] [smallint] NOT NULL,
	[ImagePath] [nvarchar](500) NOT NULL,
	[PlateImagePath] [nvarchar](500) NOT NULL,
	[Allowed] [bit] NOT NULL,
	[AllowReasonId] [int] NOT NULL,
	[SymfaAllowed] [bit] NOT NULL,
	[SymfaEndDate] [datetime] NULL,
	[WrongDirection] [int] NULL,
	[ViolationId] [bigint] NULL,
	[ViolationAllowed] [bit] NULL,
	[ViolationSendDate] [datetime] NULL,
	[ImageBWPath] [nvarchar](500) NULL,
	[IsSendToNaja] [bit] NULL,
	[ValidForSms] [bit] NULL,
 CONSTRAINT [PK_New_EmsInfo] PRIMARY KEY NONCLUSTERED 
(
	[EmsInfoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [SHM_VTR_ANA]([Date])

GO
/****** Object:  Table [dbo].[EmsInfoMotor]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmsInfoMotor](
	[EmsInfoId] [bigint] IDENTITY(1,1) NOT NULL,
	[DeviceId] [int] NOT NULL,
	[DeviceCompanySystemId] [int] NOT NULL,
	[CompanyId] [int] NOT NULL,
	[Line] [int] NULL,
	[PassDatetime] [datetime] NOT NULL,
	[ReceiveDateTime] [datetime] NOT NULL,
	[ImageScore] [int] NOT NULL,
	[InvalidInfo] [int] NOT NULL,
	[MasterPlateNumber] [bigint] NOT NULL,
	[SystemId] [int] NOT NULL,
	[CarSpeed] [int] NOT NULL,
	[RFIDNumber] [nvarchar](50) NULL,
	[ParkometerId] [int] NULL,
	[CrimeCode] [int] NOT NULL,
	[Year] [int] NOT NULL,
	[Month] [tinyint] NOT NULL,
	[Date] [bigint] NOT NULL,
	[Inout] [smallint] NOT NULL,
	[ImagePath] [nvarchar](500) NOT NULL,
	[PlateImagePath] [nvarchar](500) NOT NULL,
	[Allowed] [bit] NOT NULL,
	[AllowReasonId] [int] NOT NULL,
	[SymfaAllowed] [bit] NOT NULL,
	[SymfaEndDate] [datetime] NULL,
	[WrongDirection] [int] NULL,
	[ViolationId] [bigint] NULL,
	[ViolationAllowed] [bit] NULL,
	[ViolationSendDate] [datetime] NULL,
	[ImageBWPath] [nvarchar](500) NULL,
	[IsSendToNaja] [bit] NULL,
	[ValidForSms] [bit] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[EmsInfoSmsLog]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmsInfoSmsLog](
	[EsmInfoId] [bigint] NOT NULL,
	[Status] [smallint] NULL,
	[CreateDate] [datetime] NULL CONSTRAINT [DF_EmsInfoSmsLog_CreateDate]  DEFAULT (getdate()),
 CONSTRAINT [PK_EmsInfoSmsLog] PRIMARY KEY CLUSTERED 
(
	[EsmInfoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[EmsTransfer]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmsTransfer](
	[EmsInfoId] [bigint] NOT NULL,
	[TransferDate] [datetime] NULL,
	[HasImage] [bit] NULL,
 CONSTRAINT [PK_EmsTransfer] PRIMARY KEY CLUSTERED 
(
	[EmsInfoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ErrorType]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ErrorType](
	[Id] [int] NOT NULL,
	[Title] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_ErrorCode] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ImageProcessingPlate]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImageProcessingPlate](
	[ID] [bigint] NOT NULL,
	[Plate] [bigint] NOT NULL,
	[CodeTypeId] [int] NOT NULL,
	[PlateQuality] [int] NOT NULL,
	[PlateSize] [int] NOT NULL,
	[StartTime] [time](7) NOT NULL,
	[EndTime] [time](7) NOT NULL,
	[DeviceId] [int] NOT NULL,
	[ErrorId] [int] NOT NULL,
	[CreateDate] [datetime] NOT NULL CONSTRAINT [DF_new_ImageProcessingPlate_CreateDate]  DEFAULT (getdate()),
 CONSTRAINT [PK_new_ImageProcessingPlate] PRIMARY KEY NONCLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [SHM_VTR_SL]([ID])

GO
/****** Object:  Table [dbo].[ImageProcessingPlate_Demo]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImageProcessingPlate_Demo](
	[ID] [bigint] NOT NULL,
	[Plate] [bigint] NOT NULL,
	[CodeTypeId] [int] NOT NULL,
	[PlateQuality] [int] NOT NULL,
	[PlateSize] [int] NOT NULL,
	[StartTime] [time](7) NOT NULL,
	[EndTime] [time](7) NOT NULL,
	[DeviceId] [int] NOT NULL,
	[ErrorId] [int] NOT NULL,
	[CreateDate] [datetime] NOT NULL CONSTRAINT [DF_ImageProcessingPlate_CreateDate_Demo]  DEFAULT (getdate()),
 CONSTRAINT [PK_ImageProcessingPlate_Demo] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ImageQuality]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImageQuality](
	[Id] [int] NOT NULL,
	[Title] [nvarchar](50) NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LinkServerError]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LinkServerError](
	[LinkServerId] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[ErrorNumber] [int] NULL,
	[ErrorMessage] [nvarchar](max) NULL,
	[PlateNumber] [bigint] NULL,
	[PassDatetime] [datetime] NULL,
	[CreateDate] [datetime] NULL,
 CONSTRAINT [PK_LinkServerError] PRIMARY KEY CLUSTERED 
(
	[LinkServerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Missed_Image]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Missed_Image](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[RecentPath] [nvarchar](500) NOT NULL,
	[ImagePath] [nvarchar](50) NOT NULL,
	[IsSend] [bit] NOT NULL CONSTRAINT [DF___Image_IsSend]  DEFAULT ((0)),
	[Description] [nvarchar](150) NULL,
 CONSTRAINT [PK___Image] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Naja_Car]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Naja_Car](
	[KARBARI] [nvarchar](50) NOT NULL,
	[ZIR_KARBRI] [nvarchar](50) NOT NULL,
	[SYSTEM_DESC] [nvarchar](50) NOT NULL,
	[TIP_DESC] [nvarchar](50) NOT NULL,
	[NASB_DATE] [nvarchar](10) NULL,
	[MNFK_SHDN_DATE] [datetime2](7) NULL,
	[MHL_SHOMRGOZARI] [nvarchar](50) NOT NULL,
	[MDL] [numeric](4, 0) NOT NULL,
	[PLAK] [nvarchar](4000) NULL,
	[VIN_NUM] [nvarchar](17) NULL,
	[KESHVAR_SAZANDEH] [nvarchar](50) NOT NULL,
	[NOE_SOKHT] [nvarchar](50) NOT NULL,
	[MASTERPLATENUMBER] [nvarchar](9) NULL,
	[Ms] [bigint] NULL,
	[PlateNumber] [bigint] NULL,
	[CarTypeId] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ShUnv_Log]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ShUnv_Log](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MinID] [bigint] NOT NULL,
	[MaxId] [bigint] NOT NULL,
	[Description] [nvarchar](50) NULL,
	[CreateDate] [datetime] NOT NULL,
 CONSTRAINT [PK_ShUnv_Log] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Symfa]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Symfa](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Plate] [bigint] NOT NULL,
	[PassDatetime] [datetime] NOT NULL,
	[CheckDate] [datetime] NULL,
	[StatusCode] [int] NULL,
	[EndDate] [datetime] NULL,
	[Description] [nvarchar](200) NULL,
	[Model] [int] NULL,
	[StartTime] [datetime] NULL,
	[CarType] [nvarchar](50) NULL,
 CONSTRAINT [PK_Symfa] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Symfa_New]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Symfa_New](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Plate] [bigint] NOT NULL,
	[PassDatetime] [datetime] NOT NULL,
	[CheckDate] [datetime] NULL,
	[StartTime] [datetime] NULL,
	[IsWorn] [int] NULL,
	[ValidTest] [int] NULL,
	[NajaServiceStatus] [nvarchar](50) NULL,
	[SymfaServiceStatus] [nvarchar](50) NULL,
	[ExpireDatetime] [datetime] NULL,
	[FuelSystem] [int] NULL,
	[Description] [nvarchar](max) NULL,
 CONSTRAINT [PK_Symfa_Text] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SymfaStatus]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SymfaStatus](
	[StatusCode] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SymfaTemp]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SymfaTemp](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Plate] [bigint] NOT NULL,
	[PassDatetime] [datetime] NOT NULL,
	[CheckDate] [datetime] NULL,
	[StatusCode] [int] NULL,
	[EndDate] [datetime] NULL,
	[Description] [nvarchar](200) NULL,
 CONSTRAINT [PK_SymfaTemp] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaxiBlackListPlate]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaxiBlackListPlate](
	[ردیف] [float] NULL,
	[P] [nvarchar](255) NULL,
	[تیپ ] [nvarchar](255) NULL,
	[سیستم ] [nvarchar](255) NULL,
	[مدل ] [nvarchar](255) NULL,
	[سوخت ] [nvarchar](255) NULL,
	[vin ] [nvarchar](255) NULL,
	[نام و نام خانوادگي] [nvarchar](255) NULL,
	[نام پدر] [nvarchar](255) NULL,
	[كد ملي] [nvarchar](255) NULL,
	[Plate] [bigint] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaxiPlates]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaxiPlates](
	[Plate] [bigint] NOT NULL,
 CONSTRAINT [PK_TaxiPlates] PRIMARY KEY CLUSTERED 
(
	[Plate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TechnicalCheck]    Script Date: 9/3/2017 8:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TechnicalCheck](
	[Plate] [bigint] NOT NULL,
	[Status] [int] NULL,
	[InqueryDate] [datetime] NULL,
	[Description] [nvarchar](max) NULL,
 CONSTRAINT [PK_TechnicalCheck] PRIMARY KEY CLUSTERED 
(
	[Plate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
ALTER TABLE [dbo].[Arka_Proccess] ADD  CONSTRAINT [DF_Arka_Proccess_CreateDate]  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[LinkServerError] ADD  CONSTRAINT [DF_LinkServerError_CreateDate]  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Arka_Proccess]  WITH CHECK ADD  CONSTRAINT [FK_Arka_Proccess_Arka_Color] FOREIGN KEY([ColorId])
REFERENCES [dbo].[Arka_Color] ([Id])
GO
ALTER TABLE [dbo].[Arka_Proccess] CHECK CONSTRAINT [FK_Arka_Proccess_Arka_Color]
GO
ALTER TABLE [dbo].[Arka_Proccess]  WITH CHECK ADD  CONSTRAINT [FK_Arka_Proccess_Arka_SubClass] FOREIGN KEY([SubClassId])
REFERENCES [dbo].[Arka_SubClass] ([ID])
GO
ALTER TABLE [dbo].[Arka_Proccess] CHECK CONSTRAINT [FK_Arka_Proccess_Arka_SubClass]
GO
ALTER TABLE [dbo].[Arka_SubClass]  WITH CHECK ADD  CONSTRAINT [FK_Arka_SubClass_Arka_SubClass] FOREIGN KEY([ClassId])
REFERENCES [dbo].[Arka_Class] ([Id])
GO
ALTER TABLE [dbo].[Arka_SubClass] CHECK CONSTRAINT [FK_Arka_SubClass_Arka_SubClass]
GO
ALTER TABLE [dbo].[EmsInfoArchive]  WITH CHECK ADD  CONSTRAINT [FK_new_EmsInfo_EmsInfo] FOREIGN KEY([EmsInfoId])
REFERENCES [dbo].[EmsInfoArchive] ([EmsInfoId])
GO
ALTER TABLE [dbo].[EmsInfoArchive] CHECK CONSTRAINT [FK_new_EmsInfo_EmsInfo]
GO
