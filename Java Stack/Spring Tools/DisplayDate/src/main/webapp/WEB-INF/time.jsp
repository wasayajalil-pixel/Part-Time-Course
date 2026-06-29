<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Time</title>
</head>
<body>

    <h1>
        <fmt:formatDate value="${currentTime}" pattern="h:mm a" />
    </h1><br>
    <a href="/">Go Back</a>

</body>
</html>