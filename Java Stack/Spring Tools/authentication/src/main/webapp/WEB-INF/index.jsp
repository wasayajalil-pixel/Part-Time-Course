<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login and Registration</title>
</head>
<body>

    <h1>Register</h1>

    <form:form action="/register" method="post" modelAttribute="newUser">

        <p>
            <form:label path="username">Name:</form:label>
            <form:input path="username"/>
            <form:errors path="username" style="color:red"/>
        </p>

        <p>
            <form:label path="email">Email:</form:label>
            <form:input path="email"/>
            <form:errors path="email" style="color:red"/>
        </p>

        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
            <form:errors path="password" style="color:red"/>
        </p>

        <p>
            <form:label path="confirmPassword">Confirm Password:</form:label>
            <form:password path="confirmPassword"/>
            <form:errors path="confirmPassword" style="color:red"/>
        </p>

        <button type="submit">Register</button>

    </form:form>

    <hr>

    <h1>Login</h1>

    <form:form action="/login" method="post" modelAttribute="newLogin">

        <p>
            <form:label path="email">Email:</form:label>
            <form:input path="email"/>
            <form:errors path="email" style="color:red"/>
        </p>

        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
            <form:errors path="password" style="color:red"/>
        </p>

        <button type="submit">Login</button>

    </form:form>

</body>
</html>