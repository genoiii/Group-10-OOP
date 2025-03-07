/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import Class.EMS.Employee;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 *
 * @author 63909
 */
public class DailyAttendance {
    // Formatters for date, time, and decimals
    private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm");
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
    // Attributes for attendance
    private String attendanceID;
    private Employee employee;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private double hoursWorked, hoursLate, hoursOvertime;

    public DailyAttendance() {}
    
    /**
     * Constructs a DailyAttendance object from a CSV record.
     * Expects the array to have at least 8 elements.
     *
     * @param dailyAttendanceData CSV data representing an attendance record.
     */
    public DailyAttendance(String[] dailyAttendanceData) {
        if (dailyAttendanceData == null || dailyAttendanceData.length < 8) {
            throw new IllegalArgumentException("Invalid attendance data provided.");
        }
        
        try {
            this.attendanceID = dailyAttendanceData[0];
            this.employee = new Employee(dailyAttendanceData[1]); // Assuming Employee constructor takes an ID
            this.date = LocalDate.parse(dailyAttendanceData[2], formatterDate);
            this.timeIn = LocalTime.parse(dailyAttendanceData[3], formatterTime);
            this.timeOut = LocalTime.parse(dailyAttendanceData[4], formatterTime);
            
            // Check if hours are missing
            boolean needsUpdate = dailyAttendanceData[5].isEmpty() || 
                                  dailyAttendanceData[6].isEmpty() || 
                                  dailyAttendanceData[7].isEmpty();
            
            if (needsUpdate) {
            // Compute missing values
            double[] calculatedValues = AttendanceCalculator.calculateDailyAttendance(timeIn, timeOut);
            this.hoursWorked = calculatedValues[0];
            this.hoursLate = calculatedValues[1];
            this.hoursOvertime = calculatedValues[2];

            // Store updated values for later CSV update
//            String[] updatedValues = new String[]{
//                this.attendanceID,
//                this.employee.getEmployeeID(),
//                this.date.format(formatterDate),
//                this.timeIn.format(formatterTime),
//                this.timeOut.format(formatterTime),                
//                String.valueOf(this.hoursLate),
//                String.valueOf(this.hoursOvertime),
//                String.valueOf(this.hoursWorked)
//            };
            
        } else {            
            this.hoursLate = parseDoubleSafely(dailyAttendanceData[5]);
            this.hoursOvertime = parseDoubleSafely(dailyAttendanceData[6]);
            this.hoursWorked = parseDoubleSafely(dailyAttendanceData[7]);
        }

        } catch (DateTimeParseException | NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing attendance data: " + Arrays.toString(dailyAttendanceData), e);
        }
    }   
        
    /**
     * Safely parses a String into a double. Returns 0.0 if parsing fails.
     *
     * @param value The String value to parse.
     * @return The parsed double value or 0.0 if parsing fails.
     */
    private double parseDoubleSafely(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0; // Default to 0.0 if parsing fails
        }
    }
    
    /**
     * Calculates daily attendance values.
     * 
     * - Hours Worked is calculated from timeIn to an effective timeOut, capped at 5:00 PM (17:00).
     * - Lunch break (12:00 - 13:00) is deducted if applicable.
     * - Overtime is computed separately if timeOut is after 5:00 PM.
     * - Hours Late is computed if timeIn is more than 10 minutes after 8:00 AM.
     * 
     * @param timeIn  The time when the employee clocked in.
     * @param timeOut The time when the employee clocked out.
     * @return An array containing [hoursWorked (excluding overtime), hoursLate, hoursOvertime]
     */
    public double[] calculateDailyAttendance(LocalTime timeIn, LocalTime timeOut) {
        double hoursLate = 0.0, hoursWorked = 0.0, hoursOvertime = 0.0;
        double hoursBreak = 1.0;

        // Define shift boundaries (start at 8:00 AM and end at 5:00 PM)
        LocalTime startShift = LocalTime.of(8, 0);
        LocalTime endShift = LocalTime.of(17, 0);

        // Calculate late hours (if clock-in is after 8:10 AM)
        if (timeIn.isAfter(startShift.plusMinutes(10))) {
            hoursLate = Duration.between(startShift, timeIn).toMinutes() / 60.0;
        }

        // For worked hours, cap the end time at 5:00 PM (exclude overtime)
        LocalTime effectiveTimeOut = timeOut.isAfter(endShift) ? endShift : timeOut;
        hoursWorked = Duration.between(timeIn, effectiveTimeOut).toMinutes() / 60.0;

        // Deduct lunch break if work spans the lunch period (12 PM - 1 PM)
        if (timeIn.isBefore(LocalTime.NOON) && timeOut.isAfter(LocalTime.of(13, 0))) {
            hoursWorked -= hoursBreak;
        }

        // Calculate overtime separately if timeOut is after 5:00 PM
        if (timeOut.isAfter(endShift)) {
            hoursOvertime = Duration.between(endShift, timeOut).toMinutes() / 60.0;
        }

        // Format values to two decimal places (assuming decimalFormat is defined elsewhere)
        hoursWorked = Double.parseDouble(decimalFormat.format(hoursWorked));
        hoursLate = Double.parseDouble(decimalFormat.format(hoursLate));
        hoursOvertime = Double.parseDouble(decimalFormat.format(hoursOvertime));

        return new double[]{hoursWorked, hoursLate, hoursOvertime};
    }

    
