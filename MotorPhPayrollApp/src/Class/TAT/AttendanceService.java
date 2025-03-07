/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import CSVFileManager.CsvFile;
import Class.CollectionUtils;
import Class.EMS.*;
import Class.PPS.PayPeriod;
import Class.TableModel;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 63909
 */
public class AttendanceService {
    private List<DailyAttendance> dailyAttendanceList;
    private List<Employee> employeeList;

    public AttendanceService() {
        this.dailyAttendanceList = CsvFile.DAILYATTENDANCE.readFile(DailyAttendance::new);
        this.employeeList = CsvFile.EMPLOYEEINFORMATION.readFile(Employee::new);
    }
    
    public List<DailyAttendance> getFilteredDailyAttendance(Employee employee, PayPeriod payPeriod){
         // Fetch attendance records for the employee and convert to a map
        List<DailyAttendance> filteredList = dailyAttendanceList.stream()
            .filter(dtr -> dtr.getEmployee().getEmployeeID().equals(employee.getEmployeeID()))
            .filter(dtr -> !dtr.getDate().isBefore(payPeriod.getStartDate()) &&
                           !dtr.getDate().isAfter(payPeriod.getEndDate()))
            .collect(Collectors.toList());
        
        return filteredList;
    }          
        
    public DefaultTableModel getAttendanceTableModel(Employee employee, PayPeriod payPeriod) {       
//        // Update file to ensure CSV reflects latest computed values
//        updateDailyAttendanceFile();
        int[] selectedColumns = {2,3,4,5,6,7};
        String[] selectedHeaders = new String[selectedColumns.length];
        
        for (int i = 0; i < selectedColumns.length; i++) {
            selectedHeaders[i] = CsvFile.DAILYATTENDANCE.getTableHeader()[selectedColumns[i]];
        } 
        
        // Generate full list of dates for the pay period
        List<LocalDate> allDates = payPeriod.getStartDate().datesUntil(payPeriod.getEndDate().plusDays(1))
                                            .collect(Collectors.toList());

        // Fetch attendance records for the employee and convert to a map
        List<DailyAttendance> filteredList = getFilteredDailyAttendance(employee, payPeriod);

        Map<LocalDate, DailyAttendance> attendanceMap = CollectionUtils.listToMap(filteredList, DailyAttendance::getDate);

        // Create table rows ensuring every date in the pay period is included
        List<String[]> tableRows = new ArrayList<>();
        for (LocalDate date : allDates) {
            DailyAttendance dtr = attendanceMap.get(date);

            String[] row = new String[]{
                date.format(DateTimeFormatter.ofPattern("M/d/yyyy")),  // Date
                (dtr != null && dtr.getTimeIn() != null) ? dtr.getTimeIn().format(DateTimeFormatter.ofPattern("H:mm")) : "N/A",
                (dtr != null && dtr.getTimeOut() != null) ? dtr.getTimeOut().format(DateTimeFormatter.ofPattern("H:mm")) : "N/A",
                (dtr != null) ? String.valueOf(dtr.getHoursLate()) : "0.0",
                (dtr != null) ? String.valueOf(dtr.getHoursOvertime()) : "0.0",
                (dtr != null) ? String.valueOf(dtr.getHoursWorked()) : "0.0"
            };

            tableRows.add(row);
        }

        return new TableModel(tableRows, selectedHeaders, row -> row).getTableModel();
    }

