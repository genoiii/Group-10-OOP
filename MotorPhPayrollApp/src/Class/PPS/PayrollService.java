/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import CSVFileManager.CsvFile;
import Class.EMS.*;
import Class.TAT.*;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 63909
 */
public class PayrollService {
    private static final DateTimeFormatter formatterDate1  = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static final DateTimeFormatter formatterDate2  = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private List<PayPeriod> payPeriodList;
    private EmployeeService employeeService;
    private AttendanceService attendanceService;

    public PayrollService() {
        this.payPeriodList = CsvFile.PAYPERIOD.readFile(PayPeriod::new);
        this.employeeService = new EmployeeService();
        this.attendanceService = new AttendanceService(); 
    }    
       
    /**
     * Generates a list of PayrollRecords for all employees in a given pay period.
     *
     * @param payPeriod        the PayPeriod object (can be used for filtering or validation if needed)
     * @return a List of PayrollRecord objects, one for each employee
     */
    public List<PayrollRecord> generatePayrollRecord(PayPeriod payPeriod) {
        List<PayrollRecord> payrollRecords = new ArrayList<>();
        for (Employee employee : employeeService.getEmployeeRecords()) {
            
            // Retrieve payroll inputs from each Employee object
            double payableHours = AttendanceCalculator.calculatePayableHours(attendanceService.getFilteredDailyAttendance(employee, payPeriod));
            double hourlyRate = Double.parseDouble(employee.getHourlyRate().replace(",", ""));
            double rice = Double.parseDouble(employee.getRiceSubsidy().replace(",", ""));
            double phone = Double.parseDouble(employee.getPhoneAllowance().replace(",", ""));
            double clothing = Double.parseDouble(employee.getClothingAllowance().replace(",", ""));

            // Calculate the payroll record for the employee using the PayrollCalculator
            PayrollRecord record = PayrollCalculator.calculatePayrollRecord(payableHours, hourlyRate, rice, phone, clothing);
            payrollRecords.add(record);
        }
        return payrollRecords;
    }
    
    public DefaultComboBoxModel<String> getComboBoxModel(){
        // Convert payPeriodList to a String[]
        String[] payPeriodArray = payPeriodList.stream()
            .map(payPeriod -> payPeriod.getStartDate().format(formatterDate1) + " : " + payPeriod.getEndDate().format(formatterDate1))
            .toArray(String[]::new); // Proper way to get a String[]

        return new DefaultComboBoxModel<String>(payPeriodArray); // Use DefaultComboBoxModel
    }
    
