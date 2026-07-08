<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Dojo</title>
</head>
<body>
	<h1>New Dojo</h1>

	<form:form action="/dojos" method="post" modelAttribute="dojo">
		<form:label path="dojoName">Dojo Name:</form:label>
		<form:input path="dojoName" /><br><br>
		<form:errors path="dojoName"></form:errors>
		
		<button>Create Dojo</button>
	</form:form>
</body>
</html>