package edu.matc.controller;

import edu.matc.entity.Users;
import edu.matc.utility.LoggedInUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        urlPatterns = {"/login/*"}
)

public class Login extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestServlet = req.getPathInfo();
        String loginPath = "/";

        if (requestServlet != null) {
            loginPath = requestServlet + "?" + req.getQueryString();
        }

        LoggedInUser helper = new LoggedInUser();
        Users user = helper.getLoggedInUser(req);
        logger.info("UserId " + user.getId() + " logged in");

        req.setAttribute("path", loginPath);

        resp.sendRedirect("/CookingRecipes" + loginPath);
    }
}
