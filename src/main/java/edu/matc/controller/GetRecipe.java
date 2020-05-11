package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Recipe;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/getRecipe"}
)
public class GetRecipe extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        try {
            int id = Integer.parseInt(idString);

            GenericDao recipeDao = new GenericDao(Recipe.class);
            ObjectMapper jsonMapper = new ObjectMapper();

            Recipe recipeToEdit = (Recipe) recipeDao.getById(id);
            String recipeJson = jsonMapper.writeValueAsString(recipeToEdit);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(recipeJson);
        } catch (Exception e) {
            //TODO redirect to 500 page
        }
    }
}
