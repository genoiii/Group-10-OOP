/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 *
 * @author 63909
 */
public class GovernmentInformation implements Information {
    private Employee employeeID; 
    private String sssNumber; 
    private String philhealthNumber;
    private String pagibigNumber;
    private String taxIdentificationNumber;
    private String withholdingTaxStatus;

    public GovernmentInformation(Employee employeeID, String sssNumber, String philhealthNumber, String pagibigNumber, String taxIdentificationNumber, String withholdingTaxStatus) {
        this.employeeID = employeeID;
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.pagibigNumber = pagibigNumber;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.withholdingTaxStatus = withholdingTaxStatus;
    }

    @Override
    public String getInformation() {
        return "Employee ID: " + employeeID + ", SSS: " + sssNumber + ", TIN: " + taxIdentificationNumber;
    }
    
    @Override
    public void setInformation(String info) {
        this.sssNumber = info;
    }
}
