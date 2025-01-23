/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author 63909
 */
public class Service extends Sale{

    public Service(String ItemName, int price) {
        super(ItemName, price);
    }

    @Override
    public int computeTotalSale(int hours) {
        return price * hours;
    }
      
    
}
