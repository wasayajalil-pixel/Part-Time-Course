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
    <title>Edit Book</title>

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

    <h1>Edit Book</h1>

    <a href="/books/${book.id}">Back to Book</a>

    <form:form
        action="/books/${book.id}"
        method="post"
        modelAttribute="book">

        <input type="hidden" name="_method" value="put"/>

        <form:hidden path="id"/>

        <label>Title</label>
        <form:input path="title"/>
        <form:errors path="title" cssClass="error"/>

        <label>Author</label>
        <form:input path="author"/>
        <form:errors path="author" cssClass="error"/>

        <label>Description</label>
        <form:textarea path="description"/>
        <form:errors path="description" cssClass="error"/>

        <button type="submit">Save Changes</button>

    </form:form>

</body>
</html>