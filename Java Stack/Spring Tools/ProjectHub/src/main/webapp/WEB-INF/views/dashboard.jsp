<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Projects</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<!-- Navigation bar -->
	<nav>
		<h2>ProjectHub</h2>
		<div>
			Welcome, ${loggedUser.firstName} | <a href="/users/${loggedUser.id}">
				My Profile </a> | <a href="/logout"> Logout </a>
		</div>
	</nav>
	<div class="container two-columns dashboard-grid">
		<!-- Create project section -->
		<section class="card">
			<h2>Create Project</h2>
			<form:form action="/projects" method="post"
				modelAttribute="newProject">
				<!-- Project title -->
				<label>Title</label>
				<form:input path="title" placeholder="Enter project title" />
				<form:errors path="title" cssClass="error" />

				<!-- Technology dropdown -->
				<label>Technology</label>
				<form:select path="technology">
					<form:option value="" label="Select technology" />
					<form:option value="Java" label="Java" />
					<form:option value="Spring Boot" label="Spring Boot" />
					<form:option value="Python" label="Python" />
					<form:option value="Django" label="Django" />
					<form:option value="JavaScript" label="JavaScript" />
					<form:option value="React" label="React" />
					<form:option value="MERN" label="MERN" />
				</form:select>
				<form:errors path="technology" cssClass="error" />

				<!-- Release date -->
				<label>Release Date</label>
				<form:input path="releaseDate" type="date" />
				<form:errors path="releaseDate" cssClass="error" />

				<!-- Project description -->
				<label>Description</label>
				<form:textarea path="description" rows="6"
					placeholder="Write a description of the project" />
				<form:errors path="description" cssClass="error" />

				<button type="submit">Create Project</button>
			</form:form>
		</section>
		<!-- All projects section -->
		<section class="card">
			<h2>All Projects</h2>
			<!-- Projects table -->
			<table>

				<thead>
					<tr>
						<th><a href="/projects?sort=title">Title</a></th>
						<th><a href="/projects?sort=technology">Technology</a></th>
						<th>Created By</th>
						<th><a href="/projects?sort=releaseDate">Release Date</a></th>
						<th>Actions</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="project" items="${projects}">

						<tr>

							<!-- Project title -->
							<td><a href="/projects/${project.id}"> ${project.title}
							</a></td>

							<!-- Technology -->
							<td>${project.technology}</td>

							<!-- Creator -->
							<td><a class="creator-link"
								href="/users/${project.creator.id}"> <c:if
										test="${not empty project.creator.profileImage}">
										<img class="profile-image small"
											src="/uploads/profiles/${project.creator.profileImage}"
											alt="${project.creator.firstName}">
									</c:if> ${project.creator.firstName} ${project.creator.lastName}

							</a></td>

							<!-- Release date -->
							<td>${project.releaseDate}</td>

							<!-- Action buttons -->
							<td><a class="button" href="/projects/${project.id}">
									Details </a> <!-- Only the creator can edit and delete --> <c:if
									test="${project.creator.id == loggedUser.id}">

									<a class="button warning" href="/projects/${project.id}/edit">
										Edit </a>

									<form action="/projects/${project.id}" method="post"
										class="inline-form">
										<input type="hidden" name="_method" value="delete">
										<button class="danger" type="submit"
											onclick="return confirm('Delete this project?')">
											Delete</button>
									</form>
								</c:if></td>
						</tr>
					</c:forEach>
					<!-- Show message when there are no projects -->
					<c:if test="${empty projects}">
						<tr>
							<td colspan="5">No projects have been created yet.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</section>
	</div>
</body>
</html>
