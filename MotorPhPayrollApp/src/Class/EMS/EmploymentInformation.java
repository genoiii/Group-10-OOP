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
public class EmploymentInformation implements Information {
    private Employee employeeID; 
    private Job jobTitle; 
    private String employmentType; 
    private String employmentStatus;
    private Date dateHired; 

    public EmploymentInformation(Employee employeeID, Job jobTitle) {
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
    }
    
    @Override
    public String getInformation() {
        return "Employee ID: " + employeeID + ", Job Title: " + jobTitle;
    }

    @Override
    public void setInformation(String info) {
        this.employmentStatus = info;
    }
}
