package AbstractArt;

import java.util.ArrayList;

public class Museum {
	public static void main(String[]args) {
		ArrayList<Art> museum = new ArrayList<Art> ();
		
		museum.add(new Painting(
				"Monalisa", "Leonardo de Vinci", "A portrait of a Women", "Oil"
				));
		museum.add(new Painting(
				"Venus Rising","Jean-Léon Gérôme","A potrait of a Gladiater","Oil"
				));
		museum.add(new Painting(
				"Immaculate Conception c. 1628","Peter Paul Rubens","A potrait of Holy Mary","Oil"
				));
		museum.add(new Sculpture(
	                "David","Michelangelo","Renaissance sculpture","Marble"));

	    museum.add(new Sculpture(
	                "The Thinker","Auguste Rodin","Man thinking deeply","Bronze"));
	         
	    for(int i =0;i < museum.size();i++) {
	    	museum.get(i).viewArt();
	    	
	    
	    }
	}

}
