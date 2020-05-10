<form id="newRecipe" method="POST" action="createRecipe" class="col-md-8 offset-md-2 col-lg-6 offset-lg-3"
      onsubmit="return validateForm()" enctype="multipart/form-data">
    <div id="errorMessage"></div>
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" name="name" id="name" required>
    </div>
    <div class="form-group">
        <label for="description">Description</label>
        <textarea type="text" class="form-control" name="description" id="description" rows="4" required></textarea>
    </div>
    <div class="form-group">
        <label for="category">Category</label>
        <input type="text" class="form-control" name="category" id="category" required>
    </div>
    <div class="form-group">
        <label for="recipeImage">Recipe Image</label>
        <input type="file" class="form-control" name="recipeImage" id="recipeImage" accept="image/*">
    </div>
    <div class="form-group">
        <label for="imageDescription">Image Description</label>
        <input type="text" class="form-control" name="imageDescription" id="imageDescription">
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
        <textarea type="text" class="form-control col-9" id="newInstruction" placeholder="Instruction" rows="3"></textarea>
        <i class="material-icons col-2 offset-1" id="addInstruction">add_circle_outline</i>
        <input type="file" class="form-control col-9" id="newInstructionImage" accept="image/*">
    </div>

    <br>

    <div class="addedInstructions">

    </div>
    <button type="submit" id="submitButton" class="btn btn-primary">Create Recipe</button>
</form>