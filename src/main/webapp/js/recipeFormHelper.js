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
    deleteIcon.addEventListener("click", deleteInputGroup);

    let ingredientToAdd = document.createElement("div");
    ingredientToAdd.setAttribute("class", "row form-group");
    ingredientToAdd.appendChild(newQuantityTop);
    ingredientToAdd.appendChild(newQuantityBottom);
    ingredientToAdd.appendChild(newUnit);
    ingredientToAdd.appendChild(newIngredientName);
    ingredientToAdd.appendChild(deleteIcon);

    return ingredientToAdd;
}

const createInstructionFormElement = (instruction, maxNumberOfElements) =>{
    let instructionText = document.createElement("textarea");
    instructionText.setAttribute("type", "text");
    instructionText.setAttribute("class", "form-control col-9 newInstructionText");
    instructionText.setAttribute("name", "instruction");
    instructionText.appendChild(document.createTextNode(instruction));
    instructionText.setAttribute("rows", 3);
    instructionText.setAttribute("required", null);
    instructionText.addEventListener("blur", validateField)

    let optionDiv = document.createElement("div");
    optionDiv.setAttribute("class", "col-2");

    let deleteIcon = document.createElement("i");
    deleteIcon.setAttribute("class", "material-icons col-12");
    deleteIcon.innerHTML = "delete";
    deleteIcon.addEventListener("click", deleteInputGroup);
    optionDiv.appendChild(deleteIcon);

    let numberOfChilds = document.querySelector(".addedInstructions").childElementCount;

    if (numberOfChilds != 0) {
        let upIcon = document.createElement("i");
        upIcon.setAttribute("class", "material-icons col-12");
        upIcon.innerHTML = "keyboard_arrow_up";
        upIcon.setAttribute("direction", "up");
        upIcon.addEventListener("click", moveInstructionGroup);
        optionDiv.appendChild(upIcon);
    }

    if (numberOfChilds != maxNumberOfElements) {
        let downIcon = document.createElement("i");
        downIcon.setAttribute("class", "material-icons col-12");
        downIcon.innerHTML = "keyboard_arrow_down";
        downIcon.setAttribute("direction", "down");
        downIcon.addEventListener("click", moveInstructionGroup);
        optionDiv.appendChild(downIcon);
    }

    let instructionToAdd = document.createElement("div");
    instructionToAdd.setAttribute("class", "row form-group");
    instructionToAdd.appendChild(instructionText);
    instructionToAdd.appendChild(optionDiv);

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

    let textToMoveUp = instGroupToMoveUp.childNodes[0].value;
    let textToMoveDown = instGroupToMoveDown.childNodes[0].value;

    instGroupToMoveUp.childNodes[0].value = textToMoveDown;
    instGroupToMoveDown.childNodes[0].value = textToMoveUp;
}