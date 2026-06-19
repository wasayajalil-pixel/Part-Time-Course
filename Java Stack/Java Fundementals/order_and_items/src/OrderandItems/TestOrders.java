package OrderandItems;

public class TestOrders {
	public static void main(String[] args) {
//Items
		Items items1 = new Items("mocha",3.50);
		Items items2 = new Items("latte",4.50);
		Items items3 = new Items("drip coffee",5.50);
		Items items4 = new Items("cappuccino",6.50);


//Orders
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();
		Order order4 = new Order();
		
		order1.name = "Rami";
		order2.name = "Jimmy";
		order3.name = "Noah";
		order4.name = "Sam";
		
		System.out.println(order1);
		System.out.println(order1.total);
		//Add item1 to order2's item list and increment the order's total
		order2.items.add(items1);
		order2.total += items1.price;
		//Noah ordered a cappuccino
		order3.items.add(items4);
		order3.total += items4.price;
		//Sam added a latte
		order4.items.add(items2);
		order4.total += items2.price;
		//Rami’s order is now ready
		order1.ready = true;
		//Sam ordered more drinks - 2 lattes
		order4.items.add(items2);
		order4.items.add(items2);
		order4.total += items2.price *2;
		//Jimmy’s order is now ready
		order2.ready = true;
        //print all the order		
		System.out.printf("Name: %s\n", order1.name);
        System.out.printf("Total: %s\n", order1.total);
        System.out.printf("Ready: %s\n", order1.ready);
        
		System.out.printf("Name: %s\n", order2.name);
        System.out.printf("Total: %s\n", order2.total);
        System.out.printf("Ready: %s\n", order2.ready);
        
		System.out.printf("Name: %s\n", order3.name);
        System.out.printf("Total: %s\n", order3.total);
        System.out.printf("Ready: %s\n", order3.ready);
        
		System.out.printf("Name: %s\n", order4.name);
        System.out.printf("Total: %s\n", order4.total);
        System.out.printf("Ready: %s\n", order4.ready);
		

	}

}
