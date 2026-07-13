<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game Info</title>
</head>
<body>

<h1>Welcome ${loggedUser.fullName}</h1>

<a href="/logout">Logout</a>
<a href="/dashboard">Dashboard</a>

<hr>

<h2>Game Info</h2>

<p>
    <strong>Game name:</strong>
    ${game.gameName}
</p>

<p>
    <strong>Genre:</strong>
    ${game.genre}
</p>

<p>
    <strong>Release Date:</strong>
    <fmt:formatDate value="${game.releaseDate}" pattern="dd MMMM yyyy"/>
</p>

<p>
    <strong>Description:</strong>
    ${game.description}
</p>

<p>
    <strong>Created By:</strong>
    ${game.creator.fullName}
</p>

<!-- Edit/Delete appear only for creator -->
<c:if test="${game.creator.id == loggedUser.id}">

    <a href="/edit/game/${game.id}">
        <button>Edit</button>
    </a>

    <form action="/games/${game.id}" method="post" style="display:inline;">
        <input type="hidden" name="_method" value="delete">
        <button>Delete</button>
    </form>

</c:if>

<hr>

<h3>The players who like this game the most</h3>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Player Name</th>
        <th>Rate</th>
    </tr>

    <c:forEach var="rating" items="${game.ratings}">
        <tr>
            <td>${rating.user.id}</td>
            <td>
                <a href="/profile/${rating.user.id}">
                    ${rating.user.fullName}
                </a>
            </td>
            <td>${rating.rate}</td>
        </tr>
    </c:forEach>
</table>
<c:if test="${game.creator.id != loggedUser.id}">

    <form action="/game/${game.id}/favorite" method="post">
        <button>Add To Fav</button>
    </form>

    <form action="/game/${game.id}/rate" method="post">
        <label>Rate:</label>

        <select name="rate">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>

        <button>Rate</button>
    </form>

</c:if>

</body>
</html>