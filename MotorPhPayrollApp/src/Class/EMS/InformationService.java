/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import CSVFileManager.CsvFile;
import Class.CollectionUtils;
import Class.Formatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * Service class for managing and processing Information objects.
 *
 * <p>This class is intended to provide methods for retrieving, updating, and processing
 * various Information objects. Extend this class with business logic as needed.</p>
 */
public class InformationService {
    private List<PersonalInformation> personalRecord;
    private List<EmploymentInformation> employmentRecord;
    private List<GovernmentInformation> governmentRecord;
    private Map<String, PersonalInformation> personalRecordMapByEmployeeID;
    private Map<String, EmploymentInformation> employmentRecordMapByEmployeeID;
    private Map<String, GovernmentInformation> gorvernmentRecordMapByEmployeeID;
    
    /**
     * Constructs an InformationService instance.
     *
     * <p>This constructor loads personal, employment, and government records from their respective CSV files.
     * It then creates lookup maps keyed by employee ID for quick access to these records.</p>
     */  
    public InformationService(){
        personalRecord = CsvFile.PERSONAL_RECORD.readFile(row -> new PersonalInformation(row[0], row));
        employmentRecord = CsvFile.EMPLOYMENT_RECORD.readFile(row -> new EmploymentInformation(row[0], row));     
        governmentRecord = CsvFile.GOVERNMENT_RECORD.readFile(row -> new GovernmentInformation(row[0], row));
        
        this.personalRecordMapByEmployeeID = CollectionUtils.listToMap(personalRecord, PersonalInformation::getEmployeeID);
        this.employmentRecordMapByEmployeeID = CollectionUtils.listToMap(employmentRecord, EmploymentInformation::getEmployeeID);
        this.gorvernmentRecordMapByEmployeeID = CollectionUtils.listToMap(governmentRecord, GovernmentInformation::getEmployeeID);
    }

    public void addPersonalInformation(PersonalInformation newPersonalInformation){
            
        CsvFile.PERSONAL_RECORD.appendFile(newPersonalInformation.getInformation()); // Append the employee's information to the CSV file.

        // Update in-memory structures
        personalRecord.add(newPersonalInformation);
        personalRecordMapByEmployeeID.put(newPersonalInformation.getEmployeeID(), newPersonalInformation);     
    }
    
    public void addEmploymentInformation(EmploymentInformation newEmploymentInformation){
            
        CsvFile.EMPLOYMENT_RECORD.appendFile(newEmploymentInformation.getInformation()); // Append the employee's information to the CSV file.

        // Update in-memory structures
        employmentRecord.add(newEmploymentInformation);
        employmentRecordMapByEmployeeID.put(newEmploymentInformation.getEmployeeID(), newEmploymentInformation);     
    }
    
    public void addGovernmentInformation(GovernmentInformation newGovernmentInformation){
            
        CsvFile.GOVERNMENT_RECORD.appendFile(newGovernmentInformation.getInformation()); // Append the employee's information to the CSV file.

        // Update in-memory structures
        governmentRecord.add(newGovernmentInformation);
        gorvernmentRecordMapByEmployeeID.put(newGovernmentInformation.getEmployeeID(), newGovernmentInformation);     
    }
    
    

    /**
     * Updates the personal information of an employee.
     *
     * <p>This method updates the in-memory personal record (both map and list) for the specified employee.
     * It then writes the updated records to the CSV file.</p>
     *
     * @param updatedPersonalInformation the updated PersonalInformation object.
     */  
    public void updatePersonalInformation(PersonalInformation updatedPersonalInformation){
        // Check if the employee exists in the map.
        if (!personalRecordMapByEmployeeID.containsKey(updatedPersonalInformation.getEmployeeID())) return; // Employee not found; optionally log an error.

        personalRecordMapByEmployeeID.replace(updatedPersonalInformation.getEmployeeID(), updatedPersonalInformation); // Update the personal record in the map.
        
        // Update the record in the list, preserving the order.
        for (int i = 0; i < personalRecord.size(); i++) {
            if (personalRecord.get(i).getEmployeeID().equals(updatedPersonalInformation.getEmployeeID())) {
                personalRecord.set(i, updatedPersonalInformation);
                break;
            }
        }
        
        // Convert the updated personal records to a List<String[]> for CSV writing.
        List<String[]> updatedPersonalInformationRecord = new ArrayList<>();
        for (PersonalInformation personalInformation : personalRecord) {
            updatedPersonalInformationRecord.add(personalInformation.getInformation());
        }
        
        CsvFile.PERSONAL_RECORD.writeFile(updatedPersonalInformationRecord); // Write the updated records back to the CSV file.
    }
    
