const toggleFavorite = async(id) => {
    const queryString = new URLSearchParams(window.location.search);
    let recipeId = queryString.get('id');

    if (id != null) {
        recipeId = id;
    }

    let favoriteIcon = document.querySelector(".favoriteIcon" + recipeId);

    let url = `toggleFavorite?recipeId=${recipeId}`;
    let togglePromise = fetch(url);
    await togglePromise.then(() => {
        if (favoriteIcon.innerHTML === "favorite_border") {
            favoriteIcon.innerHTML = "favorite";
        } else if (favoriteIcon.innerHTML === "favorite") {
            favoriteIcon.innerHTML = "favorite_border";
        }
    })
}