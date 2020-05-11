package edu.matc.controller;

import edu.matc.entity.FavoritedRecipe;
import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
import edu.matc.persistence.GenericDao;
import edu.matc.utility.LoggedInUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(
        urlPatterns = {"/toggleFavorite"}
)
public class ToggleFavorite extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(this.getClass());
        int recipeId = Integer.parseInt(req.getParameter("recipeId"));
        GenericDao favoriteDao = new GenericDao(FavoritedRecipe.class);
        Boolean isFavorite = false;

        LoggedInUser helper = new LoggedInUser();
        Users loggedInUser = helper.getLoggedInUser(req);

        if (loggedInUser != null) {
            Set<FavoritedRecipe> favoriteSet = loggedInUser.getFavoriteRecipes();
            logger.info("Fav Set: " + favoriteSet);
            for(FavoritedRecipe favorite : favoriteSet) {
                logger.info("Favorite: " + favorite);
                logger.info("Favorite recipe Id: " + favorite.getRecipe().getId());

                if (recipeId == favorite.getRecipe().getId()) {
                    isFavorite = true;
                    logger.info("Deleted favorite: " + favorite);
                    favoriteDao.delete(favorite);
                    break;
                }
            }

            if (!isFavorite) {
                GenericDao recipeDao = new GenericDao(Recipe.class);
                Recipe recipe = (Recipe) recipeDao.getById(recipeId);
                FavoritedRecipe favToInsert = new FavoritedRecipe(loggedInUser, recipe);
                logger.info("Inserted favorite: " + favToInsert);
                favoriteDao.insert(favToInsert);
            }
        }
    }
}
