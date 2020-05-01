<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/19/20
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="template/head.jsp"%>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Recipe Step-By-Step
    </div>
</div>

<div class="recipeHeading row">
    <h1 class="text-center col-12">${recipe.name}</h1>
</div>
<div class="main stepByStep">
    <div class="container">
        <div class="row recipeDescription">
            <p>${recipe.description}</p>
            <c:if test="${recipe.image != null}">
                <img src="img/${recipe.image.filepath}" alt="${recipe.description}" class="ml-auto img-fluid recipeImage">
            </c:if>
        </div>

        <div class="row">
            <h3 id = "step-1" class="text-center ingredientsHeading col-4 offset-2 offset-sm-4 my-auto">Ingredients</h3>
            <a href="#step0" class="col-2 offset-4 offset-sm-2"><img src="img/downChevron.png" class="chevron img-fluid"></a>
        </div>

        <h3 class="text-center">Instructions</h3>
        <div class="col">
            <ul class="list-unstyled row">
                <c:forEach var="ingredient" items="${recipe.ingredients}">
                    <li class="list-item col-6 col-md-3 text-center ingredient">${ingredient.quantityNumerator}/${ingredient.quantityDenominator}
                            ${ingredient.unitOfMeasurement} ${ingredient.name}</li>
                </c:forEach>
            </ul>
        </div>

        <div class="col">
            <ol class="list-unstyled row">
                <c:forEach var="instruction" items="${recipe.instructions}" varStatus="loop">
                    <li id="step${loop.index}" class="list-item row instruction stepByStepInstruction">
                        <c:if test="${not empty instruction.image}">
                            <p class="col-10 col-md-5 align-self-md-center order-1">${instruction.description}</p>
                            <img src="img/${instruction.image.filepath}" alt="${instruction.image.description}"
                                 class="ml-md-auto img-fluid instructionImage col-6 offset-3 col-md-3 order-3 order-md-2 align-self-center">
                        </c:if>

                        <c:if test="${empty instruction.image}">
                            <p class="col-10 align-self-center order-1">${instruction.description}</p>
                        </c:if>

                        <div class="row col-2 order-2 order-md-3 align-self-center stepChevron">
                            <a href="#step${loop.index - 1}" class="col-12"><img src="img/upChevron.png" class="img-fluid"></a>
                            <a href="#step${loop.index + 1}" class="col-12"><img src="img/downChevron.png" class="img-fluid"></a>
                        </div>
                    </li>
                </c:forEach>
            </ol>
        </div>
    </div>
</div>
</body>
</html>