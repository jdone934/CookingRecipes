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
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="template/template-head.jsp"%>
</head>
<body>
<div class="container-fluid">
    <c:set var="recipe" value="${recipe}"/>

    <h1 class="text-center recipeHeading">${recipe.name}</h1>
    <div class="row">
        <p>${recipe.description}</p>
        <c:if test="${recipe.image != null}">
            <img src="img/${recipe.image.filepath}" alt="${recipe.description}" class="ml-auto img-fluid recipeImage">
        </c:if>
    </div>

    <h3 class="text-center ingredientsHeading">Ingredients</h3>
    <div class="container">
        <div class="col">
            <ul class="list-unstyled row">
                <c:forEach var="ingredient" items="${recipe.ingredients}">
                    <li class="list-item col-6 text-center">${ingredient.quantityNumerator}/${ingredient.quantityDenominator}
                             ${ingredient.unitOfMeasurement} ${ingredient.name}</li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <h3 class="text-center">Instructions</h3>
    <ol class="list-unstyled">
        <c:forEach var="instruction" items="${recipe.instructions}">
            <li class="list-item row instruction">
                <p class="col-8 align-self-center">${instruction.description}</p>
                <c:if test="${instruction.image != null}">
                    <img src="img/${instruction.image.filepath}" alt="${instruction.image.description}" class="ml-auto img-fluid instructionImage">
                </c:if>
            </li>
        </c:forEach>
    </ol>
</div>
</body>
</html>