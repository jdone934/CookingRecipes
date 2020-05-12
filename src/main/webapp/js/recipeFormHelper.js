const createIngredientFormElement = (quantityTop, quantityBottom, unitOfMeasurement, ingredientName) => {
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
    deleteIcon.addEventListener("click", deleteIngredientGroup);

    let ingredientToAdd = document.createElement("div");
    ingredientToAdd.setAttribute("class", "row form-group");
    ingredientToAdd.appendChild(newQuantityTop);
    ingredientToAdd.appendChild(newQuantityBottom);
    ingredientToAdd.appendChild(newUnit);
    ingredientToAdd.appendChild(newIngredientName);
    ingredientToAdd.appendChild(deleteIcon);

    return ingredientToAdd;
}

const createInstructionFormElement = (instruction, imageFile, imageFilepath) =>{
    let instructionText = document.createElement("textarea");
    instructionText.setAttribute("type", "text");
    instructionText.setAttribute("class", "form-control newInstructionText");
    instructionText.setAttribute("name", "instruction");
    instructionText.appendChild(document.createTextNode(instruction));
    instructionText.setAttribute("rows", 3);
    instructionText.setAttribute("required", null);
    instructionText.addEventListener("blur", validateField);

    let textDiv = document.createElement("div");
    textDiv.setAttribute("class", "form-group col-9");
    textDiv.appendChild(instructionText);

    let optionDiv = document.createElement("div");
    optionDiv.setAttribute("class", "col-2");

    let fileInput = document.createElement("input");
    fileInput.setAttribute("type", "file");
    fileInput.setAttribute("class", "form-control");
    fileInput.setAttribute("name", "instructionImage");

    let imageGroup = document.createElement("div");
    imageGroup.setAttribute("class", "form-group col-9");


    if (imageFile !== null) {
        fileInput.files = imageFile;
        imageGroup.appendChild(fileInput);
    } else if (imageFilepath !== null) {
        imageGroup.appendChild(fileInput);

        console.log(`Filepath: ${imageFilepath}`);
        let recipeImage = document.createElement("label");
        recipeImage.setAttribute("for", "deleteRecipeImage");
        recipeImage.innerHTML = imageFilepath;
        console.log(`Element: ${recipeImage}`);

        let deleteIcon = document.createElement("i");
        deleteIcon.setAttribute("class", "material-icons");
        deleteIcon.innerHTML = "delete";

        imageGroup.appendChild(recipeImage);
        imageGroup.appendChild(deleteIcon);
    }

    let instructionToAdd = document.createElement("div");
    instructionToAdd.setAttribute("class", "row form-group");
    instructionToAdd.appendChild(textDiv);
    instructionToAdd.appendChild(optionDiv);
    instructionToAdd.appendChild(imageGroup);

    return instructionToAdd;
}

const moveInstructionGroup = event => {
    let instGroupToMoveUp = 0;
    let instGroupToMoveDown = 0;

    let direction = event.target.getAttribute("direction");

    if (direction === "up") {
        instGroupToMoveUp = event.target.parentNode.parentNode;
        instGroupToMoveDown = instGroupToMoveUp.previousSibling;
    } else {
        instGroupToMoveDown = event.target.parentNode.parentNode;
        instGroupToMoveUp = instGroupToMoveDown.nextElementSibling;
    }

    let tempGroupUp = instGroupToMoveUp;

    let parentList = instGroupToMoveUp.parentNode;

    let indexMoveUp = Array.prototype.indexOf.call(parentList.children, instGroupToMoveUp);
    parentList.insertBefore(parentList.childNodes[indexMoveUp], parentList.childNodes[indexMoveUp - 1]);

    redetermineOptionGroup(parentList);
}

const deleteIngredientGroup = event => {
    let ingredientGroup = event.target.parentNode;
    let parent = ingredientGroup.parentNode;
    parent.removeChild(ingredientGroup);

    if (parent.childElementCount === 1) {
        parent.innerHTML = "";
    }
}

const deleteInstructionGroup = event => {
    let instructionGroup = event.target.parentNode;
    let formGroup = instructionGroup.parentNode;
    let parent = formGroup.parentNode;
    parent.removeChild(formGroup);

    if (parent.childElementCount === 1) {
        parent.innerHTML = "";
    }

    redetermineOptionGroup(parent);
}








