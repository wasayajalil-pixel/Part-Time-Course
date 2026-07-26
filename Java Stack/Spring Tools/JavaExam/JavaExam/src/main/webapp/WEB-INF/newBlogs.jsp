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
    <title>Add a Book</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            width: 600px;
            margin: 40px auto;
        }

        label {
            display: block;
            margin-top: 15px;
        }

        input,
        textarea {
            width: 100%;
            padding: 8px;
        }

        textarea {
            height: 120px;
        }

        button {
            margin-top: 20px;
            padding: 10px 20px;
        }

        .error {
            color: red;
        }
    </style>
</head>

<body>

    <h1>Create a Blog</h1>

    

    <form:form
        action="/blogs"
        method="post"
        modelAttribute="blogs">

        <label>Title</label>
        <form:input path="title"/>
        <form:errors path="title" cssClass="error"/>

        <label>Category</label>
        <form:input path="category"/>
        <form:errors path="category" cssClass="error"/>

        <label>Content</label>
        <form:textarea path="description"/>
        <form:errors path="description" cssClass="error"/>

        <button type="submit">save</button>

    </form:form>
    
    <a href="/dashboard"> Cancel</a>
    
    
</body>
</html>