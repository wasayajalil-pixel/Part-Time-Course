# 📚 BooksAPI

A Spring Boot MVC CRUD application for managing books. This project demonstrates the fundamentals of Spring Boot, Spring MVC, Spring Data JPA, MySQL, and JSP by allowing users to create, read, update, and delete books.

---

## 🚀 Features

- View all books
- View a single book's details
- Create a new book
- Update an existing book
- Delete a book
- REST API endpoints for CRUD operations
- JSP pages for displaying book data

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3
- Spring MVC
- Spring Data JPA
- MySQL
- JSP
- JSTL
- Maven
- Tomcat

---

## 📂 Project Structure

```
src
├── main
│   ├── java
│   │   └── com.axsos.booksapi
│   │       ├── controllers
│   │       ├── models
│   │       ├── repositories
│   │       ├── services
│   │       └── BooksApiApplication.java
│   │
│   ├── resources
│   │   └── application.properties
│   │
│   └── webapp
│       └── WEB-INF
│           ├── index.jsp
│           └── show.jsp
```

---

## 📌 API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/books` | Retrieve all books |
| GET | `/api/books/{id}` | Retrieve one book |
| POST | `/api/books` | Create a new book |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |

---

## 🌐 Web Pages

| URL | Description |
|-----|-------------|
| `/books` | Display all books |
| `/books/{id}` | Display a single book |

---

## ⚙️ Database Configuration

Configure your `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/booksdb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.mvc.view.prefix=/WEB-INF/
spring.mvc.view.suffix=
```

Replace `YOUR_PASSWORD` with your MySQL password.

---

## ▶️ Running the Project

1. Clone the repository

```bash
git clone https://github.com/your-username/BooksAPI.git
```

2. Open the project in Spring Tool Suite (STS) or Eclipse.

3. Create the MySQL database (or let Spring create it):

```sql
CREATE DATABASE booksdb;
```

4. Update `application.properties` with your MySQL credentials.

5. Run the application:

```
Run As → Spring Boot App
```

6. Open your browser:

```
http://localhost:8080/books
```

or test the REST API:

```
http://localhost:8080/api/books
```

---


---

## 📖 Learning Objectives

This project demonstrates how to:

- Build a Spring Boot MVC application
- Use Spring Data JPA with MySQL
- Implement CRUD operations
- Create REST APIs
- Pass data from the controller to JSP views
- Use JSTL to render dynamic data
- Follow the MVC design pattern

---

## 👨‍💻 Author

**Jalil Wasaya**

