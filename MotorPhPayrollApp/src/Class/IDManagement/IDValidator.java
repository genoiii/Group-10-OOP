/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.IDManagement;

/**
 *
 * @author 63909
 */
public class IDValidator {
    public static boolean isValidID(String id, IDPrefix prefix) {
        String regex = "^" + prefix.getPrefix() + "-\\d{4}$";
        return id.matches(regex);
    }
}
