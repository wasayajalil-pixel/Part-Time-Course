package com.Axsos.CoffeeApp;

import java.util.ArrayList;

public class Order {
    private String name;
    private boolean ready;
    private ArrayList<Items> items;

    public Order(String name) {
        this.name = name;
        this.setReady(false);
        this.items = new ArrayList<Items>();
    }

    public void addItem(Items item) {
        this.items.add(item);
    }

    public double getOrderTotal() {
        double total = 0;

        for (Items item : items) {
            total += item.getPrice();
        }

        return total;
    }

    public void display() {
        System.out.println("Customer: " + this.name);

        for (Items item : items) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }

        System.out.println("Total: $" + getOrderTotal());
    }

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
}