<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Expense Details</title>
</head>
<body>

    <h1>Expense Details</h1>

    <p><strong>Expense Name:</strong> ${expense.expenseName}</p>
    <p><strong>Vendor Name:</strong> ${expense.vendorName}</p>
    <p><strong>Amount:</strong> $${expense.amount}</p>
    <p><strong>Description:</strong> ${expense.description}</p>

    <a href="/expenses/${expense.id}/edit">Edit</a>
    |
    <a href="/">Go Back</a>

</body>
</html>