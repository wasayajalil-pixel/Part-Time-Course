# 🎋 Omikuji Fortune Generator

The **Omikuji Fortune Generator** is a Spring Boot MVC web application that generates personalized fortunes based on user input. Users complete a form by entering a number, city, person, hobby, living thing, and a kind message. After submitting the form, the application stores the information in an HTTP session and redirects the user to a page that displays a customized fortune.

## 🚀 Features

* Display an Omikuji input form
* Process form submissions using POST requests
* Store user data with `HttpSession`
* Redirect to a fortune page
* Dynamically display fortunes using JSP and Expression Language (EL)
* Simple and clean user interface

## 🛠️ Technologies Used

* Java 17
* Spring Boot
* Spring MVC
* JSP
* JSTL
* Maven
* HTML5
* CSS3

## 📂 Project Structure

```text
src
└── main
    ├── java
    │   └── com.Axsos.Omikuji
    │       ├── OmikujiApplication.java
    │       └── controllers
    │           └── OmikujiController.java
    ├── resources
    │   ├── application.properties
    │   └── static
    │       └── css
    │           └── style.css
    └── webapp
        └── WEB-INF
            ├── index.jsp
            └── show.jsp
```

## 📋 Application Flow

1. Open **/omikuji**.
2. Fill out the Omikuji form.
3. Submit the form.
4. The controller receives the data using `@RequestParam`.
5. The data is stored in an `HttpSession`.
6. The application redirects to **/omikuji/show**.
7. The fortune page displays the personalized message using JSP and EL.

## 📚 Concepts Practiced

* Spring MVC
* Controllers
* `@GetMapping`
* `@PostMapping`
* `@RequestParam`
* `HttpSession`
* Redirects
* JSP
* JSTL
* Expression Language (EL)
* Static Resources


## 👨‍💻 Author

**Jalil Wasaya**
