package AlfredBot;
import java.util.Date;

public class AlfredQuotes {
 public String basicGreeting() {
	 return "Hello, lovely to see you. How are you?";
 }
 public String guestGreeting(String name) {
     return String.format("Hello, %s. Lovely to see you.", name);
 }
 public String dateAnnouncement() {
     Date today = new Date();
     return String.format("It is currently %s.", today);
 }
 public String respondBeforeAlexis(String conversation) {
     if (conversation.indexOf("Alexis") >= 0) {
         return "Right away, sir. She certainly isn't sophisticated enough for that.";
     } else if (conversation.indexOf("Alfred") >= 0) {
         return "At your service. As you wish, naturally.";
     } else {
         return "Right. And with that I shall retire.";
     }
 }
 
 
}
