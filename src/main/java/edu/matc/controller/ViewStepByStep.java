package edu.matc.controller;

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

/**
 * View recipe in step by step view
 * @author Jacob Doney
 */

@WebServlet(
        urlPatterns = {"/viewRecipeStepByStep"} //viewRecipeStepByStep
)

public class ViewStepByStep extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao recipeDao = new GenericDao(Recipe.class);
        LoggedInUser helper = new LoggedInUser();

        Users loggedInUser = helper.getLoggedInUser(req);
        req.setAttribute("user", loggedInUser);

        String loginPath = "/viewRecipeStepByStep?" + req.getQueryString();
        req.setAttribute("path", loginPath);

        int recipeId = Integer.parseInt(req.getParameter("id"));
        Recipe recipe = (Recipe) recipeDao.getById(recipeId);
        req.setAttribute("recipe", recipe);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/stepByStep.jsp");
        dispatcher.forward(req, resp);
    }
}
