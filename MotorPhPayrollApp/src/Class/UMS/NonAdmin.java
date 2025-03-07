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
    public NonAdmin(String username, String password){
        super(username, password);
    }
    
    public NonAdmin(User nonAdmin){
        this.username = nonAdmin.getUsername();
        this.password = nonAdmin.getPassword();
        this.employeeID = nonAdmin.getEmployeeID();
        this.roleID = nonAdmin.getRoleID();
    }
    
    public NonAdmin(Input userInputCredential) {        
        super(userInputCredential);
//        this.username = userInputCredential.getUsername();
//        this.password = userInputCredential.getPassword();
//        this.roleID = userInputCredential.getRoleID(); // Assign role ID from user input
//        this.employeeID = userInputCredential.getEmployeeID(); // Assign employee ID from user input
    }
    
    public static void login(User user){        
        Access.accessProfilePage(user); // Open the dashboard page for the admin
    }
    
//    /**
//     * Overrides the login method to authenticate a Non-Admin user and open their profile page.
//     * @param userAccount The Non-Admin user account attempting to log in
//     */
//    @Override
//    public void login(User userAccount) {
//        new ProfilePage(userAccount).setVisible(true); // Open the profile page for the non-admin user  
//    }
}
