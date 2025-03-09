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

public class ApproveOvertime extends ApproveRequest {
    public double totalOvertime;
    private double payableHours;

    // Constructor that accepts requestID and fetches data from the CSV
    public ApproveOvertime(String requestID) {
        super("PENDING");  // Default status as "PENDING"
        loadDataFromCSV(requestID);  // Load the overtime data from the CSV file based on the requestID
    }

    // Method to load data from the CSV file based on the requestID
    public void loadDataFromCSV(String requestID) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/CSVFiles/MotorPH Employee Data - Overtime.csv"));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                
                // RequestID is at index 0
                if (columns[0].equals(requestID)) {
                    // Extract the data from CSV and assign to fields
                    this.requestID = requestID;
                    this.totalOvertime = Double.parseDouble(columns[5]);
                    found = true;
                    break;
                }
            }
            reader.close();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Method to approve or reject the overtime request
    public void approveOT(boolean approve) {
        if (approve) {
            setStatus("APPROVED");
            calculatePayableHours(); // Calculate payable hours if approved
        } else {
            setStatus("REJECTED");
            payableHours = 0; // Set to 0 if rejected
        }
        updateCSVFile("src/CSVFiles/MotorPH Employee Data - Overtime.csv"); // Update the CSV file after approval/rejection
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
                    row[7] = getStatus();               // Update Status column
                    row[8] = getProcessedBy();          // Update Processed By column
                    row[9] = getDateProcessed().toString(); // Update Date Processed column
                    row[10] = String.valueOf(getPayableHours()); // Update Payable hours column
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

    // Calculate payable hours (if approved, set to total overtime, otherwise set to 0)
    public void calculatePayableHours() {
        if (getStatus().equals("APPROVED")) {
            payableHours = totalOvertime; // Approved, so the hours will be paid
        } else {
            payableHours = 0; // Rejected, so the overtime won't be paid
        }
    }

    // Getter for payableHours
    public double getPayableHours() {
        return payableHours;
    }
}