<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Book</title>
</head>
<body>
	<h1>Book Details</h1>

	<p>ID: ${book.id}</p>
	<p>Title: ${book.title}</p>
	<p>Description: ${book.description}</p>
	<p>Language: ${book.language}</p>
	<p>Number of Pages: ${book.numberOfPages}</p>

	<a href="/books">Back to All Books</a>
</body>
</html>