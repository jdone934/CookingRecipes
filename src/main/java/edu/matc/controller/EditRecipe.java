package edu.matc.controller;

import edu.matc.entity.Recipe;
import edu.matc.persistence.GenericDao;
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
import com.fasterxml.jackson.databind.ObjectMapper;

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

            dispatcher = req.getRequestDispatcher("/editRecipe.jsp");
        } catch (NumberFormatException e) {
            dispatcher = req.getRequestDispatcher("/profile.jsp");
        }

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        RecipeExtractor recipeManager = new RecipeExtractor(req);
        Recipe recipeToUpdate = recipeManager.updateRecipe(id);

        req.setAttribute("recipeId", id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/successfulEdit.jsp");
        dispatcher.forward(req, resp);
    }
}
