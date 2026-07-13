<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Game</title>
</head>
<body>

<h1>Edit Game</h1>

<form:form action="/games/${game.id}" method="post" modelAttribute="game">

    <!-- Hidden input makes POST act like PUT -->
    <input type="hidden" name="_method" value="put">

    <p>
        Game Name:
        <form:input path="gameName"/>
        <form:errors path="gameName" style="color:red"/>
    </p>

    <p>
        Genre:
        <form:select path="genre">
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

    <button>Apply</button>

    <a href="/game/${game.id}">
        <button type="button">Cancel</button>
    </a>

</form:form>

</body>
</html>