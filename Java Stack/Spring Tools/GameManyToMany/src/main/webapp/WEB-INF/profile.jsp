<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile Page</title>
</head>
<body>

<h1>Welcome ${loggedUser.fullName}</h1>

<a href="/dashboard">Dashboard</a>
<a href="/logout">Logout</a>

<hr>

<h2>Player Info</h2>


<p>Name: ${profileUser.fullName}</p>
<p>Email: ${profileUser.email}</p>

<p>
    DOB:
    <fmt:formatDate value="${profileUser.dateOfBirth}" pattern="dd/MM/yyyy"/>
</p>

<hr>

<h2>Favourite Games</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Game</th>
    </tr>

    <c:forEach var="game" items="${profileUser.favoriteGames}">
        <tr>
            <td>${game.id}</td>
            <td>
                <a href="/game/${game.id}">
                    ${game.gameName}
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>