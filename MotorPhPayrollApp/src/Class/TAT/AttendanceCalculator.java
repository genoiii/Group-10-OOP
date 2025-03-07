/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author 63909
 */
public class AttendanceCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
//    public static PayPeriodAttendance calculateBiweeklyAttendance(List<DailyAttendance> filteredDailyAttendance, PayPeriod payPeriod){
//        double payableHours = AttendanceCalculator.calculatePayableHours(filteredDailyAttendance);
//        double regularHours = AttendanceCalculator.calculateRegularWorkedHours(filteredDailyAttendance);
//        double overtime = AttendanceCalculator.calculateApprovedOverTimeHours(filteredDailyAttendance);
//    }
    
    public static double[] calculateDailyAttendance(LocalTime timeIn, LocalTime timeOut) {        
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
    
    /**
     * Aggregates the regular worked hours (excluding overtime) from a list of
     * DailyAttendance records.
     * @param filteredDailyAttendance
     * @return 
     */
    public static double calculateRegularWorkedHours(List<DailyAttendance> filteredDailyAttendance) {
        double totalRegular = 0.0;
        for (DailyAttendance record : filteredDailyAttendance) {
            totalRegular += record.getHoursWorked();
        }
        return Double.parseDouble(decimalFormat.format(totalRegular));
    }
    
    /**
     * Aggregates the late hours from a list of DailyAttendance records.
     * @param filteredDailyAttendance
     * @return 
     */
    public static double calculateLateHours(List<DailyAttendance> filteredDailyAttendance) {
        double totalLate = 0.0;
        for (DailyAttendance record : filteredDailyAttendance) {
            totalLate += record.getHoursLate();
        }
        return Double.parseDouble(decimalFormat.format(totalLate));
    }
    
    /**
     * Aggregates the approved overtime hours from a list of DailyAttendance records.
     * @param filteredDailyAttendance
     * @return 
     */
    public static double calculateApprovedOverTimeHours(List<DailyAttendance> filteredDailyAttendance) {
        double totalOvertime = 0.0;
        for (DailyAttendance record : filteredDailyAttendance) {
            totalOvertime += record.getHoursOvertime();
        }
        return Double.parseDouble(decimalFormat.format(totalOvertime));
    }
    
    /**
     * Calculates the payable hours for the period.
     * Here, we assume payable hours are computed as:
     * (regular worked hours - late hours, not going below 0) + approved overtime hours.
     * @param filteredDailyAttendance
     * @return 
     */
    public static double calculatePayableHours(List<DailyAttendance> filteredDailyAttendance) {
        double regular = calculateRegularWorkedHours(filteredDailyAttendance);
        double overtime = calculateApprovedOverTimeHours(filteredDailyAttendance);
        double payable = Math.max(regular, 0.0) + overtime;
        return Double.parseDouble(decimalFormat.format(payable));
    }
}
