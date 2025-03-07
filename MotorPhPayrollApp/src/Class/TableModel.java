/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.*;
import java.util.function.Function;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 63909
 */

/**
 * The Table class manages a DefaultTableModel for employee data.
 * It provides methods to clear and populate the table model with employee records.
 */
public class TableModel<T> {
    private DefaultTableModel tableModel; // TableModel model for storing employee data in a tabular format
    private List<T> dataFile;
    private int[] selectedColumns;
    private String[] customHeaders;  
    Function<T, String[]> rowMapper;
    
    /**
     * Default constructor. 
     */    
    public TableModel() {
        tableModel = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
    }  
    
    public TableModel(List<T> dataFile, String[] columnHeaders, Function<T, String[]> rowMapper) {
        this.dataFile = dataFile;
        this.rowMapper = rowMapper;
        
        tableModel = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        if (!dataFile.isEmpty()) {
            tableModel.setColumnIdentifiers(columnHeaders);

            // Add data rows
            for (T item : dataFile) {
                String[] rowData = rowMapper.apply(item);
                tableModel.addRow(rowData);
            }
        }
    }
    
    public TableModel(ArrayList<String[]> dataFile, int[] selectedColumns) {
        tableModel = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        if (!dataFile.isEmpty()) {
            // Set column headers based on selected indices
            String[] columnHeaders = new String[selectedColumns.length];
            for (int i = 0; i < selectedColumns.length; i++) {
                columnHeaders[i] = dataFile.get(0)[selectedColumns[i]];
            }
            tableModel.setColumnIdentifiers(columnHeaders);

            // Add data rows based on selected columns
            for (int i = 1; i < dataFile.size(); i++) { // Skip header row
                String[] rowData = new String[selectedColumns.length];
                for (int j = 0; j < selectedColumns.length; j++) {
                    rowData[j] = dataFile.get(i)[selectedColumns[j]];
                }
                tableModel.addRow(rowData);
            }
        }
    }
    
    /**
     * Clears all rows in the employee table model.
     * 
     * @return The cleared DefaultTableModel.
     */
    public DefaultTableModel clearModel() {
        // Ensure the table model is not null before attempting to clear
        if (tableModel != null) {              
            tableModel.setRowCount(0); // Remove all rows from the model
        }
        return tableModel; // Return the cleared model
    }
    
    public void setTableData(List<T> dataFile) {
        this.dataFile = dataFile;
        updateTableModel();
    }

    public void setSelectedColumns(int[] selectedColumns) {
        this.selectedColumns = selectedColumns;
        updateTableModel();
    }

    public void setCustomHeaders(String[] customHeaders) {
        this.customHeaders = customHeaders;
        updateTableModel();
    }

    private void updateTableModel() {
//        tableModel.setRowCount(0); // Clear table model
//
//        if (dataFile == null || dataFile.isEmpty() || selectedColumns == null || customHeaders == null) {
//            return; // Ensure all data is set before updating
//        }
//
//        // Validate that the number of selected columns matches the custom headers
//        if (selectedColumns.length != customHeaders.length) {
//            throw new IllegalArgumentException("Mismatch between selected columns and custom headers");
//        }
//
//        tableModel.setColumnIdentifiers(customHeaders); // Set custom column headers
//
//        for (int i = 1; i < dataFile.size(); i++) { // Skip header row
//            String[] rowData = new String[selectedColumns.length];
//            for (int j = 0; j < selectedColumns.length; j++) {
//                rowData[j] = dataFile.get(i)[selectedColumns[j]];
//            }
//            tableModel.addRow(rowData);
//        }
        tableModel.setRowCount(0); // Clear existing table data

        if (dataFile == null || dataFile.isEmpty() || selectedColumns == null || customHeaders == null) {
            return; // Ensure all necessary data is available
        }

        // Validate column-header consistency
        if (selectedColumns.length != customHeaders.length) {
            throw new IllegalArgumentException("Mismatch between selected columns and custom headers");
        }

        tableModel.setColumnIdentifiers(customHeaders); // Set custom column headers

        // Loop through dataFile and extract only selected columns
        for (T item : dataFile) {
            String[] rowData = rowMapper.apply(item); // Convert object to String[]
            tableModel.addRow(rowData);
        }

        tableModel.fireTableDataChanged(); // Notify JTable of changes
    }

    public DefaultTableModel getTableModel() {        
        return tableModel;
    }
    
    
    
    /**
     * Sets the table model with the provided employee list.
     * The first row in the list is used as the table header.
     * 
     * @param employeeList A list of employee data, where each row is a String array.
     * @return The populated DefaultTableModel.
     */
    public DefaultTableModel setModel(ArrayList<String[]> employeeList){
        tableModel = new DefaultTableModel(0, 0){
            @Override
            public boolean isCellEditable(int i, int j) {
                return false; // Makes all cells non-editable
            }
        };
        
        if (employeeList.isEmpty()) {  
            return tableModel; // Return an empty model if the employee list is empty
        }
        
        for (int i = 0; i < employeeList.size(); i++) {
            // First row is used as the table header
            if (i == 0) {
//                String[] tableHeader = {employeeList.get(i)[0],
//                                        employeeList.get(i)[1],
//                                        employeeList.get(i)[2],
//                                        employeeList.get(i)[3],
//                                        employeeList.get(i)[4],
//                                        employeeList.get(i)[5]};
//                employeeTableModel.setColumnIdentifiers(tableHeader);
                continue; // Skip to the next iteration after setting headers
            }
            
            String[] employeeRow = {employeeList.get(i)[0],
                                    employeeList.get(i)[1],
                                    employeeList.get(i)[2],
                                    employeeList.get(i)[3],
                                    employeeList.get(i)[4],
                                    employeeList.get(i)[5]};                                                
            tableModel.addRow(employeeRow); // Add employee row data to the table model
        }        
        return tableModel; // Return the populated table model
    }
    
}
