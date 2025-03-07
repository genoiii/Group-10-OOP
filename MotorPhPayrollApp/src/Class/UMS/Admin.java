/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.UMS;

import Class.*;

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
     */
    public Admin(String username, String password){
        super(username, password);
    }
    
    public Admin(User admin){
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.employeeID = admin.getEmployeeID();
        this.roleID = admin.getRoleID();
    }
    
    public Admin(Input userInputCredential) {
        super(userInputCredential);
//        this.username = userInputCredential.getUsername();
//        this.password = userInputCredential.getPassword();
//        this.roleID = userInputCredential.getRoleID(); // Assign role ID from user input
//        this.employeeID = userInputCredential.getEmployeeID(); // Assign employee ID from user input
    }
    
    public static void login(User admin){        
        Access.accessCompanyHomePage((Admin) admin); // Open the dashboard page for the admin
    } 
    
//    /**
//     * Overrides the login method to authenticate an Admin and open the admin dashboard.
//     * @param userAccount The Admin user account attempting to log in
//     */
//    @Override
//    public void login(User userAccount){        
//        new DashboardPage(userAccount).setVisible(true); // Open the dashboard page for the admin         
//    } 
}
