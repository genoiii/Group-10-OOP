/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.UMS;

import CSVFileManager.File;
import java.util.*;

/**
 * User class to manage user authentication and data storage.
 */
public class User {  
    protected String employeeID;
    protected String username;
    protected String password;
    protected String roleID;
    protected ArrayList<String[]> userData; // List to store raw user data
    protected boolean authenticationResult; // Result of authentication attempt
    protected HashMap<String, String[]> userMap; // Map to store user data for quick lookup
    protected String[] userInfo; // Array to store information about a specific user

    // Constructor to initialize the user with username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.setUserMap();  
    }

    // Method to attempt login using username and password
    public boolean isUserAuthenticated(String username, String password) {
        // Checks if the username exists in userMap
        if (this.userMap.containsKey(username)) {
            // Checks if the password matches the stored password for the username
            if (this.userMap.get(username)[3].equals(password)) {
                authenticationResult = true; // Authentication successful
                this.username = username;
                this.password = password;
                this.roleID = this.userMap.get(username)[4]; // Assuming roleID is in the 5th column (index 4)
            } else {
                authenticationResult = false; // Incorrect password
            }
        } else {
            authenticationResult = false; // Username not found
        }
        return authenticationResult; // Returns the result of authentication
    }

    // Method to set the user data from a CSV file
    protected void setUserMap() {
        File userFile = new File("UserFile", "src/CSVFiles/MotorPH Employee Data - User Details.csv");
        this.userData = userFile.readFile(); // Reading user data from CSV
        this.userMap = new HashMap<String, String[]>(); // Initializing the userMap
        
        // Iterates through each row in userData
        for (String[] i : this.userData) {
            // Adds each row (user) to the userMap with the username as the key
            this.userMap.put(i[2], i); // Username is at index 2 in the CSV
        }                    
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getRoleID() {
        return roleID;
    }

    public String[] getUserInfo() {
        return userInfo;
    }

    // Get the userMap 
    public HashMap<String, String[]> getUserMap() {
        return userMap;
    }
}