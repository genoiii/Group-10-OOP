/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import CSVFileManager.CsvFile;
import java.util.HashMap;

/**
 *
 * @author 63909
 */

/**
 * The Query class provides a way to retrieve specific data from a mapped dataset.
 * It allows searching for a specific key and retrieving data from a specified index.
 */
public class Query {
    private CsvFile csvFile; // Reference to the CSV file associated with the dataset
    private HashMap<String,String[]> dataMap; // HashMap storing data, where keys are unique identifiers (e.g., usernames, IDs) 
                                              // and values are arrays representing rows of data

    /**
     * Constructs a Query object with a given CSV file and data mapping.
     * 
     * @param csvFile The CSV file containing the data.
     * @param dataMap The HashMap storing the parsed data from the CSV file.
     */    
    public Query(CsvFile csvFile, HashMap<String,String[]> dataMap) {
        this.csvFile = csvFile;
        this.dataMap = dataMap;
    }  
    
    /**
     * Retrieves a single data value from the dataMap based on a given search key and column index.
     * Ensures the index is within bounds before accessing the data.
     * 
     * @param searchKey The key used to locate the corresponding row in the dataset.
     * @param index The column index from which the data should be retrieved.
     * @return The value at the specified index if the key exists and the index is valid; 
     *         otherwise, returns "Not Found" or "Index Out of Bounds".
     */    
    public String singleDataQuery(String searchKey, int index){        
        // Check if the key exists in the dataset
        if(this.dataMap.containsKey(searchKey)){
            String[] rowData = this.dataMap.get(searchKey); // Retrieve the row data corresponding to the search key
            
            // Ensure the index is within valid bounds
            if (index >= 0 && index < rowData.length) {                  
                return rowData[index];  
            } else {  
                return "Index Out of Bounds"; // Return an error message if the requested index is out of range
            }
        } else {  
            return "Not Found"; // Return "Not Found" if the key does not exist in the dataset
        }  
    }
    
    public void deleteQuery(){    
    
    }
    
    public void updateQuery(){    
    
    }
}
