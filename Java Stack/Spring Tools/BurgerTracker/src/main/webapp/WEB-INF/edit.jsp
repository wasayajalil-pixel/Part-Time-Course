<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Burger</title>
</head>
<body>

    <h1>Edit Burger</h1>

    <form:form action="/burgers/${burger.id}/update" method="post" modelAttribute="burger">

        <!-- Important: keep the id so Spring knows this is update, not create -->
        <form:hidden path="id" />

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

        <button type="submit">Update Burger</button>

    </form:form>

    <br>
    <a href="/">Back to Home</a>

</body>
</html>