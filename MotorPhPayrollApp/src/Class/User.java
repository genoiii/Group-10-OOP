/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.*;

/**
 *
 * @author 63909
 */
public final class User {    
    private String userID;
    private String username;
    private String password;
    private ArrayList<String[]> userData; // List to store raw user data
    private boolean logginInResult; // Result of authentication attempt
    //private UserRole role;
    private HashMap<String,String[]> userMap; // Map to store user data for quick lookup
    private String[] userInfo; // Array to store information about a specific user
           
    // getters and setters
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }        

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }      

    // getters and setters
    public HashMap<String, String[]> getUserMap() {
        return userMap;
    }
    public void setUserMap(ArrayList<String[]> dataFile) {
        this.userData = dataFile; // Assigns the input dataFile to userData
        this.userMap = new HashMap<String, String[]>(); // Initializes the userMap
        
        // Iterates through each string in userData
        for (String[] i : this.userData){
            // Puts the divided  row into userMap with the username as the key
            this.userMap.put(i[2],i);
        }                    
    }     
    
    //Setter and Getter for a specific User
    public String[] getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(String[] userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isUserAuthenticated(String username, String password) {
        this.username = username; // Sets the username
        this.password = password; // Sets the password        
        
        // Checks if the username exists in userMap
        if(this.userMap.containsKey(this.username)){
            // Checks if the password matches the stored password for the username
            if(this.userMap.get(this.username)[3].equals(this.password)){
                logginInResult = true; // Authentication successful
                setUsername(username);
                setPassword(password);
            } else {
                logginInResult = false; // Incorrect password
            }
        } else {
            logginInResult = false; // Username not found
        }

        return logginInResult; // Returns the result of authentication
    }        
        
}
