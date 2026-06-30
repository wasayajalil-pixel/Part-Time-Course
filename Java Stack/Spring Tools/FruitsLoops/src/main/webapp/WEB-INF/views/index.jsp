<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fruit Store</title>
<style>
body {
	font-family: Arial, sans-serif;
	display: flex;
	justify-content: center;
	padding: 40px;
	background-color: #f9f9f9;
}

.container {
	width: 500px;
}

h1 {
	color: #e05a7a;
	text-align: center;
	font-size: 2rem;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	border: 2px solid #e05a7a;
}

th {
	background-color: #f7c5d0;
	padding: 10px 20px;
	text-align: left;
	font-weight: bold;
}

td {
	padding: 10px 20px;
	border-top: 1px solid #f0a0b0;
}

tr:nth-child(even) {
	background-color: #fff0f3;
}

tr:nth-child(odd) {
	background-color: #ffffff;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Fruit Store</h1>
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="fruit" items="${x}">
					<tr>
						<td>${fruit.name}</td>
						<td>${fruit.price}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>