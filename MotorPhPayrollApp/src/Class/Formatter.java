/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.text.DecimalFormat;

/**
 * Utility class for formatting values.
 *
 * <p>This class is designed to hold static formatting methods for various data types.
 * It is not meant to be instantiated.</p>
 */
public class Formatter {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    
    /**
     * Formats an SSS number to the pattern "XX-XXXXXXX-X".
     *
     * <p>This method strips all non-numeric characters from the input. If the resulting string
     * has exactly 10 digits, it formats it as "XX-XXXXXXX-X". Otherwise, it returns the processed string as-is.</p>
     *
     * @param sss the SSS number as a string.
     * @return the formatted SSS number if it has 10 digits; otherwise, the original string.
     */
    public static String formatSSS(String sss) {
        sss = sss.replaceAll("[^0-9]", ""); // Remove all non-numeric characters
        if (sss.length() == 10) {
            return String.format("%s-%s-%s", sss.substring(0, 2), sss.substring(2, 9), sss.substring(9)); // Format the string into the pattern XX-XXXXXXX-X
        }
        return sss; // Return original string if it does not have 10 digits
    }

    /**
     * Formats a TIN to the pattern "XXX-XXX-XXX-XXX".
     *
     * <p>This method removes all non-digit characters. If the resulting string has exactly 12 digits,
     * it formats the TIN according to the pattern; otherwise, it returns the cleaned string as-is.</p>
     *
     * @param tin the TIN string.
     * @return the formatted TIN if it has 12 digits; otherwise, the cleaned TIN string.
     */
    public static String formatTIN(String tin) {
        tin = tin.replaceAll("[^0-9]", ""); // Remove non-numeric characters
        if (tin.length() == 12) {
            return String.format("%s-%s-%s-%s", tin.substring(0, 3), tin.substring(3, 6), tin.substring(6, 9), tin.substring(9)); // Format the string into the pattern XXX-XXX-XXX-XXX
        }
        return tin; // Return original string if it does not have 12 digits
    }    
    
    /**
     * Formats a numeric amount for display.
     *
     * <p>This method removes commas and extra spaces from the input string, parses it to a double,
     * and formats it using the predefined decimalFormat. If the input is not a valid number,
     * it returns the original string.</p>
     *
     * @param amount the amount string to format.
     * @return the formatted amount if valid; otherwise, the original string.
     */
    public static String formatAmount(String amount) {
        try {
            double value = Double.parseDouble(amount.replaceAll(",", "").trim()); // Remove commas, trim spaces, and parse the string into a double
            return decimalFormat.format(value); // Format the number and return the formatted string
        } catch (NumberFormatException e) {
            return amount; // Return original if not a valid number
        }
    }
    
    /**
     * Formats a phone number to the pattern "(XXX) XXX-XXXX".
     *
     * <p>This method removes all non-digit characters from the input. If the resulting string contains exactly
     * 10 digits, it formats the number as "(XXX) XXX-XXXX". Otherwise, it returns the original input.</p>
     *
     * @param phoneNumber the phone number to format.
     * @return the formatted phone number if valid; otherwise, the original string.
     */
    public static String formatPhoneNumber(String phoneNumber) {
        String digits = phoneNumber.replaceAll("\\D", ""); // Remove all non-numeric characters

        // Ensure it has exactly 10 digits (adjust if the format is different)
        if (digits.length() == 10) {
            return String.format("(%s) %s-%s", 
                digits.substring(0, 3),  // Area code
                digits.substring(3, 6),  // First three digits
                digits.substring(6));    // Last four digits
        }

        return phoneNumber; // Return original input if it doesn't have 10 digits
    }
    
    /**
     * Removes all non-numeric characters from a formatted phone number.
     *
     * <p>This method strips formatting from the phone number, leaving only the digits.</p>
     *
     * @param formattedPhone the phone number with formatting.
     * @return the phone number containing only digits.
     */
    public static String removePhoneFormatting(String formattedPhone) {
        return formattedPhone.replaceAll("\\D", ""); // Remove non-numeric characters and return the cleaned phone number.
    }
    
    /**
     * Removes formatting from a numeric amount string for CSV storage.
     *
     * <p>This method strips commas and trims extra spaces from the provided amount string.</p>
     *
     * @param formattedAmount the amount string with formatting.
     * @return the cleaned numeric string.
     */
    public static String removeAmountFormatting(String formattedAmount) {
        return formattedAmount.replaceAll(",", "").trim(); // Remove commas and trim extra spaces
    }

}
