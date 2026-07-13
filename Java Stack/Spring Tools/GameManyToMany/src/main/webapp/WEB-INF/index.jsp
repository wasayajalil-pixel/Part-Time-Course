<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Registration</title>
</head>
<body>

<h1>Login Registration</h1>

<div style="display:flex; gap:80px;">

    <!-- Registration Form -->
    <div>
        <h2>Create Account</h2>

        <form:form action="/register" method="post" modelAttribute="newUser" enctype="multipart/form-data">

            <p>
                First Name:
                <form:input path="firstName"/>
                <form:errors path="firstName" style="color:red"/>
            </p>

            <p>
                Last Name:
                <form:input path="lastName"/>
                <form:errors path="lastName" style="color:red"/>
            </p>

            <p>
                Email:
                <form:input path="email"/>
                <form:errors path="email" style="color:red"/>
            </p>

            <p>
                Date Of Birth:
                <form:input path="dateOfBirth" type="date"/>
                <form:errors path="dateOfBirth" style="color:red"/>
            </p>

            <p>
                Password:
                <form:password path="password"/>
                <form:errors path="password" style="color:red"/>
            </p>

            <p>
                Confirm Password:
                <form:password path="confirmPassword"/>
                <form:errors path="confirmPassword" style="color:red"/>
            </p>

 
            <button>Create Account</button>

        </form:form>
    </div>

    <!-- Login Form -->
    <div>
        <h2>Sign In</h2>

        <form:form action="/login" method="post" modelAttribute="newLogin">

            <p>
                Email:
                <form:input path="email"/>
                <form:errors path="email" style="color:red"/>
            </p>

            <p>
                Password:
                <form:password path="password"/>
                <form:errors path="password" style="color:red"/>
            </p>

            <button>Sign In</button>

        </form:form>
    </div>

</div>

</body>
</html>