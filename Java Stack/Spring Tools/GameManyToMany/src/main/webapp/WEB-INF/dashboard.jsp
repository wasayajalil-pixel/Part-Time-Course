<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
</head>
<body>

<h1>Welcome ${loggedUser.fullName}</h1>

<a href="/logout">Logout</a>

<hr>

<div style="display:flex; gap:60px;">

    <!-- Create Game Form -->
    <div>
        <h2>Create a Game</h2>

        <form:form action="/games" method="post" modelAttribute="newGame">

            <p>
                Game Name:
                <form:input path="gameName"/>
                <form:errors path="gameName" style="color:red"/>
            </p>

            <p>
                Genre:
                <form:select path="genre">
                    <form:option value="">-- Choose Genre --</form:option>
                    <form:option value="Action">Action</form:option>
                    <form:option value="RPG">RPG</form:option>
                    <form:option value="Arcade">Arcade</form:option>
                    <form:option value="Strategy">Strategy</form:option>
                    <form:option value="Tactical shooter">Tactical shooter</form:option>
                    <form:option value="Adventure">Adventure</form:option>
                </form:select>
                <form:errors path="genre" style="color:red"/>
            </p>

            <p>
                Release Date:
                <form:input path="releaseDate" type="date"/>
                <form:errors path="releaseDate" style="color:red"/>
            </p>

            <p>
                Description:
                <form:textarea path="description"/>
                <form:errors path="description" style="color:red"/>
            </p>

            <button>Create a Game</button>

        </form:form>
    </div>

    <!-- Games Table -->
    <div>
        <h2>Games</h2>

        <table border="1">
            <thead>
                <tr>
                    <th>
                        <a href="/dashboard?sort=game">Game</a>
                    </th>
                    <th>
                        <a href="/dashboard?sort=genre">Genre</a>
                    </th>
                    <th>
                        <a href="/dashboard?sort=date">Release Date</a>
                    </th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="game" items="${games}">
                    <tr>
                        <td>
                            <a href="/game/${game.id}">
                                ${game.gameName}
                            </a>
                        </td>

                        <td>${game.genre}</td>

                        <td>
                            <fmt:formatDate value="${game.releaseDate}" pattern="dd MMMM yyyy"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>