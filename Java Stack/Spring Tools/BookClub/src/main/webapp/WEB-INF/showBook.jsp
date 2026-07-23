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
    <title>Book Details</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            width: 700px;
            margin: 40px auto;
        }

        .book-box {
            border: 1px solid #999;
            border-radius: 8px;
            padding: 25px;
            margin-top: 25px;
        }

        .actions {
            display: flex;
            gap: 10px;
            margin-top: 25px;
        }

        button {
            padding: 8px 15px;
            cursor: pointer;
        }
    </style>
</head>

<body>

    <a href="/books">Back to Books</a>

    <div class="book-box">

        <h1>
            <c:out value="${book.title}"/>
        </h1>

        <h3>
            Author:
            <c:out value="${book.author}"/>
        </h3>

        <p>
            Posted by:
            <strong>
                <c:out value="${book.postedBy.userName}"/>
            </strong>
        </p>

        <p>
            <c:out value="${book.description}"/>
        </p>

        <!-- Show edit and delete only to the owner -->
        <c:if test="${book.postedBy.id == userId}">

            <div class="actions">

                <a href="/books/${book.id}/edit">
                    Edit
                </a>

                <form action="/books/${book.id}" method="post">
                    <input type="hidden" name="_method" value="delete"/>

                    <button type="submit">
                        Delete
                    </button>
                </form>

            </div>

        </c:if>

    </div>

</body>
</html>