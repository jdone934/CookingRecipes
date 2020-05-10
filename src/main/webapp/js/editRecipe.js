const editInit = (recipeId) => {
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
    createInstructionsList(recipe.instructions, recipeId);

    document.querySelector("#submitButton").innerHTML = "Update Recipe";

    let recipeImagePath = recipe.image.filepath;

    if (recipeImagePath.length > 0) {
        let recipeImage = document.createElement("label");
        recipeImage.setAttribute("for", "deleteRecipeImage");
        recipeImage.innerHTML = recipe.image.filepath;

        let deleteIcon = document.createElement("i");
        deleteIcon.setAttribute("class", "material-icons");
        deleteIcon.setAttribute("id", "deleteRecipeImage");
        deleteIcon.setAttribute("onclick", `removeRecipeImage(${recipeId})`);
        deleteIcon.innerHTML = "delete";

        let recipeImageGroup = document.querySelector("#recipeImage").parentNode;
        recipeImageGroup.appendChild(recipeImage);
        recipeImageGroup.appendChild(deleteIcon);
    }
}

const removeRecipeImage = async(recipeId) => {
    let url = `removeRecipeImage?id=${recipeId}`;
    let removalPromise = fetch(url);
    let removalComplete = await removalPromise.then(() => {
        removePreviousRecipeImageGroup();
    })
}

const removePreviousRecipeImageGroup = () => {
    let imageGroup = document.querySelector("#recipeImage").parentNode;
    imageGroup.removeChild(imageGroup.childNodes[6]);
    imageGroup.removeChild(imageGroup.childNodes[5]);
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

const createInstructionsList = (instructions, recipeId) => {
    instructions.forEach((instruction, i) => {
        let image = instruction.image;
        if (image == null) {
            addInstructionToSet(createInstructionFormElement(instruction.description));
        } else {
            let newElement = createInstructionFormElement(instruction.description, null, image.filepath);
            addInstructionToSet(newElement);
            let addedInstructions = document.querySelector(".addedInstructions");
            let indexOfNewElement = Array.prototype.indexOf.call(addedInstructions.children, newElement);
            addedInstructions.childNodes[indexOfNewElement].childNodes[2].childNodes[2].setAttribute("onclick",`removeInstructionGroup(${recipeId}, ${indexOfNewElement})`);
        }
    })
}

const removeInstructionGroup = async(recipeId, instructionRank) => {
    let url = `removeInstructionImage?recipeId=${recipeId}&instRank=${instructionRank}`;
    let removalPromise = fetch(url);
    let removalComplete = await removalPromise.then(() => {
        removePreviousInstructionImageGroup(instructionRank);
    })
}

const removePreviousInstructionImageGroup = rank => {
    let addedInstructions = document.querySelector(".addedInstructions");
    let groupToRemoveFrom = addedInstructions.childNodes[rank].childNodes[2];
    groupToRemoveFrom.removeChild(groupToRemoveFrom.childNodes[2]);
    groupToRemoveFrom.removeChild(groupToRemoveFrom.childNodes[1]);
}