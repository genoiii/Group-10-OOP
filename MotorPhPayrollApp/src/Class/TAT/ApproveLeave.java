/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

/**
 *
 * @author Charm
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApproveLeave extends ApproveRequest {
    private double totalDays;
    private double payableDays;

    // Constructor that accepts requestID and fetches data from the CSV
    public ApproveLeave(String requestID) {
        super("PENDING");  // Default status as "PENDING"
        loadDataFromCSV(requestID);  // Load the leave data from the CSV file based on the requestID
    }
    
    // Method to load data from the CSV file based on the requestID
    public void loadDataFromCSV(String requestID) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/CSVFiles/MotorPH Employee Data - Leave.csv"));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                
                // RequestID is at index 0
                if (columns[0].equals(requestID)) {
                    // Extract the data from CSV and assign to fields
                    this.requestID = requestID;
                    // Load totalDays from the CSV 
                    this.totalDays = Double.parseDouble(columns[5]);
                    found = true;
                    break;
                }
            }
            reader.close();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Method to approve or reject the leave request
    public void approveLeaveType(boolean approve) {
        if (approve) {
            setStatus("APPROVED");
            calculatePayableDays(); // Calculate payable days if approved
        } else {
            setStatus("REJECTED");
            payableDays = 0; // Set to 0 if rejected
        }
        updateCSVFile("src/CSVFiles/MotorPH Employee Data - Leave.csv"); // Update the CSV file after approval/rejection
    }

    // Override the updateCSVFile method to update the CSV with processed data
    @Override
    public void updateCSVFile(String csvFilePath) {
        try {
            List<String[]> rows;
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                rows = new ArrayList<>();
                String line;
                // Read all rows into a list
                while ((line = reader.readLine()) != null) {
                    rows.add(line.split(","));
                }
            }
            
            // Find the row corresponding to the requestID
            for (String[] row : rows) {
                if (row[0].equals(requestID)) {
                    // Update the specific columns 
                    row[8] = getStatus();               // Update Status column
                    row[9] = getProcessedBy();          // Update Processed By column
                    row[10] = getDateProcessed().toString(); // Update Date Processed column
                    row[11] = String.valueOf(getPayableDays()); // Update Payable days column
                }
            }     
            // Rewrite the updated rows back to the CSV file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                for (String[] row : rows) {
                    writer.write(String.join(",", row));
                    writer.newLine();
                }
            }

        } catch (IOException e) {
        }
    }

    // Calculate payable days (if approved, set to total days, otherwise set to 0)
    public void calculatePayableDays() {
        if (getStatus().equals("APPROVED")) {
            payableDays = totalDays; // Approved, so the days will be paid
        } else {
            payableDays = 0; // Rejected, so the leave won't be paid
        }
    }

    // Getter for payableDays
    public double getPayableDays() {
        return payableDays;
    }
}
