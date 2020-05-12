<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/11/20
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
    <script src="/CookingRecipes/js/adminSearch.js" charset="utf-8"></script>
</head>
<body>
<div class="header">
    <%@ include file="template/adminNavbar.jsp"%>
    <div class="display-4 text-center">
        Admin Search Results
    </div>
</div>

<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-success" role="alert">
            Recipes were successfully deleted
        </div>
    </c:if>
    <table class="table table-striped col-12">
        <c:if test="${not empty users}">
            <tr>
                <th>Username</th><th>First Name</th><th>Last Name</th>
            </tr>

            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td><td>${user.firstName}</td><td>${user.lastName}</td>
                </tr>
            </c:forEach>
        </c:if>

        <c:if test="${not empty recipes}">
            <tr>
                <th>Name</th><th>Description</th><th>Category</th>
                <th><a onclick="deleteRecipes()" id="deleteRecipes" class="btn btn-primary">Delete Recipes</a></th>
            </tr>

            <c:forEach var="recipe" items="${recipes}">
                <tr>
                    <td>${recipe.name}</td><td>${recipe.description}</td><td>${recipe.category}</td>
                    <td><input type="checkbox" class="recipeCheckbox" id="${recipe.id}" /></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>

</body>
</html>
