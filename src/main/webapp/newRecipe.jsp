<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/28/20
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
    <script src="/CookingRecipes/js/createRecipe.js" charset="utf-8"></script>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        New Recipe
    </div>
</div>

<div class="alert alert-success" role="alert">
    Recipe successfully created! View it below!
</div>

<c:set var="recipe" value="${recipe}" />
<div class="card" style="width: 18rem;">
    <c:if test="${not empty recipe.image}">
        <img class="card-img-top" src="img/${recipe.image.filepath}" alt="${recipe.image.description}">
    </c:if>

    <div class="card-body">
        <h5 class="card-title">${recipe.name}</h5>
        <p class="card-text">${recipe.description}</p>
        <a href="viewRecipeOverview?id=${recipe.id}" class="btn btn-primary">View Recipe</a>
    </div>
</div>

</body>
</html>