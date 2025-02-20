/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.*;

/**
 *
 * @author 63909
 */

/**
 * DataMap class that stores data from a CSV file into a HashMap for quick lookup.
 * Each entry in the HashMap uses a specific column value as the key and the full row as the value.
 */
public class DataMap {
    private HashMap<String,String[]> dataMap; // HashMap to store CSV data, using a specific column value as the key for fast retrieval
    
    /**
     * Constructs a DataMap object by storing data from a given list into a HashMap.
     * The key for each entry is determined by the specified column index.
     * 
     * @param dataFile The list of string arrays representing CSV rows.
     * @param keyColumnNumber The index of the column to be used as the key in the HashMap.
     */
    public DataMap(ArrayList<String[]> dataFile, int keyColumnNumber) {
        this.dataMap = new HashMap<String, String[]>(); // Initialize the HashMap to store the CSV data
        
        // Iterates through each string in userData
        for (String[] i : dataFile){
            this.dataMap.put(i[keyColumnNumber],i); // Store the row in the HashMap using the value from the keyColumnNumber as the key
        }   
    }
    
    /**
     * Retrieves the HashMap containing the CSV data.
     * 
     * @return The HashMap where keys are specified column values and values are full rows.
     */
    public HashMap<String, String[]> getDataMap() {
        return dataMap;// Return the stored data map
    }   
}
