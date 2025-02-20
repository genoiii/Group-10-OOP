/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 63909
 */

/**
 * The Table class manages a DefaultTableModel for employee data.
 * It provides methods to clear and populate the table model with employee records.
 */
public class Table {
    private DefaultTableModel employeeTableModel; // Table model for storing employee data in a tabular format
    
    /**
     * Default constructor. 
     */    
    public Table(){        
    }
    
    /**
     * Clears all rows in the employee table model.
     * 
     * @return The cleared DefaultTableModel.
     */
    public DefaultTableModel clearModel() {
        // Ensure the table model is not null before attempting to clear
        if (employeeTableModel != null) {              
            employeeTableModel.setRowCount(0); // Remove all rows from the model
        }
        return employeeTableModel; // Return the cleared model
    }
    
    /**
     * Sets the table model with the provided employee list.
     * The first row in the list is used as the table header.
     * 
     * @param employeeList A list of employee data, where each row is a String array.
     * @return The populated DefaultTableModel.
     */
    public DefaultTableModel setModel(ArrayList<String[]> employeeList){
        employeeTableModel = new DefaultTableModel(0, 0){
            @Override
            public boolean isCellEditable(int i, int j) {
                return false; // Makes all cells non-editable
            }
        };
        
        if (employeeList.isEmpty()) {  
            return employeeTableModel; // Return an empty model if the employee list is empty
        }
        
        for (int i = 0; i < employeeList.size(); i++) {
            // First row is used as the table header
            if (i == 0) {
                String[] tableHeader = {employeeList.get(i)[0],
                                        employeeList.get(i)[1],
                                        employeeList.get(i)[2],
                                        employeeList.get(i)[3],
                                        employeeList.get(i)[4],
                                        employeeList.get(i)[5]};
                employeeTableModel.setColumnIdentifiers(tableHeader);
                continue; // Skip to the next iteration after setting headers
            }
            
            String[] employeeRow = {employeeList.get(i)[0],
                                    employeeList.get(i)[1],
                                    employeeList.get(i)[2],
                                    employeeList.get(i)[3],
                                    employeeList.get(i)[4],
                                    employeeList.get(i)[5]};                                                
            employeeTableModel.addRow(employeeRow); // Add employee row data to the table model
        }        
        return employeeTableModel; // Return the populated table model
    }    
    
}
