/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents government-related information for an employee.
 *
 * <p>This class extends Information and holds details such as the SSS number, PhilHealth number,
 * Pag-IBIG number, and Tax Identification Number.</p>
 */
public class GovernmentInformation extends Information {
    private String sssNumber; 
    private String philhealthNumber;
    private String pagibigNumber;
    private String taxIdentificationNumber;
//    private String withholdingTaxStatus;

    /**
     * Constructs a GovernmentInformation object using the provided government data.
     *
     * <p>This constructor initializes government-related details for an employee based on an array of strings.
     * The first element (employeeID) is handled by the superclass. The array must have at least 5 elements
     * corresponding to SSS number, PhilHealth number, Pag-IBIG number, and Tax Identification Number.
     * Note: The error message mentions 6 elements, which might need to be adjusted.</p>
     *
     * @param employeeID the unique identifier of the employee.
     * @param governmentData an array of strings representing government information.
     * @throws IllegalArgumentException if the governmentData array has fewer than 5 elements.
     */
    public GovernmentInformation(String employeeID, String[] governmentData) {
        super(employeeID); // Initialize the superclass with the employee ID.
        
        // Ensure the provided government data array has at least 5 elements.
        if (governmentData.length < 5) {
            throw new IllegalArgumentException("Invalid data: Employee information must have 6 elements.");
        }
        
        // Assign government-related fields from the provided data array.
        this.sssNumber = governmentData[1];
        this.philhealthNumber = governmentData[2];
        this.pagibigNumber = governmentData[3];
        this.taxIdentificationNumber = governmentData[4];
//        this.withholdingTaxStatus = governmentData[5];
    }

    /**
     * Retrieves the government-related information as an array of strings.
     *
     * @return an array containing the employee ID, SSS number, PhilHealth number,
     * Pag-IBIG number, and Tax Identification Number.
     */
    @Override
    public String[] getInformation() {
        // Return an array with government-related details.
        return new String[] {employeeID,
                            sssNumber,
                            philhealthNumber,
                            pagibigNumber,
                            taxIdentificationNumber,
//                            withholdingTaxStatus
                            };
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public String getPhilhealthNumber() {
        return philhealthNumber;
    }

    public String getPagibigNumber() {
        return pagibigNumber;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }
    
}
