/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

/**
 *
 * @author 63909
 */
import Class.EMS.Employee;
import Class.EMS.EmployeeService;
import Class.Formatter;
import java.time.LocalDate;

/**
 * Represents a generic request in the system.
 *
 * <p>This class serves as a base for various types of requests, such as leave requests, data requests, or other
 * system interactions. Extend this class with additional fields and methods as needed to capture the specifics
 * of each request type.</p>
 */
public class Request {
    private String requestID;
    private String requestTypeID;
    private Employee employeeID;
    private LocalDate requestDate;
    private String reason;
    private String status;
    private Employee approvedBy;
    private LocalDate processedDate;
    
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
        // Validate input length to ensure at least 8 elements are provided.
        if (requestData == null || requestData.length < 8) {
            throw new IllegalArgumentException("Insufficient request data provided.");
        }
        
        EmployeeService employeeService = new EmployeeService(); // Create an instance of EmployeeService to retrieve Employee objects.
        
        this.requestID = requestData[0];
        this.requestTypeID = requestData[1];
        this.employeeID = employeeService.getEmployeeInformation(requestData[2]);
        this.requestDate = Formatter.formatDate(requestData[3]);
        this.reason = requestData[4];
        this.status = requestData[5];        
        this.approvedBy = requestData[6].isEmpty() ? null : employeeService.getEmployeeInformation(requestData[6]); // Assuming approvedBy is another Employee object (can be null if not yet approved)
        this.processedDate = requestData[7].isEmpty() ? null : Formatter.formatDate(requestData[7]);
    }

    // Getters and Setters
    public String getRequestID() {
        return requestID;
    }

    public Employee getEmployeeID() {
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

    public Employee getApprovedBy() {
        return approvedBy;
    }
}

