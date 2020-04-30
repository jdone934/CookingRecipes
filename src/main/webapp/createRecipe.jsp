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
    <form method="POST" action="createRecipe" class="col-md-8 offset-md-2 col-lg-6 offset-lg-3">
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
            <input type="text" class="form-control" name="category" id="category">
        </div>
        <br>

        <div class="row">
            <h2 class="formSubHeading">Ingredients</h2>
            <i class="material-icons" data-toggle="tooltip" data-placement="left" title="The first quantity is for the top of a fraction, the second is the bottom. If the second is blank, the quantity will be a whole number.">help</i>
        </div>

        <div class="addIngredient row form-group">
            <input type="number" class="form-control col-3" id="quantityTop" placeholder="Quantity">
            <input type="number" class="form-control col-3 offset-1" id="quantityBottom" placeholder="Quantity">
            <input type="text" class="form-control col-3 offset-1" id="unit" placeholder="Unit">
        </div>
        <div class="row">
            <input type="text" class="form-control col-9" id="ingredientName" placeholder="Ingredient">
            <i class="material-icons col-1 offset-1" id="addIngredient" data-toggle="tooltip" data-placement="top" title="Tooltip on top">add_circle_outline</i>
        </div>
        <div class="addedIngredients">

        </div>

        <h2 class="formSubHeading">Instructions</h2>
        <div class="addInstruction row form-group">
            <input type="text" class="form-control col-9" id="newInstruction" placeholder="Instruction">
            <i class="material-icons col-2 offset-1" id="addInstruction">add_circle_outline</i>
        </div>

        <br>

        <div class="addedInstructions">

        </div>
        <button type="submit" class="btn btn-primary">Create Recipe</button>
    </form>
</div>

</body>
</html>
