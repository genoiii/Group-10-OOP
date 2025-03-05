/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CSVFileManager;

import com.opencsv.CSVWriter;
import java.io.*;
import java.util.*;
import java.util.function.Function;

// Enum representing different CSV files used in the system
public enum CsvFile {
    // Enum constants representing different CSV file paths
    USER("src/CSVFiles/MotorPH Employee Data - User Details.csv"),
    EMPLOYEEINFORMATION("src/CSVFiles/MotorPH Employee Data - Employee Details.csv"),
    PERSONAL_RECORD("src/CSVFiles/MotorPH Employee Data - Employee Personal Information.csv"),
    EMPLOYMENT_RECORD("src/CSVFiles/MotorPH Employee Data - Employee Employment Information.csv"),
    GOVERNMENT_RECORD("src/CSVFiles/MotorPH Employee Data - Employee Government Information.csv"),
    EMPLOYEEID("src/CSVFiles/MotorPH Employee Data - Employee IDs.csv"),
    DAILYATTENDANCE("src/CSVFiles/MotorPH Employee Data - Daily Attendance.csv"),
    PAYPERIOD("src/CSVFiles/MotorPH Employee Data - Pay Period Schedule.csv");
    
    public final String filePath; // Stores the file path of the CSV file
    
    // Constructor to initialize file path for each enum constant
    private CsvFile(String filePath) {
        this.filePath = filePath;
    }    
    
    /**
     * Reads data from the CSV file and returns it as a List of String arrays.
     * Each String array represents a row in the CSV file.
     * @param <T>
     * @param mapper
     * @return 
     */
    public <T> List<T> readFile(Function<String[], T> mapper) {
        boolean isFirstRow = true;
        List<T> dataFile = new ArrayList<>(); // List to store CSV rows
        String line; // Variable to store each line read from the file
        
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {            
            // Read each line from the file until the end is reached
            while((line = reader.readLine()) != null){
                String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split the line into an array using commas as delimiters 
                                                                                    // while correctly handling quoted values (avoiding splitting within quotes)
                                
                // Clean up each cell by trimming and removing quotes
                for (int j = 0; j < row.length; j++) { // Iterate through each element in the row
                    row[j] = row[j].trim(); // Remove leading and trailing spaces from the cell value
                    row[j] = row[j].replace("\"", ""); // Remove any double quotes surrounding the cell value
                }
                if (isFirstRow){
                    //tableHeader = row; 
                    isFirstRow = false;
                    continue;
                }                
                dataFile.add(mapper.apply(row)); // Add the processed row to the dataFile list
            }
        }catch(Exception e) {
            e.printStackTrace(); // Print stack trace if an exception occurs during file reading
        }
        return dataFile; // Return the list containing parsed CSV data
    }
            
    /**
    * Writes data to a CSV file from a given List of String arrays.
    * Each String array represents a row in the CSV file.
    * The method ensures that the file is properly closed after writing.
    * 
    * @param dataFile The list of string arrays to be written to the CSV file.
    */
    public void writeFile(List<String[]> dataFile) {
        // Check if the provided data list is empty to avoid writing an empty file.
        if (dataFile.isEmpty()) {
            System.err.println("Warning: Attempted to write an empty data list to CSV.");
            return;
        }
        
        // Retrieve the header row for the CSV file.
        String[] tableHeader = getTableHeader();
        
        // Use try-with-resources to automatically close CSVWriter after writing.
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(this.filePath))) {  
            // If a valid table header exists (not null and has elements), write it to the file.
            if (tableHeader != null && tableHeader.length > 0) {
                csvWriter.writeNext(tableHeader);
            } else {
                System.err.println("Warning: Table header is empty. Skipping header write."); // Log a warning message if the table header is empty or null.
            }

            // Iterate through each row in the data list and write it to the CSV file.
            for (String[] row : dataFile) {
                csvWriter.writeNext(row);
            }
        // Catch any IOException that occurs during file writing.
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage()); // Log the error message to the standard error stream.
        }
    }
        
    /**
     * Appends a row of CSV data to the CSV file.
     *
     * <p>This method uses a CSVWriter to append the provided data as a new row to the file located at
     * {@code filePath}. The file is opened in append mode, so any new data will be added to the end of the file.
     * If an I/O error occurs, an error message is printed to the standard error stream.</p>
     *
     * @param data an array of String values representing a row of CSV data to append to the file.
     */    
    public void appendFile(String[] data) {
        // Use try-with-resources to create a CSVWriter that automatically closes after use.
        // FileWriter is initialized in append mode (true) so new data is added to the end of the file.
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(this.filePath, true))) {
            csvWriter.writeNext(data);
        } catch (IOException e) {
            System.err.println("Error appending to CSV file: " + e.getMessage());
        }
    }   
    
    /**
     * Retrieves the CSV file header.
     *
     * <p>Reads the first line from the file, splits it on commas (ignoring commas within quotes),
     * trims each value, and removes surrounding quotes. Returns an empty array if no valid header is found.</p>
     *
     * @return the header array, or an empty array if missing.
     */
    public String[] getTableHeader() {
         // Open the file using try-with-resources.
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {     
            
            String headerLine = reader.readLine(); // Read the first line (header row).
            
            // Validate the header line.
            if (headerLine != null && !headerLine.trim().isEmpty()) {
                String[] header = headerLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split on commas not inside quotes.

                // Trim and remove quotes from each header element.
                for (int i = 0; i < header.length; i++) {
                    header[i] = header[i].trim().replace("\"", ""); // Remove surrounding quotes
                }
                return header;
            } else {
                System.err.println("Warning: CSV file has an empty or missing header.");
            }
        } catch (IOException e) {
            System.err.println("Error reading table header: " + e.getMessage());
        }

        return new String[0]; // Return empty array if header is not found
    }
    
    // Getter methods
    public String getFilePath() {
        return filePath;
    }
}