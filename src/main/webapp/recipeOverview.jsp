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
            <img src="img/${recipe.image.filepath}" alt="${recipe.description}" class="ml-auto">
        </c:if>
    </div>

    <div class="row">

    </div>
</div>
</body>
</html>