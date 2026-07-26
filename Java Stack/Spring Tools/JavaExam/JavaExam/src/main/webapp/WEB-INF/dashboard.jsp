<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Blogs</title>
<style>
body {
	font-family: Arial, sans-serif;
	width: 900px;
	margin: 40px auto;
}

.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 30px;
}

th, td {
	border: 1px solid #999;
	padding: 12px;
	text-align: left;
}

th {
	background-color: #eee;
}

a {
	margin-right: 10px;
}
</style>
</head>
<body>
	<div class="header">
		<div>
			<h1>
				Welcome,
				<c:out value="${loggedUser.firstName}" />
				!
			</h1>
			<p>All Blogs</p>
		</div>

		<div>
			<a href="/blogs/new">Create a Blog</a> <a href="/logout">Logout</a>
		</div>
	</div>

	<table>
		<thead>
			<tr>
				<th>Blog Title</th>
				<th>Author</th>
				<th>Created on</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="blogs" items="${blogs}">
				<tr>
					<td><a href="/blogs/${blogs.id}"> <c:out
								value="${blogs.title}" />
					</a></td>

					<td><c:out value="${blogs.postedBy.firstName}" /></td>

					<td><c:out value="${blogs.createdAt}" /></td>

					<td><a href="/blogs/${blogs.id}/edit "> Edit </a></td>

					<td> <a href="/delete/blogs/${blogs.id}"> Delete </a></td>
					
				</tr>
			</c:forEach>

		</tbody>
	</table>


</body>
</html>