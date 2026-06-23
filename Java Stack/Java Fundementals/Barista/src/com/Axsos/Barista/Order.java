package com.Axsos.Barista;
import java.util.ArrayList;

public class Order {
    private String name;
    private boolean ready;
    private ArrayList<Items> item;
    
    

    public Order(String name , boolean ready) {
        this.name = name;
        this.ready = ready;
        this.item = new ArrayList<Items>();
    }
    //Add Items
    public void addItem(Items item) {
        this.item.add(item);
    }
    // function return String
    public String getStatusMessage() {
        if (this.ready) {
            return "Your order is ready.";
        } else {
            return "Thank you for waiting. Your order will be ready soon.";
        }
    }
    //get the price of the items
    public double getOrderTotal() {
        double total = 0;

        for (int i = 0; i < item.size();i++) {
            total += item.get(i).getPrice();
        }

        return total;
    }
    //Display Function
    public void display() {
        System.out.println("Customer Name: " + this.name);

        for (Items item : this.item) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }

        System.out.println("Total: $" + this.getOrderTotal());
    }
    //Getters
    public String getName() {
        return name;
    }

    public boolean isReady() {
        return ready;
    }

    public ArrayList<Items> getItems() {
        return item;
    }
    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setItems(ArrayList<Items> items) {
        this.item = items;
    }
}
