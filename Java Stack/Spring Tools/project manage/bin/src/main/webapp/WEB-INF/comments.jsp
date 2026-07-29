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

<h1>Blog: <c:out value="${blog.title}" /> </h1>
<h2> Author : <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /> </h2>
<a href="/dashboard">back to the dashboard</a>

<form:form action="/details/${blog_id}/comments/new" method="post" modelAttribute="comment">
    <div>

        <form:label path="commentContent">Add Comment</form:label>
        <form:textarea path="commentContent" />
        <form:errors path="commentContent" />

    </div>

    <input type="submit" value="Submit" />
</form:form>


<h2>Comments</h2>
<c:forEach var="comment" items="${MyComments}">
<h3><c:out value="${comment.user.firstName}" /> <c:out value="${comment.user.lastName}" /> </h3>
<p><c:out value="${comment.commentContent}" /> </p>

</c:forEach>
</body>
</html>