<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add a Blog</title>
</head>
<body>

<h1>Add a Blog to your blogs!</h1>

<form:form action="/create/new" method="post" modelAttribute="blog">
    <div>
        <form:label path="title">Title</form:label>
        <form:input path="title" />
        <form:errors path="title" />
    </div>

    <div>
        <form:label path="category">Category</form:label>
        <form:input path="category" />
        <form:errors path="category" />
    </div>

    <div>
        <form:label path="content">Content</form:label>
        <form:textarea path="content" />
        <form:errors path="content" />
    </div>

    <input type="submit" value="Submit" />
</form:form>
<br>
<button><a href="/dashboard">cancel</a></button>


</body>
</html>