package OrderandItems;
import java.util.ArrayList;

public class Order {
	public String name;
	public double total;
	public boolean ready;
	public ArrayList<Items> items;
	
    public Order() {
    	name ="" ;
    	total = 0.00;
    	ready = false;
    	items = new ArrayList<>();	
 }
}
