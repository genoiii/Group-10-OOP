/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 *
 * @author 63909
 */
public class Job {
    private String jobID; 
    private String jobName;
    private Salary salary; 
    private Department department;
    private Employee immediateSupervisor;

    public Job(String jobID, String jobName, Salary salary) {
        this.jobID = jobID;
        this.jobName = jobName;
        this.salary = salary;
    }

    public String getJobID() {
        return jobID;
    }

    public String getJobName() {
        return jobName;
    }

    public Salary getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee getImmediateSupervisor() {
        return immediateSupervisor;
    }
    
    
    
}
