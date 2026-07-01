<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show result</title>
</head>
<body>
	<h1>Here's Your Omikuji!</h1>
	<p>
		In <strong>${number}</strong> years, you will live in <strong>${city}</strong>
		with <strong>${person}</strong> as your roommate, <strong>${hobby}</strong>
		for a living. The next time you see a <strong>${thing}</strong>, you
		will have good luck. Also, <strong>${nice}</strong>
	</p><br>
	 <a href="/omikuji">Go Back</a>
</body>
</html>