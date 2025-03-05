/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.UMS;

//import CSVFileManager.File;
import java.util.*;

/**
 *
 * @author 63909
 */

/**
 * Abstract class representing a User.
 * This serves as a base class for different types of users (e.g., Admin, NonAdmin).
 * It contains common attributes and methods that all user types share.
 */
public abstract class User {  
    protected String employeeID;
    protected String username;
    protected String password;
    protected String roleID;
    protected ArrayList<String[]> userData; // List to store raw user data
    protected boolean authenticationResult; // Stores the result of the authentication attempt
    protected String[] userInfo; // Array to store information about a specific user

    /**
     * Constructor to initialize a User object with a username and password.
     * @param username The username of the user
     * @param password The password of the user
     */    
    
// Static variable for current logged-in user
    private static User currentUser;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getter for currentUser
    public static User getCurrentUser() {
        return currentUser;
    }

    // Setter for currentUser
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public boolean login(String enteredUsername, String enteredPassword) {
    // Check if entered credentials match the stored user credentials
    if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
        setCurrentUser(this); // Set the current user if authentication succeeds
        return true;
    }
    return false;
}
      

    
    // Getters and Setters  
    
    /**
     * Retrieves the username of the user.
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the employee ID of the user.
     * @return The employee ID
     */   
    public String getEmployeeID() {
        return employeeID;
    }
    
    /**
     * Retrieves detailed user information.
     * @return An array containing user details
     */
    public String[] getUserInfo() {
        return userInfo;
    }

    /**
     * Retrieves the role ID of the user.
     * @return The role ID (e.g., Admin, Employee)
     */
    public String getRoleID() {
        return roleID;
    }    

}
