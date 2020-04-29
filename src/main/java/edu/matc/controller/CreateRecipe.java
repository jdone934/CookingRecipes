package edu.matc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A simple servlet to welcome the recipe.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/createRecipe"}
)

public class CreateRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/createRecipe.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        String[] instructions = req.getParameterValues("instructions");

        String firstInstruction = instructions[0];
        int instructionSize = instructions.length;

        req.setAttribute("first", firstInstruction);
        req.setAttribute("len", instructionSize);

        req.setAttribute("instructions", instructions);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/newRecipe.jsp");
        dispatcher.forward(req, resp);
    }
}
