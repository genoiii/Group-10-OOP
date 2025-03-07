/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author 63909
 */
public class PayPeriod {
    private static final DateTimeFormatter FORMATTER  = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private String payPeriodID;
    private LocalDate  startDate;
    private LocalDate  endDate;
    private LocalDate  payDate;
    private LocalDate  payrollDueDate;   

    public PayPeriod(){
    }
    
    public PayPeriod(String startDate, String endDate){
        try {
            this.startDate = LocalDate.parse(startDate, FORMATTER);
            this.endDate = LocalDate.parse(endDate, FORMATTER);
            
            validateDateOrder(this.startDate, this.endDate, "Start date must be before end date.");
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: MM-dd-yyyy", e);
        }
    }
    
    public PayPeriod(String payPeriodID) {
        this.payPeriodID = payPeriodID;
    }
    
    public PayPeriod(String[] payPeriodData) {
        if (payPeriodData.length < 5) {
            throw new IllegalArgumentException("Invalid data length for pay period.");
        }
        
        try {
            this.payPeriodID = payPeriodData[0];

            this.startDate = LocalDate.parse(payPeriodData[1], FORMATTER );
            this.endDate = LocalDate.parse(payPeriodData[2], FORMATTER );
            this.payDate = LocalDate.parse(payPeriodData[3], FORMATTER );
            this.payrollDueDate = LocalDate.parse(payPeriodData[4], FORMATTER);

            validateDateOrder(startDate, endDate, "Start date must be before end date.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }      
    }
    
    private void validateDateOrder(LocalDate first, LocalDate second, String errorMessage) {
        if (first.isAfter(second)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public String getPayPeriodID() {
        return payPeriodID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public LocalDate getPayrollDueDate() {
        return payrollDueDate;
    }  
    
    @Override
    public String toString() {
        return "PayPeriod{" +
                "payPeriodID='" + payPeriodID + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", payDate=" + payDate +
                ", payrollDueDate=" + payrollDueDate +
                '}';
    }
}
