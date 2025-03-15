/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.CSVFileManagement;

import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author 63909
 */
public class CSVFileManager {
    private final CSVFile CSVFile;

    public CSVFileManager(CSVFile csvFile) {
        this.CSVFile = csvFile;
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
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(this.CSVFile.getFilePath(), true))) {
            csvWriter.writeNext(data);
        } catch (IOException e) {
            System.err.println("Error appending to CSV file: " + e.getMessage());
        }
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
        
        try (BufferedReader reader = new BufferedReader(new FileReader(this.CSVFile.getFilePath()))) {            
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
    
}
