package com.bullhorn.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings(value = "unused")
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
    @ApiModelProperty(notes = "Employee Last Name")
    public String EmployeeLastName;// [Required]//[Required]w
    @ApiModelProperty(notes = "Employee Email Address")
    public String EmployeeEmailAddress;
    @ApiModelProperty(notes = "Employee ID")
    public String EmployeeID;// [Required]//[Required]w
    @ApiModelProperty(notes = "Employee Social Security Number")
    public String EmployeeSSN;// [Required]//[Required]w
    @ApiModelProperty(notes = "Employee PIN")
    public String EmployeePIN;
    @ApiModelProperty(notes = "Employee Badge")
    public String EmployeeBadge;
    @ApiModelProperty(notes = "Assignment Number / Placement ID")
    public String AssignmentNumber;// [Required]//[Required]w
    @ApiModelProperty(notes = "Job Description")
    public String JobDescription;// [Required]w
    @ApiModelProperty(notes = "Clock Group")
    public String ClockGroup;// [Required]c
    @ApiModelProperty(notes = "Assignment Start Date")
    public String AssignmentStartDate;// [Required]//[Required]w
    @ApiModelProperty(notes = "Assignment End Date")
    public String AssignmentEndDate;// [Required]
    @ApiModelProperty(notes = "Assignment End Reason")
    public String AssignmentEndReason;
    @ApiModelProperty(notes = "Department Mapping")
    public String DepartmentMapping;// [Required]c
    @ApiModelProperty(notes = "Department Abbreviation")
    public String DepartmentAbbr;
    @ApiModelProperty(notes = "Client Department Code")
    public String ClientDepartmentCode;
    @ApiModelProperty(notes = "Department Name")
    public String DepartmentName;
    @ApiModelProperty(notes = "Clock Mapping")
    public String ClockMapping;
    @ApiModelProperty(notes = "Shift Code")
    public String ShiftCode;// [Required]
    @ApiModelProperty(notes = "Bill Rate")
    public String BillRate;
    @ApiModelProperty(notes = "Pay Rate")
    public String PayRate;
    @ApiModelProperty(notes = "Over time Billing Factor")
    public String OTBillingFactor;
    @ApiModelProperty(notes = "Double time Billing Factor")
    public String DTBillingFactor;
    @ApiModelProperty(notes = "Over time Pay Factor")
    public String OTPayFactor;
    @ApiModelProperty(notes = "Double time Pay Factor")
    public String DTPayFactor;
    @ApiModelProperty(notes = "Last day of the Week")
    public String LastDayOfWeek;// [Required]w
    @ApiModelProperty(notes = "Work Site ID")
    public String WorkSiteID;
    @ApiModelProperty(notes = "Work Site Name")
    public String WorkSiteName;
    @ApiModelProperty(notes = "Work Site State")
    public String WorkSiteState;
    @ApiModelProperty(notes = "Approver First Name")
    public String Approver1FirstName; // ; //
    @ApiModelProperty(notes = "Approver Last Name")
    public String Approver1LastName;
    @ApiModelProperty(notes = "Approver Email")
    public String Approver1Email;
    @ApiModelProperty(notes = "Second Approver First Name")
    public String Approver2FirstName;
    @ApiModelProperty(notes = "Second Approver Last Name")
    public String Approver2LastName;
    @ApiModelProperty(notes = "Second Approver Email")
    public String Approver2Email;
    @ApiModelProperty(notes = "Agency Code")
    public String AgencyCode;// ;//
    @ApiModelProperty(notes = "Agency Name")
    public String AgencyName;// ;//
    @ApiModelProperty(notes = "VMS Employee ID")
    public String VMSEmployeeID;
    @ApiModelProperty(notes = "VMS Assignment Number")
    public String VMSAssignmentNumber;
    @ApiModelProperty(notes = "VMS Cost Center")
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
    public String SaturdayShiftStop;

    // additional fields (OnDemand implementation)
    public String WorkSiteAddr1;
    public String WorkSiteAddr2;
    public String WorkSiteCity;
    public String WorkSiteZip;
    public String CallOff;
    public String AssignmentStatus;

    // additional fields (AMN implementation)
    public String SortOrder;
    public String OrderID;
    public String MinHours;

    // additional fields (KForce implementation)
    public String Reg_TRC_Code;
    public String OT_TRC_Code;
    public String DT_TRC_Code;
    public String UDFValues;

    public String Custom_Value1;
    public String Custom_Value2;
    public String Custom_Value3;
    public String Custom_Value4;
    public String Custom_Value5;
    public String Custom_Value6;
    public String Custom_Value7;
    public String Custom_Value8;
    public String Custom_Value9;
    public String Custom_Value10;
    public String Custom_Value11;
    public String Custom_Value12;
    public String Custom_Value13;
    public String Custom_Value14;
    public String Custom_Value15;
    public String Custom_Value16;
    public String Custom_Value17;
    public String Custom_Value_18;
    public String Custom_Value_19;
    public String Custom_Value_20;

    public TargetAssignments() {
    }

}
