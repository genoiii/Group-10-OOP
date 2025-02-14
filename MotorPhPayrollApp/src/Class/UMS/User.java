/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.UMS;

import Class.File;
import java.util.*;

/**
 *
 * @author 63909
 */
public class User {  
    protected String employeeID;
    protected String username;
    protected String password;
    protected String roleID;
    protected ArrayList<String[]> userData; // List to store raw user data
    protected boolean authenticationResult; // Result of authentication attempt
    //private UserRole role;
    protected HashMap<String,String[]> userMap; // Map to store user data for quick lookup
    protected String[] userInfo; // Array to store information about a specific user

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public void login(User userAccount){}
      
    public boolean isUserAuthenticated(String username, String password) {
        setUserMap();
        // Checks if the username exists in userMap
        if(this.userMap.containsKey(username)){
            // Checks if the password matches the stored password for the username
            if(this.userMap.get(username)[3].equals(password)){
                authenticationResult = true; // Authentication successful
                this.username = username;
                this.password = password;
                this.roleID = this.userMap.get(username)[4];
            } else {
                authenticationResult = false; // Incorrect password
            }
        } else {
            authenticationResult = false; // Username not found
        }

        return authenticationResult; // Returns the result of authentication
    }
    
    // getters and setters
    public HashMap<String, String[]> getUserMap() {
        return userMap;
    }    
    protected void setUserMap() {
        File userFile = new File("UserFile","src/CSV/MotorPH Employee Data - User Details.csv");
        this.userData = userFile.readFile(); // Assigns the input dataFile to userData
        this.userMap = new HashMap<String, String[]>(); // Initializes the userMap
        
        // Iterates through each string in userData
        for (String[] i : this.userData){
            // Puts the divided  row into userMap with the username as the key
            this.userMap.put(i[2],i);
        }                    
    } 
    
    // getters and setters  
    public String getUsername() {
        return username;
    }

    public String getEmployeeID() {
        return employeeID;
    }
    
    //Setter and Getter for a specific User
    public String[] getUserInfo() {
        return userInfo;
    }

    public String getRoleID() {
        return roleID;
    }
    
}
