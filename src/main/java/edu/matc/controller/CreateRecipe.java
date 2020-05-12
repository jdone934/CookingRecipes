package edu.matc.controller;

import edu.matc.entity.Recipe;
import edu.matc.utility.RecipeExtractor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A simple servlet to welcome the recipe.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/createRecipe"}
)
public class CreateRecipe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/createRecipe.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipeExtractor recipeManager = new RecipeExtractor(req, getServletContext());
        Recipe newRecipe = recipeManager.createRecipe();

        req.setAttribute("recipe", newRecipe);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/newRecipe.jsp");
        dispatcher.forward(req, resp);
    }
}
