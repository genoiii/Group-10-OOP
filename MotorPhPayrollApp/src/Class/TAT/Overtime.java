/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

/**
 *
 * @author 63909
 */
import Class.EntityManagement.EntityManager;
import Class.EntityManagement.EntityType;
import Class.IDManagement.IDManager;
import Class.IDManagement.Identifiable;
import Class.Parser;
import java.time.LocalDate;
import java.time.LocalTime;

public class Overtime implements Identifiable{
    private String overtimeID;
    private String employeeID;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double totalHours;
    private Double payableHours; // Changed to Double to allow null values
    private boolean isApproved;
    private transient static final LocalTime REGULAR_END_TIME = LocalTime.of(17, 0); // Regular shift ends at 5:00 PM

    public Overtime() {
    }
    
    // Constructor that initializes using DailyAttendance
    public Overtime(DailyAttendance attendance) {
        if (attendance.hasOvertime()) {
            this.date = attendance.getDate();
            this.employeeID = attendance.getEmployee().getEmployeeID();
            LocalTime timeIn = attendance.getTimeIn();
            LocalTime timeOut = attendance.getTimeOut();

            // Overtime starts after regular working hours
            this.startTime = (timeOut.isAfter(REGULAR_END_TIME)) ? REGULAR_END_TIME : timeOut;
            this.endTime = timeOut;
            
            // Calculate overtime hours
            this.totalHours = attendance.getHoursOvertime();
        } else {
            throw new IllegalArgumentException("No overtime recorded for this date.");
        }
    }

    public Overtime(String[] overtimeData) {
        if (overtimeData == null) {
            throw new IllegalArgumentException("Overtime data cannot be null.");
        }

        switch (overtimeData.length) {
            case 5 -> {
                // New overtime entry (ID is generated)
                EntityManager entityManager = new EntityManager(EntityType.OVERTIME);
                this.overtimeID = IDManager.generateID(entityManager.getEntityType().getIdPrefix());
                IDManager.saveIDCounters();
                
                this.employeeID = overtimeData[0];
                this.date = Parser.parseLocalDate(overtimeData[1], null);
                this.startTime = Parser.parseValue(overtimeData[2], null, LocalTime::parse);
                this.endTime = Parser.parseValue(overtimeData[3], null, LocalTime::parse);
                this.totalHours = Parser.parseDouble(overtimeData[4], 0.0);
                
                this.payableHours = 0.0; // Default to null since it's not provided in this case
                this.isApproved = false; // New requests are not approved by default
            }
            case 8 -> {
                // Existing overtime entry (ID is provided)
                this.overtimeID = overtimeData[0];
                this.employeeID = overtimeData[1];
                this.date = Parser.parseLocalDate(overtimeData[2], null, "M/d/yyyy");
                this.startTime = Parser.parseLocalTime(overtimeData[3], null, "H:mm");
                this.endTime = Parser.parseLocalTime(overtimeData[4], null, "H:mm");
                this.totalHours = Parser.parseDouble(overtimeData[5], 0.0);
                this.payableHours = Parser.parseDouble(overtimeData[6], null); // ✅ Nullable
                this.isApproved = (overtimeData[7] == null || overtimeData[7].isEmpty()) ? null : Boolean.parseBoolean(overtimeData[7]);
            }
            default -> throw new IllegalArgumentException("Invalid input data format. Expected 5 (new) or 8 (existing) parameters.");
        }
    }

    // Getters and Setters
    @Override
    public String getID() {        
        return overtimeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public Double getPayableHours() {
        return payableHours;
    }

    public boolean isIsApproved() {
        return isApproved;
    }
    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
        
}

