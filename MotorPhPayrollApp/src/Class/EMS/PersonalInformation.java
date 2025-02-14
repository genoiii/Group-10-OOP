/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import java.util.*;

/**
 *
 * @author 63909
 */
public class PersonalInformation implements Information {
    private Employee employeeID; 
    private String firstName;
    private String lastName;
    private Date birthday;
    private String address;
    private String phoneNumber;
    private String email;

    public PersonalInformation(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    @Override
    public String getInformation() {
        return "Employee ID: " + employeeID + ", Name: " + firstName + " " + lastName;
    }
    
    @Override
    public void setInformation(String info) {
        this.firstName = info;
    }

}
