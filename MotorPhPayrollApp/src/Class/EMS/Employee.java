/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 *
 * @author 63909
 */

/**
 * The Employee class represents an employee's personal, government, 
 * employment, and salary-related information.
 */
public class Employee {
    private String employeeID; // Unique identifier for the employee
    
    //Personal Information
    private String lastName, firstName;
    private String birthday, address, phoneNumber;

    //Goverment Information
    private String sssNumber, philhealthNumber, tinNumber, pagibigNumber;

    //Employement Information
    private String employmentStatus, position, immediateSupervisor;

    // Compensation & Benefits
    private String basicSalary;
    private String riceSubsidy, phoneAllowance, clothingAllowance;
    private String grossSemiMonthlyRate, hourlyRate;

    /**
     * Constructor that initializes an Employee with an ID.
     *
     * @param employeeID The unique employee ID.
     */
    public Employee(String employeeID) {
        this.employeeID = employeeID;
    }   
    
    /**
     * Constructor that initializes an Employee object from an array of information.
     * Validates array length to prevent out-of-bounds errors.
     *
     * @param information String array containing employee details.
     *                    Expected format: [employeeID, lastName, firstName, birthday, address, phoneNumber, 
     *                    sssNumber, philhealthNumber, tinNumber, pagibigNumber, employmentStatus, position, 
     *                    immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, 
     *                    grossSemiMonthlyRate, hourlyRate]
     */
    public Employee(String[] information) {
        if (information.length < 19) {
            throw new IllegalArgumentException("Invalid data: Employee information must have 19 elements.");
        }
        setEmployeeID(information[0]);
        setLastName(information[1]);
        setFirstName(information[2]);
        setBirthday(information[3]);
        setAddress(information[4]);
        setPhoneNumber(information[5]);
        setSSSNumber(information[6]);
        setPhilhealthNumber(information[7]);
        setTinNumber(information[8]);
        setPagibigNumber(information[9]);
        setEmploymentStatus(information[10]);
        setPosition(information[11]);
        setImmediateSupervisor(information[12]);
        setBasicSalary(information[13]);
        setRiceSubsidy(information[14]);
        setPhoneAllowance(information[15]);
        setClothingAllowance(information[16]);
        setGrossSemiMonthlyRate(information[17]);
        setHourlyRate(information[18]);
    }
    
    /**
     * Retrieves all employee information as a String array.
     *
     * @return A String array containing employee details.
     */
    public String[] getEmployeeInformation() {
        return new String[] {employeeID,
                            lastName,
                            firstName,
                            birthday,
                            address,
                            phoneNumber,
                            sssNumber,
                            philhealthNumber,
                            tinNumber,
                            pagibigNumber,
                            employmentStatus,
                            position,
                            immediateSupervisor,
                            basicSalary,
                            riceSubsidy,
                            phoneAllowance,
                            clothingAllowance,
                            grossSemiMonthlyRate,
                            hourlyRate
                            };
    }
    
    // Getter for Employee ID
    public String getEmployeeID() {
        return employeeID;
    }
    
    // Getters and Setters for Personal Information
    public String getLastName() {
            return lastName;
    }
    public void setLastName(String lastName) {
            this.lastName = lastName;
    }

    public String getFirstName() {
            return firstName;
    }
    public void setFirstName(String firstName) {
            this.firstName = firstName;
    }

    public String getBirthday() {
            return birthday;
    }
    public void setBirthday(String birthday) {
            this.birthday = birthday;
    }

    public String getAddress() {
            return address;
    }
    public void setAddress(String address) {
            this.address = address;
    }
  
    public String getPhoneNumber() {
            return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
    }

    // Getters and Setters for Government Information
    public String getSSSNumber() {
            return sssNumber;
    }
    public void setSSSNumber(String sssNumber) {
            this.sssNumber = sssNumber;
    }

    public String getPhilhealthNumber() {
            return philhealthNumber;
    }
    public void setPhilhealthNumber(String philhealthNumber) {
            this.philhealthNumber = philhealthNumber;
    }

    public String getTinNumber() {
            return tinNumber;
    }
    public void setTinNumber(String tinNumber) {
            this.tinNumber = tinNumber;
    }

    public String getPagibigNumber() {
            return pagibigNumber;
    }
    public void setPagibigNumber(String pagibigNumber) {
            this.pagibigNumber = pagibigNumber;
    }

    // Getters and Setters for Employment Information
    public String getEmploymentStatus() {
            return employmentStatus;
    }
    public void setEmploymentStatus(String employmentStatus) {
            this.employmentStatus = employmentStatus;
    }

    public String getPosition() {
            return position;
    }
    public void setPosition(String position) {
            this.position = position;
    }

    public String getImmediateSupervisor() {
            return immediateSupervisor;
    }
    public void setImmediateSupervisor(String immediateSupervisor) {
            this.immediateSupervisor = immediateSupervisor;
    }

    // Getters and Setters for Compensation & Benefits
    public String getBasicSalary() {
            return basicSalary;
    }
    public void setBasicSalary(String basicSalary) {
            this.basicSalary = basicSalary;
    }

    public String getRiceSubsidy() {
            return riceSubsidy;
    }
    public void setRiceSubsidy(String riceSubsidy) {
            this.riceSubsidy = riceSubsidy;
    }

    public String getPhoneAllowance() {
            return phoneAllowance;
    }
    public void setPhoneAllowance(String phoneAllowance) {
            this.phoneAllowance = phoneAllowance;
    }

    public String getClothingAllowance() {
            return clothingAllowance;
    }
    public void setClothingAllowance(String clothingAllowance) {
            this.clothingAllowance = clothingAllowance;
    }

    public String getGrossSemiMonthlyRate() {
            return grossSemiMonthlyRate;
    }
    public void setGrossSemiMonthlyRate(String grossSemiMonthlyRate) {
            this.grossSemiMonthlyRate = grossSemiMonthlyRate;
    }

    public String getHourlyRate() {
            return hourlyRate;
    }
    public void setHourlyRate(String hourlyRate) {
            this.hourlyRate = hourlyRate;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    
           

    
            
    

}
