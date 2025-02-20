package Class;

import CSVFileManager.CsvFile;
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 63909
 */

/**
 * The Input class handles user authentication and role identification. It
 * verifies user credentials against stored data and determines user roles.
 */
public class Input {
    private boolean authenticationResult; // Stores the result of the authentication attempt (true if successful, false otherwise)
    private HashMap<String,String[]> userMap; // HashMap to store user data for quick lookup using usernames as keys
    private String roleID; // Stores the role ID of the authenticated user
    private String employeeID; // Stores the employee ID of the authenticated user
    
    /**
     * Checks if the authenticated user is an administrator.
     * 
     * @return true if the user an admin (roleID != "1"), false otherwise.
     */
    public boolean isAdmin(){ 
        boolean roleResult = !this.roleID.equals("1"); 
        // Returns true if roleID is NOT "1" ("1" represents NonAdmin)
        return roleResult; 
    }
    
    /**
     * Authenticates a user by verifying their username and password against stored data.
     * If authentication is successful, it sets the user's role ID and employee ID.
     * 
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean isUserAuthenticated(String username, String password) {
        CsvFile userFile = CsvFile.USER; // Reference to the CSV file containing user data
        DataMap userDataMap = new DataMap(userFile.readFile(), 2); // Reads user data from the file and maps it using column index 2 as the key (username)
        
        this.userMap = userDataMap.getDataMap(); // Retrieve the mapped user data for quick access
        
         // Check if the username exists in the data
        if(this.userMap.containsKey(username)){
            // Verify if the stored password (at index 3) matches the provided password
            if(this.userMap.get(username)[3].equals(password)){
                authenticationResult = true; // Set authentication status to true
                this.roleID = this.userMap.get(username)[4]; // Retrieve and store the user's role ID from index 4
                this.employeeID = this.userMap.get(username)[1]; // Retrieve and store the employee ID from index 1
            } else {
                authenticationResult = false; // Set authentication status to false if password is incorrect
            }
        } else {
            authenticationResult = false; // Set authentication status to false if username is not found
        }

        return authenticationResult; // Return the authentication result
    }    

    /**
     * Retrieves the role ID of the authenticated user.
     * 
     * @return The role ID as a string.
     */
    public String getRoleID() {
        return roleID;
    }

    /**
     * Retrieves the employee ID of the authenticated user.
     * 
     * @return The employee ID as a string.
     */
    public String getEmployeeID() {
        return employeeID;
    }
    
    
}
