<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/18/20
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="template/template-head.jsp"%>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Search Results
    </div>
</div>

<div class="container-fluid">
<h2>Search Results: </h2>
<c:set var="recipes" value="${recipes}"/>
<c:if test="${not empty recipes}">
    <c:forEach var ="recipe" items="${recipes}">
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
    </c:forEach>
</c:if>
<c:if test="${empty recipes}">
    Sorry, there were no results found
</c:if>

<a href="index.jsp">Try Again!</a>

<c:set var="user" value = "${user}" />
<c:if test="${not empty user}">
    <p>testing<p>
    <p>${user.username}<p>
</c:if>
</div>
</body>
</html>
