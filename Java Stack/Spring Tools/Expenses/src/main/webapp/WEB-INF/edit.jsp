<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Expense</title>
</head>
<body>

    <h1>Edit Expense</h1>

    <form:form action="/expenses/${expense.id}" method="post" modelAttribute="expense">

        <input type="hidden" name="_method" value="put">

        <p>
            Expense Name:
            <form:input path="expenseName"/>
            <form:errors path="expenseName"/>
        </p>

        <p>
            Vendor Name:
            <form:input path="vendorName"/>
            <form:errors path="vendorName"/>
        </p>

        <p>
            Amount:
            <form:input path="amount" type="number" step="0.01"/>
            <form:errors path="amount"/>
        </p>

        <p>
            Description:
            <form:textarea path="description"/>
            <form:errors path="description"/>
        </p>

        <button type="submit">Update</button>

    </form:form>

    <a href="/">Cancel</a>

</body>
</html>