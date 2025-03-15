/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

/**
 *
 * @author 63909
 */
import Class.EntityManagement.EntityManager;
import Class.EntityManagement.EntityType;
import Class.IDManagement.IDManager;
import Class.IDManagement.Identifiable;
import Class.Parser;
import java.time.LocalDate;

/**
 * Represents a generic request in the system.
 *
 * <p>This class serves as a base for various types of requests, such as leave requests, data requests, or other
 * system interactions. Extend this class with additional fields and methods as needed to capture the specifics
 * of each request type.</p>
 */
public class Request implements Identifiable {
    private String requestID;
    private String requestTypeID;
    private String employeeID;
    private LocalDate requestDate;
    private String reason;
    private String status;
    private String processedBy;
    private LocalDate processedDate;
    private String remarks;
    
    /**
     * Default constructor for Request.
     *
     * <p>Initializes a new instance of the Request class with default values.
     * Additional initialization logic can be added here if needed.</p>
     */
    public Request() {
    }
    
    /**
     * Constructs a Request from the provided request data.
     *
     * <p>The requestData array should contain at least 8 elements representing:
     * <ul>
     *   <li>Index 0: Request ID</li>
     *   <li>Index 1: Request Type ID</li>
     *   <li>Index 2: Employee ID (for the requesting employee, which will be converted to an Employee object)</li>
     *   <li>Index 3: Request date (to be formatted using Formatter.formatDate)</li>
     *   <li>Index 4: Reason for the request</li>
     *   <li>Index 5: Request status</li>
     *   <li>Index 6: Approved By (Employee ID; if empty, set to null)</li>
     *   <li>Index 7: Processed date (to be formatted; if empty, set to null)</li>
     * </ul>
     * </p>
     *
     * @param requestData an array of strings containing the request details.
     * @throws IllegalArgumentException if requestData is null or contains fewer than 8 elements.
     */
    public Request(String[] requestData) {
        if (requestData == null) {
            throw new IllegalArgumentException("Request data cannot be null.");
        }
        
        switch (requestData.length) {
            case 4 -> {
                // New request (ID is generated)
                EntityManager request = new EntityManager(EntityType.REQUEST);
                this.requestID = IDManager.generateID(request.getEntityType().getIdPrefix());
                IDManager.saveIDCounters();
                
                this.requestTypeID = requestData[0];
                this.employeeID = requestData[1];
                this.requestDate = Parser.parseLocalDate(requestData[2], null);
                
                this.reason = requestData[3].isEmpty() ? null : requestData[3];
                
                this.status = RequestStatus.PENDING.toString(); 
                this.processedBy = null; // Default to null since it's not provided in this case
                this.processedDate = null; // Default to null since it's not provided in this case
                this.remarks = null;
            }
            case 9 -> {
                // Existing request (ID is provided)
                this.requestID = requestData[0];
                this.requestTypeID = requestData[1];
                this.employeeID = requestData[2];
                this.requestDate = Parser.parseLocalDate(requestData[3], null);
                this.reason = requestData[4].isEmpty() ? null : requestData[4];
                
                this.status = RequestStatus.valueOf(requestData[5]).toString();
                this.processedBy = requestData[6].isEmpty() ? null : requestData[6];
                this.processedDate = requestData[7].isEmpty() ? null : Parser.parseLocalDate(requestData[7], null);
                this.remarks = requestData[8].isEmpty() ? null : requestData[8];
            }
            default -> throw new IllegalArgumentException("Invalid input data format. Expected 4 (new) or 9 (existing) parameters.");
        }
    }

    // Getters and Setters
    @Override
    public String getID() {
        return requestID;
    }

    public String getRequestTypeID() {
        return requestTypeID;
    }
    
    public String getEmployeeID() {
        return employeeID;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public LocalDate getProcessedDate() {
        return processedDate;
    }

    public String getRemarks() {
        return remarks;
    }   
    
    public void approve(String processedBy) {
        if (processedBy == null || processedBy.isEmpty()) {
            throw new IllegalArgumentException("Processed by cannot be null or empty.");
        }
        this.processedBy = processedBy;
        this.status = RequestStatus.APPROVED.toString();
        this.processedDate = LocalDate.now();
    }

    public void reject(String processedBy, String remarks) {
        if (processedBy == null || processedBy.isEmpty()) {
            throw new IllegalArgumentException("Processed by cannot be null or empty.");
        }
        if (reason == null || reason.isEmpty()) {
            throw new IllegalArgumentException("Rejection reason cannot be null or empty.");
        }

        this.processedBy = processedBy;
        this.status = RequestStatus.REJECTED.toString();
        this.remarks = remarks;
        this.processedDate = LocalDate.now();
    }
}

