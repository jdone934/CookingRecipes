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

</body>
</html>

<div class="row">
    <input type="text" class="form-control col-9" name="instructions" value="first instruction">
    <i class="material-icons col-2 offset-1">delete</i>
</div>
<div class="row">
    <input type="text" class="form-control col-9" name="instructions" value="second instruction">
    <i class="material-icons col-2 offset-1">delete</i>
</div>
<div class="row">
    <input type="text" class="form-control col-9" name="instructions" value="holy crap this works!">
    <i class="material-icons col-2 offset-1">delete</i>
</div>