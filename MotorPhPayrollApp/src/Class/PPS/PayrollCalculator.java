/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author 63909
 */
public class PayrollCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
    /**
     * Calculates payroll details for a single employee and returns a PayrollRecord.
     * @param hoursWorked
     * @param hourlyRate
     * @param rice
     * @param phone
     * @param clothing
     * @return 
     */
    public static PayrollRecord calculatePayrollRecord(double hoursWorked, double hourlyRate, double rice, double phone, double clothing) {
        double basicSalary = SalaryCalculator.calculateBasicSalary(hoursWorked, hourlyRate);
        double totalAllowance = AllowanceCalculator.calculateTotalAllowance(rice, phone, clothing);
        double grossSalary = SalaryCalculator.calculateGrossSalary(basicSalary, totalAllowance);

        double sss = DeductionCalculator.calculateSSS(grossSalary);
        double philHealth = DeductionCalculator.calculatePhilHealth(grossSalary);
        double pagIbig = DeductionCalculator.calculatePagIbig(grossSalary);
        double governmentContribution = DeductionCalculator.calculateGovernmentContribution(sss, philHealth, pagIbig);

        double withHoldingTax = TaxCalculator.calculateWithHoldingTax(grossSalary, governmentContribution);
        double totalDeductions = DeductionCalculator.calculateTotalDeductions(governmentContribution, withHoldingTax);
        double netSalary = grossSalary - totalDeductions;

        return new PayrollRecord(basicSalary, grossSalary, governmentContribution, withHoldingTax, totalDeductions, netSalary);
    }
    
    /**
     * Aggregates payroll records for a pay period and returns a PayrollSummary.
     * @param records
     * @return 
     */
    public static PayrollSummary calculatePayrollSummary(List<PayrollRecord> records) {
        double totalBasicSalary = 0;
        double totalGrossSalary = 0;
        double totalGovernmentContribution = 0;
        double totalWithholdingTax = 0;
        double totalDeductions = 0;
        double totalNetSalary = 0;

        for (PayrollRecord record : records) {
            totalBasicSalary += record.getBasicSalary();
            totalGrossSalary += record.getGrossSalary();
            totalGovernmentContribution += record.getGovernmentContribution();
            totalWithholdingTax += record.getWithHoldingTax();
            totalDeductions += record.getTotalDeduction();
            totalNetSalary += record.getNetSalary();
        }

        return new PayrollSummary(totalBasicSalary, totalGrossSalary,
                totalGovernmentContribution, totalWithholdingTax,
                totalDeductions, totalNetSalary);
    }
}