    /**
     * Updates the employment information for an employee.
     *
     * <p>This method updates the in-memory employment record (both map and list) for the specified employee.
     * After updating the records, it converts the updated list to a CSV-compatible format and writes it back
     * to the CSV file.</p>
     *
     * @param updatedEmploymentInformation the updated EmploymentInformation object.
     */
    public void updateEmploymentInformation(EmploymentInformation updatedEmploymentInformation){
        // Check if the employee exists in the employment map.
        if (!employmentRecordMapByEmployeeID.containsKey(updatedEmploymentInformation.getEmployeeID())) return; // Employee not found; optionally log an error.
        
        employmentRecordMapByEmployeeID.replace(updatedEmploymentInformation.getEmployeeID(), updatedEmploymentInformation); // Update the employment record in the map.
        
        // Update the record in the list while preserving the order.
        for (int i = 0; i < employmentRecord.size(); i++) {
            if (employmentRecord.get(i).getEmployeeID().equals(updatedEmploymentInformation.getEmployeeID())) {
                employmentRecord.set(i, updatedEmploymentInformation);
                break;
            }
        }
        
        // Convert the updated employment records to a List<String[]> for CSV writing.
        List<String[]> updatedEmploymentInformationRecord = new ArrayList<>();
        for (EmploymentInformation employmentInformation : employmentRecord) {
            updatedEmploymentInformationRecord.add(employmentInformation.getInformation());
        }
        
        CsvFile.EMPLOYMENT_RECORD.writeFile(updatedEmploymentInformationRecord); // Write the updated records back to the CSV file.
    }
    
    /**
     * Updates the government information for an employee.
     *
     * <p>This method updates the in-memory government record (both in a map and a list) for the specified employee.
     * It then converts the updated records to a CSV-compatible format and writes them back to the CSV file.</p>
     *
     * @param updatedGovernmentInformation the updated GovernmentInformation object.
     */
    public void updateGovernmentInformation(GovernmentInformation updatedGovernmentInformation){
        // Check if the government record map contains the employee.
        if (!gorvernmentRecordMapByEmployeeID.containsKey(updatedGovernmentInformation.getEmployeeID())) return; // Optionally log an error message if the employee is not found.
     
        gorvernmentRecordMapByEmployeeID.replace(updatedGovernmentInformation.getEmployeeID(), updatedGovernmentInformation); // Update the government record in the map.
        
        // Update the government record in the list while preserving order.
        for (int i = 0; i < governmentRecord.size(); i++) {
            if (governmentRecord.get(i).getEmployeeID().equals(updatedGovernmentInformation.getEmployeeID())) {
                governmentRecord.set(i, updatedGovernmentInformation);
                break;
            }
        }
        
        // Convert the updated government records to a List of String arrays for CSV writing.
        List<String[]> updatedGovernmentInformationRecord = new ArrayList<>();
        for (GovernmentInformation governmentInformation : governmentRecord) {
            updatedGovernmentInformationRecord.add(governmentInformation.getInformation());
        }
        
        CsvFile.GOVERNMENT_RECORD.writeFile(updatedGovernmentInformationRecord); // Write the updated records back to the CSV file.
    }
    
    public void deletePersonalInformation(String employeeID){
        // Check if the employee exists in the map (optional logging, currently commented out).
        if (!personalRecordMapByEmployeeID.containsKey(employeeID)) return; // Employee not found; exit the method.

        personalRecord.removeIf(emp -> emp.getEmployeeID().equals(employeeID)); // Remove the employee from the list based on the employeeID.

        // Convert the remaining employees into a List of String arrays for CSV writing.
        List<String[]> updatedPersonalRecord = new ArrayList<>();
        
        for (PersonalInformation personal : personalRecord) {
            updatedPersonalRecord.add(personal.getInformation());
        }
        
        // Write the updated employee records back to the CSV file.
        CsvFile.PERSONAL_RECORD.writeFile(updatedPersonalRecord);
    }
    
