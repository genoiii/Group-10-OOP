/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CSVFileManager;

import com.opencsv.CSVWriter;
import java.io.*;
import java.util.*;

public class File {    
    private String fileName;
    private String filePath;
    private ArrayList<String[]> dataFile = null;
    private BufferedReader reader = null;
    private CSVWriter csvWriter = null;
    
    // Constructor initializes fileName and filePath
    public File(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }    
    
    // Method to read the CSV file and return data as ArrayList of String[]
    public ArrayList<String[]> readFile() {         
        dataFile = new ArrayList<String[]>();
        String line;

        try {
            reader = new BufferedReader(new FileReader(this.filePath));

            // Read line by line
            while ((line = reader.readLine()) != null) {
                try {
                    // Split CSV line considering quoted commas
                    String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    
                    // Clean up each cell by trimming and removing quotes
                    for (int j = 0; j < row.length; j++) {
                        row[j] = row[j].trim();
                        row[j] = row[j].replace("\"", "");
                    }
                    
                    // Add row to dataFile
                    dataFile.add(row);
                } catch (Exception ex) {
                    System.err.println("Error parsing line: " + line);
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + this.filePath);
            e.printStackTrace();
        } finally {
            try {
               if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }    
        }

        return dataFile;
    }
    
    // Method to write data to a CSV file
    public void writeFile(String filePath, ArrayList<String[]> dataFile) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
            // Iterate through dataFile and write each row to the file
            for (String[] line : dataFile) {
                csvWriter.writeNext(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    // Getter methods
    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<String[]> getDataFile() {
        return dataFile;
    } 
}