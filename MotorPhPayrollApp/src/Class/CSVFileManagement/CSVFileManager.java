/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.CSVFileManagement;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 63909
 */
public class CSVFileManager {
    private CSVFile csvFile;

    public CSVFileManager(CSVFile csvFile) {
        this.csvFile = csvFile;
    }
    
    /**
     * Appends a row of CSV data to the CSV file.
     *
     * <p>This method uses a CSVWriter to append the provided data as a new row to the file located at
     * {@code filePath}. The file is opened in append mode, so any new data will be added to the end of the file.
     * If an I/O error occurs, an error message is printed to the standard error stream.</p>
     *
     * @param <T>
     * @param object
     */    
    public <T> void appendFile(T object) {
        String[] record = CSVFileSerializer.toCsv(object);
        // Use try-with-resources to create a CSVWriter that automatically closes after use.
        // FileWriter is initialized in append mode (true) so new data is added to the end of the file.
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(this.csvFile.getFilePath(), true))) {
            csvWriter.writeNext(record);
        } catch (IOException e) {
            System.err.println("Error appending to CSV file: " + e.getMessage());
        }
    } 
    
}
