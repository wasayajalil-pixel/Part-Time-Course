package cafe_java;

public class Main {
	public static void main(String[] args) {
//		App Variables
		String generalGreeting = "Welcome to Cafe Java, ";
        String pendingMessage = ", your order will be ready shortly";
        String readyMessage = ", your order is ready";
        String displayTotalMessage = "Your total is $";		
        
//        Menu variables
        double mochaPrice = 3.5;
        double esspressoPrice  = 2.5;
        double cappuccinoPrice = 4.99;
        double lattePrice = 3.8;
        
//        Customer name variables
        String customer1 = "Shatha";
        String customer2 = "Ahmed";
        String customer3 = "Sally";
        String customer4 = "Adam";
        
//      order completions  
        boolean isReadyOrder1 = false;
        boolean isReadyOrder2 = true;
        boolean isReadyOrder3 = false;
        boolean isReadyOrder4 = true;
        
//       Welcome example
        System.out.println(generalGreeting + customer1);
        
//        Sally ordered 
        if (isReadyOrder3) {
            System.out.println(customer3 + readyMessage);
        } else {
            System.out.println(customer3 + pendingMessage);
        }
        
//      Ahmed order and total price  
        if (isReadyOrder2) {
            System.out.println(customer2 + readyMessage);
            System.out.println(displayTotalMessage + cappuccinoPrice);
        } else {
            System.out.println(customer2 + pendingMessage);
        }
        
        double sallyTotal = lattePrice * 2;

        if (isReadyOrder3) {
            System.out.println(customer3 + readyMessage);
            System.out.println(displayTotalMessage + sallyTotal);
        } else {
            System.out.println(customer3 + pendingMessage);
            System.out.println(displayTotalMessage + sallyTotal);
        }
        
        // Adam was charged for esspresso but ordered latte
        double adamDifference = lattePrice - esspressoPrice;
        System.out.println(customer4 + readyMessage);
        System.out.println(displayTotalMessage + adamDifference);
    
        
        	
	}
	
}
