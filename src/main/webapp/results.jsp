<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/18/20
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="template/head.jsp"%>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Search Results
    </div>
</div>

<div class="container">
<h2 class="text-center">Search Results: </h2>
<c:set var="recipes" value="${recipes}"/>
<c:if test="${not empty recipes}">
    <div class="row">
        <c:forEach var ="recipe" items="${recipes}" varStatus="loop">
            <div class="col-12 col-sm-6 col-md-4">
                <div class="card">
                    <c:if test="${not empty recipe.image}">
                        <img class="card-img-top" src="recipeImg/${recipe.image.filepath}">
                    </c:if>

                    <div class="card-body">
                        <h5 class="card-title">${recipe.name}</h5>
                        <p class="card-text">${recipe.description}</p>
                        <a href="viewRecipeOverview?id=${recipe.id}" class="btn btn-primary">View Recipe</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

<c:if test="${empty recipes}">
    Sorry, there were no results found
</c:if>
</div>
</body>
</html>
