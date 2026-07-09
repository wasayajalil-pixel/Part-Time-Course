<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<h1>
		Welcome
		<c:out value="${loggedUser.firstName}" />
	</h1>

	<a href="/logout">Logout</a>
	<hr>
	<h2>Create Game</h2>
	<form:form action="/games/create" method="post"
		modelAttribute="newGame">
		<p>
			Game Name
			<form:input path="name" />
			<form:errors path="name" />
		</p>

		<p>
			Genre
			<form:input path="genre" />
			<form:errors path="genre" />
		</p>

		<p>
			Release Date
			<form:input type="date" path="releaseDate" />
			<form:errors path="releaseDate" />
		</p>

		<p>
			Description
			<form:textarea path="description" />
			<form:errors path="description" />
		</p>

		<button type="submit">Create Game</button>

	</form:form>
	<table border="1">
		<tr>
			<th><a href="/dashboard?sort=name">Game</a></th>
			<th><a href="/dashboard?sort=genre">Genre</a></th>
			<th><a href="/dashboard?sort=releaseDate">Release Date</a></th>
			<th>Action</th>
		</tr>
		<c:forEach var="game" items="${games}">
			<tr>
				<td><a href="/game/${game.id}"> <c:out value="${game.name}" /></a></td>
				<td><c:out value="${game.genre}" /></td>
				<td><c:out value="${game.releaseDate}" /></td>
				<td><a href="/edit/game/${game.id}"> Edit </a> | <a
					href="/delete/game/${game.id}"> Delete </a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>