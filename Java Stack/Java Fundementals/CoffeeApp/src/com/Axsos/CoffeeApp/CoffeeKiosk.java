package com.Axsos.CoffeeApp;

import java.util.ArrayList;

import java.util.Scanner;

public class CoffeeKiosk {
    private ArrayList<Items> menu;
    private ArrayList<Order> orders;

    public CoffeeKiosk() {
        this.menu = new ArrayList<Items>();
        this.orders = new ArrayList<Order>();
    }

    public void addMenuItem(String name, double price) {
        Items item = new Items(name, price);
        item.setIndex(this.menu.size());
        this.menu.add(item);
    }

    public void displayMenu() {
        for (Items item : menu) {
            System.out.printf("%d %s -- $%.2f%n",
                    item.getIndex(),
                    item.getName(),
                    item.getPrice());
        }
    }
    public void newOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter customer name for new order:");
        String name = scanner.nextLine();

        Order order = new Order(name);

        displayMenu();

        System.out.println("Please enter a menu item index or q to quit:");
        String itemNumber = scanner.nextLine();

        while (!itemNumber.equals("q")) {
            int index = Integer.parseInt(itemNumber);

            if (index >= 0 && index < menu.size()) {
                Items item = menu.get(index);
                order.addItem(item);
                System.out.println(item.getName() + " added to order.");
            } else {
                System.out.println("Invalid item number.");
            }

            System.out.println("Please enter a menu item index or q to quit:");
            itemNumber = scanner.nextLine();
        }

        orders.add(order);

        System.out.println("Order complete!");
        order.display();
    }
    
    // NINJA BONUS
    public void addMenuItemByInput() {
        System.out.println("Enter item name:");
        String name = System.console().readLine();

        System.out.println("Enter item price:");
        String priceInput = System.console().readLine();

        double price = Double.parseDouble(priceInput);

        addMenuItem(name, price);
    }
}