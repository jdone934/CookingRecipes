<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/18/20
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="template/head.jsp"%>
    <c:if test="${not empty pageContext.request.getRemoteUser()}">
        <script src="/CookingRecipes/js/toggleFavorite.js" charset="utf-8"></script>
    </c:if>
</head>

<body>

<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Recipe Overview
    </div>
</div>

<div class="container">
    <c:set var="recipe" value="${recipe}"/>

    <div class="recipeHeading row">
        <h1 class="text-center col-12 col-sm-6 offset-sm-3">${recipe.name}</h1>

        <div class="col-12 col-sm-3">
            <a href="viewRecipeStepByStep?id=${recipe.id}" class="btn btn-primary col-sm-12 startCooking">Start Cooking!</a>
            <c:if test="${not empty pageContext.request.getRemoteUser()}">
                <c:if test="${not empty favoriteRecipe}">
                    <i class="material-icons col-sm-12 text-center favoriteIcon${recipe.id}" onclick="toggleFavorite(${recipe.id})">favorite</i>
                </c:if>

                <c:if test="${empty favoriteRecipe}">
                    <i class="material-icons col-sm-12 text-center favoriteIcon${recipe.id}" onclick="toggleFavorite(${recipe.id})">favorite_border</i>
                </c:if>
            </c:if>
        </div>
    </div>

    <div class="row recipeDescription">
        <p>${recipe.description}</p>
        <c:if test="${recipe.image != null}">
            <img src="recipeImg/${recipe.image.filepath}" alt="${recipe.description}" class="ml-auto img-fluid recipeImage">
        </c:if>
    </div>

    <h3 class="text-center ingredientsHeading">Ingredients</h3>
    <div class="container">
        <div class="col">
            <ul class="list-unstyled row">
                <c:forEach var="ingredient" items="${recipe.ingredients}">
                    <li class="list-item col-6 text-center ingredient">${ingredient.quantityNumerator}/${ingredient.quantityDenominator}
                             ${ingredient.unitOfMeasurement} ${ingredient.name}</li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <h3 class="text-center instructionHeading">Instructions</h3>
    <ol class="list-unstyled">
        <c:forEach var="instruction" items="${recipe.instructions}">
            <li class="list-item row instruction">
                <p class="col-12 col-sm-8 align-self-sm-center">${instruction.description}</p>
                <c:if test="${instruction.image != null}">
                    <img src="instructionImg/${instruction.image.filepath}" class="ml-sm-auto img-fluid instructionImage col-6 offset-3 col-sm-4">
                </c:if>
            </li>
        </c:forEach>
    </ol>
</div>
</body>
</html>