/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 * A custom renderer for JComboBox that displays a prompt when no item is selected.
 */
public class PromptComboBoxRenderer extends BasicComboBoxRenderer {
    private String prompt; // Prompt text to display when no selection is made

    /**
     * Constructs a PromptComboBoxRenderer with the specified prompt.
     *
     * @param prompt the text to display as a prompt when no item is selected
     */
    public PromptComboBoxRenderer(String prompt)
    {
        this.prompt = prompt;
    }

    /**
     * Returns a component configured to display the given value.
     * Displays the prompt text if the value is null.
     *
     * @param list the JList we're painting
     * @param value the value to display
     * @param index the cell index
     * @param isSelected true if the cell is selected
     * @param cellHasFocus true if the cell has focus
     * @return the component used for drawing the cell
     */
    @Override
    public Component getListCellRendererComponent(
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value == null)
            setText( prompt ); // Show prompt if no value is selected

        return this;
    }
}