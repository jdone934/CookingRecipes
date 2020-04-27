package edu.matc.controller;

import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * A simple servlet to welcome the recipe.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/searchRecipe"}
)

public class SearchRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao recipeDao = new GenericDao(Recipe.class);
        LoggedInUser helper = new LoggedInUser();

        String searchTerm = req.getParameter("searchTerm");
        String searchType = req.getParameter("searchType");

        Users loggedInUser = helper.getLoggedInUser(req);
        req.setAttribute("user", loggedInUser);

        String loginPath = "/searchRecipe?" + req.getQueryString();
        req.setAttribute("path", loginPath);

        if (searchTerm == null) {
            req.setAttribute("recipes", recipeDao.getAll());
        } else {
            req.setAttribute("recipes", recipeDao.getByPropertyLike(searchType, searchTerm));
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}