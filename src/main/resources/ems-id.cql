CREATE MATERIALIZED VIEW traffic.EmsId AS
	SELECT * FROM traffic.EmsInfo
	WHERE DeviceID IS NOT NULL AND PassDatetime IS NOT NULL AND EmsInfoId IS NOT NULL
	PRIMARY KEY (EmsInfoId, DeviceID, PassDatetime);
