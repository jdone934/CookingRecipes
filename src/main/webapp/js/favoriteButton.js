const init = () => {
    let favoriteIcon = document.querySelector(".favoriteIcon");
    favoriteIcon.setAttribute("onclick", "toggleFavorite()");
}

window.onload = init;