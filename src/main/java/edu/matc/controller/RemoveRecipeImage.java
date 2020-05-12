package edu.matc.controller;

import edu.matc.entity.Image;
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

/**
 * Remove image from recipe
 * @author Jacob Doney
 */
@WebServlet(
        urlPatterns = {"/removeRecipeImage"}
)
public class RemoveRecipeImage extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        try {
            int recipeId = Integer.parseInt(idString);

            GenericDao recipeDao = new GenericDao(Recipe.class);
            GenericDao imageDao = new GenericDao(Image.class);

            Recipe recipeToDeleteFrom = (Recipe) recipeDao.getById(recipeId);
            Image image = recipeToDeleteFrom.getImage();
            imageDao.delete(image);
            logger.info("Image deleted: " + image);
        } catch (Exception e) {
            //TODO redirect to 500 page
        }
    }
}
