package com.bullhorn.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings(value = "unused")
public class TargetAssignments {
    @ApiModelProperty(notes = "[Required] APIKey per client")
    private String IntegrationKey;

    @ApiModelProperty(notes = "It's a MessageID from Listener - Asssigned at runtime by service bus/ listener")
    private String MessageID;

    @ApiModelProperty(notes = "Our version of JobID - generated jobID based on a timestamp negative random num for a TRANSACTION")
    private String JobID;

    @ApiModelProperty(notes = "It's a random asssigned at run time by service bus/ listener")
    private String RecID;

    @ApiModelProperty(notes = "It's a JobDateTime from Listener @JobDateTime = DateTime.Now() DATETIME")
    private String TransDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    @ApiModelProperty(notes = "It's a client Code @Client from Listener VARCHAR(4)")
    private String Client;

    @ApiModelProperty(notes = "It's a flag @FirstTime from Listener =\"Y\" CHAR(1)")
    private String FirstTime = "Y";

    @ApiModelProperty(notes = "[Required]")
    private String Source;

    @ApiModelProperty(notes = "[Optional]")
    private String BranchName;

    @ApiModelProperty(notes = "[Required]")
    private String BranchID;// [Required]w

    @ApiModelProperty(notes = "[Required]")
    private String ClientName;// [Required]w

    @ApiModelProperty(notes = "[Required]")
    private String ClientID;// [Required]//[Required]w

    @ApiModelProperty(notes = "[Required]")
    private String EmployeeFirstName;// [Required]//[Required]w
    @ApiModelProperty(notes = "Employee Last Name")
    private String EmployeeLastName;// [Required]//[Required]w
    @ApiModelProperty(notes = "Employee Email Address")
    private String EmployeeEmailAddress;
    @ApiModelProperty(notes = "Employee ID")
    private String EmployeeID;// [Required]//[Required]w
    @ApiModelProperty(notes = "Employee Social Security Number")
    private String EmployeeSSN;// [Required]//[Required]w
    @ApiModelProperty(notes = "Employee PIN")
    private String EmployeePIN;
    @ApiModelProperty(notes = "Employee Badge")
    private String EmployeeBadge;
    @ApiModelProperty(notes = "Assignment Number / Placement ID")
    private String AssignmentNumber;// [Required]//[Required]w
    @ApiModelProperty(notes = "Job Description")
    private String JobDescription;// [Required]w
    @ApiModelProperty(notes = "Clock Group")
    private String ClockGroup;// [Required]c
    @ApiModelProperty(notes = "Assignment Start Date")
    private String AssignmentStartDate;// [Required]//[Required]w
    @ApiModelProperty(notes = "Assignment End Date")
    private String AssignmentEndDate;// [Required]
    @ApiModelProperty(notes = "Assignment End Reason")
    private String AssignmentEndReason;
    @ApiModelProperty(notes = "Department Mapping")
    private String DepartmentMapping;// [Required]c
    @ApiModelProperty(notes = "Department Abbreviation")
    private String DepartmentAbbr;
    @ApiModelProperty(notes = "Client Department Code")
    private String ClientDepartmentCode;
    @ApiModelProperty(notes = "Department Name")
    private String DepartmentName;
    @ApiModelProperty(notes = "Clock Mapping")
    private String ClockMapping;
    @ApiModelProperty(notes = "Shift Code")
    private String ShiftCode;// [Required]
    @ApiModelProperty(notes = "Bill Rate")
    private String BillRate;
    @ApiModelProperty(notes = "Pay Rate")
    private String PayRate;
    @ApiModelProperty(notes = "Over time Billing Factor")
    private String OTBillingFactor;
    @ApiModelProperty(notes = "Double time Billing Factor")
    private String DTBillingFactor;
    @ApiModelProperty(notes = "Over time Pay Factor")
    private String OTPayFactor;
    @ApiModelProperty(notes = "Double time Pay Factor")
    private String DTPayFactor;
    @ApiModelProperty(notes = "Last day of the Week")
    private String LastDayOfWeek;// [Required]w
    @ApiModelProperty(notes = "Work Site ID")
    private String WorkSiteID;
    @ApiModelProperty(notes = "Work Site Name")
    private String WorkSiteName;
    @ApiModelProperty(notes = "Work Site State")
    private String WorkSiteState;
    @ApiModelProperty(notes = "Approver First Name")
    private String Approver1FirstName; // ; //
    @ApiModelProperty(notes = "Approver Last Name")
    private String Approver1LastName;
    @ApiModelProperty(notes = "Approver Email")
    private String Approver1Email;
    @ApiModelProperty(notes = "Second Approver First Name")
    private String Approver2FirstName;
    @ApiModelProperty(notes = "Second Approver Last Name")
    private String Approver2LastName;
    @ApiModelProperty(notes = "Second Approver Email")
    private String Approver2Email;
    @ApiModelProperty(notes = "Agency Code")
    private String AgencyCode;// ;//
    @ApiModelProperty(notes = "Agency Name")
    private String AgencyName;// ;//
    @ApiModelProperty(notes = "VMS Employee ID")
    private String VMSEmployeeID;
    @ApiModelProperty(notes = "VMS Assignment Number")
    private String VMSAssignmentNumber;
    @ApiModelProperty(notes = "VMS Cost Center")
    private String VMSCostCenter;
    private String VMSBuyerID;
    private String VMSRequisitionID;
    private String EmployeeOTType;
    private String ExpenseApproverFName;
    private String ExpenseApproverLName;
    private String ExpenseApproverEmail;
    private String ExpenseApprover2FName;
    private String ExpenseApprover2LName;
    private String ExpenseApprover2Email;
    private String InOutIndicator;
    private String AlternateWorkSchedule;// ;//
    private String Rounding;
    private String Branding;
    private String PayRules;
    private String ApprovalMethod;
    private String EntryFrequency;
    private String HolidayCode;
    private String ConsultantType;
    private String ProjectIndicator;
    private String ExpenseIndicator;
    private String BranchContactPhone;
    private String EmployeeCellPhone;

