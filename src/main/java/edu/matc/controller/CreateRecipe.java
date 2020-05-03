package edu.matc.controller;

import edu.matc.entity.Ingredient;
import edu.matc.entity.Instruction;
import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
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
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String category = req.getParameter("category");

        //getting ingredients info
        ArrayList<Integer> quantityTops = parseInts(req.getParameterValues("quantityTop"));
        ArrayList<Integer> quantityBottoms = parseInts(req.getParameterValues("quantityBottom"));
        String[] units = req.getParameterValues("unit");
        String[] ingredientNames = req.getParameterValues("ingredientName");

        String[] instructions = req.getParameterValues("instruction");

        LoggedInUser helper = new LoggedInUser();
        Users user = helper.getLoggedInUser(req);

        Recipe recipeToInsert = new Recipe(name, description, category, user);
        GenericDao recipeDao = new GenericDao(Recipe.class);
        int id = recipeDao.insert(recipeToInsert);
        Recipe newRecipe = (Recipe) recipeDao.getById(id);

        GenericDao ingredientDao = new GenericDao(Ingredient.class);
        for (int i = 0; i < ingredientNames.length; i++) {
            ingredientDao.insert(new Ingredient(ingredientNames[i], units[i], quantityTops.get(i),
                                                quantityBottoms.get(i), newRecipe));
        }

        GenericDao instructionDao = new GenericDao(Instruction.class);
        for (int i = 0; i < instructions.length; i++) {
            instructionDao.insert(new Instruction(i + 1, instructions[i], newRecipe));
        }

        newRecipe = (Recipe) recipeDao.getById(id);

        req.setAttribute("recipe", newRecipe);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/newRecipe.jsp");
        dispatcher.forward(req, resp);
    }

    private ArrayList<Integer> parseInts(String[] arrayOfValues) {
        ArrayList<Integer> parsedInts = new ArrayList<Integer>();
        for (String value : arrayOfValues) {
            parsedInts.add(Integer.parseInt(value));
        }
        return parsedInts;
    }
}