    public void deleteEmploymentInformation(String employeeID){
        // Check if the employee exists in the map (optional logging, currently commented out).
        if (!employmentRecordMapByEmployeeID.containsKey(employeeID)) return; // Employee not found; exit the method.

        employmentRecord.removeIf(emp -> emp.getEmployeeID().equals(employeeID)); // Remove the employee from the list based on the employeeID.

        // Convert the remaining employees into a List of String arrays for CSV writing.
        List<String[]> updatedEmploymentInformation = new ArrayList<>();
        
        for (EmploymentInformation employment : employmentRecord) {
            updatedEmploymentInformation.add(employment.getInformation());
        }
        
        // Write the updated employee records back to the CSV file.
        CsvFile.EMPLOYMENT_RECORD.writeFile(updatedEmploymentInformation);
    }
    
    public void deleteGovernmentInformation(String employeeID){
        // Check if the employee exists in the map (optional logging, currently commented out).
        if (!gorvernmentRecordMapByEmployeeID.containsKey(employeeID)) return; // Employee not found; exit the method.

        governmentRecord.removeIf(emp -> emp.getEmployeeID().equals(employeeID)); // Remove the employee from the list based on the employeeID.

        // Convert the remaining employees into a List of String arrays for CSV writing.
        List<String[]> updatedGovernmentInformation= new ArrayList<>();
        
        for (GovernmentInformation government : governmentRecord) {
            updatedGovernmentInformation.add(government.getInformation());
        }
        
        // Write the updated employee records back to the CSV file.
        CsvFile.GOVERNMENT_RECORD.writeFile(updatedGovernmentInformation);
    }
    
    /**
     * Retrieves the list of personal information records.
     *
     * @return a list of PersonalInformation objects.
     */    
    public List<PersonalInformation> getPersonalRecord(){
        return this.personalRecord; // Return the in-memory list of personal records.
    }
    
    /**
     * Retrieves the list of employment information records.
     *
     * @return a list of EmploymentInformation objects.
     */    
    public List<EmploymentInformation> getEmploymentRecord(){
        return this.employmentRecord; // Return the in-memory list of employment records.
    }
    
    /**
     * Retrieves the list of government information records.
     *
     * @return a list of GovernmentInformation objects.
     */ 
    public List<GovernmentInformation> getGovernmentRecord(){
        return this.governmentRecord; // Return the in-memory list of government records.
    }
    
    /**
     * Retrieves the PersonalInformation associated with the specified employee ID.
     *
     * @param employeeID the unique identifier of the employee.
     * @return the PersonalInformation object if found; otherwise, null.
     */ 
    public PersonalInformation getPersonalInformation(String employeeID){        
        return personalRecordMapByEmployeeID.get(employeeID); // Return the PersonalInformation from the lookup map using the employeeID.
    }
    
    /**
     * Retrieves the EmploymentInformation associated with the specified employee ID.
     *
     * @param employeeID the unique identifier of the employee.
     * @return the EmploymentInformation object if found; otherwise, null.
     */   
    public EmploymentInformation getEmploymentInformation(String employeeID){        
        return employmentRecordMapByEmployeeID.get(employeeID); // Return the EmploymentInformation from the lookup map using the employeeID.
    } 
    
    /**
     * Retrieves the GovernmentInformation associated with the specified employee ID.
     *
     * @param employeeID the unique identifier of the employee.
     * @return the GovernmentInformation object if found; otherwise, null.
     */
    public GovernmentInformation getGovernmentInformation(String employeeID){        
        return gorvernmentRecordMapByEmployeeID.get(employeeID); // Return the GovernmentInformation from the lookup map using the employeeID.
    } 
        
    public void updateInformation(){
        
    }
    
    public void addInformation(){
        
    }
    
    public DefaultTableModel getEmployeeInformationTableModel(){
        String[] columnNames = {
            "Employee ID", "Last Name", "First Name", "Birthdate", "Address", "Phone Number"
        };
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        
        String outputFormat = "M-d-yyyy";  // Desired format
        
        for (PersonalInformation personal : personalRecord) {
            // Construct a row using the aggregated values.
            Object[] row = {
                personal.getEmployeeID(),
                personal.getLastName(),
                personal.getFirstName(),
                Formatter.formatDate(personal.getBirthday(), outputFormat),
                personal.getAddress(),
                personal.getPhoneNumber()
            };

            model.addRow(row);
            
        }
        
        return model;
    }
}
