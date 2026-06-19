package MapOfTheHashmatique;
import java.util.HashMap;
import java.util.Set;

public class MapOfTheHashmatique {
	public static void main(String[] args) {
		// Create HashMap
		HashMap<String,String> trackList = new HashMap<String,String>();
		// Add at least 4 songs
		trackList.put("Wael Kfoury", "Chou Meshta2li");
		trackList.put("Saint Levant", "Sabah El Ward");
		trackList.put("Haifa Wehbe", "Shul Matloub");
		trackList.put("Tamer Hosny", "Hormone Elsaada");
        // pull out of the song by the track title
		trackList.get("Haifa Wehbe");
		System.out.println(trackList.get("Haifa Wehbe"));
		// Print all tracks
		for(String name:trackList.keySet()) {
			System.out.println(name + ":" + trackList.get(name));
		}
		
	}

}
