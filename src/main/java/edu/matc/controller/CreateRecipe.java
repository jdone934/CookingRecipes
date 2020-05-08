package edu.matc.controller;

import edu.matc.entity.Ingredient;
import edu.matc.entity.Instruction;
import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
import edu.matc.persistence.GenericDao;
import edu.matc.utility.LoggedInUser;
import edu.matc.utility.RecipeExtractor;
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

/**
 * A simple servlet to welcome the recipe.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/createRecipe"}
)

public class CreateRecipe extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/createRecipe.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipeExtractor recipeManager = new RecipeExtractor(req);
        Recipe newRecipe = recipeManager.createRecipe();

        req.setAttribute("recipe", newRecipe);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/newRecipe.jsp");
        dispatcher.forward(req, resp);
    }
}
