/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author 63909
 */
public class Input {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");    
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
    
    public String validateNumber(JTextField textField, String fieldName, int count) {
        String text = textField.getText().replace("-", "");
        try {
            Long value = Long.parseLong(text);
            int valueDigits = text.length();
            if (valueDigits != count) {
                return fieldName + " must be between " + count + " digits.\n";
            }
        } catch (NumberFormatException e) {
            return fieldName + " Invalid ID.\n";
        }
        return "";
    }
    
    public boolean isDate(String date) {
        try {
            Date validDate = dateFormat.parse(date);
            return true;
        } catch (ParseException ex) {
            return false;
        }        
    }
    
    public Date toDate(String date) {
        try {
            Date validDate = dateFormat.parse(date);
            return validDate;
        } catch (ParseException ex) {
            return null;
        }  
    }
    
    public Date toTime(String time) {
        try {
            Date validTime = timeFormat.parse(time);
            return validTime;
        } catch (ParseException ex) {
            return null;
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
