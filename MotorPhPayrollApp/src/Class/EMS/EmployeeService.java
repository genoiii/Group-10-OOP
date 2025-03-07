/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Class.EMS;

import CSVFileManager.CsvFile;
import Class.CollectionUtils;
import Class.TableModel;
import java.util.*;
import java.util.function.Function;
import javax.swing.table.DefaultTableModel;

/**
 * Service class for managing employee operations.
 *
 * <p>This class provides business logic for handling employees, including creating,
 * updating, and retrieving employee information. It serves as a central point for employee-related
 * operations and can be extended with additional methods as needed.</p>
 */
public class EmployeeService {
    private List<Employee> employeeList; // List of Employee objects loaded from the CSV file.      
    private Map<String, Employee> employeeMapByEmployeeID; // Map of employees keyed by employee ID for quick lookup.
    
    /**
     * Constructs an EmployeeService instance.
     *
     * <p>This constructor loads employee information from the CSV file into a list and
     * then creates a map keyed by employee ID for efficient access.</p>
     */    
    public EmployeeService() {
        this.employeeList = CsvFile.EMPLOYEEINFORMATION.readFile(Employee::new); // Load employee information from the CSV file using Employee's constructor.
        this.employeeMapByEmployeeID = CollectionUtils.listToMap(employeeList, Employee::getEmployeeID); // Convert the list of employees into a map where the key is the employee ID.
    }
    
    /**
     * Retrieves the list of employee records.
     *
     * @return the list of Employee objects.
     */
    public List<Employee> getEmployeeRecords(){
        return this.employeeList; // Return the in-memory list of employees.
    }
    
    /**
     * Retrieves the Employee object associated with the specified employee ID.
     *
     * @param employeeID the unique identifier for the employee.
     * @return the Employee object if found; otherwise, null.
     */ 
    public Employee getEmployeeInformation(String employeeID){        
        return employeeMapByEmployeeID.get(employeeID); // Return the Employee object from the map using the employeeID as the key.
    } 
      
    /**
     * Updates the information of an existing employee.
     *
     * <p>This method updates the employee information in both the in-memory map and list,
     * then writes the updated employee records back to the CSV file.</p>
     *
     * @param updatedEmployee the Employee object with updated information.
     */
    public void updateInformation(Employee updatedEmployee){
        // Check if the employee exists in the map.
        if (!employeeMapByEmployeeID.containsKey(updatedEmployee.getEmployeeID())) return; // Employee not found; exit the method.

        employeeMapByEmployeeID.replace(updatedEmployee.getEmployeeID(), updatedEmployee); // Update the employee information in the map.
        
        // Update the employee information in the list while preserving order.
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getEmployeeID().equals(updatedEmployee.getEmployeeID())) {
                employeeList.set(i, updatedEmployee);
                break;
            }
        }
        
        // Convert the updated employee list to a List of String arrays.
        List<String[]> updatedEmployeeRecord = new ArrayList<>();
        for (Employee employee : employeeList) {
            updatedEmployeeRecord.add(employee.getEmployeeInformation());
        }
        
        CsvFile.EMPLOYEEINFORMATION.writeFile(updatedEmployeeRecord); // Write the updated records to the CSV file.
    }

    /**
     * Deletes an employee using the provided employee ID.
     *
     * <p>This method removes the employee from the in-memory list, updates the employee's status to "Terminated"
     * in the Employee ID registry, converts the remaining employee records to a CSV-compatible format, and writes
     * the updated records back to the CSV file.</p>
     *
     * @param employeeID the unique identifier of the employee to delete.
     */
    public void deleteEmployee(String employeeID){
        // Check if the employee exists in the map (optional logging, currently commented out).
        if (!employeeMapByEmployeeID.containsKey(employeeID)) return; // Employee not found; exit the method.

        employeeList.removeIf(emp -> emp.getEmployeeID().equals(employeeID)); // Remove the employee from the list based on the employeeID.

        EmployeeIDRegistry.updateEmployeeStatus(employeeID, "Terminated"); // Update the employee's status to "Terminated" in the Employee ID registry.

        // Convert the remaining employees into a List of String arrays for CSV writing.
        List<String[]> updatedEmployeeRecord = new ArrayList<>();
        
        for (Employee employee : employeeList) {
            updatedEmployeeRecord.add(employee.getEmployeeInformation());
        }
        
        // Write the updated employee records back to the CSV file.
        CsvFile.EMPLOYEEINFORMATION.writeFile(updatedEmployeeRecord);
    }
    
    /**
     * Adds a new employee to the system.
     *
     * <p>This method generates a new employee ID, sets it to the employee, appends the employee's information
     * to the CSV file, and updates the in-memory list and map for quick access.</p>
     *
     * @param employee the Employee object to add.
     */  
    public void addEmployee(Employee employee){        
        String newID = EmployeeIDRegistry.generateNewEmployeeID(); // Generate a new unique employee ID and set it on the employee object.
        employee.setEmployeeID(newID);
            
        CsvFile.EMPLOYEEINFORMATION.appendFile(employee.getEmployeeInformation()); // Append the employee's information to the CSV file.

        // Update in-memory structures
        employeeList.add(employee);
        employeeMapByEmployeeID.put(employee.getEmployeeID(), employee);     
    }
    
    /**
     * Creates a DefaultTableModel for displaying employee records in a GUI.
     *
     * <p>This method selects specific columns from the CSV file's header, maps Employee objects to rows 
     * using selected attributes, and constructs a TableModel which is then converted to a DefaultTableModel.</p>
     *
     * @return a DefaultTableModel representing the employee records.
     */
    public DefaultTableModel getEmployeeTableModel(){
        int[] selectedColumns = {0,1,2,3,4,5}; // Array of indices indicating the selected columns to display.

        // Create an array to hold the selected column headers.
        String[] selectedHeaders = new String[selectedColumns.length];
        for (int i = 0; i < selectedColumns.length; i++) {
            selectedHeaders[i] = CsvFile.EMPLOYEEINFORMATION.getTableHeader()[selectedColumns[i]]; // Extract header for each selected column from the CSV file.
        }
        
        // Define a function that maps an Employee to a row of String values.
        Function<Employee, String[]> rowMapper = (Employee emp) -> {
            return new String[] {
                emp.getEmployeeID(), emp.getLastName(), emp.getFirstName(), emp.getBirthday(), emp.getAddress(),
                emp.getPhoneNumber()
            };
        };
        
        // Construct the TableModel using the employee list, selected headers, and the row mapper.
        TableModel employeeTableModel = new TableModel(this.employeeList, selectedHeaders, rowMapper); 
        return employeeTableModel.getTableModel();
    }
}
