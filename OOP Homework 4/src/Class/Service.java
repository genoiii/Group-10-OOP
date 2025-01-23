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

    private int totalServiceCost;

    public Service(String ItemName, int price) {
        super(ItemName, price);
    }

    @Override
    public int computeTotalSale(int quantity) {
        return super.getPrice() * quantity;
    }
      
    
}
