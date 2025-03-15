/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import Class.IDManagement.Identifiable;

/**
 *
 * @author 63909
 */
public class LeaveType implements Identifiable {
    private String leaveTypeID;
    private String leaveTypeName;
    private String description;
    private boolean isPaidLeave;
    private int minDaysAllowed;
    private int maxDaysAllowed;

    public LeaveType() {
    }

    // Parameterized constructor
    public LeaveType(String leaveTypeID, String leaveTypeName, String description, boolean isPaidLeave, int minDaysAllowed, int maxDaysAllowed) {
        this.leaveTypeID = leaveTypeID;
        this.leaveTypeName = leaveTypeName;
        this.description = description;
        this.isPaidLeave = isPaidLeave;
        this.maxDaysAllowed = minDaysAllowed;
        this.maxDaysAllowed = maxDaysAllowed;
    }

    // Constructor using String array
    public LeaveType(String[] leaveTypeData) {
        if (leaveTypeData == null || leaveTypeData.length < 5) {
            throw new IllegalArgumentException("Insufficient leave type data provided.");
        }

        this.leaveTypeID = leaveTypeData[0];
        this.leaveTypeName = leaveTypeData[1];
        this.description = leaveTypeData[2];
        this.isPaidLeave = Boolean.parseBoolean(leaveTypeData[3]);
        this.maxDaysAllowed = minDaysAllowed;
        this.maxDaysAllowed = Integer.parseInt(leaveTypeData[4]);
    }

    // Getters and Setters    
    @Override
    public String getID() {
        return leaveTypeID;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPaidLeave() {
        return isPaidLeave;
    }

    public int getMinDaysAllowed() {
        return minDaysAllowed;
    }    

    public int getMaxDaysAllowed() {
        return maxDaysAllowed;
    }
}

