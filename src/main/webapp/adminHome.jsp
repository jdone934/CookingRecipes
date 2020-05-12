<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/11/20
  Time: 6:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
    <script src="/CookingRecipes/js/adminHome.js" charset="utf-8"></script>
</head>
<body>
<div class="header">
    <%@ include file="template/adminNavbar.jsp"%>
    <div class="display-4 text-center">
        Admin Home
    </div>
</div>

<div class="adminHome container">
    <div class="row">
        <form method="GET" action="/CookingRecipes/adminOnly/search" class="form col-6 offset-3">
            <div class="form-group">
                <label for="searchTerm">Search Term:</label>
                <input type="text" class="form-control" placeholder="Enter search here" name="searchTerm" id="searchTerm">
            </div>
            <div class="form-group">
                <label for="searchFor">Search For:</label>
                <select class="form-control" name="searchFor" id="searchFor">
                    <option value="user" id="forUser">User</option>
                    <option value="recipe" id="forRecipe">Recipe</option>
                </select>
            </div>
            <div class="form-group" id="userSearchOptions">
                <label for="userSearchBy">Search By:</label>
                <select class="form-control" name="userSearchBy" id="userSearchBy">
                    <option value="firstName" id="byFirstName">First Name</option>
                    <option value="lastName" id="byLastName">Last Name</option>
                    <option value="username" id="byUsername">Username</option>
                </select>
            </div>
            <div class="form-group" id="recipeSearchOptions">
                <label for="userSearchBy">Search By:</label>
                <select class="form-control" name="recipeSearchBy" id="recipeSearchBy">
                    <option value="name" id="byName">Name</option>
                    <option value="category" id="byCategory">Category</option>
                    <option value="description" id="byDescription">Description</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>

    <hr>

    <div class="row">
        <a href="email" class="btn btn-primary ml-auto mr-auto">Compose Email</a>
    </div>
</div>

</body>
</html>
