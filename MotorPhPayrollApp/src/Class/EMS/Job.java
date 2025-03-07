/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents a job position.
 *
 * <p>This class encapsulates job-related details such as the job name.
 * Additional job-related properties and methods can be added as needed.</p>
 */
public class Job {
    private String jobID; 
    private String jobName;
    private Salary salary; 
    private Department department;
    private Employee immediateSupervisor;

    /**
     * Constructs a Job with the specified job ID, job name, and salary.
     *
     * @param jobID the unique identifier for the job.
     * @param jobName the name of the job.
     * @param salary the Salary object associated with the job.
     */
    public Job(String jobID, String jobName, Salary salary) {
        this.jobID = jobID;
        this.jobName = jobName;
        this.salary = salary;
    }
    
    /**
     * Constructs a Job with the specified job name.
     *
     * @param jobName the name of the job.
     */    
    public Job(String jobName) {
        this.jobName = jobName;
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
