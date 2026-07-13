<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Project</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<nav>
		<h2>Edit Project</h2>
		<a href="/projects/${project.id}">Cancel</a>
	</nav>
	<div class="container narrow">
		<section class="card">
			<form:form action="/projects/${project.id}" method="post"
				modelAttribute="project">
				

				<label>Title</label>
				<form:input path="title" />
				<form:errors path="title" cssClass="error" />

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
				<label>Release Date</label>
				<form:input path="releaseDate" type="date" />
				<form:errors path="releaseDate" cssClass="error" />

            <label>Description</label>
				<form:textarea path="description" rows="7" />
				<form:errors path="description" cssClass="error" />

				<button type="submit">Save Changes</button>
			</form:form>
		</section>
	</div>
</body>
</html>
