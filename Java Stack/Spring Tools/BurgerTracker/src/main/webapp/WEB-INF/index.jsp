<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Burger Tracker</title>
</head>
<body>

    <h1>Burger Tracker</h1>

    <h2>All Burgers</h2>

    <table border="1">
        <thead>
            <tr>
                <th>Burger Name</th>
                <th>Restaurant</th>
                <th>Rating</th>
                <th>Notes</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="burger" items="${burgers}">
                <tr>
                    <td>${burger.burgerName}</td>
                    <td>${burger.restaurantName}</td>
                    <td>${burger.rating}</td>
                    <td>${burger.notes}</td>
                    <td>
                        <a href="/burgers/${burger.id}/edit">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <hr>

    <h2>Add a Burger</h2>

    <form:form action="/burgers" method="post" modelAttribute="burger">

        <p>
            <form:label path="burgerName">Burger Name:</form:label>
            <form:input path="burgerName" />
            <form:errors path="burgerName" />
        </p>

        <p>
            <form:label path="restaurantName">Restaurant Name:</form:label>
            <form:input path="restaurantName" />
            <form:errors path="restaurantName" />
        </p>

        <p>
            <form:label path="rating">Rating:</form:label>
            <form:input type="number" path="rating" />
            <form:errors path="rating" />
        </p>

        <p>
            <form:label path="notes">Notes:</form:label>
            <form:textarea path="notes" />
            <form:errors path="notes" />
        </p>

        <button type="submit">Add Burger</button>

    </form:form>

</body>
</html>