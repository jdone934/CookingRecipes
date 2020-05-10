<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/3/20
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@include file="template/head.jsp"%>

    <script src="/CookingRecipes/js/recipeFormHelper.js" charset="utf-8"></script>
    <script src="/CookingRecipes/js/createRecipe.js" charset="utf-8"></script>
    <script src="/CookingRecipes/js/editRecipe.js" charset="utf-8"></script>
</head>
<body onload="editInit(${recipeId})">
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Edit Recipe
    </div>
</div>

<div class="editRecipeMain container">
    <%@ include file="template/recipeForm.jsp"%>
</div>

</body>
</html>