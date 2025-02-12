/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.UMS;

import Frame.Admin.*;

/**
 *
 * @author 63909
 */
public class Admin extends User {
    
    public Admin(String username, String password) {
        super(username, password);
        setUserMap();
        this.roleID = this.userMap.get(username)[4];
        this.employeeID = this.userMap.get(username)[1];
    }
    
    @Override
    public void login(User userAccount){        
        new DashboardPage(userAccount).setVisible(true);         
    }
    
    
    
}
