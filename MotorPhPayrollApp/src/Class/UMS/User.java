/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.UMS;

//import CSVFileManager.File;
import Class.*;
import Frame.LoginPage;
import java.util.*;
import javax.swing.JFrame;

/**
 *
 * @author 63909
 */

/**
 * Abstract class representing a User.
 * This serves as a base class for different types of users (e.g., Admin, NonAdmin).
 * It contains common attributes and methods that all user types share.
 */
public class User {  
    protected String employeeID;
    protected String username;
    protected String password;
    protected String roleID;
    protected ArrayList<String[]> userData; // List to store raw user data
    protected boolean authenticationResult; // Stores the result of the authentication attempt
    protected String[] userInfo; // Array to store information about a specific user
    protected Input userInputCredential;
    protected User user;
    
    public User(){
        
    }
    public User(String[] userData){
        this.username = userData[0];
        this.password = userData[1];
        this.employeeID = userData[2];
        this.roleID = userData[3];
    }
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
    
    public User(Input userInputCredential) {
        this.userInputCredential = userInputCredential;
//        this.username = userInputCredential.getUsername();
//        this.password = userInputCredential.getPassword();
//        this.employeeID = userInputCredential.getEmployeeID();
//        this.roleID = userInputCredential.getRoleID();
    }
    
    public ArrayList<Object> User(ArrayList<String[]> userData){
        ArrayList<Object> userList = new ArrayList<Object>();
        
        
        return userList;
    }
    
    public void login(boolean isAdmin){
//        // Check if the authenticated user is a Non-Admin
//        if (!isAdmin) {
//            NonAdmin nonAdmin = new NonAdmin(this.userInputCredential); // Create NonAdmin user
//            new ProfilePage(nonAdmin).setVisible(true); // Open the Profile Page
//            return; // Exit the method
//        }
//
//        // If user is an Admin, create an Admin object and redirect to the Company Home Page
//        Admin admin = new Admin(this.userInputCredential); // Create Admin user
//        new CompanyHomePage(admin).setVisible(true); // Open the Company Home PageSS        

//        if (user instanceof NonAdmin) {
//            Access.accessUserDashboard((NonAdmin) user);
//        }
    }
      
//    public boolean isUserAuthenticated(String username, String password) {
//        setUserMap();
//        // Checks if the username exists in userMap
//        if(this.userMap.containsKey(username)){
//            // Checks if the password matches the stored password for the username
//            if(this.userMap.get(username)[3].equals(password)){
//                authenticationResult = true; // Authentication successful
//                this.username = username;
//                this.password = password;
//                this.roleID = this.userMap.get(username)[4];
//            } else {
//                authenticationResult = false; // Incorrect password
//            }
//        } else {
//            authenticationResult = false; // Username not found
//        }
//
//        return authenticationResult; // Returns the result of authentication
//    }
    
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

    public String getPassword() {
        return password;
    }
    
    public void setAuthenticationResult(boolean authenticationResult) {
        this.authenticationResult = authenticationResult;
    }
    
    public void logout(JFrame jFrame){
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true); // Show login page
        jFrame.setVisible(false); // Hide the current window
    }
}
