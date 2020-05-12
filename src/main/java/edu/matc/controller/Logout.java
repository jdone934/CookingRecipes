package edu.matc.controller;

import edu.matc.persistence.GenericDao;
import edu.matc.entity.Users;
import edu.matc.utility.LoggedInUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * View recipe in step by step view
 * @author Jacob Doney
 */

@WebServlet(
        urlPatterns = {"/logout/*"}
)

public class Logout extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoggedInUser helper = new LoggedInUser();
        Users user = helper.getLoggedInUser(req);
        logger.info("User " + user.getId() + " logged out");

        HttpSession session = req.getSession();
        session.invalidate();

        String requestServlet = req.getPathInfo();
        String loginPath = "/";

        if (requestServlet != null) {
            loginPath = requestServlet + "?" + req.getQueryString();
        }

        req.setAttribute("path", loginPath);

        resp.sendRedirect("/CookingRecipes" + loginPath);
    }
}