//    public double[] calculateDailyAttendance(LocalTime timeIn, LocalTime timeOut) {
//        double hoursWorked, hoursLate = 0.0, hoursOvertime = 0.0, hoursBreak = 1.0;
//        
//        int hoursIn = timeIn.getHour();
//        int minIn = timeIn.getMinute();
//        int hoursOut = timeOut.getHour();
//        int minOut = timeOut.getMinute();
//        
//        // Define shift boundaries
//        LocalTime startShift = LocalTime.of(8, 0);
//        LocalTime endShift = LocalTime.of(17, 0);
//        
//        // Check if the employee is late.
//        // This example flags as late if the employee clocks in after 8:10 AM or after 1:10 PM.
//        if ((hoursIn > 8 &&  hoursIn <= 12) || (hoursIn == 13 && minIn > 10) || (hoursIn == 8 && minIn > 10)) {
//            // For times past 8 AM, calculate late hours as the difference from 8:00 AM plus fractional minutes
//            hoursLate = (hoursIn > 8) ? (hoursIn - 8) + (minIn / 60.0) : 0;
//        }
//        
//        // Calculate worked hours up to 5:00 PM.
//        // Math.min(hoursOut, 17) ensures we only count time until 5 PM (excluding overtime).
//        hoursWorked = (Math.min(hoursOut, 17) - hoursIn) + ((minOut - minIn) / 60.0);
//        
//        // Deduct the lunch break (typically from 12 PM to 1 PM) if applicable.
//        if (hoursIn < 12 && hoursOut > 13) {
//            hoursWorked -= hoursBreak; // Compound assignment to subtract lunch break.
//        }
//        
//        // Calculate overtime hours if the employee clocks out after 5 PM.
//        if (hoursOut > 17) {
//            hoursOvertime = hoursOut - 17 + (minOut / 60.0);
//        }
//        
//        // Exclude overtime from the calculated hours worked, as overtime requires separate approval.
//        hoursWorked = Math.max(hoursWorked - hoursOvertime, 0.0);
//
//        
//        // Format the values to two decimal places (assuming 'decimalFormat' is defined elsewhere in your class)
//        hoursWorked = Double.parseDouble(decimalFormat.format(hoursWorked));
//        hoursLate = Double.parseDouble(decimalFormat.format(hoursLate));
//        hoursOvertime = Double.parseDouble(decimalFormat.format(hoursOvertime)); // Assuming a calculation for overtime is added later
//        
//        return new double[]{hoursWorked, hoursLate, hoursOvertime};
//    }
    
    public String getAttendanceID() {
        return attendanceID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public Double getHoursLate() {
        return hoursLate;
    }

    public Double getHoursOvertime() {
        return hoursOvertime;
    }
    
    
    
}
