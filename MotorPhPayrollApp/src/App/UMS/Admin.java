/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.UMS;

/**
 *
 * @author 63909
 */
public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);  // Calls the constructor of the superclass (User)
        
        setUserMap();  // This loads the user data into the userMap

        // Check if the username exists in the userMap
        if (this.userMap.containsKey(username)) {
            String[] userInfo = this.userMap.get(username);  // Retrieve the user info array for the username

            // Check if the user data is complete 
            if (userInfo != null && userInfo.length > 4) {
                this.roleID = userInfo[4];  // Set roleID from the user data
                this.employeeID = userInfo[1];  // Set employeeID from the user data
            } else {
                // Handle case where user data is incomplete 
                this.roleID = "defaultRole";  // Set a default role if the data is incomplete
                this.employeeID = "defaultEmployeeID";  // Set a default employee ID if the data is incomplete
            }
        } else {
            // Handle the case where the username is not found in the userMap
            this.roleID = "defaultRole";  // Set a default role if username is not found
            this.employeeID = "defaultEmployeeID";  // Set a default employee ID if username is not found
        }
    }
}