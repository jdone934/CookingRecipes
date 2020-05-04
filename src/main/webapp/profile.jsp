<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/27/20
  Time: 11:37 PM
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
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Profile
    </div>
</div>

<div class="profileMain container">
    <c:set var="user" value="${user}" />

    <h2 class="text-center">Account Information</h2>

    <div class="row">
        <table class="table table-striped col-12 col-sm-8 offset-sm-2">
            <tr>
                <td>User Name</td>
                <td>${user.username}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td>First Name</td>
                <td>${user.firstName}</td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>${user.lastName}</td>
            </tr>
        </table>
    </div>

    <c:set var="recipes" value="${user.createdRecipes}" />
    <c:if test="${not empty recipes}">
        <h2 class="text-center">My Recipes</h2>
        <div class="row">
            <table class="table table-striped col-12 col-sm-8 offset-sm-2">
                <tr>
                    <th>Name</th><th>Description</th><th>Edit</th></tr>
                </tr>
                <c:forEach var="recipe" items="${recipes}">
                    <tr>
                        <td><a class="recipeLink" href="viewRecipeOverview?id=${recipe.id}">${recipe.name}</a></td><td>${recipe.description}</td>
                        <td><a href="editRecipe?id=${recipe.id}"><i class="material-icons">edit</i></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>
