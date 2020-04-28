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
    <%@ include file="template/head.jsp"%>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Create New Recipe
    </div>
</div>

<div class="newRecipeMain container">
    <form method="POST" action="newRecipe" class="col-md-8 offset-md-2 col-lg-6 offset-lg-3">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" id="name">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea type="text" class="form-control" name="description" id="description" rows="4"></textarea>
        </div>
        <div class="form-group">
            <label for="category">Category</label>
        </div>

        <button type="submit" class="btn btn-primary">Create Recipe</button>
    </form>
</div>

</body>
</html>
