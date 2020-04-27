package edu.matc.controller;

import edu.matc.persistence.GenericDao;
import edu.matc.entity.Users;

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
        urlPatterns = {"/login/*"}
)

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestServlet = req.getPathInfo();
        String loginPath = "/";

        if (requestServlet != null) {
            loginPath = requestServlet + "?" + req.getQueryString();
        }

        req.setAttribute("path", loginPath);

        //dispatcher.forward(req, resp);
        resp.sendRedirect("/CookingRecipes" + loginPath);
    }
}