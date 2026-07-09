<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game Info</title>
</head>
<body>

<h1>Game Info</h1>

<h3>Welcome ${loggedUser.firstName} ${loggedUser.lastName}</h3>
<a href="/dashboard">Dashboard</a>
|
<a href="/logout">Logout</a>

<hr>

<p>Game name: ${game.name}</p>
<p>Genre: ${game.genre}</p>
<p>Release Date: ${game.releaseDate}</p>
<p>Description: ${game.description}</p>
<p>Created By: ${game.creator.firstName} ${game.creator.lastName}</p>

<c:if test="${game.creator.id == loggedUser.id}">
    <a href="/edit/game/${game.id}">Edit</a>
    |
    <a href="/delete/game/${game.id}">Delete</a>
</c:if>

</body>
</html>