/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.UMS;

import Class.*;
import Frame.EmployeeDashboard;

/**
 *
 * @author 63909
 */

/**
 * Represents a Non-Admin user in the system.
 * This class extends the User class and provides specific functionality for regular users.
 */
public class NonAdmin extends User {
   
    /**
     * Constructor to initialize a NonAdmin object with username, password, and user credentials.
     * @param username The non-admin user's username
     * @param password The non-admin user's password
     * @param userInputCredential The Input object containing role ID and employee ID
     */
    public NonAdmin(String username, String password, String employeeID, String roleID) {
        super(username, password); // Call the parent constructor for username and password
        this.employeeID = employeeID; // Set the employee ID
        this.roleID = roleID; // Set the role ID
    }
       
    /**
     * Overrides the login method to authenticate a Non-Admin user and open their profile page.
     * @param userAccount The Non-Admin user account attempting to log in
     */
    public void login(User userAccount) {
        new EmployeeDashboard(userAccount).setVisible(true); // Open the EmployeeDashboard page for the non-admin user  
    }
}
