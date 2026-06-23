package com.Axsos.Barista;
 
public class TestCases {
    public static void main(String[] args) {
        Items dripCoffee = new Items("Drip Coffee", 1.50);
        Items cappuccino = new Items("Cappuccino", 3.50);
 
        Order order1 = new Order("Jalil",true);    
        
        order1.addItem(dripCoffee);
        order1.addItem(cappuccino);
 
        System.out.println(order1.getOrderTotal());
 
        order1.setReady(true);
        System.out.println(order1.getStatusMessage());
 
        order1.display();
    }
}