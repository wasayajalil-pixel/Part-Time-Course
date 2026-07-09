<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Game</title>
</head>
<body>

<h1>Edit Game</h1>

<form:form action="/update/game/${game.id}" method="post" modelAttribute="game">

    <p>
        Game Name:
        <form:input path="name"/>
        <form:errors path="name"/>
    </p>

    <p>
        Genre:
        <form:input path="genre"/>
        <form:errors path="genre"/>
    </p>

    <p>
        Release Date:
        <form:input type="date" path="releaseDate"/>
        <form:errors path="releaseDate"/>
    </p>

    <p>
        Description:
        <form:textarea path="description"/>
        <form:errors path="description"/>
    </p>

    <button type="submit">Apply</button>
    <a href="/game/${game.id}">Cancel</a>

</form:form>

</body>
</html>