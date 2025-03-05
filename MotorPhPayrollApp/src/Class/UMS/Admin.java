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

    public Admin(String username, String password, String employeeID, String roleID) {
        super(username, password); // Initialize username and password in the parent class
        this.employeeID = employeeID; // Set the employee ID for the admin
        this.roleID = roleID; // Set the role ID for the admin
    }
   
    /**
     * Overrides the login method to authenticate an Admin and open the admin dashboard.
     * @param userAccount The Admin user account attempting to log in
     */
    public void login(User userAccount){        
        new DashboardPage(userAccount).setVisible(true); // Open the dashboard page for the admin         
    } 
}
