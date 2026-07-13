<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Details</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<nav>
    <h2>Project Details</h2>
    <a href="/projects">Back to Projects</a>
</nav>
<div class="container">
    <section class="card details-card">
        <h1>${project.title}</h1>
        <p><strong>Technology:</strong> ${project.technology}</p>
        <p><strong>Description:</strong></p>
        <p>${project.description}</p>
        <p><strong>Created by:</strong></p>
        <a class="creator-link" href="/users/${project.creator.id}">
            <img class="profile-image small" src="/uploads/profiles/${project.creator.profileImage}"
                 alt="${project.creator.firstName}">
            ${project.creator.firstName} ${project.creator.lastName}
        </a>

        <c:if test="${project.creator.id == loggedUserId}">
            <a class="button warning" href="/projects/${project.id}/edit">Edit</a>
            <form action="/projects/${project.id}" method="post" class="inline-form">
                <input type="hidden" name="_method" value="delete">
                <button class="danger" type="submit" onclick="return confirm('Delete this project?')">Delete</button>
            </form>
        </c:if>
    </section>
</div>
</body>
</html>
