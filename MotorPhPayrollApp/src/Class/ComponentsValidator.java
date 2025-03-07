/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

/**
 * Utility class for validating UI components.
 *
 * <p>This class is intended to hold static methods for validating various components.
 * It is not meant to be instantiated.</p>
 */
public class ComponentsValidator {
    
    /**
     * Validates that the given JTextField is not empty.
     *
     * @param textField the JTextField to validate.
     * @return an error message if the field is empty, otherwise an empty string.
     */
    public static String validateRequiredKTextField(JTextField textField){
        // Check if the text field is empty after trimming whitespace.
        if (textField.getText().trim().isEmpty()) {
            return "Please fill in all required fields.";
        }
        
        return ""; // Return an empty string if the text field is populated.
    }
    
    /**
     * Validates that all provided JTextFields are not empty.
     *
     * @param textFields an array of JTextFields to validate.
     * @return an error message if any field is empty; otherwise, an empty string.
     */   
    public static String validateRequiredJTextField(JTextField[] textFields){
        // Iterate through each text field in the array.
        for (JTextField textField : textFields) {
            // Check if the text field is empty after trimming whitespace.
            if (textField.getText().trim().isEmpty()) {
                return "Please fill in all required fields.";
            }
        }
        
        return ""; // Return an empty string if all fields have content.
    }
 
    /**
     * Validates that a date is selected in the given JDateChooser.
     *
     * @param jDateChooser the JDateChooser to validate.
     * @return an error message if no date is selected; otherwise, an empty string.
     */    
    public static String validateRequiredJDateChooser(JDateChooser jDateChooser){
        // Check if no date is selected.
        if (jDateChooser.getDate() == null) {
            return "Please select a valid birthdate.";
        }
        
        return ""; // Return an empty string if a date is selected.
    }
    
}
