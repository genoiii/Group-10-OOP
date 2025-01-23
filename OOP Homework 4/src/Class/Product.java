/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author 63909
 */
public class Product extends Sale{

    public Product(String ItemName, int price) {
        super(ItemName, price);
    }

    @Override
    public int computeTotalSale(int quantity) {
        return price * quantity;
    }
      
    
}
