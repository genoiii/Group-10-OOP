/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Class.EMS;

/**
 * Abstract base class for employee-related information.
 *
 * <p>This class serves as a common superclass for different types of information
 * (e.g., personal, government, employment) associated with an employee.
 * Extend this class to implement specific information structures as needed.</p>
 */
public abstract class Information {
    protected String employeeID; // Employee's unique identifier.

    /**
     * Constructs an Information object with the specified employee ID.
     *
     * @param employeeID the unique identifier of the employee.
     */
    public Information(String employeeID) {
        this.employeeID = employeeID; // Initialize the employee ID field.
    }

    /**
     * Retrieves the employee's unique identifier.
     *
     * @return the employeeID.
     */
    public String getEmployeeID() {
        return employeeID; // Return the stored employee ID.
    }  
        
    /**
     * Retrieves the object's information as an array of strings.
     *
     * <p>Subclasses must implement this method to provide a formatted array of strings
     * representing the object's details.</p>
     *
     * @return an array of strings containing the object's information.
     */ 
    public abstract String[] getInformation();
}
