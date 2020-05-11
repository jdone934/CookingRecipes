package edu.matc.controller;

import edu.matc.entity.FavoritedRecipe;
import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
import edu.matc.persistence.GenericDao;
import edu.matc.utility.LoggedInUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Set;

/**
 * A simple servlet to welcome the recipe.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/viewRecipeOverview"}
)

public class ViewRecipeOverview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao recipeDao = new GenericDao(Recipe.class);
        LoggedInUser helper = new LoggedInUser();
        int recipeId = Integer.parseInt(req.getParameter("id"));

        Users loggedInUser = helper.getLoggedInUser(req);
        req.setAttribute("user", loggedInUser);

        if (loggedInUser != null) {
            Set<FavoritedRecipe> favoriteSet = loggedInUser.getFavoriteRecipes();

            for(FavoritedRecipe favorite : favoriteSet) {
                if (recipeId == favorite.getRecipe().getId()) {
                    req.setAttribute("favoriteRecipe", "true");
                    break;
                }
            }
        }

        String loginPath = "/viewRecipeOverview?" + req.getQueryString();
        req.setAttribute("path", loginPath);

        Recipe recipe = (Recipe) recipeDao.getById(recipeId);
        req.setAttribute("recipe", recipe);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/recipeOverview.jsp");
        dispatcher.forward(req, resp);
    }
}