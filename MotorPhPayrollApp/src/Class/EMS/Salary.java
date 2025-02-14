/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 *
 * @author 63909
 */
public class Salary {
    private String salaryID;
    private String salaryName;
    private double amount;

    public Salary(String salaryID, String salaryName, double amount) {
        this.salaryID = salaryID;
        this.salaryName = salaryName;
        this.amount = amount;
    }

    public String getSalaryID() {
        return salaryID;
    }

    public String getSalaryName() {
        return salaryName;
    }

    public double getAmount() {
        return amount;
    }
    
    
    
}
