/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CSVFileManager;

import com.opencsv.CSVWriter;
import java.io.*;
import java.util.*;

// Enum representing different CSV files used in the system
public enum CsvFile {
    // Enum constants representing different CSV file paths
    USER("src/CSVFiles/MotorPH Employee Data - User Details.csv"),
    EMPLOYEEINFORMATION("src/CSVFiles/MotorPH Employee Data - Employee Details.csv"),
    ATTENDANCE("src/CSVFiles/MotorPH Employee Data - Attendance.csv"),
    PAYPERIOD("src/CSVFiles/MotorPH Employee Data - Pay Period Schedule.csv");
    
    public final String filePath; // Stores the file path of the CSV file
    private BufferedReader reader = null;
    private CSVWriter csvWriter = null;
    ArrayList<String[]> dataFile = null;

    // Constructor to initialize file path for each enum constant
    private CsvFile(String filePath) {
        this.filePath = filePath;
    }    
    
    /**
     * Reads data from the CSV file and returns it as a List of String arrays.
     * Each String array represents a row in the CSV file.
     * @return 
     */
    public ArrayList<String[]> readFile() {         
        dataFile = new ArrayList<>(); // List to store CSV rows
        String line; // Variable to store each line read from the file
        
        try {
            reader = new BufferedReader(new FileReader(this.filePath)); // Initialize BufferedReader to read the file located at filePath
            
            // Read each line from the file until the end is reached
            while((line = reader.readLine()) != null){
                String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split the line into an array using commas as delimiters 
                                                                                    // while correctly handling quoted values (avoiding splitting within quotes)

                // Clean up each cell by trimming and removing quotes
                for (int j = 0; j < row.length; j++) { // Iterate through each element in the row
                    row[j] = row[j].trim(); // Remove leading and trailing spaces from the cell value
                    row[j] = row[j].replace("\"", ""); // Remove any double quotes surrounding the cell value
                }
                dataFile.add(row); // Add the processed row to the dataFile list
            }
        }catch(Exception e) {
            e.printStackTrace(); // Print stack trace if an exception occurs during file reading
        }finally {
            try {
               reader.close(); // Ensure the BufferedReader is closed to free up system resources
            } catch (IOException e) {
                e.printStackTrace(); // Print stack trace if an exception occurs while closing the reader
            }    
        }
        return dataFile; // Return the list containing parsed CSV data
    }

//        try {
//            reader = new BufferedReader(new FileReader(this.filePath));
//
//            // Read line by line
//            while ((line = reader.readLine()) != null) {
//                try {
//                    // Split CSV line considering quoted commas
//                    String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//                    
//                    // Clean up each cell by trimming and removing quotes
//                    for (int j = 0; j < row.length; j++) {
//                        row[j] = row[j].trim();
//                        row[j] = row[j].replace("\"", "");
//                    }
//                    
//                    // Add row to dataFile
//                    dataFile.add(row);
//                } catch (Exception ex) {
//                    System.err.println("Error parsing line: " + line);
//                    ex.printStackTrace();
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading file: " + this.filePath);
//            e.printStackTrace();
//        } finally {
//            try {
//               if (reader != null) reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }    
//        }

        
    
    /**
    * Writes data to a CSV file from a given List of String arrays.
    * Each String array represents a row in the CSV file.
    * The method ensures that the file is properly closed after writing.
    * 
    * @param dataFile The list of string arrays to be written to the CSV file.
    */
    public void writeFile(ArrayList<String[]> dataFile) {
        this.dataFile = dataFile; // Store the passed dataFile reference in the class attribute
        String row = ""; // Declare a string variable (though it's unused in this method)
        
        try {
            csvWriter = new CSVWriter(new FileWriter(filePath)); // Initialize CSVWriter with FileWriter to write data to the specified filePath
            
            for (String[] line : this.dataFile) {  // Iterate through each row (array of strings) in dataFile
//                row = String.join(",", line);
//                row = row.substring(1, row.length() - 1);
//                writer.write(row);
//                writer.write("\n");
                  csvWriter.writeNext(line); // Write the row to the CSV file
            }
            
        }catch(Exception e) {
            e.printStackTrace(); // Print stack trace if an exception occurs during writing
        }finally {
            try {
               csvWriter.close(); // Ensure the CSVWriter is closed to free up system resources
            } catch (IOException e) {
                e.printStackTrace(); // Print stack trace if an exception occurs while closing the writer
            }    
        }
    }
//    public void writeFile(ArrayList<String[]> dataFile) {
//        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(this.filePath))) {
//            // Iterate through dataFile and write each row to the file
//            for (String[] line : dataFile) {
//                csvWriter.writeNext(line);
//            }
//        } catch (IOException e) {
//            System.err.println("Error writing to file: " + this.filePath);
//            e.printStackTrace();
//        }
//    }
    
    public void appendFile(String[] data) {
        
    }
    
    // Getter methods
    public String getFilePath() {
        return filePath;
    }

    public ArrayList<String[]> getDataFile() {
        return dataFile;
    } 
}