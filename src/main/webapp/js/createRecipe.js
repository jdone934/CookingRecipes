const init = () => {
    formSetUp();
}

const formSetUp = () => {
    document.querySelector("#name").addEventListener("blur", validateField);
    document.querySelector("#description").addEventListener("blur", validateField);
    document.querySelector("#category").addEventListener("blur", validateField);

    let addInstruction = document.querySelector("#addInstruction");
    let addIngredient = document.querySelector("#addIngredient");
    let form = document.querySelector("#newRecipe");

    addInstruction.addEventListener("click", addNewInstruction);
    addIngredient.addEventListener("click", addNewIngredient);
    form.addEventListener("submit", () => {return validateForm()});
}

const addNewInstruction = () => {
    let instruction = document.querySelector("#newInstruction");
    let instructionImage = document.querySelector("#newInstructionImage");
    let instructionGroup  = document.querySelector(".addInstruction");

    addInstructionToSet(createInstructionFormElement(instruction.value, instructionImage.files));

    instructionGroup.innerHTML = "";
    rebuildNewInstruction(instructionGroup);

    instruction.value = "";
    instruction.focus();
}

const rebuildNewInstruction = inputGroup => {
    let instructionText = document.createElement("textarea");
    instructionText.setAttribute("type", "text");
    instructionText.setAttribute("class", "form-control newInstructionText col-9");
    instructionText.setAttribute("id", "newInstruction");
    instructionText.setAttribute("placeholder", "Instruction");
    instructionText.setAttribute("rows", 3);

    let addIcon = document.createElement("i");
    addIcon.setAttribute("class", "material-icons col-2 offset-1");
    addIcon.setAttribute("id", "addInstruction");
    addIcon.innerHTML = "add_circle_outline";
    addIcon.addEventListener("click", addNewInstruction);

    let fileInput = document.createElement("input");
    fileInput.setAttribute("type", "file");
    fileInput.setAttribute("class", "form-control col-9");
    fileInput.setAttribute("id", "newInstructionImage");
    fileInput.setAttribute("accept", "image/*");

    inputGroup.appendChild(instructionText);
    inputGroup.appendChild(addIcon);
    inputGroup.appendChild(fileInput);
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

    addIngredientToSet(createIngredientFormElement(quantityTop, quantityBottom, unitOfMeasurement, ingredientName));

    quantityTopElement.value = "";
    quantityBottomElement.value = "";
    unitOfMeasurementElement.value = "";
    ingredientNameElement.value = "";

    quantityTopElement.focus();
}

const addInstructionToSet = instructionToAdd => {
    let addedInstructions = document.querySelector(".addedInstructions");

    if (addedInstructions.childElementCount == 0) {
        addedInstructions.appendChild(document.createElement("hr"))
    }

    addedInstructions.appendChild(instructionToAdd);
    redetermineOptionGroup(addedInstructions);
}

const redetermineOptionGroup = instructionGroup => {
    if (instructionGroup.childElementCount > 1) {
        if (instructionGroup.childNodes[0].nodeType === 3) {
            instructionGroup.childNodes[0].remove();
        }

        for (let i = 1; i < instructionGroup.childElementCount; i++) {
            setOptionGroup(instructionGroup.childNodes[i]);
        }
    }
}

const setOptionGroup = instruction => {
    let parentList = instruction.parentNode;

    let optionDiv = instruction.childNodes[1];
    optionDiv.innerHTML = "";

    let deleteIcon = document.createElement("i");
    deleteIcon.setAttribute("class", "material-icons col-12");
    deleteIcon.innerHTML = "delete";
    deleteIcon.addEventListener("click", deleteInstructionGroup);
    optionDiv.appendChild(deleteIcon);

    let numberOfChildren = instruction.parentNode.childElementCount;
    let indexOfInstruction = Array.prototype.indexOf.call(parentList.children, instruction);

    let upIcon = document.createElement("i");
    upIcon.setAttribute("class", "material-icons col-12");
    upIcon.innerHTML = "keyboard_arrow_up";
    upIcon.setAttribute("direction", "up");
    upIcon.addEventListener("click", moveInstructionGroup);

    let downIcon = document.createElement("i");
    downIcon.setAttribute("class", "material-icons col-12");
    downIcon.innerHTML = "keyboard_arrow_down";
    downIcon.setAttribute("direction", "down");
    downIcon.addEventListener("click", moveInstructionGroup);

    if (numberOfChildren > 2) {
        if (indexOfInstruction === 1) {
            optionDiv.appendChild(downIcon);
        } else if (indexOfInstruction === numberOfChildren - 1) {
            optionDiv.appendChild(upIcon);
        } else {
            optionDiv.appendChild(upIcon);
            optionDiv.append(downIcon);
        }
    }
}

const addIngredientToSet = ingredientToAdd => {
    let ingredientSet = document.querySelector(".addedIngredients");

    if (ingredientSet.childElementCount == 0) {
        ingredientSet.appendChild(document.createElement("hr"));
    }

    ingredientSet.appendChild(ingredientToAdd);
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