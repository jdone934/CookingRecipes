const editInit = recipeId => {
    formSetUp();
    fillInFormDetails(recipeId);
}

const fillInFormDetails = async(recipeId) => {
    let form = document.querySelector("#newRecipe");
    form.setAttribute("id", "editRecipe");
    form.setAttribute("action", `editRecipe?id=${recipeId}`);

    let recipe = await getRecipe(recipeId);

    document.querySelector("#name").value = recipe.name;
    document.querySelector("#description").value = recipe.description;
    document.querySelector("#category").value = recipe.category;

    createIngredientsList(recipe.ingredients);
    createInstructionsList(recipe.instructions);

    document.querySelector("#submitButton").innerHTML = "Update Recipe";
}

const getRecipe = async(id) => {
    let url = `getRecipe?id=${id}`;
    let recipePromise = fetch(url);
    let recipe = await recipePromise.then(results => {
        return results.json();
    });

    return recipe;
}

const createIngredientsList = ingredients => {
    ingredients.forEach((ingredient, i) => {
        addIngredientToSet(createIngredientFormElement(ingredient.quantityNumerator,
                                                       ingredient.quantityDenominator,
                                                       ingredient.unitOfMeasurement,
                                                       ingredient.name));
    })
}

const createInstructionsList = instructions => {
    instructions.forEach((instruction, i) => {
        addInstructionToSet(createInstructionFormElement(instruction.description, instructions.length));
    })
}

//window.onload = editInit;