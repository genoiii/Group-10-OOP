/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.text.DecimalFormat;

/**
 *
 * @author 63909
 */
public class SalaryCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
    /**
     * Calculates the basic salary based on hours worked and hourly rate.
     * @param hoursWorked
     * @param hourlyRate
     * @return 
     */
    public static double calculateBasicSalary(double hoursWorked, double hourlyRate) {
        return Double.parseDouble(decimalFormat.format(hoursWorked * hourlyRate));
    }

    /**
     * Calculates the gross salary by adding the basic salary and total allowances.
     * @param basicSalary
     * @param totalAllowance
     * @return 
     */
    public static double calculateGrossSalary(double basicSalary, double totalAllowance) {
        return Double.parseDouble(decimalFormat.format(basicSalary + totalAllowance));
    } 
    
    /**
     * Calculates the net salary by orchestrating the basic salary, allowances,
     * government contributions, and tax deductions.
     * @param grossSalary
     * @param totalDeductions
     * @param withHtax
     * @return 
     */
    public static double calculateNetSalary(double grossSalary, double totalDeductions, double withHtax) {
            return Double.parseDouble(decimalFormat.format(grossSalary - (totalDeductions + withHtax)));
    }
}
