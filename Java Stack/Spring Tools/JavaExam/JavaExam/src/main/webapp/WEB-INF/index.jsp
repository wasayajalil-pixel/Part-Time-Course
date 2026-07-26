<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login-Registration</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div class="container two-columns">
		<section class="card">
			<h1>Register</h1>
			<form:form action="/register" method="post" modelAttribute="newUser"
				enctype="multipart/form-data">
				<label>First Name</label>
				<form:input path="firstName" />
				<form:errors path="firstName" cssClass="error" />

				<label>Last Name</label>
				<form:input path="lastName" />
				<form:errors path="lastName" cssClass="error" />
				<label>Email</label>
				<form:input path="email" type="email" />
				<form:errors path="email" cssClass="error" />
				<label>Password</label>
				<form:password path="password" />
				<form:errors path="password" cssClass="error" />

				<label>Confirm Password</label>
				<form:password path="confirm" />
				<form:errors path="confirm" cssClass="error" />

				<button type="submit">Submit</button>
			</form:form>
		</section>
		<section class="card">
			<h1>Login</h1>
			<form:form action="/login" method="post" modelAttribute="newLogin">
				<label>Email</label>
				<form:input path="email" type="email" />
				<form:errors path="email" cssClass="error" />

				<label>Password</label>
				<form:password path="password" />
				<form:errors path="password" cssClass="error" />

				<button type="submit">Submit</button>
			</form:form>
		</section>
	</div>

</body>
</html>