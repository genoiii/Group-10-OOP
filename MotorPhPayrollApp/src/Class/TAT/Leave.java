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

public class Leave implements Identifiable {
    private String leaveID;
    private String employeeID;    
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
    private int totalDays;
    private int payableDays;
    private boolean isApproved;

    public Leave() {
    }
        
    // Constructor using String array
    public Leave(String[] leaveData) {
        if (leaveData == null) {
            throw new IllegalArgumentException("Leave data cannot be null.");
        }

        switch (leaveData.length) {
            case 5 -> {
                // New leave request (ID is generated)
                EntityManager entityManager = new EntityManager(EntityType.LEAVE);
                this.leaveID = IDManager.generateID(entityManager.getEntityType().getIdPrefix());
                IDManager.saveIDCounters();
                
                this.employeeID = leaveData[0];
                this.startDate = Parser.parseLocalDate(leaveData[1], null);
                this.endDate = Parser.parseLocalDate(leaveData[2], null);
                this.leaveType = leaveData[3];
                this.totalDays = Parser.parseInteger(leaveData[4], 0);
                
                this.payableDays = 0; // Default to 0 since it's not provided in this case
                this.isApproved = false; // New requests are not approved by default
            }
            case 8 -> {
                // Existing leave request (ID is provided)
                this.leaveID = leaveData[0];
                this.employeeID = leaveData[1];
                this.startDate = Parser.parseLocalDate(leaveData[2], null);
                this.endDate = Parser.parseLocalDate(leaveData[3], null);
                this.leaveType = leaveData[4];
                this.totalDays = Parser.parseInteger(leaveData[5], 0);
                this.payableDays = Parser.parseInteger(leaveData[6], 0);
                this.isApproved = leaveData[7].isEmpty() ? null : Boolean.parseBoolean(leaveData[7]);
            }
            default -> throw new IllegalArgumentException("Invalid input data format. Expected 5 (new) or 8 (existing) parameters.");
        }
    }
    
    // Getters and Setters
    @Override
    public String getID() {
        return leaveID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getLeaveType() {
        return leaveType;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getPayableDays() {
        return payableDays;
    }
    public void setPayableDays(int payableDays) {
        this.payableDays = payableDays;
    }    

    public boolean isIsApproved() {
        return isApproved;
    }   
    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
    
}

