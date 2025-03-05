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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 63909
 */

/**
 * The Input class handles user authentication and role identification. It
 * verifies user credentials against stored data and determines user roles.
 * @param <T>
 */
public class Input<T> {
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
  
    public static String isEmptyField(String fieldValue, String fieldName) {
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            return fieldName + " cannot be empty.";
        }
        return "";
    }
    
    // Validate Integer Input
    public static boolean isValidInteger(String input) {
        try {
            Integer.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Validate Double Input
    public static boolean isValidDouble(String input) {
        try {
            Double.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Check if Date is in the Future
    public static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }   
        
    public static String isValidGovernmentIDNumber(JTextField textField, String fieldName, int count) {
        String text = textField.getText().replace("-", "");
        try {
            int valueDigits = text.length();
            if (valueDigits != count) {
                return fieldName + " must be between " + count + " digits.\n";
            }
        } catch (NumberFormatException e) {
            return fieldName + " Invalid ID.\n";
        }
        return "";
    }
    
    // Validate Phone Number (09XXXXXXXXX - Philippines)
    public static String isValidPhoneNumber(String phone) {
        if (phone == null || phone.isEmpty()) {
            return "Phone number cannot be empty.";
        }
        
        phone = Formatter.removePhoneFormatting(phone);
        
        if (!phone.matches("^09\\d{9}$")) {
            return "Invalid phone number format. It should be 11 digits and start with '09'.";
        }
        return ""; // No error, valid phone number
    }
    
    // Validate Birthday (Format: MM-dd-yyyy, must not be in the future, and age must be 18+)
    public static String isValidBirthday(JDateChooser jDateChooser, String format) {
        String date = ((JTextField)jDateChooser.getDateEditor().getUiComponent()).getText();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate birthDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();

            if (birthDate.isAfter(today)) {
                return "Birthday cannot be in the future.";
            }

            // Calculate age and ensure it is at least 18
            int age = today.getYear() - birthDate.getYear();
            if (birthDate.plusYears(age).isAfter(today)) {
                age--; // Adjust age if the birthday hasn't occurred yet this year
            }

            if (age < 18) {
                return "Employee must be at least 18 years old.";
            }

            return ""; // No error, valid birthday
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use M/dd/yyyy.";
        }
    }
    
    // Validate Salary and Allowances (Must be a positive number)
    public static String isValidAmount(String amount, String fieldName) {
        amount = Formatter.removeAmountFormatting(amount);
        try {
            double value = Double.parseDouble(amount);
            if (value <= 0) {
                return "Amount must be greater than zero.";
            }
            return ""; // No error, valid salary amount
        } catch (NumberFormatException e) {
            return "Invalid " + fieldName + ". Please enter a valid number.";
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
