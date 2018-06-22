package com.bullhorn.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class TargetAssignments {
	@ApiModelProperty(notes = "[Required] APIKey per client")
	public String IntegrationKey;
	
	@ApiModelProperty(notes = "It's a MessageID from Listener - Asssigned at runtime by service bus/ listener")
	public String MessageID;
	
	@ApiModelProperty(notes = "Our version of JobID - generated jobID based on a timestamp negative random num for a TRANSACTION")
	public String JobID;

	@ApiModelProperty(notes = "It's a random asssigned at run time by service bus/ listener")
	public String RecID; 
	
	@ApiModelProperty(notes = "It's a JobDateTime from Listener @JobDateTime = DateTime.Now() DATETIME")
	public String TransDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	
	@ApiModelProperty(notes = "It's a client Code @Client from Listener VARCHAR(4)")
	public String Client;
	
	@ApiModelProperty(notes = "It's a flag @FirstTime from Listener =\"Y\" CHAR(1)")
	public String FirstTime = "Y";
	
	@ApiModelProperty(notes = "[Required]")
	public String Source;
	
	@ApiModelProperty(notes = "[Optional]")
	public String BranchName;
	
	@ApiModelProperty(notes = "[Required]")
	public String BranchID;// [Required]w

	@ApiModelProperty(notes = "[Required]")
	public String ClientName;// [Required]w

	@ApiModelProperty(notes = "[Required]")
	public String ClientID;// [Required]//[Required]w
	
	@ApiModelProperty(notes = "[Required]")
	public String EmployeeFirstName;// [Required]//[Required]w
	@ApiModelProperty(notes = "")
	public String EmployeeLastName;// [Required]//[Required]w
	@ApiModelProperty(notes = "")
	public String EmployeeEmailAddress;
	@ApiModelProperty(notes = "")
	public String EmployeeID;// [Required]//[Required]w
	@ApiModelProperty(notes = "")
	public String EmployeeSSN;// [Required]//[Required]w
	@ApiModelProperty(notes = "")
	public String EmployeePIN;
	@ApiModelProperty(notes = "")
	public String EmployeeBadge;
	@ApiModelProperty(notes = "")
	public String AssignmentNumber;// [Required]//[Required]w
	@ApiModelProperty(notes = "")
	public String JobDescription;// [Required]w
	@ApiModelProperty(notes = "")
	public String ClockGroup;// [Required]c
	@ApiModelProperty(notes = "")
	public String AssignmentStartDate;// [Required]//[Required]w
	@ApiModelProperty(notes = "")
	public String AssignmentEndDate;// [Required]
	@ApiModelProperty(notes = "")
	public String AssignmentEndReason;
	@ApiModelProperty(notes = "")
	public String DepartmentMapping;// [Required]c
	@ApiModelProperty(notes = "")
	public String DepartmentAbbr;
	@ApiModelProperty(notes = "")
	public String ClientDepartmentCode;
	@ApiModelProperty(notes = "")
	public String DepartmentName;
	@ApiModelProperty(notes = "")
	public String ClockMapping;
	@ApiModelProperty(notes = "")
	public String ShiftCode;// [Required]
	@ApiModelProperty(notes = "")
	public String BillRate;
	@ApiModelProperty(notes = "")
	public String PayRate;
	@ApiModelProperty(notes = "")
	public String OTBillingFactor;
	@ApiModelProperty(notes = "")
	public String DTBillingFactor;
	@ApiModelProperty(notes = "")
	public String OTPayFactor;
	@ApiModelProperty(notes = "")
	public String DTPayFactor;
	@ApiModelProperty(notes = "")
	public String LastDayOfWeek;// [Required]w
	@ApiModelProperty(notes = "")
	public String WorkSiteID;
	@ApiModelProperty(notes = "")
	public String WorkSiteName;
	@ApiModelProperty(notes = "")
	public String WorkSiteState;
	@ApiModelProperty(notes = "")
	public String Approver1FirstName; // ; //
	@ApiModelProperty(notes = "")
	public String Approver1LastName;
	@ApiModelProperty(notes = "")
	public String Approver1Email;
	@ApiModelProperty(notes = "")
	public String Approver2FirstName;
	@ApiModelProperty(notes = "")
	public String Approver2LastName;
	@ApiModelProperty(notes = "")
	public String Approver2Email;
	@ApiModelProperty(notes = "")
	public String AgencyCode;// ;//
	@ApiModelProperty(notes = "")
	public String AgencyName;// ;//
	@ApiModelProperty(notes = "")
	public String VMSEmployeeID;
	@ApiModelProperty(notes = "")
	public String VMSAssignmentNumber;
	@ApiModelProperty(notes = "")
	public String VMSCostCenter;
	public String VMSBuyerID;
	public String VMSRequisitionID;
	public String EmployeeOTType;
	public String ExpenseApproverFName;
	public String ExpenseApproverLName;
	public String ExpenseApproverEmail;
	public String ExpenseApprover2FName;
	public String ExpenseApprover2LName;
	public String ExpenseApprover2Email;
	public String InOutIndicator;
	public String AlternateWorkSchedule;// ;//
	public String Rounding;
	public String Branding;
	public String PayRules;
	public String ApprovalMethod;
	public String EntryFrequency;
	public String HolidayCode;
	public String ConsultantType;
	public String ProjectIndicator;
	public String ExpenseIndicator;
	public String BranchContactPhone;
	public String EmployeeCellPhone;

	public String EmployeeWebImage;
	public String TimeCodes;
	public String ExpenseCodes;
	public String PreventWorkedTime;
	public String NoBill_ExpAprvr_FirstName;
	public String NoBill_ExpAprvr_LastName;
	public String NoBill_ExpAprvr_Email;
	public String NoBill_ExpAprvr_FirstName2;
	public String NoBill_ExpAprvr_LastName2;
	public String NoBill_ExpAprvr_Email2;

	public String NoBill_TS_Aprvr_FirstName;
	public String NoBill_TS_Aprvr_LastName;
	public String NoBill_TS_Aprvr_Email;
	public String NoBill_TS_Aprvr_FirstName2;
	public String NoBill_TS_Aprvr_LastName2;
	public String NoBill_TS_Aprvr_Email2;

	public String SalaryIndicator;
	public String EmployeeCPAFlag;
	public String ProxyCPAFlag;
	public String MobileCarrier;
	public String FoxAssignmentNo;
	public String AssignmentCostCenter;
	public String Setting;

	// additional fields (STAF implementation)
	public String WaiveBreak1;
	public String WaiveBreak2;
	public String PR_EmployeeType;
	public String PR_BreakLength;
	public String Rate_Effective_Structure;
	public String PONumber;
	public String Division;
	public String ShiftName;
	public String SundayShiftStart;
	public String SundayShiftStop;
	public String MondayShiftStart;
	public String MondayShiftStop;
	public String TuesdayShiftStart;
	public String TuesdayShiftStop;
	public String WednesdayShiftStart;
	public String WednesdayShiftStop;
	public String ThursdayShiftStart;
	public String ThursdayShiftStop;
	public String FridayShiftStart;
	public String FridayShiftStop;
	public String SaturdayShiftStart;
	@ApiModelProperty(notes = "")
	public String SaturdayShiftStop;

	// additional fields (OnDemand implementation)
	@ApiModelProperty(notes = "")
	public String WorkSiteAddr1;
	@ApiModelProperty(notes = "")
	public String WorkSiteAddr2;
	@ApiModelProperty(notes = "")
	public String WorkSiteCity;
	@ApiModelProperty(notes = "")
	public String WorkSiteZip;
	@ApiModelProperty(notes = "")
	public String CallOff;
	@ApiModelProperty(notes = "")
	public String AssignmentStatus;

	// additional fields (AMN implementation)
	@ApiModelProperty(notes = "")
	public String SortOrder;
	@ApiModelProperty(notes = "")
	public String OrderID;
	@ApiModelProperty(notes = "")
	public String MinHours;

	// additional fields (KForce implementation)
	@ApiModelProperty(notes = "")
	public String Reg_TRC_Code;
	@ApiModelProperty(notes = "")
	public String OT_TRC_Code;
	@ApiModelProperty(notes = "")
	public String DT_TRC_Code;
	@ApiModelProperty(notes = "")
	public String UDFValues;
	
	public TargetAssignments() {
	}
}
