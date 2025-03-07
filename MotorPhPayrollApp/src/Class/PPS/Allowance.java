/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

/**
 * Represents an allowance.
 *
 * <p>This class encapsulates allowance-related details such as the type of allowance and its amount.
 * Extend this class with additional properties and methods as needed.</p>
 */
public class Allowance {
    private String allowanceID;
    private String allowanceName;
    private String description;
    
    /**
     * Default constructor for Allowance.
     *
     * <p>Creates an instance of Allowance with default values.</p>
     */
    public Allowance() {
    }
    
    /**
     * Constructs an Allowance with the specified allowance ID.
     *
     * @param allowanceID the unique identifier for the allowance.
     */
    public Allowance(String allowanceID) {
        this.allowanceID = allowanceID; // Initialize the allowance ID.
    }

    /**
     * Constructs an Allowance with the specified attributes.
     *
     * @param allowanceID the unique identifier for the allowance.
     * @param allowanceName the name of the allowance.
     * @param description a description of the allowance.
     */
    public Allowance(String allowanceID, String allowanceName, String description) {
        this.allowanceID = allowanceID;
        this.allowanceName = allowanceName;
        this.description = description;
    }    

    // Getters and Setters
    public String getAllowanceID() {
        return allowanceID;
    }

    public String getAllowanceName() {
        return allowanceName;
    }

    public String getDescription() {
        return description;
    }
}

