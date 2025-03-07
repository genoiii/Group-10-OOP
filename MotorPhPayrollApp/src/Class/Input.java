/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Class;

import CSVFileManager.CsvFile;
import Class.UMS.*;
import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The Input class handles user authentication and role identification. It
 * verifies user credentials against stored data and determines user roles.
 */
/**
 * Utility class for input validation and authentication.
 *
 * <p>This class provides static methods for validating and authenticating user input.
 * It is not intended to be instantiated.</p>
 */
public class Input {
    private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm");
    private boolean authenticationResult; // Stores the result of the authentication attempt (true if successful, false otherwise)
    private Map<String, Object> userMap; // HashMap to store user data for quick lookup using usernames as keys
    private String roleID; // Stores the role ID of the authenticated user
    private String employeeID; // Stores the employee ID of the authenticated user
    private String username, password;
    private User user;
    private Admin admin;
    private NonAdmin nonAdmin;
    
    public Input(){
        
    }
    
    public Input(String firstInput){
        
    }
    
    public Input(String firstInput, String secondInput){
        
    }
    /**
     * Checks if the authenticated user is an administrator.
     * 
     * @return true if the user an admin (roleID != "1"), false otherwise.
     */
    public boolean isAdmin(){ 
        boolean roleResult = !this.roleID.equals("1"); 
        // Returns true if roleID is NOT "1" ("1" represents NonAdmin)
        return roleResult; 
    }
    
    /**
     * Authenticates a user by verifying their username and password against stored data.
     * If authentication is successful, it sets the user's role ID and employee ID.
     * 
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return true if authentication is successful, false otherwise.
     */
    public User isAuthenticated(String username, String password) {
        List<User> users = CsvFile.USER.readFile(User::new);        
        Map<String, User> userMapByUsername = CollectionUtils.listToMap(users, User::getUsername);
//        System.out.println("User with Name " + userMapByUsername.get(username).getUsername() + " : " + userMapByUsername.get(username).getRoleID());
        
         // Check if the username exists in the data
        if(userMapByUsername.get(username) != null){
            // Verify if the stored password (at index 3) matches the provided password
            if(userMapByUsername.get(username).getPassword().equals(password)){
                authenticationResult = true; // Set authentication status to true
                this.roleID = userMapByUsername.get(username).getRoleID();
//                this.user = (User) userMapByUsername.get(username);                
                if (!isAdmin()){                    
                    this.user = new NonAdmin(userMapByUsername.get(username));
                } else {
                    this.user = new Admin(userMapByUsername.get(username));
                }
//                this.user.setAuthenticationResult(true);
            } else {
                this.user = null;
            }
        } else {
            this.user = null;
            
        }       

        return user; // Return the authentication result
    }
    
