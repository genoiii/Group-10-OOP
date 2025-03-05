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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Request {
    protected String requestID;
    protected String employeeID;
    protected Date requestDate;
    private String status;
    //private Date dateProcessed;
    //protected String processedBy;

    // Constructor for creating a new request with today's date
    public Request(String prefix, Date requestDate) throws IOException{
        this.requestID = generateRequestID(prefix); // Generate the request ID using the prefix
        this.employeeID = User.getCurrentUser().getEmployeeID(); // Fetch employeeID from current user
        this.requestDate = new Date();
        this.status = "PENDING"; // Initial status
        //this.dateProcessed = new Date();
        //this.processedBy = User.getCurrentUser().getEmployeeID();
    }

    // Abstract method to be implemented by subclasses
    public abstract void processRequest();

    // Method to generate unique request ID (using prefix and year)
    public String generateRequestID(String prefix) throws IOException {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return prefix + year + "-" + getNextRequestID(prefix, year);
    }

    // Method to get the next request ID from the CSV file (auto-increment)
    public int getNextRequestID(String prefix, int year) throws FileNotFoundException, IOException {
        int nextRequestID = 1;
        String filePath = getFilePath(prefix);  // Get the specific file path based on prefix
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String requestID = data[0];
            if (requestID.startsWith(prefix + year)) {
                String[] idParts = requestID.split("-");
                int id = Integer.parseInt(idParts[1]);
                if (id >= nextRequestID) {
                    nextRequestID = id + 1;
                }
            }
        }
        reader.close();
        return nextRequestID;
    }
    
    // Method to get the correct file path based on the prefix (Leave, Overtime, etc.)
    private String getFilePath(String prefix) {
        if (prefix.equals("L")) {
            return "src/CSVFiles/MotorPH Employee Data - Leave.csv";
        } else if (prefix.equals("OT")) {
            return "src/CSVFiles/MotorPH Employee Data - Overtime.csv";
        }
        return ""; // Return empty string if no matching prefix found
    }

    // Method to write request data to CSV
    public void writeToCSV(String requestID, String filePath, String[] dataFields) throws IOException {
        File file = new File(filePath);
        boolean isEmpty = file.length() == 0; // Check if file is empty
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Write header if the file is empty
            if (isEmpty) {
                writer.write(String.join(",", dataFields));  // Write header
                writer.newLine();
            }
            
            // Convert Date to String 
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
            String formattedRequestDate = dateFormatter.format(requestDate);  // Format Date to String

            // Write the request data to the CSV
            String employeeID = getEmployeeID();  
            String status = "PENDING";  // Default status
            writer.write(String.join(",", requestID, employeeID, formattedRequestDate, status));
            writer.newLine();
            }
    }
    
    // Set the status of the request 
    public void setStatus(String status) {
        this.status = status;
    }

    /* Set the processed date 
    public void setDateProcessed(Date dateProcessed) {
       this.dateProcessed = dateProcessed;  
    }
    
    // Set the processed By
    public void setProcessedBy(String employeeID) {
       this.processedBy = employeeID;  
    }

    // Process the request (update status and processed date). This method will be called in the Jframe buttons
    public void updateRequestStatus(String action) {
        if ("APPROVED".equals(action)) {
            setStatus("APPROVED");
            setProcessedBy (User.getCurrentUser().getEmployeeID()); 
            setDateProcessed(new Date()); 
        } else if ("REJECTED".equals(action)) {
            setStatus("REJECTED");
            setProcessedBy (User.getCurrentUser().getEmployeeID()); 
            setDateProcessed(new Date());  
        }
    }*/

    // Getter methods
    public String getRequestID() {
        return requestID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public Date getRequestDate() {
        return requestDate; 
    }

    public String getStatus() {
        return status;
    }
    
    /*public Date getDateProcessed() {
        return dateProcessed;
    }
    
    public String getProcessedBy() {
        return processedBy;
    }*/
}