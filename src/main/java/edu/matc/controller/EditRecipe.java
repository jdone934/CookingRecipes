package edu.matc.controller;

import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
import edu.matc.persistence.GenericDao;
import edu.matc.utility.LoggedInUser;
import edu.matc.utility.RecipeExtractor;
import net.bytebuddy.description.type.TypeList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/editRecipe"}
)
public class EditRecipe extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        RequestDispatcher dispatcher;

        try {
            int id = Integer.parseInt(idString);
            req.setAttribute("recipeId", id);

            LoggedInUser helper = new LoggedInUser();
            Users user = helper.getLoggedInUser(req);

            GenericDao recipeDao = new GenericDao(Recipe.class);
            Recipe recipe = (Recipe) recipeDao.getById(id);

            if (recipe.getCreatedByUser().getId() == user.getId()) {
                dispatcher = req.getRequestDispatcher("/editRecipe.jsp");
            } else {
                dispatcher = req.getRequestDispatcher("/index.jsp");
            }


        } catch (NumberFormatException e) {
            dispatcher = req.getRequestDispatcher("/profile.jsp");
        }

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        RecipeExtractor recipeManager = new RecipeExtractor(req, getServletContext());
        recipeManager.updateRecipe(id);

        req.setAttribute("recipeId", id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/successfulEdit.jsp");
        dispatcher.forward(req, resp);
    }
}
