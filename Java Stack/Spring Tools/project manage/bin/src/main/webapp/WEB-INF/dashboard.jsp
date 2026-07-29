<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>The Book Broker</title>
</head>
<body>
<a href="/logout">logout</a>
<p>Hello, <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" />. Welcome</p>
<h1>All Blogs</h1>
<a href="/create">+ new blog</a>
<a href = "/address"> +new address </a>

<%-- ===== Table 1: All Projects (excluding your own) ===== --%>
<c:choose>
<c:when test="${empty availableBlogs}">
    <p>there are no any blogs yet.</p>
</c:when>
<c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>created On</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="blog" items="${availableBlogs}">
                <tr>
                    <td><c:out value="${blog.title}" /></td>
                    <td><c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /> </td>
                    <td> <c:out value="${blog.createdAt}"/></td>
                    <td>
                        <a href="/details/${blog.blog_id}">
                            Read
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</c:otherwise>
</c:choose>
<%-- ===== Table 2: Your Projects — blogs YOU created ===== --%>
<h2>My Blogs</h2>
<c:choose>
    <c:when test="${empty MyBlogs}">
        <p>You haven't created any blogs yet.</p>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>created On</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="blog" items="${MyBlogs}">
                <tr>
                    <td><c:out value="${blog.title}" /></td>
                    <td><c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /> </td>
                    <td><c:out value="${blog.createdAt}"/>  </td>
                    <td>
                        <a href="/edit/${blog.blog_id}">edit</a>
                        <a href="/delete/${blog.blog_id}">delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>


</body>
</html>