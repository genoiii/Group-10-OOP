/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 *
 * @author 63909
 */
public class Department {
    private String departmentID; 
    private String departmentName;
    private Employee departmentHead; 

    public Department(String departmentID, String departmentName, Employee departmentHead) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Employee getDepartmentHead() {
        return departmentHead;
    }
    
    
}
