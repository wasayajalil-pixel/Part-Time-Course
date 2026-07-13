<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProjectHub - Login & Registration</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div class="container two-columns">
		<section class="card">
			<h1>Create Account</h1>
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

				<label>Birthday</label>
				<form:input path="birthday" type="date" />
				<form:errors path="birthday" cssClass="error" />
				<small>You must be at least 18 years old.</small>

				<label>Gender</label>

				<form:select path="gender">
					<form:option value="" label="Select gender" />
					<form:option value="Male" label="Male" />
					<form:option value="Female" label="Female" />
				</form:select>

				<form:errors path="gender" cssClass="error" />

				<label>Profile Image</label>
				<form:input path="profileImageFile" type="file"
					accept="image/jpeg,image/png,image/gif,image/webp" />
				<form:errors path="profileImageFile" cssClass="error" />
				<small>JPG, PNG, GIF, or WEBP. Maximum size: 5 MB.</small>

				<label>Bio</label>
				<form:textarea path="bio" rows="3" />

				<label>Password</label>
				<form:password path="password" />
				<form:errors path="password" cssClass="error" />

				<label>Confirm Password</label>
				<form:password path="confirm" />
				<form:errors path="confirm" cssClass="error" />

				<button type="submit">Register</button>
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

				<button type="submit">Login</button>
			</form:form>
		</section>
	</div>
</body>
</html>