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

    public PersonalInformation(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public PersonalInformation(Employee employeeID, String firstName, String lastName, Date birthday, String address, String phoneNumber, String email) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
