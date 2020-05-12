package edu.matc.controller;

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

@WebServlet(
        urlPatterns = {"/adminOnly/search"}
)
public class AdminSearch extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchTerm = req.getParameter("searchTerm");
        String searchFor = req.getParameter("searchFor");

        if (searchFor.equals("user")) {
            String searchBy = req.getParameter("userSearchBy");

            GenericDao userDao = new GenericDao(Users.class);
            req.setAttribute("users", userDao.getByPropertyLike(searchBy, searchTerm));
            logger.info("Searched for Users. Searched By: " + searchBy + " Search Term: " + searchTerm);
        } else {
            String searchBy = req.getParameter("recipeSearchBy");

            GenericDao recipeDao = new GenericDao(Recipe.class);
            req.setAttribute("recipes", recipeDao.getByPropertyLike(searchBy, searchTerm));
            logger.info("Searched for Recipes. Searched By: " + searchBy + " Search Term: " + searchTerm);
        }

        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminSearch.jsp");
        dispatcher.forward(req, resp);
    }
}
