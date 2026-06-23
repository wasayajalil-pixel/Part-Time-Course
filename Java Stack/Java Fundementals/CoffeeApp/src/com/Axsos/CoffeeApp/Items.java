package com.Axsos.CoffeeApp;

public class Items {
	//Items Variable
	private String name;
	private double price;
	private int index;
	//CONSTRUCTOR
	public Items(String name,Double price) {
		this.name = name;
		this.price = price;
	}
	//getter
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
    public int getIndex() {
        return index;
    }
    //setter
    public void setIndex(int index) {
        this.index = index;
    }
}
