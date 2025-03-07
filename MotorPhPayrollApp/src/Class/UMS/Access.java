/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.UMS;

import Class.EMS.Employee;
import Class.PPS.*;
import Frame.*;
import Frame.NonAdmin.*;
import java.text.ParseException;
import javax.swing.JFrame;

/**
 *
 * @author 63909
 */
public class Access {
    // Access method for Admin to open the User Dashboard
    public static JFrame accessCompanyHomePage(Admin admin) {
        CompanyHomePage companyHomePage = new CompanyHomePage(admin);
        companyHomePage.setVisible(true);
        return companyHomePage;
    }

    // Access method for Admin
    public static JFrame accessEmployeeInformation(Admin admin) {
        EmployeeInformation employeeInformation = new EmployeeInformation(admin);
        employeeInformation.setVisible(true);
        return employeeInformation;
    }
    
    // Access method for Admin
    public static JFrame accessDTR(Admin admin, String employeeID, PayPeriod selectedPayPeriod) {
        AttendanceDailyRecord dailiyAttendanceRecord = new AttendanceDailyRecord(admin, employeeID, selectedPayPeriod);
        dailiyAttendanceRecord.setVisible(true);
        return dailiyAttendanceRecord;
    }
    
    // Access method for Admin
    public static JFrame accessDTR(Admin admin, Employee employee) {
        AttendanceDailyRecord dailiyAttendanceRecord = new AttendanceDailyRecord(admin, employee);
        dailiyAttendanceRecord.setVisible(true);
        return dailiyAttendanceRecord;
    }
    
    // Access method for Admin
    public static JFrame accessDTR(Employee employee) {
        AttendanceDailyRecord dailiyAttendanceRecord = new AttendanceDailyRecord(employee);
        dailiyAttendanceRecord.setVisible(true);
        return dailiyAttendanceRecord;
    }
    
    // Access method for Admin
    public static JFrame accessAttendanceBiweekly(Admin admin) {
        AttendanceBiweekly attendanceBiweeklyRecord = new AttendanceBiweekly(admin);
        attendanceBiweeklyRecord.setVisible(true);
        return attendanceBiweeklyRecord;
    }

    // Access method for all type Users    
    public static JFrame accessViewEmployeeDetails(User user, String employeeID) {
        ViewEmployeeDetails employeeDetails = new ViewEmployeeDetails(user, employeeID);
        employeeDetails.setVisible(true);
        return employeeDetails;
    }
    
    // Access method for all type Users    
    public static JFrame accessViewEmployeeDetails(User user) {
        ViewEmployeeDetails employeeDetails = new ViewEmployeeDetails((Admin) user);
        employeeDetails.setVisible(true);
        return employeeDetails;
    }
    
    public static JFrame accessProfilePage(User user) {
        ProfilePage profilePage = new ProfilePage(user);
        profilePage.setVisible(true);
        return profilePage;
    }
    
    public static JFrame accessEmployeePayslip(User user) {
        EmployeePayslip employeePayslip = new EmployeePayslip(user);
        employeePayslip.setVisible(true);
        return employeePayslip;
    }
    
    public static JFrame accessEmployeeAttendance(User user) {
        EmployeeAttendance employeeAttendance = new EmployeeAttendance(user);
        employeeAttendance.setVisible(true);
        return employeeAttendance;
    }
    
    public static JFrame accessPayrollList(Admin admin) {
        PayrollList payrollList = new PayrollList(admin);
        payrollList.setVisible(true);
        return payrollList;
    }
    
    public static JFrame accessPayrollEmployeeSelection(Admin admin, PayPeriod payPeriod) {
        PayrollEmployeeSelection payrollEmployeeSelection = new PayrollEmployeeSelection(admin, payPeriod);
        payrollEmployeeSelection.setVisible(true);
        return payrollEmployeeSelection;
    }
    
//    public static JFrame accessPayrollEmployeeSelection(PayPeriod payPeriod) {
//        PayrollEmployeeSelection payrollEmployeeSelection = new PayrollEmployeeSelection(payPeriod);
//        payrollEmployeeSelection.setVisible(true);
//        return payrollEmployeeSelection;
//    }
    
    public static JFrame accessPayrollEarnings(Admin admin, PayPeriod payPeriod) {
        PayrollEarnings payrollEmployeeEarning = new PayrollEarnings(admin, payPeriod);
        payrollEmployeeEarning.setVisible(true);
        return payrollEmployeeEarning;
    }
    
    public static JFrame accessPayrollEarnings(PayPeriod payPeriod) {
        PayrollEarnings payrollEmployeeEarning = new PayrollEarnings(payPeriod);
        payrollEmployeeEarning.setVisible(true);
        return payrollEmployeeEarning;
    }
    
    public static JFrame accessPayrollDeductions(Admin admin, PayPeriod payPeriod) {
        PayrollDeductions payrollEmployeeDeduction= new PayrollDeductions(admin, payPeriod);
        payrollEmployeeDeduction.setVisible(true);
        return payrollEmployeeDeduction;
    }
    
    public static JFrame accessPayrollDeductions(PayPeriod payPeriod) {
        PayrollDeductions payrollEmployeeDeduction = new PayrollDeductions(payPeriod);
        payrollEmployeeDeduction.setVisible(true);
        return payrollEmployeeDeduction;
    }
    
    public static JFrame accessPayrollNetPay(Admin admin, PayPeriod payPeriod) {
        PayrollNetPay payrollEmployeeNetPay= new PayrollNetPay(admin, payPeriod);
        payrollEmployeeNetPay.setVisible(true);
        return payrollEmployeeNetPay;
    }
    
    public static JFrame accessPayrollNetPay(PayPeriod payPeriod) {
        PayrollNetPay payrollEmployeeNetPay = new PayrollNetPay(payPeriod);
        payrollEmployeeNetPay.setVisible(true);
        return payrollEmployeeNetPay;
    }
    
    public static JFrame accessPayrollSummaryPage(Admin admin, PayPeriod payPeriod) {
        PayrollSummaryPage payrollSummaryPage = new PayrollSummaryPage(admin, payPeriod);
        payrollSummaryPage.setVisible(true);
        return payrollSummaryPage;
    }
    
    public static JFrame accessPayrollSummaryPage(PayPeriod payPeriod) {
        PayrollSummaryPage payrollSummaryPage = new PayrollSummaryPage(payPeriod);
        payrollSummaryPage.setVisible(true);
        return payrollSummaryPage;
    }
}
