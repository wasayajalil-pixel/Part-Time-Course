<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>The Book Broker</title>
</head>
<body>
<h1> hi this is address page </h1>

<form:form action="/address/new" method="post" modelAttribute="address">
    <div>
        <form:label path="street">Street</form:label>
        <form:input path="street" />
        <form:errors path="street" />
    </div>

    <div>
        <form:label path="country">Country</form:label>
        <form:input path="country" />
        <form:errors path="country" />
    </div>

    <input type="submit" value="Submit" />
</form:form>
</body>
</html>