    private String EmployeeWebImage;
    private String TimeCodes;
    private String ExpenseCodes;
    private String PreventWorkedTime;
    private String NoBill_ExpAprvr_FirstName;
    private String NoBill_ExpAprvr_LastName;
    private String NoBill_ExpAprvr_Email;
    private String NoBill_ExpAprvr_FirstName2;
    private String NoBill_ExpAprvr_LastName2;
    private String NoBill_ExpAprvr_Email2;

    private String NoBill_TS_Aprvr_FirstName;
    private String NoBill_TS_Aprvr_LastName;
    private String NoBill_TS_Aprvr_Email;
    private String NoBill_TS_Aprvr_FirstName2;
    private String NoBill_TS_Aprvr_LastName2;
    private String NoBill_TS_Aprvr_Email2;

    private String SalaryIndicator;
    private String EmployeeCPAFlag;
    private String ProxyCPAFlag;
    private String MobileCarrier;
    private String FoxAssignmentNo;
    private String AssignmentCostCenter;
    private String Setting;

    // additional fields (STAF implementation)
    private String WaiveBreak1;
    private String WaiveBreak2;
    private String PR_EmployeeType;
    private String PR_BreakLength;
    private String Rate_Effective_Structure;
    private String PONumber;
    private String Division;
    private String ShiftName;
    private String SundayShiftStart;
    private String SundayShiftStop;
    private String MondayShiftStart;
    private String MondayShiftStop;
    private String TuesdayShiftStart;
    private String TuesdayShiftStop;
    private String WednesdayShiftStart;
    private String WednesdayShiftStop;
    private String ThursdayShiftStart;
    private String ThursdayShiftStop;
    private String FridayShiftStart;
    private String FridayShiftStop;
    private String SaturdayShiftStart;
    private String SaturdayShiftStop;

    // additional fields (OnDemand implementation)
    private String WorkSiteAddr1;
    private String WorkSiteAddr2;
    private String WorkSiteCity;
    private String WorkSiteZip;
    private String CallOff;
    private String AssignmentStatus;

    // additional fields (AMN implementation)
    private String SortOrder;
    private String OrderID;
    private String MinHours;

    // additional fields (KForce implementation)
    private String Reg_TRC_Code;
    private String OT_TRC_Code;
    private String DT_TRC_Code;
    private String UDFValues;

    private String Custom_Value1;
    private String Custom_Value2;
    private String Custom_Value3;
    private String Custom_Value4;
    private String Custom_Value5;
    private String Custom_Value6;
    private String Custom_Value7;
    private String Custom_Value8;
    private String Custom_Value9;
    private String Custom_Value10;
    private String Custom_Value11;
    private String Custom_Value12;
    private String Custom_Value13;
    private String Custom_Value14;
    private String Custom_Value15;
    private String Custom_Value16;
    private String Custom_Value17;
    private String Custom_Value_18;
    private String Custom_Value_19;
    private String Custom_Value_20;

    public TargetAssignments() {
    }

