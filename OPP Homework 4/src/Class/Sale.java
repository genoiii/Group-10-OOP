/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author 63909
 */
public abstract class Sale {
    private String ItemName;
    private int price;
    
    public Sale(String ItemName, int price){
        this.ItemName = ItemName;
        this.price = price;
    }
    
    public abstract int computeTotalSale(int quantity);
    
    public int getPrice(){
        return price;
    }
    
    public String getItemName(){
        return ItemName;
    }
}
