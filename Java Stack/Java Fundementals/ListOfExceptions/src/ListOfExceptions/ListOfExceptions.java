package ListOfExceptions;

import java.util.ArrayList;

public class ListOfExceptions {
	public static void main(String[] args) {
		ArrayList<Object> myList = new ArrayList<Object>();
		myList.add("13");
		myList.add("Hello World");
		myList.add(48);
		myList.add("Goodbye World");
		for (int i = 0; i < myList.size(); i++) {
			try {
				Integer castedValue = (Integer) myList.get(i);
				System.out.println(castedValue);
				System.out.println("--------------------");
			} catch (ClassCastException e) {

				System.out.println("Exception: " + e);
				System.out.println("Index: " + i);
				System.out.println("Value: " + myList.get(i));
				System.out.println("--------------------");

			}
		}
	}
}
