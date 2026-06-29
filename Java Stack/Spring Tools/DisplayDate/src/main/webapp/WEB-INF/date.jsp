<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Date Template</title>
</head>
<body>
	<h1>
		<fmt:formatDate value="${currentDate}" pattern="EEEE, MMMM dd, yyyy" />
	</h1><br>
	<a href="/">Go Back</a>

</body>
</html>