    public TargetAssignments(String integrationKey, String messageID, String jobID, String recID, String transDateTime, String client, String firstTime, String source, String branchName, String branchID, String clientName, String clientID, String employeeFirstName, String employeeLastName, String employeeEmailAddress, String employeeID, String employeeSSN, String employeePIN, String employeeBadge, String assignmentNumber, String jobDescription, String clockGroup, String assignmentStartDate, String assignmentEndDate, String assignmentEndReason, String departmentMapping, String departmentAbbr, String clientDepartmentCode, String departmentName, String clockMapping, String shiftCode, String billRate, String payRate, String OTBillingFactor, String DTBillingFactor, String OTPayFactor, String DTPayFactor, String lastDayOfWeek, String workSiteID, String workSiteName, String workSiteState, String approver1FirstName, String approver1LastName, String approver1Email, String approver2FirstName, String approver2LastName, String approver2Email, String agencyCode, String agencyName, String VMSEmployeeID, String VMSAssignmentNumber, String VMSCostCenter, String VMSBuyerID, String VMSRequisitionID, String employeeOTType, String expenseApproverFName, String expenseApproverLName, String expenseApproverEmail, String expenseApprover2FName, String expenseApprover2LName, String expenseApprover2Email, String inOutIndicator, String alternateWorkSchedule, String rounding, String branding, String payRules, String approvalMethod, String entryFrequency, String holidayCode, String consultantType, String projectIndicator, String expenseIndicator, String branchContactPhone, String employeeCellPhone, String employeeWebImage, String timeCodes, String expenseCodes, String preventWorkedTime, String noBill_ExpAprvr_FirstName, String noBill_ExpAprvr_LastName, String noBill_ExpAprvr_Email, String noBill_ExpAprvr_FirstName2, String noBill_ExpAprvr_LastName2, String noBill_ExpAprvr_Email2, String noBill_TS_Aprvr_FirstName, String noBill_TS_Aprvr_LastName, String noBill_TS_Aprvr_Email, String noBill_TS_Aprvr_FirstName2, String noBill_TS_Aprvr_LastName2, String noBill_TS_Aprvr_Email2, String salaryIndicator, String employeeCPAFlag, String proxyCPAFlag, String mobileCarrier, String foxAssignmentNo, String assignmentCostCenter, String setting, String waiveBreak1, String waiveBreak2, String PR_EmployeeType, String PR_BreakLength, String rate_Effective_Structure, String PONumber, String division, String shiftName, String sundayShiftStart, String sundayShiftStop, String mondayShiftStart, String mondayShiftStop, String tuesdayShiftStart, String tuesdayShiftStop, String wednesdayShiftStart, String wednesdayShiftStop, String thursdayShiftStart, String thursdayShiftStop, String fridayShiftStart, String fridayShiftStop, String saturdayShiftStart, String saturdayShiftStop, String workSiteAddr1, String workSiteAddr2, String workSiteCity, String workSiteZip, String callOff, String assignmentStatus, String sortOrder, String orderID, String minHours, String reg_TRC_Code, String OT_TRC_Code, String DT_TRC_Code, String UDFValues) {
        IntegrationKey = integrationKey;
        MessageID = messageID;
        JobID = jobID;
        RecID = recID;
        TransDateTime = transDateTime;
        Client = client;
        FirstTime = firstTime;
        Source = source;
        BranchName = branchName;
        BranchID = branchID;
        ClientName = clientName;
        ClientID = clientID;
        EmployeeFirstName = employeeFirstName;
        EmployeeLastName = employeeLastName;
        EmployeeEmailAddress = employeeEmailAddress;
        EmployeeID = employeeID;
        EmployeeSSN = employeeSSN;
        EmployeePIN = employeePIN;
        EmployeeBadge = employeeBadge;
        AssignmentNumber = assignmentNumber;
        JobDescription = jobDescription;
        ClockGroup = clockGroup;
        AssignmentStartDate = assignmentStartDate;
        AssignmentEndDate = assignmentEndDate;
        AssignmentEndReason = assignmentEndReason;
        DepartmentMapping = departmentMapping;
        DepartmentAbbr = departmentAbbr;
        ClientDepartmentCode = clientDepartmentCode;
        DepartmentName = departmentName;
        ClockMapping = clockMapping;
        ShiftCode = shiftCode;
        BillRate = billRate;
        PayRate = payRate;
        this.OTBillingFactor = OTBillingFactor;
        this.DTBillingFactor = DTBillingFactor;
        this.OTPayFactor = OTPayFactor;
        this.DTPayFactor = DTPayFactor;
        LastDayOfWeek = lastDayOfWeek;
        WorkSiteID = workSiteID;
        WorkSiteName = workSiteName;
        WorkSiteState = workSiteState;
        Approver1FirstName = approver1FirstName;
        Approver1LastName = approver1LastName;
        Approver1Email = approver1Email;
        Approver2FirstName = approver2FirstName;
        Approver2LastName = approver2LastName;
        Approver2Email = approver2Email;
        AgencyCode = agencyCode;
        AgencyName = agencyName;
        this.VMSEmployeeID = VMSEmployeeID;
        this.VMSAssignmentNumber = VMSAssignmentNumber;
        this.VMSCostCenter = VMSCostCenter;
        this.VMSBuyerID = VMSBuyerID;
        this.VMSRequisitionID = VMSRequisitionID;
        EmployeeOTType = employeeOTType;
        ExpenseApproverFName = expenseApproverFName;
        ExpenseApproverLName = expenseApproverLName;
        ExpenseApproverEmail = expenseApproverEmail;
        ExpenseApprover2FName = expenseApprover2FName;
        ExpenseApprover2LName = expenseApprover2LName;
        ExpenseApprover2Email = expenseApprover2Email;
        InOutIndicator = inOutIndicator;
        AlternateWorkSchedule = alternateWorkSchedule;
        Rounding = rounding;
        Branding = branding;
        PayRules = payRules;
        ApprovalMethod = approvalMethod;
        EntryFrequency = entryFrequency;
        HolidayCode = holidayCode;
        ConsultantType = consultantType;
        ProjectIndicator = projectIndicator;
        ExpenseIndicator = expenseIndicator;
        BranchContactPhone = branchContactPhone;
        EmployeeCellPhone = employeeCellPhone;
        EmployeeWebImage = employeeWebImage;
        TimeCodes = timeCodes;
        ExpenseCodes = expenseCodes;
        PreventWorkedTime = preventWorkedTime;
        NoBill_ExpAprvr_FirstName = noBill_ExpAprvr_FirstName;
        NoBill_ExpAprvr_LastName = noBill_ExpAprvr_LastName;
        NoBill_ExpAprvr_Email = noBill_ExpAprvr_Email;
        NoBill_ExpAprvr_FirstName2 = noBill_ExpAprvr_FirstName2;
        NoBill_ExpAprvr_LastName2 = noBill_ExpAprvr_LastName2;
        NoBill_ExpAprvr_Email2 = noBill_ExpAprvr_Email2;
        NoBill_TS_Aprvr_FirstName = noBill_TS_Aprvr_FirstName;
        NoBill_TS_Aprvr_LastName = noBill_TS_Aprvr_LastName;
        NoBill_TS_Aprvr_Email = noBill_TS_Aprvr_Email;
        NoBill_TS_Aprvr_FirstName2 = noBill_TS_Aprvr_FirstName2;
        NoBill_TS_Aprvr_LastName2 = noBill_TS_Aprvr_LastName2;
        NoBill_TS_Aprvr_Email2 = noBill_TS_Aprvr_Email2;
        SalaryIndicator = salaryIndicator;
        EmployeeCPAFlag = employeeCPAFlag;
        ProxyCPAFlag = proxyCPAFlag;
        MobileCarrier = mobileCarrier;
        FoxAssignmentNo = foxAssignmentNo;
        AssignmentCostCenter = assignmentCostCenter;
        Setting = setting;
        WaiveBreak1 = waiveBreak1;
        WaiveBreak2 = waiveBreak2;
        this.PR_EmployeeType = PR_EmployeeType;
        this.PR_BreakLength = PR_BreakLength;
        Rate_Effective_Structure = rate_Effective_Structure;
        this.PONumber = PONumber;
        Division = division;
        ShiftName = shiftName;
        SundayShiftStart = sundayShiftStart;
        SundayShiftStop = sundayShiftStop;
        MondayShiftStart = mondayShiftStart;
        MondayShiftStop = mondayShiftStop;
        TuesdayShiftStart = tuesdayShiftStart;
        TuesdayShiftStop = tuesdayShiftStop;
        WednesdayShiftStart = wednesdayShiftStart;
        WednesdayShiftStop = wednesdayShiftStop;
        ThursdayShiftStart = thursdayShiftStart;
        ThursdayShiftStop = thursdayShiftStop;
        FridayShiftStart = fridayShiftStart;
        FridayShiftStop = fridayShiftStop;
        SaturdayShiftStart = saturdayShiftStart;
        SaturdayShiftStop = saturdayShiftStop;
        WorkSiteAddr1 = workSiteAddr1;
        WorkSiteAddr2 = workSiteAddr2;
        WorkSiteCity = workSiteCity;
        WorkSiteZip = workSiteZip;
        CallOff = callOff;
        AssignmentStatus = assignmentStatus;
        SortOrder = sortOrder;
        OrderID = orderID;
        MinHours = minHours;
        Reg_TRC_Code = reg_TRC_Code;
        this.OT_TRC_Code = OT_TRC_Code;
        this.DT_TRC_Code = DT_TRC_Code;
        this.UDFValues = UDFValues;
    }

