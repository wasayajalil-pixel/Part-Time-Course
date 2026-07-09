<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Registration</title>
</head>
<body>

    <h1>Login Registration</h1>

    <!-- ================= REGISTER FORM ================= -->
    <h2>Create Account</h2>

    <form:form action="/register" method="post" modelAttribute="newUser">

        <p>
            First Name:
            <form:input path="firstName"/>
            <form:errors path="firstName"/>
        </p>

        <p>
            Last Name:
            <form:input path="lastName"/>
            <form:errors path="lastName"/>
        </p>

        <p>
            Email:
            <form:input path="email"/>
            <form:errors path="email"/>
        </p>

        <p>
            Date Of Birth:
            <form:input type="date" path="birthday"/>
            <form:errors path="birthday"/>
        </p>

        <p>
            Password:
            <form:password path="password"/>
            <form:errors path="password"/>
        </p>

        <p>
            Confirm Password:
            <form:password path="confirm"/>
            <form:errors path="confirm"/>
        </p>

        <button type="submit">Create Account</button>

    </form:form>


    <hr>


    <!-- ================= LOGIN FORM ================= -->
    <h2>Sign In</h2>

    <form:form action="/login" method="post" modelAttribute="newLogin">

        <p>
            Email:
            <form:input path="email"/>
            <form:errors path="email"/>
        </p>

        <p>
            Password:
            <form:password path="password"/>
            <form:errors path="password"/>
        </p>

        <button type="submit">SignIn</button>

    </form:form>

</body>
</html>