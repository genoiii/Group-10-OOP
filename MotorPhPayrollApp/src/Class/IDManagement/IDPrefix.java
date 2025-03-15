/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.IDManagement;

/**
 *
 * @author 63909
 */
public enum IDPrefix {
    EMPLOYEE("EMP"), USER("USR"), ADMIN("ADM"), NON_ADMIN("NAD"),
    JOB("JOB"), SALARY("SAL"), DEPARTMENT("DEP"),
    ROLE("ROL"), ACCESS("ACC"), PERMISSION("PER"),
    DAILY_ATTENDANCE("ATT"), PAY_PERIOD_ATTENDANCE("PPA"),
    REQUEST("REQ"), LEAVE("LEA"), LEAVE_TYPE("LVT"), OVERTIME("OT"),
    DEDUCTION("DED"), TAX("TAX"), ALLOWANCE("ALL"),
    PAY_PERIOD("PP"), PAYROLL("PAY"), PAYSLIP("PSL");

    private final String prefix;

    IDPrefix(String prefix) { this.prefix = prefix; }

    public String getPrefix() { return prefix; }
}
