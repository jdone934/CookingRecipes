const init = () => {
    document.querySelector("#name").addEventListener("blur", validateField);
    document.querySelector("#description").addEventListener("blur", validateField);
    document.querySelector("#category").addEventListener("blur", validateField);
    document.querySelector("#quantityTop").addEventListener("blur", validateField);
    document.querySelector("#unit").addEventListener("blur", validateField);
    document.querySelector("#ingredientName").addEventListener("blur", validateField);

    let addInstruction = document.querySelector("#addInstruction");
    let addIngredient = document.querySelector("#addIngredient");
    let form = document.querySelector("#newRecipe");

    addInstruction.addEventListener("click", addNewInstruction);
    addIngredient.addEventListener("click", addNewIngredient);
    //form.addEventListener("submit", () => {return validateForm()});
}

const addNewInstruction = () => {
    let instruction = document.querySelector("#newInstruction");

    let instructionText = document.createElement("input");
    instructionText.setAttribute("type", "text");
    instructionText.setAttribute("class", "form-control col-9 newInstructionText");
    instructionText.setAttribute("name", "instruction");
    instructionText.setAttribute("value", instruction.value);
    instructionText.setAttribute("required", null);
    instructionText.addEventListener("blur", validateField)

    let deleteIcon = document.createElement("i");
    deleteIcon.setAttribute("class", "material-icons col-2 offset-1");
    deleteIcon.innerHTML = "delete";
    deleteIcon.addEventListener("click", deleteInputGroup);

    let instructionToAdd = document.createElement("div");
    instructionToAdd.setAttribute("class", "row form-group");
    instructionToAdd.appendChild(instructionText);
    instructionToAdd.appendChild(deleteIcon);

    addInstructionToSet(instructionToAdd);

    instruction.value = "";
    instruction.focus();
}

const addNewIngredient = () => {
    let quantityTopElement = document.querySelector("#quantityTop");
    let quantityBottomElement = document.querySelector("#quantityBottom");
    let unitOfMeasurementElement = document.querySelector("#unit");
    let ingredientNameElement = document.querySelector("#ingredientName");

    let quantityTop = quantityTopElement.value;
    let quantityBottom = quantityBottomElement.value;
    let unitOfMeasurement = unitOfMeasurementElement.value;
    let ingredientName = ingredientNameElement.value;

    let newQuantityTop = document.createElement("input");
    newQuantityTop.setAttribute("type", "number");
    newQuantityTop.setAttribute("class", "form-control col-3 newQuantityTopField");
    newQuantityTop.setAttribute("name", "quantityTop");
    newQuantityTop.setAttribute("value", quantityTop);
    newQuantityTop.setAttribute("required", null);
    newQuantityTop.addEventListener("blur", validateField);

    let newQuantityBottom = document.createElement("input");
    newQuantityBottom.setAttribute("type", "number");
    newQuantityBottom.setAttribute("class", "form-control col-3 offset-1");
    newQuantityBottom.setAttribute("name", "quantityBottom");
    newQuantityBottom.setAttribute("value", quantityBottom);

    let newUnit = document.createElement("input");
    newUnit.setAttribute("type", "text");
    newUnit.setAttribute("class", "form-control col-3 offset-1 newUnitField");
    newUnit.setAttribute("name", "unit");
    newUnit.setAttribute("value", unitOfMeasurement);
    newUnit.setAttribute("required", null);
    newUnit.addEventListener("blur", validateField);

    let newIngredientName = document.createElement("input");
    newIngredientName.setAttribute("type", "text");
    newIngredientName.setAttribute("class", "form-control col-9 newIngredientNameField");
    newIngredientName.setAttribute("name", "ingredientName");
    newIngredientName.setAttribute("value", ingredientName);
    newIngredientName.setAttribute("required", null);
    newIngredientName.addEventListener("blur", validateField);

    let deleteIcon = document.createElement("i");
    deleteIcon.setAttribute("class", "material-icons col-1 offset-2");
    deleteIcon.innerHTML = "delete";
    deleteIcon.addEventListener("click", deleteInputGroup);

    let ingredientToAdd = document.createElement("div");
    ingredientToAdd.setAttribute("class", "row form-group");
    ingredientToAdd.appendChild(newQuantityTop);
    ingredientToAdd.appendChild(newQuantityBottom);
    ingredientToAdd.appendChild(newUnit);
    ingredientToAdd.appendChild(newIngredientName);
    ingredientToAdd.appendChild(deleteIcon);

    addIngredientToSet(ingredientToAdd);

    quantityTopElement.value = "";
    quantityBottomElement.value = "";
    unitOfMeasurementElement.value = "";
    ingredientNameElement.value = "";

    quantityTopElement.focus();
}

const addInstructionToSet = instructionToAdd => {
    let instructionSet = document.querySelector(".addedInstructions");

    if (instructionSet.childElementCount == 0) {
        instructionSet.appendChild(document.createElement("hr"))
    }

    instructionSet.appendChild(instructionToAdd);
}

const addIngredientToSet = ingredientToAdd => {
    let ingredientSet = document.querySelector(".addedIngredients");

    if (ingredientSet.childElementCount == 0) {
        ingredientSet.appendChild(document.createElement("hr"));
    }

    ingredientSet.appendChild(ingredientToAdd);
}

const deleteInputGroup = event => {
    let inputGroup = event.target.parentNode;
    let parent = inputGroup.parentNode;
    parent.removeChild(inputGroup);

    if (parent.childElementCount == 1) {
        parent.innerHTML = "";
    }
}

const validateField = event => {
    let target = event.target;
    let value = target.value;

    if(value =="") {
        setErrorMessage();
    } else {
        document.querySelector("#errorMessage").innerHTML = "";
    }
}

const setErrorMessage = message => {
    let errorDiv = document.querySelector("#errorMessage");
    errorDiv.innerHTML = "";

    let errorMessage = document.createElement("div");
    errorMessage.setAttribute("class", "alert alert-danger");
    errorMessage.setAttribute("role", "alert");
    if (message != null) {
        errorMessage.innerHTML = message;
    } else {
        errorMessage.innerHTML = "That field is required. Make sure to go back and fill it in.";
    }

    errorDiv.appendChild(errorMessage);
}

const validateForm = () => {
    let addedIngredients = document.querySelector(".addedIngredients");

    if(addedIngredients.childElementCount == 0) {
        setErrorMessage("You need at least 1 ingredient");
        return false;
    }

    let addedInstructions = document.querySelector(".addedInstructions");

    if(addedInstructions.childElementCount == 0) {
        setErrorMessage("You need at least 1 instruction");
        return false;
    }
}

window.onload = init;