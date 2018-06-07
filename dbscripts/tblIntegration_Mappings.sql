SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE TimeCurrent.dbo.tblIntegration_Mappings(
	RecordID int NOT NULL,
	MapName varchar(20) NOT NULL,
	Attribute varchar(20) NOT NULL,
	Expression varchar(500) NULL,
	Version int NOT NULL,
	CreatedBy varchar(100) NULL,
	CreateDateTime datetime NULL,
PRIMARY KEY CLUSTERED 
(
	RecordID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON PRIMARY
) ON PRIMARY

GO
