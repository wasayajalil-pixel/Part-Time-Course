<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Club</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            width: 900px;
            margin: 40px auto;
        }

        .forms {
            display: flex;
            justify-content: space-between;
            gap: 60px;
        }

        .form-box {
            width: 45%;
            padding: 25px;
            border: 1px solid #999;
            border-radius: 8px;
        }

        label {
            display: block;
            margin-top: 15px;
        }

        input {
            width: 95%;
            padding: 8px;
        }

        button {
            margin-top: 20px;
            padding: 10px 20px;
            cursor: pointer;
        }

        .error {
            color: red;
        }
    </style>
</head>

<body>

    <h1>Welcome to Book Club</h1>

    <div class="forms">

        <div class="form-box">
            <h2>Register</h2>

            <form:form
                action="/register"
                method="post"
                modelAttribute="newUser">

                <label>User Name</label>
                <form:input path="userName"/>
                <form:errors path="userName" cssClass="error"/>

                <label>Email</label>
                <form:input path="email"/>
                <form:errors path="email" cssClass="error"/>

                <label>Password</label>
                <form:password path="password"/>
                <form:errors path="password" cssClass="error"/>

                <label>Confirm Password</label>
                <form:password path="confirm"/>
                <form:errors path="confirm" cssClass="error"/>

                <button type="submit">Register</button>

            </form:form>
        </div>

        <div class="form-box">
            <h2>Login</h2>

            <form:form
                action="/login"
                method="post"
                modelAttribute="newLogin">

                <label>Email</label>
                <form:input path="email"/>
                <form:errors path="email" cssClass="error"/>

                <label>Password</label>
                <form:password path="password"/>
                <form:errors path="password" cssClass="error"/>

                <button type="submit">Login</button>

            </form:form>
        </div>

    </div>

</body>
</html>