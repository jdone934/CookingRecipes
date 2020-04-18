package edu.matc.controller;

import edu.matc.entity.Recipe;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchRecipe extends HttpServlet {
    GenericDao recipeDao = new GenericDao(Recipe.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Recipe userData = new Recipe();
        String searchTerm = req.getParameter("searchTerm");
        String searchType = req.getParameter("searchType");
        if (searchTerm == null) {
            req.setAttribute("recipes", recipeDao.getAll());
        } else {
            req.setAttribute("recipes", recipeDao.);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}