const queryString = window.location.search;
const recipeId = new URLSearchParams.get(queryString).get('id');

const init = () => {
    const favoriteIcon = document.querySelector(".favoriteIcon");

}

window.onload = init;