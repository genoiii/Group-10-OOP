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
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public void login(User userAccount){}
      
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
    
    // getters and setters
//    public HashMap<String, String[]> getUserMap() {
//        return userMap;
//    }    
//    protected void setUserMap() {
//        File userFile = new File("UserFile","src/CSV/MotorPH Employee Data - User Details.csv");
//        this.userData = userFile.readFile(); // Assigns the input dataFile to userData
//        this.userMap = new HashMap<String, String[]>(); // Initializes the userMap
//        
//        // Iterates through each string in userData
//        for (String[] i : this.userData){
//            // Puts the divided  row into userMap with the username as the key
//            this.userMap.put(i[2],i);
//        }                    
//    } 
    
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
