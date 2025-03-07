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
public class PayrollSummary {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private double totalBasicSalary;
    private double totalGrossSalary;
    private double totalGovernmentContribution;
    private double totalWithholdingTax;
    private double totalDeduction;
    private double totalNetSalary;

    public PayrollSummary(double totalBasicSalary, double totalGrossSalary,
                          double totalGovernmentContribution, double totalWithholdingTax,
                          double totalDeduction, double totalNetSalary) {
        this.totalBasicSalary = Double.parseDouble(decimalFormat.format(totalBasicSalary));
        this.totalGrossSalary = Double.parseDouble(decimalFormat.format(totalGrossSalary));
        this.totalGovernmentContribution = Double.parseDouble(decimalFormat.format(totalGovernmentContribution));
        this.totalWithholdingTax = Double.parseDouble(decimalFormat.format(totalWithholdingTax));
        this.totalDeduction = Double.parseDouble(decimalFormat.format(totalDeduction));
        this.totalNetSalary = Double.parseDouble(decimalFormat.format(totalNetSalary));
    }

    // Getters for each field
    public double getTotalBasicSalary() { return totalBasicSalary; }
    public double getTotalGrossSalary() { return totalGrossSalary; }
    public double getTotalGovernmentContribution() { return totalGovernmentContribution; }
    public double getTotalWithholdingTax() { return totalWithholdingTax; }
    public double getTotalDeductions() { return totalDeduction; }
    public double getTotalNetSalary() { return totalNetSalary; }

    @Override
    public String toString() {
        return "PayrollSummary{" +
                "totalBasicSalary=" + totalBasicSalary +
                ", totalGrossSalary=" + totalGrossSalary +
                ", totalGovernmentContribution=" + totalGovernmentContribution +
                ", totalWithholdingTax=" + totalWithholdingTax +
                ", totalDeduction=" + totalDeduction +
                ", totalNetSalary=" + totalNetSalary +
                '}';
    }
}

