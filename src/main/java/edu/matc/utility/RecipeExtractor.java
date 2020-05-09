package edu.matc.utility;

import edu.matc.entity.Ingredient;
import edu.matc.entity.Instruction;
import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
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
import java.util.HashMap;
import java.util.List;

public class RecipeExtractor {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private ServletContext servletContext;

    private HttpServletRequest request;
    private HashMap<String, Object> recipeInfo;

    private String name;
    private String description;
    private String category;
    private ArrayList<Integer> quantityTops = new ArrayList<>();
    private ArrayList<Integer> quantityBottoms = new ArrayList<>();
    private ArrayList<String> units = new ArrayList<>();
    private ArrayList<String> ingredientNames = new ArrayList<>();
    private ArrayList<String> instructions = new ArrayList<>();

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

            case "imageDescription":
                imageDescription = value;
                break

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
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String webappPath = "/src/main/webapp/";
        String uploadPath = removeTargetDirectory(servletContext.getRealPath("")) + webappPath;

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

        String fileName = new File(item.getName()).getName();
        String filePath = uploadDirectory + File.separator + fileName;
        File storeFile = new File(filePath);

        // saves the file on disk
        item.write(storeFile);

        imagePath = fileName;
    }

    public Recipe createRecipe() {
        extractRecipe();

        Recipe recipeToInsert = new Recipe(name, description, category, user);
        GenericDao recipeDao = new GenericDao(Recipe.class);

        int id = recipeDao.insert(recipeToInsert);
        Recipe newRecipe = (Recipe) recipeDao.getById(id);

        GenericDao ingredientDao = new GenericDao(Ingredient.class);
        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredientDao.insert(new Ingredient(ingredientNames.get(i), units.get(i), quantityTops.get(i),
                    quantityBottoms.get(i), newRecipe));
        }

        GenericDao instructionDao = new GenericDao(Instruction.class);
        for (int i = 0; i < instructions.size(); i++) {
            instructionDao.insert(new Instruction(i + 1, instructions.get(i), newRecipe));
        }

        return (Recipe) recipeDao.getById(id);
    }

    public Recipe updateRecipe(int id){
        GenericDao recipeDao = new GenericDao(Recipe.class);
        Recipe recipeToUpdate = (Recipe) recipeDao.getById(id);

        recipeToUpdate.setName(name);
        recipeToUpdate.setDescription(description);
        recipeToUpdate.setCategory(category);
        recipeDao.saveOrUpdate(recipeToUpdate);

        GenericDao ingredientDao = new GenericDao(Ingredient.class);
        for(Ingredient ingredient : recipeToUpdate.getIngredients()) {
            ingredientDao.delete(ingredient);
        }

        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredientDao.insert(new Ingredient(ingredientNames.get(i), units.get(i), quantityTops.get(i),
                    quantityBottoms.get(i), recipeToUpdate));
        }

        GenericDao instructionDao = new GenericDao(Instruction.class);
        for (Instruction instruction : recipeToUpdate.getInstructions()) {
            instructionDao.delete(instruction);
        }

        for (int i = 0; i < instructions.size(); i++) {
            instructionDao.insert(new Instruction(i + 1, instructions.get(i), recipeToUpdate));
        }

        return (Recipe) recipeDao.getById(id);
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