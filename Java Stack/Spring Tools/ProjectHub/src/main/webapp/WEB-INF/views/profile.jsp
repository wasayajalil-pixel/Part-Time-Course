<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<nav>
    <h2>User Profile</h2>
    <a href="/projects">Back to Projects</a>
</nav>
<div class="container">
    <section class="card">
        <div class="profile-header">
            <img class="profile-image large" src="/uploads/profiles/${profileUser.profileImage}"
                 alt="${profileUser.firstName} ${profileUser.lastName}">
            <div>
                <h1>${profileUser.firstName} ${profileUser.lastName}</h1>
                <p><strong>Email:</strong> ${profileUser.email}</p>
                <p><strong>Gender:</strong> ${profileUser.gender}</p>
                <p><strong>Birthday:</strong> ${profileUser.birthday}</p>
                <p><strong>Bio:</strong> ${profileUser.bio}</p>
            </div>
        </div>
        <h2>Projects Created</h2>

        <c:choose>
            <c:when test="${empty profileUser.projects}">
                <p>No projects created yet.</p>
            </c:when>
            <c:otherwise>
                <ul class="project-list">
                    <c:forEach var="project" items="${profileUser.projects}">
                        <li>
                            <a href="/projects/${project.id}">${project.title}</a>
                            — ${project.technology}
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </section>
</div>
</body>
</html>
