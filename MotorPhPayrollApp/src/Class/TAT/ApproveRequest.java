/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

/**
 *
 * @author Charm
 */
import Class.UMS.User;
import java.util.Date;

public abstract class ApproveRequest {
    protected String requestID;
    String status;
    Date dateProcessed;
    protected String processedBy;

    // Constructor for approving request
    public ApproveRequest(String status) {
        this.status = status; 
        this.dateProcessed = new Date(); 
        this.processedBy = User.getCurrentUser().getEmployeeID(); 
    }

    // Abstract method to be implemented in child classes
    public abstract void loadDataFromCSV(String requestID);
    public abstract void updateCSVFile(String csvFilePath);

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public String getProcessedBy() {
        return processedBy;
    }
}