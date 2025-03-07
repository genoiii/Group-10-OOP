/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents an Employee entity.
 *
 * <p>This class serves as a model for employee data. It can be extended to include properties
 * such as employee ID, name, department, and other relevant details.</p>
 */
public class Employee {
    private String employeeID; // Unique identifier for the employee
    private PersonalInformation personalInformation; // Holds personal details like name, contact info, etc.
    private GovernmentInformation governmentInformation; // Contains government-related information such as IDs and other credentials.
    private EmploymentInformation employmentInformation; // Stores employment details such as position, department, and salary.
    
    //Personal Information
    private String lastName, firstName;
    private String birthday, address, phoneNumber;

    //Government Information
    private String sssNumber, philhealthNumber, tinNumber, pagibigNumber;

    //Employment Information
    private String employmentStatus, position, immediateSupervisor;

    // Compensation & Benefits
    private String basicSalary;
    private String riceSubsidy, phoneAllowance, clothingAllowance;
    private String grossSemiMonthlyRate, hourlyRate;
    
    public Employee() {} 
    
    /**
     * Constructor that initializes an Employee with an ID.
     *
     * @param employeeID the unique identifier for the employee.
     */
    public Employee(String employeeID) {
        this.employeeID = employeeID; // Initialize employeeID field with the provided value.
    }
    
    /**
     * Constructs an Employee with the specified personal information.
     *
     * @param personalInformation the PersonalInformation object containing the employee's personal details.
     */
    public Employee(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation; // Initialize the personalInformation field.
    }
    
    /**
     * Constructs an Employee with the specified government information.
     *
     * @param governmentInformation the GovernmentInformation object containing the employee's government details.
     */    
    public Employee(GovernmentInformation governmentInformation) {
        this.governmentInformation = governmentInformation; // Initialize the governmentInformation field with the provided object.
    }
    
    /**
     * Constructs an Employee with the specified employment information.
     *
     * @param employmentInformation the EmploymentInformation object containing the employee's employment details.
     */    
    public Employee(EmploymentInformation employmentInformation) {
        this.employmentInformation = employmentInformation; // Initialize the employmentInformation field with the provided object.
    }
    
    public Employee(String employeeId, String lastName, String firstName, String birthday, String address, String phone){
        setEmployeeID(employeeId);
        setLastName(lastName);
        setFirstName(firstName);
        setBirthday(birthday);
        setAddress(address);
        setPhoneNumber(phone);
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
