# Daikichi Path Variables

## Description
This project is a Spring Boot application that demonstrates the use of static and dynamic routes with `@PathVariable`. The application returns plain text responses based on the URL entered in the browser.

## Technologies Used
- Java 17
- Spring Boot
- Maven
- Spring Web

## Features
- Welcome message
- Today's fortune
- Tomorrow's fortune
- Travel message using a city path variable
- Lotto fortune based on whether the provided number is even or odd

## Routes

| URL | Description |
|------|-------------|
| `/daikichi` | Displays "Welcome!" |
| `/daikichi/today` | Displays today's fortune |
| `/daikichi/tomorrow` | Displays tomorrow's fortune |
| `/daikichi/travel/{city}` | Displays a travel message for the specified city |
| `/daikichi/lotto/{number}` | Displays a fortune based on whether the number is even or odd |

## Example URLs

### Welcome
http://localhost:8080/daikichi

Output:
Welcome!

### Today
http://localhost:8080/daikichi/today

Output:
Today you will find luck in all your endeavors!

### Tomorrow
http://localhost:8080/daikichi/tomorrow

Output:
Tomorrow, an opportunity will arise, so be sure to be open to new ideas!

### Travel
http://localhost:8080/daikichi/travel/kyoto

Output:
Congratulations! You will soon travel to kyoto!

### Lotto (Even)
http://localhost:8080/daikichi/lotto/8

Output:
You will take a grand journey in the near future but be wary of tempting offers.

### Lotto (Odd)
http://localhost:8080/daikichi/lotto/7

Output:
You have enjoyed the fruits of your labor, but now is a great time to spend time with family and friends.

## Concepts Practiced
- `@RestController`
- `@RequestMapping`
- `@PathVariable`
- Dynamic Routing
- Conditional Statements (`if` / `else`)

## Author
Jalil Wasaya