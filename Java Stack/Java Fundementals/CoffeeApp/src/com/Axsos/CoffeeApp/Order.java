package com.Axsos.CoffeeApp;
import java.util.ArrayList;

public class Order {
    //Order Variables
    private String name;
    private double total;
    private boolean ready;
    private ArrayList<Items> items;

  //CONSTRUCTOR
    public Order(String name) {
        this.name = name;
        this.total = 0;
        this.ready = false;
        this.items = new ArrayList<>();
    }
    //Getter
    public ArrayList<Items> getItems() {
        return items;
    }

    public void addItem(Items item) {
        items.add(item);
        total += item.getPrice();
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        return total;
    }

    public boolean isReady() {
        return ready;
    }
    //Setter
    public void setReady(boolean ready) {
        this.ready = ready;
    }
}