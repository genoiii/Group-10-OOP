/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

/**
 * Represents a deduction.
 *
 * <p>This class encapsulates details related to deductions, such as the deduction type and amount.
 * Extend this class with additional fields and methods as needed for managing deduction details.</p>
 */
public class Deduction {
    private String deductionID;
    private String deductionName;
    private String description;

    /**
     * Default constructor for Deduction.
     *
     * <p>Creates an instance of Deduction with default values.</p>
     */
    public Deduction() {
    }
    
    /**
     * Constructs a Deduction with the specified deduction ID.
     *
     * @param deductionID the unique identifier for the deduction.
     */
    public Deduction(String deductionID) {
        this.deductionID = deductionID; // Initialize the deduction ID.
    }
    
    /**
     * Constructs a Deduction object with the specified attributes.
     *
     * @param deductionID the unique identifier for the deduction.
     * @param deductionName the name of the deduction.
     * @param description a description of the deduction.
     */
    public Deduction(String deductionID, String deductionName, String description) {
        this.deductionID = deductionID;
        this.deductionName = deductionName;
        this.description = description;
    }
    
    // Getters and Setters
    public String getDeductionID() {
        return deductionID;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public String getDescription() {
        return description;
    }

}

