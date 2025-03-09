/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

/**
 *
 * @author Charm
 */
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Leave extends Request {
    private Date startDate;
    private Date endDate;
    private String leaveType;
    private String notes;
    private double totalDays;
    private double payableDays;

    // Constructor for Leave request
    public Leave(Date startDate, Date endDate, String leaveType, String notes, Date requestDate) throws IOException {
        super("L", requestDate); 
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.notes = notes;
        this.totalDays = calculateTotalDays(startDate, endDate);; 
        this.payableDays = payableDays;
    }

    // Method to calculate total leave days (end date - start date)
    public double calculateTotalDays(Date startDate, Date endDate) {
        // Convert java.util.Date to LocalDate
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // If the start date and end date are the same, it's 1 full day of leave
        if (startLocalDate.equals(endLocalDate)) {
            return 1;
        } else {
            // Calculate the number of days between the two dates, including both start and end date
            long difference = endLocalDate.toEpochDay() - startLocalDate.toEpochDay();

            // Return the total days, including both start and end date
            return difference + 1; // Add 1 to include both start and end date as full days
        }
    }

    @Override
    public void processRequest() {
        try {
            // Generate request ID for Leave
            String requestID = generateRequestID("L");

            // Define the data fields for Leave
            String[] dataFields = {"Leave Request ID", "Employee ID", "Start Date", "End Date", "Leave Type", "Total Days", "Notes", "Request Date", "Status"};

            // Pass the data fields and requestID to writeToCSV method
            writeToCSV(requestID, "src/CSVFiles/MotorPH Employee Data - Leave.csv", dataFields);
        } catch (IOException ex) {
            Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Override the writeToCSV method from Request class
    @Override
    public void writeToCSV(String requestID, String filePath, String[] dataFields) throws IOException {
        File file = new File(filePath);
        boolean isEmpty = file.length() == 0; // Check if file is empty

        // Use BufferedWriter to append to the CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Write header if the file is empty
            if (isEmpty) {
                writer.write(String.join(",", dataFields));  // Write header
                writer.newLine();
            }

            // Convert Date to String using SimpleDateFormat for requestDate
            Date requestDate = getRequestDate();  // Get the request date as Date
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
            String formattedRequestDate = dateFormatter.format(requestDate);  // Format Date to String

            // Convert Date to String using SimpleDateFormat for startDate and endDate 
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String formattedStartDate = dateFormat.format(startDate); // Format Date to String
            String formattedEndDate = dateFormat.format(endDate); // Format Date to String

            // Write Leave request data to CSV, including specific fields for Leave
            writer.write(String.join(",", 
                requestID, 
                getEmployeeID(), 
                formattedStartDate, 
                formattedEndDate, 
                leaveType, 
                String.valueOf(totalDays), 
                notes, 
                formattedRequestDate, 
                getStatus(),
                "", // Processed By (empty initially)
                "", // Date Processed (empty initially)
                "0"));// Payable Days (empty initially)
            writer.newLine();
        }
    }

    // Getter methods
    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public double getTotalDays() {
        return totalDays;
    }

    public String getNotes() {
        return notes;
    }
}