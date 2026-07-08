<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Ninja</title>
</head>
<body>

    <h1>New Ninja</h1>

    <form:form action="/ninjas" method="post" modelAttribute="ninja">

        <p>
            <form:label path="dojo">Dojo:</form:label>
            <form:select path="dojo">
                <c:forEach var="dojo" items="${dojos}">
                    <form:option value="${dojo.id}">
                        ${dojo.dojoName}
                    </form:option>
                </c:forEach>
            </form:select>
            <form:errors path="dojo"/>
        </p>

        <p>
            <form:label path="firstName">First Name:</form:label>
            <form:input path="firstName"/>
            <form:errors path="firstName"/>
        </p>

        <p>
            <form:label path="lastName">Last Name:</form:label>
            <form:input path="lastName"/>
            <form:errors path="lastName"/>
        </p>

        <p>
            <form:label path="age">Age:</form:label>
            <form:input path="age" type="number"/>
            <form:errors path="age"/>
        </p>

        <button>Create Ninja</button>

    </form:form>

    <br>
    <a href="/dojos/new">Create Dojo</a>

</body>
</html>