package edu.matc.controller;

import edu.matc.entity.Recipe;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        urlPatterns = {"/adminOnly/deleteRecipes"}
)
public class AdminDeleteRecipes extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] recipeStringIds = req.getParameterValues("id");
        String message;

        if (recipeStringIds.length > 0) {
            GenericDao recipeDao = new GenericDao(Recipe.class);
            Recipe recipe;
            for (String id : recipeStringIds) {
                int recipeId = Integer.parseInt(id);
                recipe = (Recipe) recipeDao.getById(recipeId);
                recipeDao.delete(recipe);
                logger.info("Recipe Deleted: " + recipe);
            }

            message = "Recipes were deleted";
        } else {
            message = "No recipes were deleted";
        }

        String query = req.getQueryString() + "&message=" + message;
        resp.sendRedirect("/CookingRecipes/adminOnly/search?" + query);
    }
}