    public String getIntegrationKey() {
        return IntegrationKey;
    }

    public void setIntegrationKey(String integrationKey) {
        IntegrationKey = integrationKey;
    }

    public String getMessageID() {
        return MessageID;
    }

    public void setMessageID(String messageID) {
        MessageID = messageID;
    }

    public String getJobID() {
        return JobID;
    }

    public void setJobID(String jobID) {
        JobID = jobID;
    }

    public String getRecID() {
        return RecID;
    }

    public void setRecID(String recID) {
        RecID = recID;
    }

    public String getTransDateTime() {
        return TransDateTime;
    }

    public void setTransDateTime(String transDateTime) {
        TransDateTime = transDateTime;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    public String getFirstTime() {
        return FirstTime;
    }

    public void setFirstTime(String firstTime) {
        FirstTime = firstTime;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getEmployeeFirstName() {
        return EmployeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        EmployeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return EmployeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        EmployeeLastName = employeeLastName;
    }

    public String getEmployeeEmailAddress() {
        return EmployeeEmailAddress;
    }

    public void setEmployeeEmailAddress(String employeeEmailAddress) {
        EmployeeEmailAddress = employeeEmailAddress;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getEmployeeSSN() {
        return EmployeeSSN;
    }

    public void setEmployeeSSN(String employeeSSN) {
        EmployeeSSN = employeeSSN;
    }

    public String getEmployeePIN() {
        return EmployeePIN;
    }

    public void setEmployeePIN(String employeePIN) {
        EmployeePIN = employeePIN;
    }

    public String getEmployeeBadge() {
        return EmployeeBadge;
    }

    public void setEmployeeBadge(String employeeBadge) {
        EmployeeBadge = employeeBadge;
    }

    public String getAssignmentNumber() {
        return AssignmentNumber;
    }

    public void setAssignmentNumber(String assignmentNumber) {
        AssignmentNumber = assignmentNumber;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String jobDescription) {
        JobDescription = jobDescription;
    }

    public String getClockGroup() {
        return ClockGroup;
    }

    public void setClockGroup(String clockGroup) {
        ClockGroup = clockGroup;
    }

    public String getAssignmentStartDate() {
        return AssignmentStartDate;
    }

    public void setAssignmentStartDate(String assignmentStartDate) {
        AssignmentStartDate = assignmentStartDate;
    }

    public String getAssignmentEndDate() {
        return AssignmentEndDate;
    }

    public void setAssignmentEndDate(String assignmentEndDate) {
        AssignmentEndDate = assignmentEndDate;
    }

    public String getAssignmentEndReason() {
        return AssignmentEndReason;
    }

    public void setAssignmentEndReason(String assignmentEndReason) {
        AssignmentEndReason = assignmentEndReason;
    }

    public String getDepartmentMapping() {
        return DepartmentMapping;
    }

    public void setDepartmentMapping(String departmentMapping) {
        DepartmentMapping = departmentMapping;
    }

    public String getDepartmentAbbr() {
        return DepartmentAbbr;
    }

    public void setDepartmentAbbr(String departmentAbbr) {
        DepartmentAbbr = departmentAbbr;
    }

    public String getClientDepartmentCode() {
        return ClientDepartmentCode;
    }

    public void setClientDepartmentCode(String clientDepartmentCode) {
        ClientDepartmentCode = clientDepartmentCode;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getClockMapping() {
        return ClockMapping;
    }

    public void setClockMapping(String clockMapping) {
        ClockMapping = clockMapping;
    }

    public String getShiftCode() {
        return ShiftCode;
    }

    public void setShiftCode(String shiftCode) {
        ShiftCode = shiftCode;
    }

    public String getBillRate() {
        return BillRate;
    }

    public void setBillRate(String billRate) {
        BillRate = billRate;
    }

    public String getPayRate() {
        return PayRate;
    }

    public void setPayRate(String payRate) {
        PayRate = payRate;
    }

    public String getOTBillingFactor() {
        return OTBillingFactor;
    }

    public void setOTBillingFactor(String OTBillingFactor) {
        this.OTBillingFactor = OTBillingFactor;
    }

    public String getDTBillingFactor() {
        return DTBillingFactor;
    }

    public void setDTBillingFactor(String DTBillingFactor) {
        this.DTBillingFactor = DTBillingFactor;
    }

    public String getOTPayFactor() {
        return OTPayFactor;
    }

    public void setOTPayFactor(String OTPayFactor) {
        this.OTPayFactor = OTPayFactor;
    }

    public String getDTPayFactor() {
        return DTPayFactor;
    }

    public void setDTPayFactor(String DTPayFactor) {
        this.DTPayFactor = DTPayFactor;
    }

    public String getLastDayOfWeek() {
        return LastDayOfWeek;
    }

    public void setLastDayOfWeek(String lastDayOfWeek) {
        LastDayOfWeek = lastDayOfWeek;
    }

    public String getWorkSiteID() {
        return WorkSiteID;
    }

    public void setWorkSiteID(String workSiteID) {
        WorkSiteID = workSiteID;
    }

    public String getWorkSiteName() {
        return WorkSiteName;
    }

    public void setWorkSiteName(String workSiteName) {
        WorkSiteName = workSiteName;
    }

    public String getWorkSiteState() {
        return WorkSiteState;
    }

    public void setWorkSiteState(String workSiteState) {
        WorkSiteState = workSiteState;
    }

    public String getApprover1FirstName() {
        return Approver1FirstName;
    }

    public void setApprover1FirstName(String approver1FirstName) {
        Approver1FirstName = approver1FirstName;
    }

    public String getApprover1LastName() {
        return Approver1LastName;
    }

    public void setApprover1LastName(String approver1LastName) {
        Approver1LastName = approver1LastName;
    }

    public String getApprover1Email() {
        return Approver1Email;
    }

    public void setApprover1Email(String approver1Email) {
        Approver1Email = approver1Email;
    }

    public String getApprover2FirstName() {
        return Approver2FirstName;
    }

    public void setApprover2FirstName(String approver2FirstName) {
        Approver2FirstName = approver2FirstName;
    }

    public String getApprover2LastName() {
        return Approver2LastName;
    }

    public void setApprover2LastName(String approver2LastName) {
        Approver2LastName = approver2LastName;
    }

    public String getApprover2Email() {
        return Approver2Email;
    }

    public void setApprover2Email(String approver2Email) {
        Approver2Email = approver2Email;
    }

    public String getAgencyCode() {
        return AgencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        AgencyCode = agencyCode;
    }

    public String getAgencyName() {
        return AgencyName;
    }

    public void setAgencyName(String agencyName) {
        AgencyName = agencyName;
    }

    public String getVMSEmployeeID() {
        return VMSEmployeeID;
    }

    public void setVMSEmployeeID(String VMSEmployeeID) {
        this.VMSEmployeeID = VMSEmployeeID;
    }

    public String getVMSAssignmentNumber() {
        return VMSAssignmentNumber;
    }

    public void setVMSAssignmentNumber(String VMSAssignmentNumber) {
        this.VMSAssignmentNumber = VMSAssignmentNumber;
    }

    public String getVMSCostCenter() {
        return VMSCostCenter;
    }

    public void setVMSCostCenter(String VMSCostCenter) {
        this.VMSCostCenter = VMSCostCenter;
    }

    public String getVMSBuyerID() {
        return VMSBuyerID;
    }

    public void setVMSBuyerID(String VMSBuyerID) {
        this.VMSBuyerID = VMSBuyerID;
    }

    public String getVMSRequisitionID() {
        return VMSRequisitionID;
    }

    public void setVMSRequisitionID(String VMSRequisitionID) {
        this.VMSRequisitionID = VMSRequisitionID;
    }

    public String getEmployeeOTType() {
        return EmployeeOTType;
    }

    public void setEmployeeOTType(String employeeOTType) {
        EmployeeOTType = employeeOTType;
    }

    public String getExpenseApproverFName() {
        return ExpenseApproverFName;
    }

    public void setExpenseApproverFName(String expenseApproverFName) {
        ExpenseApproverFName = expenseApproverFName;
    }

    public String getExpenseApproverLName() {
        return ExpenseApproverLName;
    }

    public void setExpenseApproverLName(String expenseApproverLName) {
        ExpenseApproverLName = expenseApproverLName;
    }

    public String getExpenseApproverEmail() {
        return ExpenseApproverEmail;
    }

    public void setExpenseApproverEmail(String expenseApproverEmail) {
        ExpenseApproverEmail = expenseApproverEmail;
    }

    public String getExpenseApprover2FName() {
        return ExpenseApprover2FName;
    }

    public void setExpenseApprover2FName(String expenseApprover2FName) {
        ExpenseApprover2FName = expenseApprover2FName;
    }

    public String getExpenseApprover2LName() {
        return ExpenseApprover2LName;
    }

    public void setExpenseApprover2LName(String expenseApprover2LName) {
        ExpenseApprover2LName = expenseApprover2LName;
    }

    public String getExpenseApprover2Email() {
        return ExpenseApprover2Email;
    }

    public void setExpenseApprover2Email(String expenseApprover2Email) {
        ExpenseApprover2Email = expenseApprover2Email;
    }

    public String getInOutIndicator() {
        return InOutIndicator;
    }

    public void setInOutIndicator(String inOutIndicator) {
        InOutIndicator = inOutIndicator;
    }

    public String getAlternateWorkSchedule() {
        return AlternateWorkSchedule;
    }

    public void setAlternateWorkSchedule(String alternateWorkSchedule) {
        AlternateWorkSchedule = alternateWorkSchedule;
    }

    public String getRounding() {
        return Rounding;
    }

    public void setRounding(String rounding) {
        Rounding = rounding;
    }

    public String getBranding() {
        return Branding;
    }

    public void setBranding(String branding) {
        Branding = branding;
    }

    public String getPayRules() {
        return PayRules;
    }

    public void setPayRules(String payRules) {
        PayRules = payRules;
    }

    public String getApprovalMethod() {
        return ApprovalMethod;
    }

    public void setApprovalMethod(String approvalMethod) {
        ApprovalMethod = approvalMethod;
    }

    public String getEntryFrequency() {
        return EntryFrequency;
    }

    public void setEntryFrequency(String entryFrequency) {
        EntryFrequency = entryFrequency;
    }

    public String getHolidayCode() {
        return HolidayCode;
    }

    public void setHolidayCode(String holidayCode) {
        HolidayCode = holidayCode;
    }

    public String getConsultantType() {
        return ConsultantType;
    }

    public void setConsultantType(String consultantType) {
        ConsultantType = consultantType;
    }

    public String getProjectIndicator() {
        return ProjectIndicator;
    }

    public void setProjectIndicator(String projectIndicator) {
        ProjectIndicator = projectIndicator;
    }

    public String getExpenseIndicator() {
        return ExpenseIndicator;
    }

    public void setExpenseIndicator(String expenseIndicator) {
        ExpenseIndicator = expenseIndicator;
    }

    public String getBranchContactPhone() {
        return BranchContactPhone;
    }

    public void setBranchContactPhone(String branchContactPhone) {
        BranchContactPhone = branchContactPhone;
    }

    public String getEmployeeCellPhone() {
        return EmployeeCellPhone;
    }

    public void setEmployeeCellPhone(String employeeCellPhone) {
        EmployeeCellPhone = employeeCellPhone;
    }

    public String getEmployeeWebImage() {
        return EmployeeWebImage;
    }

    public void setEmployeeWebImage(String employeeWebImage) {
        EmployeeWebImage = employeeWebImage;
    }

    public String getTimeCodes() {
        return TimeCodes;
    }

    public void setTimeCodes(String timeCodes) {
        TimeCodes = timeCodes;
    }

    public String getExpenseCodes() {
        return ExpenseCodes;
    }

    public void setExpenseCodes(String expenseCodes) {
        ExpenseCodes = expenseCodes;
    }

    public String getPreventWorkedTime() {
        return PreventWorkedTime;
    }

    public void setPreventWorkedTime(String preventWorkedTime) {
        PreventWorkedTime = preventWorkedTime;
    }

    public String getNoBill_ExpAprvr_FirstName() {
        return NoBill_ExpAprvr_FirstName;
    }

    public void setNoBill_ExpAprvr_FirstName(String noBill_ExpAprvr_FirstName) {
        NoBill_ExpAprvr_FirstName = noBill_ExpAprvr_FirstName;
    }

    public String getNoBill_ExpAprvr_LastName() {
        return NoBill_ExpAprvr_LastName;
    }

    public void setNoBill_ExpAprvr_LastName(String noBill_ExpAprvr_LastName) {
        NoBill_ExpAprvr_LastName = noBill_ExpAprvr_LastName;
    }

    public String getNoBill_ExpAprvr_Email() {
        return NoBill_ExpAprvr_Email;
    }

    public void setNoBill_ExpAprvr_Email(String noBill_ExpAprvr_Email) {
        NoBill_ExpAprvr_Email = noBill_ExpAprvr_Email;
    }

    public String getNoBill_ExpAprvr_FirstName2() {
        return NoBill_ExpAprvr_FirstName2;
    }

    public void setNoBill_ExpAprvr_FirstName2(String noBill_ExpAprvr_FirstName2) {
        NoBill_ExpAprvr_FirstName2 = noBill_ExpAprvr_FirstName2;
    }

    public String getNoBill_ExpAprvr_LastName2() {
        return NoBill_ExpAprvr_LastName2;
    }

    public void setNoBill_ExpAprvr_LastName2(String noBill_ExpAprvr_LastName2) {
        NoBill_ExpAprvr_LastName2 = noBill_ExpAprvr_LastName2;
    }

    public String getNoBill_ExpAprvr_Email2() {
        return NoBill_ExpAprvr_Email2;
    }

    public void setNoBill_ExpAprvr_Email2(String noBill_ExpAprvr_Email2) {
        NoBill_ExpAprvr_Email2 = noBill_ExpAprvr_Email2;
    }

    public String getNoBill_TS_Aprvr_FirstName() {
        return NoBill_TS_Aprvr_FirstName;
    }

    public void setNoBill_TS_Aprvr_FirstName(String noBill_TS_Aprvr_FirstName) {
        NoBill_TS_Aprvr_FirstName = noBill_TS_Aprvr_FirstName;
    }

    public String getNoBill_TS_Aprvr_LastName() {
        return NoBill_TS_Aprvr_LastName;
    }

    public void setNoBill_TS_Aprvr_LastName(String noBill_TS_Aprvr_LastName) {
        NoBill_TS_Aprvr_LastName = noBill_TS_Aprvr_LastName;
    }

    public String getNoBill_TS_Aprvr_Email() {
        return NoBill_TS_Aprvr_Email;
    }

    public void setNoBill_TS_Aprvr_Email(String noBill_TS_Aprvr_Email) {
        NoBill_TS_Aprvr_Email = noBill_TS_Aprvr_Email;
    }

    public String getNoBill_TS_Aprvr_FirstName2() {
        return NoBill_TS_Aprvr_FirstName2;
    }

    public void setNoBill_TS_Aprvr_FirstName2(String noBill_TS_Aprvr_FirstName2) {
        NoBill_TS_Aprvr_FirstName2 = noBill_TS_Aprvr_FirstName2;
    }

    public String getNoBill_TS_Aprvr_LastName2() {
        return NoBill_TS_Aprvr_LastName2;
    }

    public void setNoBill_TS_Aprvr_LastName2(String noBill_TS_Aprvr_LastName2) {
        NoBill_TS_Aprvr_LastName2 = noBill_TS_Aprvr_LastName2;
    }

    public String getNoBill_TS_Aprvr_Email2() {
        return NoBill_TS_Aprvr_Email2;
    }

    public void setNoBill_TS_Aprvr_Email2(String noBill_TS_Aprvr_Email2) {
        NoBill_TS_Aprvr_Email2 = noBill_TS_Aprvr_Email2;
    }

    public String getSalaryIndicator() {
        return SalaryIndicator;
    }

    public void setSalaryIndicator(String salaryIndicator) {
        SalaryIndicator = salaryIndicator;
    }

    public String getEmployeeCPAFlag() {
        return EmployeeCPAFlag;
    }

    public void setEmployeeCPAFlag(String employeeCPAFlag) {
        EmployeeCPAFlag = employeeCPAFlag;
    }

    public String getProxyCPAFlag() {
        return ProxyCPAFlag;
    }

    public void setProxyCPAFlag(String proxyCPAFlag) {
        ProxyCPAFlag = proxyCPAFlag;
    }

    public String getMobileCarrier() {
        return MobileCarrier;
    }

    public void setMobileCarrier(String mobileCarrier) {
        MobileCarrier = mobileCarrier;
    }

    public String getFoxAssignmentNo() {
        return FoxAssignmentNo;
    }

    public void setFoxAssignmentNo(String foxAssignmentNo) {
        FoxAssignmentNo = foxAssignmentNo;
    }

    public String getAssignmentCostCenter() {
        return AssignmentCostCenter;
    }

    public void setAssignmentCostCenter(String assignmentCostCenter) {
        AssignmentCostCenter = assignmentCostCenter;
    }

    public String getSetting() {
        return Setting;
    }

    public void setSetting(String setting) {
        Setting = setting;
    }

    public String getWaiveBreak1() {
        return WaiveBreak1;
    }

    public void setWaiveBreak1(String waiveBreak1) {
        WaiveBreak1 = waiveBreak1;
    }

    public String getWaiveBreak2() {
        return WaiveBreak2;
    }

    public void setWaiveBreak2(String waiveBreak2) {
        WaiveBreak2 = waiveBreak2;
    }

    public String getPR_EmployeeType() {
        return PR_EmployeeType;
    }

    public void setPR_EmployeeType(String PR_EmployeeType) {
        this.PR_EmployeeType = PR_EmployeeType;
    }

    public String getPR_BreakLength() {
        return PR_BreakLength;
    }

    public void setPR_BreakLength(String PR_BreakLength) {
        this.PR_BreakLength = PR_BreakLength;
    }

    public String getRate_Effective_Structure() {
        return Rate_Effective_Structure;
    }

    public void setRate_Effective_Structure(String rate_Effective_Structure) {
        Rate_Effective_Structure = rate_Effective_Structure;
    }

    public String getPONumber() {
        return PONumber;
    }

    public void setPONumber(String PONumber) {
        this.PONumber = PONumber;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public String getShiftName() {
        return ShiftName;
    }

    public void setShiftName(String shiftName) {
        ShiftName = shiftName;
    }

    public String getSundayShiftStart() {
        return SundayShiftStart;
    }

    public void setSundayShiftStart(String sundayShiftStart) {
        SundayShiftStart = sundayShiftStart;
    }

    public String getSundayShiftStop() {
        return SundayShiftStop;
    }

    public void setSundayShiftStop(String sundayShiftStop) {
        SundayShiftStop = sundayShiftStop;
    }

    public String getMondayShiftStart() {
        return MondayShiftStart;
    }

    public void setMondayShiftStart(String mondayShiftStart) {
        MondayShiftStart = mondayShiftStart;
    }

    public String getMondayShiftStop() {
        return MondayShiftStop;
    }

    public void setMondayShiftStop(String mondayShiftStop) {
        MondayShiftStop = mondayShiftStop;
    }

    public String getTuesdayShiftStart() {
        return TuesdayShiftStart;
    }

    public void setTuesdayShiftStart(String tuesdayShiftStart) {
        TuesdayShiftStart = tuesdayShiftStart;
    }

    public String getTuesdayShiftStop() {
        return TuesdayShiftStop;
    }

    public void setTuesdayShiftStop(String tuesdayShiftStop) {
        TuesdayShiftStop = tuesdayShiftStop;
    }

    public String getWednesdayShiftStart() {
        return WednesdayShiftStart;
    }

    public void setWednesdayShiftStart(String wednesdayShiftStart) {
        WednesdayShiftStart = wednesdayShiftStart;
    }

    public String getWednesdayShiftStop() {
        return WednesdayShiftStop;
    }

    public void setWednesdayShiftStop(String wednesdayShiftStop) {
        WednesdayShiftStop = wednesdayShiftStop;
    }

    public String getThursdayShiftStart() {
        return ThursdayShiftStart;
    }

    public void setThursdayShiftStart(String thursdayShiftStart) {
        ThursdayShiftStart = thursdayShiftStart;
    }

    public String getThursdayShiftStop() {
        return ThursdayShiftStop;
    }

    public void setThursdayShiftStop(String thursdayShiftStop) {
        ThursdayShiftStop = thursdayShiftStop;
    }

    public String getFridayShiftStart() {
        return FridayShiftStart;
    }

    public void setFridayShiftStart(String fridayShiftStart) {
        FridayShiftStart = fridayShiftStart;
    }

    public String getFridayShiftStop() {
        return FridayShiftStop;
    }

    public void setFridayShiftStop(String fridayShiftStop) {
        FridayShiftStop = fridayShiftStop;
    }

    public String getSaturdayShiftStart() {
        return SaturdayShiftStart;
    }

    public void setSaturdayShiftStart(String saturdayShiftStart) {
        SaturdayShiftStart = saturdayShiftStart;
    }

    public String getSaturdayShiftStop() {
        return SaturdayShiftStop;
    }

    public void setSaturdayShiftStop(String saturdayShiftStop) {
        SaturdayShiftStop = saturdayShiftStop;
    }

    public String getWorkSiteAddr1() {
        return WorkSiteAddr1;
    }

    public void setWorkSiteAddr1(String workSiteAddr1) {
        WorkSiteAddr1 = workSiteAddr1;
    }

    public String getWorkSiteAddr2() {
        return WorkSiteAddr2;
    }

    public void setWorkSiteAddr2(String workSiteAddr2) {
        WorkSiteAddr2 = workSiteAddr2;
    }

    public String getWorkSiteCity() {
        return WorkSiteCity;
    }

    public void setWorkSiteCity(String workSiteCity) {
        WorkSiteCity = workSiteCity;
    }

    public String getWorkSiteZip() {
        return WorkSiteZip;
    }

    public void setWorkSiteZip(String workSiteZip) {
        WorkSiteZip = workSiteZip;
    }

    public String getCallOff() {
        return CallOff;
    }

    public void setCallOff(String callOff) {
        CallOff = callOff;
    }

    public String getAssignmentStatus() {
        return AssignmentStatus;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        AssignmentStatus = assignmentStatus;
    }

    public String getSortOrder() {
        return SortOrder;
    }

    public void setSortOrder(String sortOrder) {
        SortOrder = sortOrder;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getMinHours() {
        return MinHours;
    }

    public void setMinHours(String minHours) {
        MinHours = minHours;
    }

    public String getReg_TRC_Code() {
        return Reg_TRC_Code;
    }

    public void setReg_TRC_Code(String reg_TRC_Code) {
        Reg_TRC_Code = reg_TRC_Code;
    }

    public String getOT_TRC_Code() {
        return OT_TRC_Code;
    }

    public void setOT_TRC_Code(String OT_TRC_Code) {
        this.OT_TRC_Code = OT_TRC_Code;
    }

    public String getDT_TRC_Code() {
        return DT_TRC_Code;
    }

    public void setDT_TRC_Code(String DT_TRC_Code) {
        this.DT_TRC_Code = DT_TRC_Code;
    }

    public String getUDFValues() {
        return UDFValues;
    }

    public void setUDFValues(String UDFValues) {
        this.UDFValues = UDFValues;
    }

    public String getCustom_Value1() {
        return Custom_Value1;
    }

    public void setCustom_Value1(String custom_Value1) {
        Custom_Value1 = custom_Value1;
    }

    public String getCustom_Value2() {
        return Custom_Value2;
    }

    public void setCustom_Value2(String custom_Value2) {
        Custom_Value2 = custom_Value2;
    }

    public String getCustom_Value3() {
        return Custom_Value3;
    }

    public void setCustom_Value3(String custom_Value3) {
        Custom_Value3 = custom_Value3;
    }

    public String getCustom_Value4() {
        return Custom_Value4;
    }

    public void setCustom_Value4(String custom_Value4) {
        Custom_Value4 = custom_Value4;
    }

    public String getCustom_Value5() {
        return Custom_Value5;
    }

    public void setCustom_Value5(String custom_Value5) {
        Custom_Value5 = custom_Value5;
    }

    public String getCustom_Value6() {
        return Custom_Value6;
    }

    public void setCustom_Value6(String custom_Value6) {
        Custom_Value6 = custom_Value6;
    }

    public String getCustom_Value7() {
        return Custom_Value7;
    }

    public void setCustom_Value7(String custom_Value7) {
        Custom_Value7 = custom_Value7;
    }

    public String getCustom_Value8() {
        return Custom_Value8;
    }

    public void setCustom_Value8(String custom_Value8) {
        Custom_Value8 = custom_Value8;
    }

    public String getCustom_Value9() {
        return Custom_Value9;
    }

    public void setCustom_Value9(String custom_Value9) {
        Custom_Value9 = custom_Value9;
    }

    public String getCustom_Value10() {
        return Custom_Value10;
    }

    public void setCustom_Value10(String custom_Value10) {
        Custom_Value10 = custom_Value10;
    }

    public String getCustom_Value11() {
        return Custom_Value11;
    }

    public void setCustom_Value11(String custom_Value11) {
        Custom_Value11 = custom_Value11;
    }

    public String getCustom_Value12() {
        return Custom_Value12;
    }

    public void setCustom_Value12(String custom_Value12) {
        Custom_Value12 = custom_Value12;
    }

    public String getCustom_Value13() {
        return Custom_Value13;
    }

    public void setCustom_Value13(String custom_Value13) {
        Custom_Value13 = custom_Value13;
    }

    public String getCustom_Value14() {
        return Custom_Value14;
    }

    public void setCustom_Value14(String custom_Value14) {
        Custom_Value14 = custom_Value14;
    }

    public String getCustom_Value15() {
        return Custom_Value15;
    }

    public void setCustom_Value15(String custom_Value15) {
        Custom_Value15 = custom_Value15;
    }

    public String getCustom_Value16() {
        return Custom_Value16;
    }

    public void setCustom_Value16(String custom_Value16) {
        Custom_Value16 = custom_Value16;
    }

    public String getCustom_Value17() {
        return Custom_Value17;
    }

    public void setCustom_Value17(String custom_Value17) {
        Custom_Value17 = custom_Value17;
    }

    public String getCustom_Value_18() {
        return Custom_Value_18;
    }

    public void setCustom_Value_18(String custom_Value_18) {
        Custom_Value_18 = custom_Value_18;
    }

    public String getCustom_Value_19() {
        return Custom_Value_19;
    }

    public void setCustom_Value_19(String custom_Value_19) {
        Custom_Value_19 = custom_Value_19;
    }

    public String getCustom_Value_20() {
        return Custom_Value_20;
    }

    public void setCustom_Value_20(String custom_Value_20) {
        Custom_Value_20 = custom_Value_20;
    }
}
