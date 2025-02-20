/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.UMS;

import Class.*;
import Frame.Admin.*;

/**
 *
 * @author 63909
 */

/**
 * Represents an Admin user in the system.
 * This class extends the User class and provides specific functionality for administrators.
 */
public class Admin extends User {
    
    /**
     * Constructor to initialize an Admin object with username, password, and user credentials.
     * @param username The admin's username
     * @param password The admin's password
     * @param userInputCredential The Input object containing role ID and employee ID
     */
    public Admin(String username, String password, Input userInputCredential) {
        super(username, password); // Call superclass constructor to set username and password
        this.roleID = userInputCredential.getRoleID(); // Assign role ID from user input
        this.employeeID = userInputCredential.getEmployeeID(); // Assign employee ID from user input
    }
    
    /**
     * Overrides the login method to authenticate an Admin and open the admin dashboard.
     * @param userAccount The Admin user account attempting to log in
     */
    @Override
    public void login(User userAccount){        
        new DashboardPage(userAccount).setVisible(true); // Open the dashboard page for the admin         
    } 
}
