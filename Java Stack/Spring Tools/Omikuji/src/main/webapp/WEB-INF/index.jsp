<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Omikuji Form</title>
</head>
<body>
	<h1>Send an Omikuji</h1>
	<form action="/omikuji/process" method="post">

		<label>Pick any number from 5 to 25:</label> <input type="number"
			name="number" min="5" max="25"><br>
		<br> <label>Enter the name of any city:</label> <input
			type="text" name="city"><br>
		<br> <label>Enter the name of any real person:</label> <input
			type="text" name="person"><br>
		<br> <label>Enter professional endeavor or hobby:</label> <input
			type="text" name="hobby"><br>
		<br> <label>Enter any type of living thing:</label> <input
			type="text" name="thing"><br>
		<br> <label>Say something nice to someone:</label>
		<textarea name="nice"></textarea>
		<br>
		<br>

		<p>Send and show a friend</p>

		<button type="submit">Send</button>

	</form>
</body>
</html>