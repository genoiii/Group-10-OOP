/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import javax.swing.JOptionPane;

/**
 * Utility class for displaying message dialogs.
 *
 * <p>This class provides static methods to show informational, warning, and error dialogs
 * to the user. It is not meant to be instantiated.</p>
 */
public class MessageDialog {
    
    /**
     * Private constructor to prevent instantiation.
     *
     * <p>This constructor is private to enforce that the MessageDialog utility class cannot be instantiated.</p>
     */
    private MessageDialog() {
        throw new AssertionError("Cannot instantiate MessageDialog"); // Throw an error if instantiation is attempted.
    }

    /**
     * Displays a message dialog.
     *
     * <p>This method wraps {@code JOptionPane.showMessageDialog} to show a message dialog with the specified
     * message, title, and message type. The parent component is set to {@code null} so that the dialog is centered
     * on the screen.</p>
     *
     * @param message the message to display.
     * @param title the title of the dialog.
     * @param messageType the type of message (e.g., {@code JOptionPane.INFORMATION_MESSAGE}).
     */
    public static void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType); // Display the dialog with the provided message, title, and type.
    }
    
    /**
     * Displays an informational message dialog.
     *
     * <p>This method wraps {@code showMessage} to display a dialog with an information icon
     * and a preset title ("Information").</p>
     *
     * @param message the message to display.
     */    
    public static void showInfoMessage(String message) {
        showMessage(message, "Information", JOptionPane.INFORMATION_MESSAGE); // Call showMessage with the preset title and message type for informational dialogs.
    }
    
    /**
     * Displays a warning message dialog.
     *
     * <p>This method wraps {@code showMessage} to display a dialog with a warning icon
     * and a preset title ("Warning").</p>
     *
     * @param message the message to display.
     */    
    public static void showWarningMessage(String message) {
        showMessage(message, "Warning", JOptionPane.WARNING_MESSAGE); // Call showMessage with the preset title and message type for warning dialogs.
    }
    
    /**
     * Displays an error message dialog.
     *
     * <p>This method wraps {@code showMessage} to display a dialog with an error icon
     * and a preset title ("Error").</p>
     *
     * @param message the message to display.
     */    
    public static void showErrorMessage(String message) {
        showMessage(message, "Error", JOptionPane.ERROR_MESSAGE); // Call showMessage with the preset title and message type for error dialogs.
    }
    
    /**
     * Displays a confirmation dialog.
     *
     * <p>This method shows a dialog with "Yes" and "No" options and returns true if the user selects "Yes".</p>
     *
     * @param message the message to display in the confirmation dialog.
     * @return true if the user confirms with "Yes"; false otherwise.
     */    
    public static boolean showConfirmMessage(String message) {
        int result = JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION); // Display the confirmation dialog with "Yes" and "No" options.
        return result == JOptionPane.YES_OPTION; // Return true if the user selected "Yes", false otherwise.
    }
}