    public DefaultTableModel getPayrollListTableModel(){
        // Define column headers
        String[] columnNames = {"Pay Period", "Pay Date", "Employee","Total Payment", "Status", "Approve Date"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (PayPeriod payPeriod : payPeriodList) {
            // Construct a row using the aggregated values.
            Object[] row = {
                "Biweekly Salary : " + payPeriod.getStartDate().format(formatterDate2) + " " + payPeriod.getEndDate().format(formatterDate2),
                payPeriod.getPayDate(),
                "",
                "",
                "",
                ""
            };

            model.addRow(row);
        }

        return model;
    }
    
    public DefaultTableModel getEmployeeSelectionTableModel(PayPeriod payPeriod) {
        // Define column headers
        String[] columnNames = {
            "Employee ID", "Employee Name", "Employee Type",
            "Total Regular Hours", "Total Overtime"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Employee employee : employeeService.getEmployeeRecords()) {
            
            List<DailyAttendance> filteredRecords = attendanceService.getFilteredDailyAttendance(employee, payPeriod);
            
            double totalRegular = AttendanceCalculator.calculateRegularWorkedHours(filteredRecords); 
            double totalOvertime = AttendanceCalculator.calculateApprovedOverTimeHours(filteredRecords);
            
            // Construct a row using the aggregated values.
            Object[] row = {
                employee.getEmployeeID(),
                employee.getFirstName() + " " + employee.getLastName(),
                employee.getEmploymentStatus(),
                totalRegular,
                totalOvertime,
            };
            
            model.addRow(row);
        }

        return model;
    }
    
    public DefaultTableModel getPayrollEmployeeEarningsTableModel(PayPeriod payPeriod) {
        // Define column headers
        String[] columnNames = {
            "Employee ID", "Employee Name", "Payable Hours", "Base Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance","Gross Pay"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Employee employee : employeeService.getEmployeeRecords()) {
            
            List<DailyAttendance> filteredRecords = attendanceService.getFilteredDailyAttendance(employee, payPeriod);
            
            double payableHours = AttendanceCalculator.calculatePayableHours(filteredRecords);
            double baseSalary = SalaryCalculator.calculateBasicSalary(payableHours, Double.parseDouble(employee.getHourlyRate().replace(",", "")));
            double totalAllowance = AllowanceCalculator.calculateTotalAllowance(Double.parseDouble(employee.getRiceSubsidy().replace(",", "")),
                                                                                Double.parseDouble(employee.getPhoneAllowance().replace(",", "")), 
                                                                                Double.parseDouble(employee.getClothingAllowance().replace(",", "")));
            double grossSalary = SalaryCalculator.calculateGrossSalary(baseSalary, totalAllowance);
            
            // Construct a row using the aggregated values.
            Object[] row = {
                employee.getEmployeeID(),
                employee.getFirstName() + " " + employee.getLastName(),
                payableHours,
                baseSalary,
                employee.getRiceSubsidy(),
                employee.getPhoneAllowance(),
                employee.getClothingAllowance(),
                grossSalary,
            };
            
            model.addRow(row);
        }

        return model;
    }
    
    public DefaultTableModel getPayrollEmployeeDeductionsTableModel(PayPeriod payPeriod) {
        // Define column headers
        String[] columnNames = {
            "Employee ID", "Employee Name", "SSS", "PhilHealth", "Pag-IBIG", "Withholding Tax" ,"Total Deduction"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Employee employee : employeeService.getEmployeeRecords()) {
            
            List<DailyAttendance> filteredRecords = attendanceService.getFilteredDailyAttendance(employee, payPeriod);
            
            double payableHours = AttendanceCalculator.calculatePayableHours(filteredRecords);
            double baseSalary = SalaryCalculator.calculateBasicSalary(payableHours, Double.parseDouble(employee.getHourlyRate().replace(",", "")));
            double totalAllowance = AllowanceCalculator.calculateTotalAllowance(Double.parseDouble(employee.getRiceSubsidy().replace(",", "")),
                                                                                Double.parseDouble(employee.getPhoneAllowance().replace(",", "")), 
                                                                                Double.parseDouble(employee.getClothingAllowance().replace(",", "")));
            double grossSalary = SalaryCalculator.calculateGrossSalary(baseSalary, totalAllowance);
            double sss = DeductionCalculator.calculateSSS(grossSalary);
            double philhealth = DeductionCalculator.calculatePhilHealth(grossSalary);            
            double pagibig = DeductionCalculator.calculatePagIbig(grossSalary);
            double govermentContribution = DeductionCalculator.calculateGovernmentContribution(sss, philhealth, pagibig);
            double tax = TaxCalculator.calculateWithHoldingTax(grossSalary, govermentContribution);
            double totalDeduction = DeductionCalculator.calculateTotalDeductions(govermentContribution, tax);
            
            
            // Construct a row using the aggregated values.
            Object[] row = {
                employee.getEmployeeID(),
                employee.getFirstName() + " " + employee.getLastName(),
                sss,
                philhealth,
                pagibig,
                tax,
                totalDeduction,
            };
            
            model.addRow(row);
        }

        return model;
    }
    
    public DefaultTableModel getPayrollEmployeeNetPayTableModel(PayPeriod payPeriod) {
        // Define column headers
        String[] columnNames = {
            "Employee ID", "Employee Name", "Payable Hours", "Basic Salary", "Gross Salary", "Total Deduction", "Net Salary"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Employee employee : employeeService.getEmployeeRecords()) {
            
            List<DailyAttendance> filteredRecords = attendanceService.getFilteredDailyAttendance(employee, payPeriod);
            
            double payableHours = AttendanceCalculator.calculatePayableHours(filteredRecords);
            double baseSalary = SalaryCalculator.calculateBasicSalary(payableHours, Double.parseDouble(employee.getHourlyRate().replace(",", "")));
            double totalAllowance = AllowanceCalculator.calculateTotalAllowance(Double.parseDouble(employee.getRiceSubsidy().replace(",", "")),
                                                                                Double.parseDouble(employee.getPhoneAllowance().replace(",", "")), 
                                                                                Double.parseDouble(employee.getClothingAllowance().replace(",", "")));
            double grossSalary = SalaryCalculator.calculateGrossSalary(baseSalary, totalAllowance);
            double sss = DeductionCalculator.calculateSSS(grossSalary);
            double philhealth = DeductionCalculator.calculatePhilHealth(grossSalary);            
            double pagibig = DeductionCalculator.calculatePagIbig(grossSalary);
            double govermentContribution = DeductionCalculator.calculateGovernmentContribution(sss, philhealth, pagibig);
            double tax = TaxCalculator.calculateWithHoldingTax(grossSalary, govermentContribution);
            double totalDeduction = DeductionCalculator.calculateTotalDeductions(govermentContribution, tax);
            double netSalary = SalaryCalculator.calculateNetSalary(grossSalary, totalDeduction, tax);
            
            
            // Construct a row using the aggregated values.
            Object[] row = {
                employee.getEmployeeID(),
                employee.getFirstName() + " " + employee.getLastName(),
                payableHours,
                baseSalary,
                grossSalary,
                totalDeduction,
                totalDeduction,
            };
            
            model.addRow(row);
        }

        return model;
    }
}
