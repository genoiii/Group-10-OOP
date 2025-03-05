/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import javax.swing.DefaultComboBoxModel;

/**
 * Custom ComboBoxModel that extends DefaultComboBoxModel.
 * Overrides getSelectedItem to allow custom processing.
 */
public class ComboBoxModel extends DefaultComboBoxModel<String> {
    /**
     * Constructs the model with the specified items.
     *
     * @param items the array of items for the combo box.
     */
    public ComboBoxModel(String[] items) {
        super(items);
    }
 
    @Override
    public String getSelectedItem() {
        String selectedItem = (String) super.getSelectedItem();
 
        // do something with this job before returning...
 
        return selectedItem;
    }    
}

