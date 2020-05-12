package edu.matc.controller;

import edu.matc.entity.Image;
import edu.matc.entity.Instruction;
import edu.matc.entity.Recipe;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/removeInstructionImage"}
)
public class RemoveInstructionImage extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipeIdString = req.getParameter("recipeId");
        String rankString = req.getParameter("instRank");

        String response;

        try {
            int recipeId = Integer.parseInt(recipeIdString);
            int rank = Integer.parseInt(rankString);

            GenericDao recipeDao = new GenericDao(Recipe.class);
            GenericDao imageDao = new GenericDao(Image.class);

            Recipe recipe = (Recipe) recipeDao.getById(recipeId);

            Instruction instructionToDeleteFrom = null;
            for (Instruction instruction : recipe.getInstructions()) {
                if (instruction.getRecipeRank() == rank) {
                    instructionToDeleteFrom = instruction;
                }
            }

            if (instructionToDeleteFrom == null) {
                throw new FileNotFoundException("Instruction was not found in recipe");
            } else {
                Image image = instructionToDeleteFrom.getImage();
                imageDao.delete(image);
                logger.info("Image Deleted: " + image);
                response = "Image deleted";
            }
        } catch (Exception e) {
            response = "there was an error";
        }

        resp.setContentType("application/text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(response);
    }
}
