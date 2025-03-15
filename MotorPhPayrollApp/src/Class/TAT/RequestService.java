/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import CSVFileManager.CsvFile;
import Class.CSVFileManagement.CSVFileSerializer;
import Class.CollectionUtils;
import Class.EMS.Employee;
import Class.EMS.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 63909
 */
public class RequestService {
    private List<Request> requestList;
    private List<Leave> leaveList;
    private List<Overtime> overtimeList;
    private Map<String, Request> requestMapByRequestID;
    private Map<String, Request> requestMapByRequestTypeID;
    private Map<String, Leave> leaveMapByLeaveID;
    private Map<String, LeaveType> leaveMapByLeaveTypeID;
    private Map<String, Overtime> overtimeMapByOvertimeID;

    public RequestService() {
        this.requestList =  CsvFile.REQUEST.readFile(Request::new);
        this.leaveList =  CsvFile.LEAVE.readFile(Leave::new);
        this.overtimeList =  CsvFile.OVERTIME.readFile(Overtime::new);
        
        this.requestMapByRequestID = CollectionUtils.listToMap(requestList, Request::getID);
        this.requestMapByRequestTypeID = CollectionUtils.listToMap(requestList, Request::getRequestTypeID);
        this.leaveMapByLeaveID = CollectionUtils.listToMap(leaveList, Leave::getID);
        this.overtimeMapByOvertimeID = CollectionUtils.listToMap(overtimeList, Overtime::getID);
    }
    
    public Leave getLeaveRecord(String leaveID){
        if (leaveID == null || !this.leaveMapByLeaveID.containsKey(leaveID)) {
            return null;
        }
        return this.leaveMapByLeaveID.get(leaveID);
    }
    
    public Overtime getOvertimeRecord(String overtimeID){
        if (overtimeID == null || !this.overtimeMapByOvertimeID.containsKey(overtimeID)) {
            return null;
        }
        return this.overtimeMapByOvertimeID.get(overtimeID);
    }
    
    public Request getRequestRecord(String requestTypeID){
        return this.requestMapByRequestTypeID.get(requestTypeID);
    }
    
    public DefaultComboBoxModel<String> getStatusComboBoxModel(){
        // Convert payPeriodList to a String[]
            String[] statusArray = {"All", "PENDING", "APPROVED", "REJECTED"};

        return new DefaultComboBoxModel<String>(statusArray); // Use DefaultComboBoxModel
    }
    
    public DefaultTableModel getLeaveRequestTableModel() {   
        String[] columnNames = {
            "Request ID", "Request Date", "Employee Name",
            "Start Date", "End Date", "Leave Type", "Total Days", "Notes", "Status"
        };
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        
        EmployeeService employeeService = new EmployeeService();
        
        for (Leave leaveRequest : leaveList) {
            
            Employee employee = employeeService.getEmployeeInformation(leaveRequest.getEmployeeID());
            Request request = getRequestRecord(leaveRequest.getID());
            // Construct a row using the aggregated values.
            Object[] row = {
                leaveRequest.getID(),
                request.getRequestDate(),
                employee.getFirstName() + " " + employee.getLastName(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getLeaveType(),
                leaveRequest.getTotalDays(),
                request.getReason(),
                request.getStatus()
            };

            model.addRow(row);
            
        }
        
        return model;
    }
    
    public DefaultTableModel getOvertimeRequestTableModel() {   
        String[] columnNames = {
            "Request ID", "Request Date", "Employee Name",
            "Start Time", "End Time", "Total Hours", "Notes", "Status"
        };
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        
        EmployeeService employeeService = new EmployeeService();
        
        for (Overtime overtimeRequest : overtimeList) {
            
            Employee employee = employeeService.getEmployeeInformation(overtimeRequest.getEmployeeID());
            Request request = getRequestRecord(overtimeRequest.getID());
            // Construct a row using the aggregated values.
            Object[] row = {
                overtimeRequest.getID(),
                request.getRequestDate(),
                employee.getFirstName() + " " + employee.getLastName(),
                overtimeRequest.getStartTime(),
                overtimeRequest.getEndTime(),
                overtimeRequest.getTotalHours(),
                request.getReason(),
                request.getStatus()
            };

            model.addRow(row);
            
        }
        
        return model;
    }
    
    public void saveRequestRecord(Request updatedRequest){
        // Check if the employee exists in the map.
        if (!requestMapByRequestTypeID.containsKey(updatedRequest.getRequestTypeID())) return; // Employee not found; exit the method.

        requestMapByRequestTypeID.replace(updatedRequest.getRequestTypeID(), updatedRequest); // Update the employee information in the map.
        
        // Update the employee information in the list while preserving order.
        for (int i = 0; i < requestList.size(); i++) {
            if (requestList.get(i).getID().equals(updatedRequest.getID())) {
                requestList.set(i, updatedRequest);
                break;
            }
        }
        
        // Convert the updated employee list to a List of String arrays.
        List<String[]> updatedRequestRecord = new ArrayList<>();
        for (Request request : requestList) {
            updatedRequestRecord.add(CSVFileSerializer.toCsv(request));
        }
        
        CsvFile.REQUEST.writeFile(updatedRequestRecord); // Write the updated records to the CSV file.
    }
    
    public void saveLeaveRecord(Leave updatedLeave){
        // Check if the employee exists in the map.
        if (!leaveMapByLeaveID.containsKey(updatedLeave.getID())) return; // Employee not found; exit the method.

        leaveMapByLeaveID.replace(updatedLeave.getID(), updatedLeave); // Update the employee information in the map.
        
        // Update the employee information in the list while preserving order.
        for (int i = 0; i < leaveList.size(); i++) {
            if (leaveList.get(i).getID().equals(updatedLeave.getID())) {
                leaveList.set(i, updatedLeave);
                break;
            }
        }
        
        // Convert the updated employee list to a List of String arrays.
        List<String[]> updatedLeaveRecord = new ArrayList<>();
        for (Leave leave : leaveList) {
            updatedLeaveRecord.add(CSVFileSerializer.toCsv(leave));
        }
        
        CsvFile.LEAVE.writeFile(updatedLeaveRecord); // Write the updated records to the CSV file.
    }
    
    public void saveOvertimeRecord(Overtime updatedOvertime){
        // Check if the employee exists in the map.
        if (!overtimeMapByOvertimeID.containsKey(updatedOvertime.getID())) return; // Employee not found; exit the method.

        overtimeMapByOvertimeID.replace(updatedOvertime.getID(), updatedOvertime); // Update the employee information in the map.
        
        // Update the employee information in the list while preserving order.
        for (int i = 0; i < overtimeList.size(); i++) {
            if (overtimeList.get(i).getID().equals(updatedOvertime.getID())) {
                overtimeList.set(i, updatedOvertime);
                break;
            }
        }
        
        // Convert the updated employee list to a List of String arrays.
        List<String[]> updatedOvertimeRecord = new ArrayList<>();
        for (Overtime overtime : overtimeList) {
            updatedOvertimeRecord.add(CSVFileSerializer.toCsv(overtime));
        }
        
        CsvFile.OVERTIME.writeFile(updatedOvertimeRecord); // Write the updated records to the CSV file.
    }
}
