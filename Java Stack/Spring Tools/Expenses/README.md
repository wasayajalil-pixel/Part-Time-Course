# Expense Tracker

## Overview

Expense Tracker is a full-stack Java Spring Boot application that allows users to manage their daily expenses in one place. The application works as a digital pocketbook where users can record every expense they make, including the expense name, vendor, amount paid, and a detailed description.

This project was developed to practice the complete CRUD (Create, Read, Update, Delete) operations using Spring Boot, Spring MVC, Spring Data JPA, MySQL, JSP, and JSTL. It demonstrates how the different layers of a Spring application work together to build a real-world web application.

---

# Project Objective

The goal of this project is to help users keep track of their spending and organize their expenses for budgeting purposes.

Users are able to:

- View all expenses
- Add a new expense
- View the details of an expense
- Edit an existing expense
- Delete an expense
- Store all data permanently in a MySQL database

This project follows the MVC (Model-View-Controller) design pattern and RESTful routing.

---

# Features

### Home Page

The home page displays all saved expenses inside a table.

Each row contains:

- Expense Name
- Vendor Name
- Amount Paid
- Action buttons

The expense name is clickable and redirects the user to a page displaying all information about that expense.

---

### Add Expense

Users can create a new expense using a form located below the expense table.

The form contains:

- Expense Name
- Vendor Name
- Amount
- Description

Spring Validation ensures that all required fields are completed before saving.

---

### Show Expense

Clicking an expense name opens a details page.

This page displays:

- Expense Name
- Vendor Name
- Amount Paid
- Description

This page is used only for viewing data.

---

### Edit Expense

Users can update any expense.

The edit form is automatically filled with the current values.

Users can update:

- Expense Name
- Vendor Name
- Amount
- Description

Validation is performed again before updating the database.

---

### Delete Expense

Users can remove an expense from the database by clicking the Delete button.

Spring Boot uses HiddenHttpMethodFilter so HTML forms can perform DELETE requests.

---

# Technologies Used

## Backend

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Maven

---

## Frontend

- JSP
- JSTL
- HTML5
- CSS

---

## Database

- MySQL

---

## Validation

- Jakarta Bean Validation

Examples:

- @NotBlank
- @NotNull
- @Min
- @Size

---

# Project Architecture

The project follows the MVC architecture.

```
                Browser
                   │
                   ▼
             ExpenseController
                   │
                   ▼
             ExpenseService
                   │
                   ▼
          ExpenseRepository
                   │
                   ▼
                MySQL
```

---

# Project Structure

```
ExpenseTracker
│
├── src
│   │
│   ├── main
│   │   │
│   │   ├── java
│   │   │
│   │   └── com.axsos.expenses
│   │       │
│   │       ├── controllers
│   │       │     └── ExpenseController.java
│   │       │
│   │       ├── models
│   │       │     └── Expense.java
│   │       │
│   │       ├── repositories
│   │       │     └── ExpenseRepository.java
│   │       │
│   │       ├── services
│   │       │     └── ExpenseService.java
│   │       │
│   │       └── ExpenseApplication.java
│   │
│   └── webapp
│       │
│       └── WEB-INF
│             │
│             ├── index.jsp
│             ├── show.jsp
│             └── edit.jsp
│
└── pom.xml
```

---

# Database Design

Table Name

```
expenses
```

Columns

| Column | Type |
|---------|------|
| id | BIGINT |
| expense_name | VARCHAR |
| vendor_name | VARCHAR |
| amount | DOUBLE |
| description | TEXT |
| created_at | DATETIME |
| updated_at | DATETIME |

---

# RESTful Routes

| HTTP Method | URL | Description |
|-------------|-----|-------------|
| GET | / | Display all expenses |
| POST | /expenses | Create expense |
| GET | /expenses/{id} | Display one expense |
| GET | /expenses/{id}/edit | Edit page |
| PUT | /expenses/{id} | Update expense |
| DELETE | /expenses/{id} | Delete expense |

---

# CRUD Operations

## Create

Users submit the Add Expense form.

The controller validates the data.

If validation succeeds:

```
Controller
      ↓
Service
      ↓
Repository
      ↓
Database
```

The expense is saved.

---

## Read

The controller retrieves all expenses from the database.

The service calls:

```
expenseRepository.findAll()
```

The data is sent to JSP and displayed inside a table.

---

## Update

The user selects Edit.

The application loads the expense information.

After editing:

```
PUT Request
        ↓
Controller
        ↓
Service
        ↓
Repository
        ↓
Database Updated
```

---

## Delete

The Delete button submits a form.

HTML only supports GET and POST.

Spring converts

```
POST
```

into

```
DELETE
```

using

```html
<input type="hidden" name="_method" value="delete">
```

---

# Validation Rules

Expense Name

- Required

Vendor Name

- Required

Amount

- Required
- Must be greater than zero

Description

- Required
- Minimum 5 characters

If validation fails, error messages are displayed next to the input fields.

---

# Spring Components Used

## Entity

Represents the database table.

```
Expense.java
```

---

## Repository

Communicates with MySQL.

```
ExpenseRepository
```

Provides methods such as:

- save()
- findAll()
- findById()
- deleteById()

---

## Service

Contains the application's business logic.

The controller never talks directly to the database.

Instead:

```
Controller
      ↓
Service
      ↓
Repository
```

---

## Controller

Handles incoming HTTP requests.

Examples:

```
GET
POST
PUT
DELETE
```

It also sends data to JSP pages.

---

## JSP

Responsible for displaying information to users.

Pages:

- index.jsp
- show.jsp
- edit.jsp

---

# Validation Flow

```
User submits form
        │
        ▼
Controller
        │
        ▼
@Valid
        │
        ▼
BindingResult
        │
 ┌──────┴──────┐
 │             │
Errors      No Errors
 │             │
 ▼             ▼
Return JSP   Save Expense
```

---

# Hidden Input

HTML forms cannot send PUT or DELETE requests.

Spring solves this using hidden inputs.

Update

```html
<input type="hidden" name="_method" value="put">
```

Delete

```html
<input type="hidden" name="_method" value="delete">
```

---

# Learning Outcomes

By completing this project, I learned how to:

- Build a complete CRUD application using Spring Boot.
- Connect Spring Boot with MySQL.
- Use Spring MVC architecture.
- Create RESTful routes.
- Perform Create, Read, Update, and Delete operations.
- Use Spring Data JPA repositories.
- Implement service and repository layers.
- Validate user input using Jakarta Validation.
- Display validation errors in JSP.
- Use ModelAttribute and BindingResult.
- Pass data between the controller and views.
- Handle PUT and DELETE requests using hidden inputs.
- Organize a Spring Boot project using the MVC design pattern.

---

# Future Improvements

Possible enhancements include:

- User authentication and authorization.
- Search expenses by name or vendor.
- Filter expenses by date.
- Expense categories.
- Monthly and yearly reports.
- Pie charts and graphs.
- Pagination.
- Export expenses to Excel or PDF.
- Responsive Bootstrap user interface.
- Dark mode.
- Dashboard showing total expenses.
- Budget limits with notifications.

---

# Conclusion

Expense Tracker is a complete full-stack CRUD application built with Java Spring Boot. It demonstrates the core concepts of modern web application development, including MVC architecture, RESTful routing, database integration, form validation, and data persistence. Through this project, I gained practical experience in building dynamic web applications and strengthened my understanding of Spring Boot, JSP, MySQL, and the full CRUD lifecycle.