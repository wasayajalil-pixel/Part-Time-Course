<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Project</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<h1>Edit Blog</h1>
	<section class="card">
		<form:form action="/blogs/${blogs.id}" method="POST"
			modelAttribute="blogs">
			<input type="hidden" name="_method" value="put"/>
			
			 <form:hidden path="id"/>
			


			<label>Title</label>
			<form:input path="title" />
			<form:errors path="title" cssClass="error" />
			
			<label>Category</label>
			<form:input path="category" />
			<form:errors path="category" cssClass="error" />
			
			<label>Content</label>
			<form:textarea path="description" rows="7" />
			<form:errors path="description" cssClass="error" />

			<button type="submit">Save Changes</button>
		</form:form>
	</section>
	<a href="/dashboard">Cancel</a>

</body>
</html>