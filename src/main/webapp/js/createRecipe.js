const init = () => {
    let addInstruction = document.querySelector("#addInstruction");

    addInstruction.addEventListener("click", addNewInstruction);
}

const addNewInstruction = () => {
    let instruction = document.querySelector("#newInstruction");

    let instructionText = document.createElement("input");
    instructionText.setAttribute("type", "text");
    instructionText.setAttribute("class", "form-control col-9");
    instructionText.setAttribute("name", "instructions");
    instructionText.setAttribute("value", instruction.value);

    console.log(instructionText);

    let deleteIcon = document.createElement("i");
    deleteIcon.setAttribute("class", "material-icons col-2 offset-1");
    deleteIcon.innerHTML = "delete";
    deleteIcon.addEventListener("click", deleteInstruction);

    let instructionToAdd = document.createElement("div");
    instructionToAdd.setAttribute("class", "row");
    instructionToAdd.appendChild(instructionText);
    instructionToAdd.appendChild(deleteIcon);

    addInstructionToSet(instructionToAdd);

    instruction.value = "";
}

const addInstructionToSet = instructionToAdd => {
    let instructionSet = document.querySelector(".addedInstructions");

    instructionSet.appendChild(instructionToAdd);
}

const deleteInstruction = event => {
    let instruction = event.target.parentNode;
    instruction.parentNode.removeChild(instruction);
}

window.onload = init;