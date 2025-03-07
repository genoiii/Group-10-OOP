/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import CSVFileManager.CsvFile;
import java.util.List;

/**
 * Manages a registry of Employee IDs.
 *
 * <p>This class is intended to store and manage unique employee identifiers.
 * Future implementations may include methods for adding, removing, and querying IDs.</p>
 */
public class EmployeeIDRegistry {
    private static List<String[]> employeeIDList = CsvFile.EMPLOYEEID.readFile(data -> data); // Static list holding employee IDs, loaded from the CSV file.  

    /**
     * Refreshes the employee ID list by re-reading data from the CSV file.
     *
     * <p>This method updates the employeeIDList by reading the latest employee IDs using the CsvFile.EMPLOYEEID enum.</p>
     */
    private static void refreshEmployeeIDList() {
        employeeIDList = CsvFile.EMPLOYEEID.readFile(data -> data);
    }

    /**
     * Retrieves the last issued employee ID from the registry.
     *
     * <p>If the employeeIDList is empty, this method returns a default starting ID of 10000.
     * Otherwise, it parses and returns the last ID in the list. In case of a parsing error,
     * it logs the error and returns the default value.</p>
     *
     * @return the last issued employee ID, or the default value (10000) if none exists or a parsing error occurs.
     */  
    public static int getLastIssuedID() {
        // Return default starting ID if the list is empty.
        if (employeeIDList.isEmpty()) return 10000; // Default starting ID
        
        try {
            return Integer.parseInt(employeeIDList.get(employeeIDList.size() - 1)[0]); // Parse the last employee ID from the list and return it.
        } catch (NumberFormatException e) {
            System.out.println("Error parsing last Employee ID: " + e.getMessage()); // Log the error and return the default starting ID in case of a parsing failure.
            return 10000; // Fallback to default
        }
    }

    /**
     * Generates a new employee ID in a thread-safe manner.
     *
     * <p>This method refreshes the employee ID list to ensure it has the latest data, increments the last issued ID by one,
     * appends the new ID with an "Active" status to the CSV file, refreshes the list again, and returns the new ID as a string.</p>
     *
     * @return the newly generated employee ID as a string.
     */    
    public static synchronized String generateNewEmployeeID() {        
        refreshEmployeeIDList(); // Refresh before generating to avoid duplicate ID generation

        int newID = getLastIssuedID() + 1; // Calculate the new ID by incrementing the last issued ID.
        String newIDString = String.valueOf(newID);

        CsvFile.EMPLOYEEID.appendFile(new String[]{newIDString, "Active"}); // Append the new ID with a status of "Active" to the CSV file.
        refreshEmployeeIDList(); // Refresh the list after updating the CSV to keep the in-memory list in sync.

        return newIDString; // Return the new employee ID.
    }

    /**
     * Updates the status of an employee record.
     *
     * <p>This method searches the employeeIDList for a record matching the provided employeeID.
     * If found, it updates the status to newStatus, writes the updated list back to the CSV file,
     * and refreshes the in-memory list. If the employeeID is not found, it logs a message.</p>
     *
     * @param employeeID the employee ID to update.
     * @param newStatus the new status to set (e.g., "Terminated").
     * @return true if the status was updated; false otherwise.
     */
    public static boolean updateEmployeeStatus(String employeeID, String newStatus) {
        boolean updated = false;
        
        // Iterate through each record in the employeeIDList.
        for (String[] record : employeeIDList) {
            // Check if the current record's ID matches the provided employeeID.
            if (record[0].equals(employeeID)) {
                record[1] = newStatus; // Update the status of the found record.
                updated = true;
                break;
            }
        }
        
        // If an update occurred, write the updated list back to the CSV and refresh the in-memory list.
        if (updated) {
            CsvFile.EMPLOYEEID.writeFile(employeeIDList);
            refreshEmployeeIDList(); // Refresh to synchronize with the CSV file after update
        } else {
            System.out.println("Employee ID " + employeeID + " not found."); // Log a message if the employeeID was not found.
        }

        return updated;
    }
}
