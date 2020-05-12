const init = () => {
    let searchBy = document.querySelector("#searchFor");
    searchBy.addEventListener("change", changeForm);
}

const changeForm = event => {
    let searchByValue = event.target.value;

    if (searchByValue === 'user') {
        changeToUser();
    } else {
        changeToRecipe();
    }
}

const changeToUser = () => {
    let userSelect = document.querySelector("#userSearchOptions");
    let recipeSelect = document.querySelector("#recipeSearchOptions");

    userSelect.style.display = "block";
    recipeSelect.style.display = "none";
}

const changeToRecipe = () => {
    let userSelect = document.querySelector("#userSearchOptions");
    let recipeSelect = document.querySelector("#recipeSearchOptions");

    userSelect.style.display = "none";
    recipeSelect.style.display = "block";
}

window.onload = init;