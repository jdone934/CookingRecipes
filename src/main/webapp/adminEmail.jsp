<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/11/20
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
</head>
<body>
<div class="header">
    <%@ include file="template/adminNavbar.jsp"%>
    <div class="display-4 text-center">
        Admin Email
    </div>
</div>

<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-success" role="alert">
            ${message}
        </div>
        <hr>
    </c:if>

    <form method="POST" action="/CookingRecipes/adminOnly/email" class="form col-6 offset-3">
        <div class="form-group">
            <label for="user">Users</label>
            <input type="radio" id="user" name="emailGroup" value="user">
            <label for="admin">Admins</label>
            <input type="radio" id="admin" name="emailGroup" value="admin" checked>
        </div>

        <div class="form-group">
            <label for="subject">Subject</label>
            <input type="text" id="subject" name="subject" class="form-control">
        </div>

        <label for="message">Email:</label>
        <textarea type="text" class="form-control" name="message" id="message" rows="10" required></textarea>
        <br>
        <button type="submit" id="submitButton" class="btn btn-primary">Send Mass Email</button>
    </form>
</div>

</body>
</html>
