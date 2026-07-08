<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dojo Details</title>
</head>
<body>

    <h1>${dojo.dojoName} Location Ninjas</h1>

    <table border="1">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
        </tr>

        <c:forEach var="ninja" items="${dojo.ninjas}">
            <tr>
                <td>${ninja.firstName}</td>
                <td>${ninja.lastName}</td>
                <td>${ninja.age}</td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="/ninjas/new">Add Ninja</a>
    <br>
    <a href="/dojos/new">Create Dojo</a>

</body>
</html>