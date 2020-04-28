package edu.matc.controller;

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
        urlPatterns = {"/accountCreated/*"}
)

public class AccountCreated extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestServlet = req.getPathInfo();
        String returnPath = "/";

        if (requestServlet != null) {
            returnPath = requestServlet + "?" + req.getQueryString();
        }

        req.setAttribute("path", returnPath);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/accountCreated.jsp");
        dispatcher.forward(req, resp);
    }
}