    /**
     * Checks if a given field is empty.
     *
     * <p>This method returns an error message if the field is null or empty (after trimming),
     * otherwise it returns an empty string.</p>
     *
     * @param fieldValue the value of the field to check.
     * @param fieldName the name of the field, used in the error message.
     * @return an error message if the field is empty; otherwise, an empty string.
     */  
    public static String isEmptyField(String fieldValue, String fieldName) {
        // Check if the field is null or empty after removing surrounding spaces.
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            return fieldName + " cannot be empty."; // Return a message indicating that the field cannot be empty.
        }
        return ""; // Return an empty string if the field is populated.
    }
    
    /**
     * Validates if the provided string is a valid integer.
     *
     * <p>This method attempts to parse the input string as an integer. If the parsing is successful,
     * it returns true; otherwise, it returns false.</p>
     *
     * @param input the string to validate.
     * @return true if the input is a valid integer, false otherwise.
     */
    public static boolean isValidInteger(String input) {
        try {
            Integer.valueOf(input); // Try to parse the input string as an integer.
            return true;
        } catch (NumberFormatException e) {
            return false; // Return false if parsing fails.
        }
    }
    
    /**
     * Validates if the provided string is a valid double.
     *
     * <p>This method attempts to parse the input string as a double. If the parsing succeeds,
     * it returns true; otherwise, it returns false.</p>
     *
     * @param input the string to validate.
     * @return true if the input can be parsed as a double; false otherwise.
     */
    public static boolean isValidDouble(String input) {
        try {
            Double.valueOf(input); // Try to parse the input string as a double.
            return true;
        } catch (NumberFormatException e) {
            return false; // Return false if parsing fails.
        }
    }
    
    /**
     * Checks if the given date is in the future.
     *
     * @param date the LocalDate to check.
     * @return true if the date is after today; false otherwise.
     */
    public static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now()); // Return true if the date is after the current date.
    }   

    /**
     * Validates a government ID number by checking its length.
     *
     * @param textField the JTextField containing the ID number.
     * @param fieldName the name of the field for error messaging.
     * @param count     the required number of digits.
     * @return an error message if invalid; otherwise, an empty string.
     */    
    public static String isValidGovernmentIDNumber(JTextField textField, String fieldName, int count) {
        String text = textField.getText().replace("-", ""); // Remove dashes and trim spaces from the input.
        
        // Check if the input contains only numeric digits.
        try {
            Integer.valueOf(text); 
            int valueDigits = text.length();
            
            // Check if the length of the input matches the required count.
            if (valueDigits != count) {
                return fieldName + " must be between " + count + " digits.\n";
            }
        } catch (NumberFormatException e) {
            return fieldName + " Invalid ID.\n"; 
        }
        return ""; // Return an empty string if the input is valid.
    }
    
    /**
     * Validates a Philippine phone number.
     *
     * <p>This method checks if the phone number is non-empty, removes any formatting,
     * and verifies that it matches the expected format "09XXXXXXXXX" (11 digits starting with '09').</p>
     *
     * @param phone the phone number string to validate.
     * @return an error message if the phone number is invalid; otherwise, an empty string.
     */
    public static String isValidPhoneNumber(String phone) {
        // Check if the phone number is null or empty.
        if (phone == null || phone.isEmpty()) {
            return "Phone number cannot be empty.";
        }
        
        phone = Formatter.removePhoneFormatting(phone); // Remove any formatting (non-numeric characters) from the phone number.
        
        // Verify that the phone number matches the pattern: starts with '09' followed by 9 digits.
        if (!phone.matches("^09\\d{9}$")) {
            return "Invalid phone number format. It should be 11 digits and start with '09'.";
        }
        
        return ""; // Return an empty string if the phone number is valid.
    }
    
    /**
     * Validates a birthday from a JDateChooser.
     *
     * <p>The birthday must follow the specified format (e.g., "MM-dd-yyyy"), not be in the future, 
     * and the age must be at least 18 years.</p>
     *
     * @param jDateChooser the JDateChooser containing the birthday.
     * @param format the expected date format pattern.
     * @return an error message if invalid; otherwise, an empty string.
     */
    public static String isValidBirthday(JDateChooser jDateChooser, String format) {
        String date = ((JTextField)jDateChooser.getDateEditor().getUiComponent()).getText(); // Get the birthday text from the JDateChooser's editor component.
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format); // Create a formatter for the expected date pattern.
            LocalDate birthDate = LocalDate.parse(date, formatter); // Parse the text into a LocalDate.
            LocalDate today = LocalDate.now();
            
            // Check if the birthday is in the future.
            if (birthDate.isAfter(today)) {
                return "Birthday cannot be in the future.";
            }

            // Calculate age and ensure it is at least 18
            int age = today.getYear() - birthDate.getYear();
            if (birthDate.plusYears(age).isAfter(today)) {
                age--; // Adjust age if the birthday hasn't occurred yet this year
            }
            
            // Verify if the person is at least 18 years old.
            if (age < 18) {
                return "Employee must be at least 18 years old.";
            }

            return ""; // No error, valid birthday
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use M/dd/yyyy."; // Return an error message for an invalid date format.
        }
    }
    
    /**
     * Validates a monetary amount.
     *
     * <p>This method removes formatting (like commas) from the amount string,
     * attempts to parse it as a double, and ensures the value is greater than zero.
     * If the amount is invalid or non-numeric, an error message is returned; otherwise, an empty string is returned.</p>
     *
     * @param amount the amount string to validate.
     * @param fieldName the name of the field, used in error messages.
     * @return an error message if invalid; otherwise, an empty string.
     */
    public static String isValidAmount(String amount, String fieldName) {
        amount = Formatter.removeAmountFormatting(amount); // Remove formatting (commas) from the amount.
        try {
            double value = Double.parseDouble(amount); // Parse the cleaned amount string into a double.
            
            // Ensure the value is greater than zero.
            if (value <= 0) {
                return "Amount must be greater than zero.";
            }
            return ""; // Return empty string if the amount is valid.
            
        } catch (NumberFormatException e) {
            return "Invalid " + fieldName + ". Please enter a valid number."; // Return an error message if parsing fails.
        }
    }
    
    public static class ValidationListener implements DocumentListener{
        private JTextField textField;
        private JLabel errorLabel;
        private String fieldName;
        private int count;

        public ValidationListener(JTextField textField, JLabel errorLabel, String fieldName, int count) {
            this.textField = textField;
            this.errorLabel = errorLabel;
            this.fieldName = fieldName;
            this.count = count;
        }
        
        @Override
        public void insertUpdate(DocumentEvent e) {
            validate();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validate();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // This is generally used for changes that do not change the actual content
            // like attribute changes, so we do not need to validate here.            
        }

        private void validate() {
            String text = textField.getText().replace("-", "");
            try {
                Long value = Long.parseLong(text);
                int valueDigits = text.length();
                
                if (valueDigits != count) {
                        errorLabel.setText(fieldName + " Must be " + count + " digits.");
                        errorLabel.setVisible(true); // Show error label
                    } else {
                        errorLabel.setText(""); // Clear error message
                        errorLabel.setVisible(false); // Hide error label
                    }
            } catch (NumberFormatException e) {
                errorLabel.setText(fieldName + " is invalid ID");
                errorLabel.setVisible(true); // Show error label
            }
        }                   
    }
    
    
}
