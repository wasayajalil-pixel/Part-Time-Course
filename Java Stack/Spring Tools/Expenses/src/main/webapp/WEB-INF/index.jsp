<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Expense Tracker</title>
</head>
<body>

    <h1>Save Travels</h1>

    <table border="1">
        <tr>
            <th>Expense</th>
            <th>Vendor</th>
            <th>Amount</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="expense" items="${expenses}">
            <tr>
                <td>
                    <a href="/expenses/${expense.id}">
                        ${expense.expenseName}
                    </a>
                </td>
                <td>${expense.vendorName}</td>
                <td>$${expense.amount}</td>
                <td>
                    <a href="/expenses/${expense.id}/edit">Edit</a>

                    <form action="/expenses/${expense.id}" method="post" style="display:inline;">
                        <input type="hidden" name="_method" value="delete">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <hr>

    <h2>Add an Expense</h2>

    <form:form action="/expenses" method="post" modelAttribute="expense">

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

        <button type="submit">Submit</button>

    </form:form>

</body>
</html>