    public DefaultTableModel getProcessedAttendanceTableModel(PayPeriod payPeriod) {
        // Define column headers
        String[] columnNames = {
            "Employee ID", "Employee Name", "Employee Type",
            "Total Late Hours", "Total Overtime", "Total Regular Hours", "Payable Hours", "Approved By"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        
        // For each employee, filter their daily attendance records for the pay period,
        // aggregate the totals, and try to get the approvedBy value from an AttendanceProcessing record.
        for (Employee employee : employeeList) {
            // Filter daily attendance records for the employee within the pay period.
            List<DailyAttendance> filteredRecords = getFilteredDailyAttendance(employee, payPeriod);

            double totalLate = AttendanceCalculator.calculateLateHours(filteredRecords);
            double totalOvertime = AttendanceCalculator.calculateApprovedOverTimeHours(filteredRecords);
            double totalRegular = AttendanceCalculator.calculateRegularWorkedHours(filteredRecords);   
            double payableHours = AttendanceCalculator.calculatePayableHours(filteredRecords);

            // Construct a row using the aggregated values.
            Object[] row = {
                employee.getEmployeeID(),
                employee.getFirstName() + " " + employee.getLastName(),
                employee.getEmploymentStatus(),
                totalLate,
                totalOvertime,
                totalRegular,
                payableHours,
                ""
            };

            model.addRow(row);
        }

        return model;
    }
    
    
//    public DefaultTableModel getProcessedAttendanceTableModel(PayPeriod payPeriod) {
//        // 1. Generate the column headers:
//        //    - First two columns: "Employee ID" and "Employee Name"
//        //    - Next columns: one per date in the pay period (formatted as M/d)
//        //    - Last column: "Total"
//        List<LocalDate> dates = payPeriod.getStartDate().datesUntil(payPeriod.getEndDate().plusDays(1))
//                                         .collect(Collectors.toList());
//        
//        int numDateColumns = dates.size();
//        String[] columnNames = new String[2 + numDateColumns + 1];
//        columnNames[0] = "Employee ID";
//        columnNames[1] = "Employee Name";
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d");
//        
//        for (int i = 0; i < dates.size(); i++) {
//            columnNames[2 + i] = dates.get(i).format(dateFormatter);
//        }
//        
//        columnNames[columnNames.length - 1] = "Total";
//
//        // 2. Create a table model with the above column headers
//        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
//
//        // 3. Populate rows for each employee
//        for (Employee emp : employeeList) {
//            // Prepare a row: employee info + one value per date + total
//            String[] row = new String[columnNames.length];
//            row[0] = emp.getEmployeeID();
//            row[1] = emp.getFirstName() + " " + emp.getLastName();
//
//            double totalHours = 0.0;
//            // For each day in the pay period, retrieve worked hours
//            for (int i = 0; i < dates.size(); i++) {
//                LocalDate date = dates.get(i);
//                // Find the attendance record for this employee and date (if it exists)
//                Optional<DailyAttendance> attendanceOpt = dailyAttendanceList.stream()
//                    .filter(d -> d.getEmployee().getEmployeeID().equals(emp.getEmployeeID()))                        
//                    .filter(d -> d.getDate().equals(date))
//                    .findFirst();
//                double hours = attendanceOpt.isPresent() ? attendanceOpt.get().getHoursWorked() : 0.0;
//                totalHours += hours;
//                row[2 + i] = String.valueOf(hours);
//            }
//            // Last column: total worked hours for the pay period
//            row[row.length - 1] = String.valueOf(totalHours);
//
//            // Add this row to the model
//            model.addRow(row);
//        }
//
//        return model;
//    }
    public void updateDailyAttendanceFile() {
        // Optionally, check if any record had missing computed values.
        // This can be based on a condition, e.g., if hoursLate, hoursOvertime, or hoursWorked are zero
        // when they shouldn't be. Adjust the condition as needed.
        boolean updateNeeded = dailyAttendanceList.stream().anyMatch(d ->
            // This condition assumes that a computed record should have non-zero values.
            // You might adjust it according to your requirements.
            (d.getTimeIn() != null && d.getTimeOut() != null && 
             (d.getHoursLate() == 0.0 && d.getHoursWorked() == 0.0 && d.getHoursOvertime() == 0.0))
        );

        // If update is needed, or if you want to update unconditionally, write the file.
        // For example, if updateNeeded is true or you simply want to sync computed values:
        List<String[]> dataToWrite = dailyAttendanceList.stream()
            .map(dtr -> new String[]{
                dtr.getAttendanceID(),
                dtr.getEmployee().getEmployeeID(),
                dtr.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy")),
                dtr.getTimeIn() != null ? dtr.getTimeIn().format(DateTimeFormatter.ofPattern("H:mm")) : "",
                dtr.getTimeOut() != null ? dtr.getTimeOut().format(DateTimeFormatter.ofPattern("H:mm")) : "",
                String.valueOf(dtr.getHoursLate()),
                String.valueOf(dtr.getHoursOvertime()),
                String.valueOf(dtr.getHoursWorked())
            })
            .collect(Collectors.toList());

        CsvFile.DAILYATTENDANCE.writeFile(dataToWrite);
    }
    
}
//    public DefaultTableModel getDTRTableModel(Employee employee, PayPeriod payPeriod) {        
//        int[] selectedColumns = {2,3,4,5,6,7};
//        String[] selectedHeaders = new String[selectedColumns.length];
//        
//        for (int i = 0; i < selectedColumns.length; i++) {
//            selectedHeaders[i] = CsvFile.DAILYATTENDANCE.getTableHeader()[selectedColumns[i]];
//        }       
//                
//        // Filter records for the given employee and pay period
////        List<DailyAttendance> filteredList = this.dailyAttendanceList.stream()
////            .filter(dtr -> dtr.getEmployeeID() != null && // Avoid NullPointerException
////                       dtr.getEmployeeID().getEmployeeID().equals(employeeID.getEmployeeID())) // Match Employee
////            .filter(dtr -> dtr.getDate() != null && // Avoid NullPointerException
////                           !dtr.getDate().isBefore(payPeriod.getStartDate()) &&
////                           !dtr.getDate().isAfter(payPeriod.getEndDate())) // Match Pay Period
////            .collect(Collectors.toList());
////        List<DailyAttendance> filteredList = new ArrayList<>();
//
////        for (DailyAttendance dtr : dailyAttendanceList) {
////            System.out.print(dtr.getEmployeeID().getEmployeeID() + " " + employeeID.getEmployeeID());
////            if (dtr.getEmployeeID().getEmployeeID() != null && dtr.getEmployeeID().getEmployeeID().equals(employeeID.getEmployeeID())) {
////                LocalDate date = dtr.getDate();
////                
////
////                if (date != null && 
////                    (date.isEqual(payPeriod.getStartDate()) || date.isAfter(payPeriod.getStartDate())) &&
////                    (date.isEqual(payPeriod.getEndDate()) || date.isBefore(payPeriod.getEndDate()))) {
////                    filteredList.add(dtr);
////                }
////            }
////        }
//        // Generate full list of dates for the pay period
//        List<LocalDate> allDates = payPeriod.getStartDate().datesUntil(payPeriod.getEndDate().plusDays(1))
//                                            .collect(Collectors.toList());
//
//        // Fetch attendance records for the employee and convert to a map
//        List<DailyAttendance> filteredList = dailyAttendanceList.stream()
//            .filter(dtr -> dtr.getEmployeeID().getEmployeeID().equals(employee.getEmployeeID()))
//            .filter(dtr -> !dtr.getDate().isBefore(payPeriod.getStartDate()) &&
//                           !dtr.getDate().isAfter(payPeriod.getEndDate()))
//            .collect(Collectors.toList());
//
//        Map<LocalDate, DailyAttendance> attendanceMap = CollectionUtils.listToMap(filteredList, DailyAttendance::getDate);
//
//        // Create table rows ensuring every date in the pay period is included
//        List<String[]> tableRows = new ArrayList<>();
//        for (LocalDate date : allDates) {
//            DailyAttendance dtr = attendanceMap.get(date);
//
//            String[] row = new String[]{
//                date.format(DateTimeFormatter.ofPattern("M/d/yyyy")),  // Date
//                (dtr != null && dtr.getTimeIn() != null) ? dtr.getTimeIn().format(DateTimeFormatter.ofPattern("H:mm")) : "N/A",
//                (dtr != null && dtr.getTimeOut() != null) ? dtr.getTimeOut().format(DateTimeFormatter.ofPattern("H:mm")) : "N/A",
//                (dtr != null) ? String.valueOf(dtr.getHoursLate()) : "0.0",
//                (dtr != null) ? String.valueOf(dtr.getHoursOvertime()) : "0.0",
//                (dtr != null) ? String.valueOf(dtr.getHoursWorked()) : "0.0"
//            };
//
//            tableRows.add(row);
//        }
//        
////        // Function to extract selected columns from DailyAttendance
////        Function<DailyAttendance, String[]> rowMapper = (DailyAttendance dtr) -> {
////            return new String[]{
////                dtr.getDate() != null ? dtr.getDate().format(formatterDate) : "N/A",
////                dtr.getTimeIn() != null ? dtr.getTimeIn().format(formatterTime) : "N/A",
////                dtr.getTimeOut() != null ? dtr.getTimeOut().format(formatterTime) : "N/A",
////                dtr.getHoursLate() != null ? decimalFormat.format(dtr.getHoursLate()) : "0.0",
////                dtr.getHoursOvertime() != null ? decimalFormat.format(dtr.getHoursOvertime()) : "0.0",
////                dtr.getHoursWorked() != null ? decimalFormat.format(dtr.getHoursWorked()) : "0.0"
////            };
////        };
//
//        return new TableModel(tableRows, selectedHeaders, row -> row).getTableModel();
//    }    
//    
//}

    
