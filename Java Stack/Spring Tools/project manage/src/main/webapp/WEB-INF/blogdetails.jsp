<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:out value="${blog.title}" /></title>
</head>
<body>
<a href="/logout">logout</a>
<h1><c:out value="${blog.title}" /></h1>
<a href="/dashboard">back to the dashboard</a>

        <p>Title :             <c:out value="${blog.title}" /> </p>
        <p>Author:             <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" />.</p>
        <p>Category:           <c:out value="${blog.category}"/> </p>
        <p>Content:</p>
        <p><c:out value="${blog.content}" /></p>

<c:if test="${blog.user.id == user.id}">
    <a href="/edit/${blog.blog_id}">edit</a>
    <a href="/delete/${blog.blog_id}">delete</a>
</c:if>

<a href="/details/${blog_id}/comments">Comments</a>
</body>
</html>