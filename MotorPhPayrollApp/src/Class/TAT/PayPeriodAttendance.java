/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import Class.EMS.Employee;
import Class.PPS.PayPeriod;
import java.util.Arrays;

/**
 *
 * @author 63909
 */
public class PayPeriodAttendance {
    private String payPeriodAttendanceID;
    private Employee employeeID;
    private PayPeriod payperiodID;
    private double totalWorkedHours;
    private double totalLateHours;
    private double totalApprovedOvertimeHours;
    private double totalPaidLeaveHours;
    private double payableHours;
    private String status;
    private Employee approvedBy;

    public PayPeriodAttendance() {
    }
    
    public PayPeriodAttendance(String payPeriodAttendanceID) {
        this.payPeriodAttendanceID = payPeriodAttendanceID;
    }
    
    public PayPeriodAttendance(String[] data) {
        if (data == null || data.length < 11) {
            throw new IllegalArgumentException("Invalid pay period attendance data provided: " + Arrays.toString(data));
        }
        
        try {
            this.payPeriodAttendanceID = data[0];
            this.employeeID = new Employee(data[1]);
            
            // Create a PayPeriod from start and end date strings.
            // This assumes you have a PayPeriod constructor that accepts two date strings.
            this.payperiodID = new PayPeriod(data[2], data[3]);
            
            // Parse numeric values
            this.totalWorkedHours = Double.parseDouble(data[4]);
            this.totalLateHours = Double.parseDouble(data[5]);
            this.totalApprovedOvertimeHours = Double.parseDouble(data[6]);
            this.totalPaidLeaveHours = Double.parseDouble(data[7]);
            this.payableHours = Double.parseDouble(data[8]);
            
            this.status = data[9];
            
            // approvedBy may be empty
            if (data[10] != null && !data[10].trim().isEmpty()) {
                this.approvedBy = new Employee(data[10]);
            } else {
                this.approvedBy = null;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing pay period attendance data: " + Arrays.toString(data), e);
        }
    }
    
    public String getPayPeriodAttendanceID() {
        return payPeriodAttendanceID;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public PayPeriod getStartDate() {
        return payperiodID;
    }

    public double getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public double getTotalLateHours() {
        return totalLateHours;
    }

    public double getTotalApprovedOvertimeHours() {
        return totalApprovedOvertimeHours;
    }

    public double getTotalPaidLeaveHours() {
        return totalPaidLeaveHours;
    }

    public double getPayableHours() {
        return payableHours;
    }

    public String getStatus() {
        return status;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }  
    
}
