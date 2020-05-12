const deleteRecipes = () => {
    let recipes = document.querySelectorAll(".recipeCheckbox");

    let recipesToDelete = [];
    recipes.forEach(recipe => {
        if (recipe.checked) {
            recipesToDelete.push(recipe.id);
        }
    })

    if (recipesToDelete.length > 0) {
        let queryParams = `${window.location.search}${recipesToDeleteQueryString(recipesToDelete)}`;
        let servletPath = "/CookingRecipes/adminOnly/deleteRecipes";
        let forwardAddress = servletPath + queryParams;

        window.location.replace(forwardAddress);
    }
}

const recipesToDeleteQueryString = recipes => {
    let queryParams = "";

    recipes.forEach(recipeId => {
        queryParams += `&id=${recipeId}`;
    })

    return queryParams;
}