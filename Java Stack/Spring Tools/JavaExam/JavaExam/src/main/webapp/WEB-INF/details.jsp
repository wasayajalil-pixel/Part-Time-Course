<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Details</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<nav>
    <h2>Blogs Details</h2>
    <a href="/dashboard">Back to blogs</a>
</nav>
<div class="container">
    <section class="card details-card">
        <h1>${blogs.title}</h1>
        <p><strong>Author:</strong> ${blogs.postedBy.firstName}${blog.postedBy.lastName}</p>
        <p><strong>Description:</strong></p>
        <p>${blogs.description}</p>
        

        <c:if test="${blogs.postedBy.id == loggedUserId}">
            <a class="button warning" href="/blogs/${blogs.id}/edit">Edit</a>
            <form action="/projects/${blogs.id}" method="post" class="inline-form">
                <input type="hidden" name="_method" value="delete">
                <button class="danger" type="submit" onclick="return confirm('Delete this project?')">Delete</button>
            </form>
        </c:if>
    </section>
</div>
</body>
</html>