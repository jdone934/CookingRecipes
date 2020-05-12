package edu.matc.controller;

import edu.matc.entity.FavoritedRecipe;
import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
import edu.matc.utility.LoggedInUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet(
        urlPatterns = {"/profile"}
)

public class Profile extends HttpServlet {
    private LoggedInUser helper = new LoggedInUser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = helper.getLoggedInUser(req);
        req.setAttribute("user", user);

        Set<Recipe> favoriteRecipes = new HashSet<>();
        Set<FavoritedRecipe> favoriteRecipeJoin = user.getFavoriteRecipes();

        if(favoriteRecipeJoin.size() > 0) {
            for (FavoritedRecipe favorite : favoriteRecipeJoin) {
                favoriteRecipes.add(favorite.getRecipe());
            }

            req.setAttribute("favoriteRecipes", favoriteRecipes);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/profile.jsp");
        dispatcher.forward(req, resp);
    }
}
