/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.CSVFileManagement;

/**
 *
 * @author 63909
 */
public enum CSVFile {
    IDCOUNTERS("src/CSVFiles/MotorPH Employee Data - ID Counters.csv"),
    USER("src/CSVFiles/MotorPH Employee Data - User Details.csv"),
    EMPLOYEEID("src/CSVFiles/MotorPH Employee Data - Employee IDs.csv"),
    EMPLOYEEINFORMATION("src/CSVFiles/MotorPH Employee Data - Employee Details.csv"),
    PERSONAL_RECORD("src/CSVFiles/MotorPH Employee Data - Employee Personal Information.csv"),
    EMPLOYMENT_RECORD("src/CSVFiles/MotorPH Employee Data - Employee Employment Information.csv"),
    GOVERNMENT_RECORD("src/CSVFiles/MotorPH Employee Data - Employee Government Information.csv"),
    REQUEST("src/CSVFiles/MotorPH Employee Data - Request.csv"),
    LEAVE("src/CSVFiles/MotorPH Employee Data - Leave.csv"),
    LEAVETYPE("src/CSVFiles/MotorPH Employee Data - Leave Type.csv"),
    OVERTIME("src/CSVFiles/MotorPH Employee Data - Overtime.csv"),
    DAILYATTENDANCE("src/CSVFiles/MotorPH Employee Data - Daily Attendance.csv"),
    PAYPERIOD("src/CSVFiles/MotorPH Employee Data - Pay Period Schedule.csv");
    
    public final String filePath; // Stores the file path of the CSV file
    
    // Constructor to initialize file path for each enum constant
    private CSVFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }  
    
}
