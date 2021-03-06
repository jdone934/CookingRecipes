<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/28/20
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>

    <script src="/CookingRecipes/js/recipeFormHelper.js" charset="utf-8"></script>
    <script src="/CookingRecipes/js/createRecipe.js" charset="utf-8"></script>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Create New Recipe
    </div>
</div>

<div class="newRecipeMain container">
    <%@ include file="template/recipeForm.jsp"%>
</div>

</body>
</html>
