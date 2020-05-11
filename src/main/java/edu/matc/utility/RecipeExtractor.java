package edu.matc.utility;

import edu.matc.entity.*;
import edu.matc.persistence.GenericDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecipeExtractor {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private ServletContext servletContext;

    private HttpServletRequest request;

    private String name;
    private String description;
    private String category;
    private ArrayList<Integer> quantityTops = new ArrayList<>();
    private ArrayList<Integer> quantityBottoms = new ArrayList<>();
    private ArrayList<String> units = new ArrayList<>();
    private ArrayList<String> ingredientNames = new ArrayList<>();
    private ArrayList<String> instructions = new ArrayList<>();
    private ArrayList<String> imagePath = new ArrayList<>();

    private Users user;

    public RecipeExtractor() {
    }

    public RecipeExtractor(HttpServletRequest request, ServletContext servletContext) {
        this.request = request;
        this.servletContext = servletContext;

        setUser();
    }

    private void setUser() {
        LoggedInUser helper = new LoggedInUser();
        user = helper.getLoggedInUser(request);
    }

    private void extractRecipe() {
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {

            return;
        }

        // configures upload settings
        DiskFileItemFactory diskFactory = new DiskFileItemFactory();
        // sets temporary location to store files
        diskFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(diskFactory);

        try {
            // parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (item.isFormField()) {
                        setRecipeValue(item.getFieldName(), item.getString());
                    } else {
                        saveImage(item);
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
    }

    private void setRecipeValue(String fieldName, String value) {
        switch (fieldName) {
            case "name":
                name = value;
                break;

            case "description":
                description = value;
                break;

            case "category":
                category = value;
                break;

            case "quantityTop":
                quantityTops.add(Integer.parseInt(value));
                break;

            case "quantityBottom":
                quantityBottoms.add(Integer.parseInt(value));
                break;

            case "unit":
                units.add(value);
                break;

            case "ingredientName":
                ingredientNames.add(value);
                break;

            case "instruction":
                instructions.add(value);
        }
    }

    private void saveImage(FileItem item) throws Exception {
        String fileName = "";

        if (item.getSize() > 0) {
            // constructs the directory path to store upload file
            // this path is relative to application's directory

            //The following two lines are for local testing only
            //String webappPath = "/src/main/webapp/";
            //String uploadPath = removeTargetDirectory(servletContext.getRealPath("")) + webappPath;

            //
            String uploadPath = servletContext.getRealPath("");
            logger.info("Upload Path: " + uploadPath);

            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uploadDirectory = uploadPath;
            if (item.getFieldName().equals("recipeImage")) {
                uploadDirectory += "recipeImg/";
            } else {
                uploadDirectory += "instructionImg/";
            }

            fileName = new File(item.getName()).getName();
            String filePath = uploadDirectory + File.separator + fileName;
            File storeFile = new File(filePath);

            // saves the file on disk
            item.write(storeFile);
        }

        imagePath.add(fileName);
    }

    public Recipe createRecipe() {
        extractRecipe();

        Recipe recipeToInsert = new Recipe(name, description, category, user);
        GenericDao recipeDao = new GenericDao(Recipe.class);

        int id = recipeDao.insert(recipeToInsert);
        Recipe newRecipe = (Recipe) recipeDao.getById(id);

        GenericDao imageDao = new GenericDao(Image.class);
        String recipeImagePath = imagePath.get(0);

        if (recipeImagePath.length() > 0) {
            imageDao.insert(new Image(recipeImagePath, newRecipe));
        }

        GenericDao ingredientDao = new GenericDao(Ingredient.class);
        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredientDao.insert(new Ingredient(ingredientNames.get(i), units.get(i), quantityTops.get(i),
                                                quantityBottoms.get(i), newRecipe));
        }

        GenericDao instructionDao = new GenericDao(Instruction.class);
        String instImagePath;
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instToInsert = new Instruction(i + 1, instructions.get(i), newRecipe);
            //instructionDao.insert(new Instruction(i + 1, instructions.get(i), newRecipe));

            instImagePath = imagePath.get(i + 1);
            if (instImagePath.length() > 0) {
                imageDao.insert(new Image(instImagePath, instToInsert));
            }
        }

        Recipe recipeAfterInsert = (Recipe) recipeDao.getById(id);
        logger.info("Recipe Inserted: " + recipeAfterInsert);
        return recipeAfterInsert;
    }

    public Recipe updateRecipe(int id){
        extractRecipe();

        GenericDao recipeDao = new GenericDao(Recipe.class);
        GenericDao ingredientDao = new GenericDao(Ingredient.class);
        GenericDao instructionDao = new GenericDao(Instruction.class);
        GenericDao imageDao = new GenericDao(Image.class);

        Recipe recipeToUpdate = (Recipe) recipeDao.getById(id);

        ArrayList<Instruction> instructionsBeforeUpdate = new ArrayList<>();

        recipeToUpdate.setName(name);
        recipeToUpdate.setDescription(description);
        recipeToUpdate.setCategory(category);
        recipeDao.saveOrUpdate(recipeToUpdate);

        String recipeImagePath = imagePath.get(0);
        if (recipeImagePath.length() > 0) {
            imageDao.insert(new Image(recipeImagePath, recipeToUpdate));
        }

        for(Ingredient ingredient : recipeToUpdate.getIngredients()) {
            ingredientDao.delete(ingredient);
        }

        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredientDao.insert(new Ingredient(ingredientNames.get(i), units.get(i), quantityTops.get(i),
                    quantityBottoms.get(i), recipeToUpdate));
        }

        for (Instruction instruction : recipeToUpdate.getInstructions()) {
            instructionsBeforeUpdate.add(instruction);
            instructionDao.delete(instruction);
        }

        for (int i = 0; i < instructions.size(); i++) {
            Instruction instructionToInsert = new Instruction(i + 1, instructions.get(i), recipeToUpdate);
            instructionDao.insert(instructionToInsert);

            if (i < instructionsBeforeUpdate.size()) {
                Image imageBefore = instructionsBeforeUpdate.get(i).getImage();
                if (imagePath.get(i + 1).length() > 0) {
                    imageDao.insert(new Image(imagePath.get(i + 1), instructionToInsert));
                } else if (imageBefore != null) {
                    imageDao.insert(new Image(imageBefore.getFilepath(), instructionToInsert));
                }
            }
        }

        Recipe recipeAfterUpdate = (Recipe) recipeDao.getById(id);
        logger.info("Recipe Updated: " + recipeAfterUpdate);
        return recipeAfterUpdate;
    }

    private ArrayList<Integer> parseInts(String[] arrayOfValues) {
        ArrayList<Integer> parsedInts = new ArrayList<Integer>();
        for (String value : arrayOfValues) {
            parsedInts.add(Integer.parseInt(value));
        }
        return parsedInts;
    }

    private String removeTargetDirectory(String contextPath) {
        int index;
        String newPath = contextPath;

        for (int i = 0; i < 3; i++) {
            index = newPath.lastIndexOf("/");
            newPath = newPath.substring(0, index);
        }

        return newPath;
    }
}