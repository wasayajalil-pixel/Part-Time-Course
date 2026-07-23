<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Books</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            width: 900px;
            margin: 40px auto;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
        }

        th,
        td {
            border: 1px solid #999;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #eee;
        }

        a {
            margin-right: 10px;
        }
    </style>
</head>

<body>

    <div class="header">
        <div>
            <h1>Welcome, <c:out value="${loggedUser.userName}"/>!</h1>
            <p>Books from everyone's shelves</p>
        </div>

        <div>
            <a href="/books/new">Add a Book</a>
            <a href="/logout">Logout</a>
        </div>
    </div>

    <table>
        <thead>
            <tr>
                <th>Book Title</th>
                <th>Author</th>
                <th>Posted By</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach var="book" items="${books}">
                <tr>
                    <td>
                        <a href="/books/${book.id}">
                            <c:out value="${book.title}"/>
                        </a>
                    </td>

                    <td>
                        <c:out value="${book.author}"/>
                    </td>

                    <td>
                        <c:out value="${book.postedBy.userName}"/>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>

</body>
</html>