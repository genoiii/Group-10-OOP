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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Overtime extends Request {
    private String startTime;
    private String endTime;
    private Date overtimeDate;
    public double totalOvertime;
    private double payableHours;

    public Overtime(String requestID, Date requestDate, String startTime, String endTime, Date overtimeDate) throws IOException {
    super("OT", requestDate);
    this.startTime = startTime;
    this.endTime = endTime;
    this.overtimeDate = overtimeDate;
    this.payableHours = payableHours;
    }

    // Time validation method for HH:MM format
    public boolean isValidTimeFormat(String time) {
        // Validate the time format (HH:MM)
        String timePattern = "^(0[1-9]|1[0-2]):([0-5][0-9])$";  // Matches HH:MM format
        return time.matches(timePattern);
    }

    // Compare two times and return the comparison result
    public int compareTimes(String startTime, String endTime) {
        // Compare start and end times (HH:MM)
        try {
            String[] startParts = startTime.split(":");
            String[] endParts = endTime.split(":");

            int startHour = Integer.parseInt(startParts[0]);
            int startMinute = Integer.parseInt(startParts[1]);

            int endHour = Integer.parseInt(endParts[0]);
            int endMinute = Integer.parseInt(endParts[1]);

            if (startHour < endHour || (startHour == endHour && startMinute < endMinute)) {
                return -1; // Start time is earlier
            } else if (startHour == endHour && startMinute == endMinute) {
                return 0; // Same time
            } else {
                return 1; // End time is earlier
            }
        } catch (NumberFormatException e) {
            return 1; // If any error in parsing time, assume invalid time
        }
    }

    // Method to calculate total overtime
    public void calculateTotalOvertime() {
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a"); // Parse time in 12-hour AM/PM format
            Date start = timeFormat.parse(this.startTime);
            Date end = timeFormat.parse(this.endTime);

            long diffInMillis = end.getTime() - start.getTime();
            this.totalOvertime = diffInMillis / (1000.0 * 60.0 * 60.0); // Convert milliseconds to hours
            this.totalOvertime = Double.parseDouble(String.format("%.3f", this.totalOvertime));
        } catch (ParseException e) {
            // Handle the error 
            System.err.println("Error parsing time: " + e.getMessage());
        }
    }

    @Override
    public void processRequest() {
        try {
            // Generate request ID for Overtime
            String requestID = generateRequestID("OT");

            // Define the data fields for Overtime
            String[] dataFields = {"Overtime Request ID", "Employee ID", "Overtime Date", "Start Time", "End Time", "Total Hours", "Request Date", "Status", "Processed by", "Date processed", "Payable Hours"};
            
            // Pass the data fields and requestID to writeToCSV method
            writeToCSV(requestID, "src/CSVFiles/MotorPH Employee Data - Overtime.csv", dataFields);
        } catch (IOException ex) {
            Logger.getLogger(Overtime.class.getName()).log(Level.SEVERE, null, ex);
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

            // Convert Date to String 
            Date requestDate = getRequestDate();  // Get the request date as Date
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
            String formattedRequestDate = dateFormatter.format(requestDate);  // Format Date to String
            String formattedOvertimeDate = new SimpleDateFormat("MM/dd/yyyy").format(overtimeDate);

            // Write Overtime request data to CSV, including specific fields for Overtime
            writer.write(String.join(",", 
                requestID, 
                getEmployeeID(), 
                formattedOvertimeDate, 
                startTime, 
                endTime, 
                String.valueOf(totalOvertime), 
                formattedRequestDate, 
                getStatus(),
                "", // Processed By (empty initially)
                "", // Date Processed (empty initially)
                "0")); //Payable hours (empty initially)
            writer.newLine();
        }
    }

    // Getter methods for the fields used in CompanyHomePage
    public Date getOvertimeDate() {
        return overtimeDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public double getTotalOvertime() {
        return totalOvertime;
    }
}