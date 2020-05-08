package edu.matc.utility;

import edu.matc.entity.Ingredient;
import edu.matc.entity.Instruction;
import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
import edu.matc.persistence.GenericDao;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeExtractor {
    private HttpServletRequest request;
    private HashMap<String, Object> recipeInfo;

    private String name;
    private String description;
    private String category;
    private ArrayList<Integer> quantityTops;
    private ArrayList<Integer> quantityBottoms;
    private String[] units;
    private String[] ingredientNames;
    private String[] instructions;

    private Users user;

    public RecipeExtractor() {
    }

    public RecipeExtractor(HttpServletRequest request) {
        this.request = request;

        extractRecipe();
        setUser();
    }

    private void setUser() {
        LoggedInUser helper = new LoggedInUser();
        user = helper.getLoggedInUser(request);
    }

    private void extractRecipe() {
        name = request.getParameter("name");
        description = request.getParameter("description");
        category = request.getParameter("category");

        //getting ingredients info
        quantityTops = parseInts(request.getParameterValues("quantityTop"));
        quantityBottoms = parseInts(request.getParameterValues("quantityBottom"));
        units = request.getParameterValues("unit");
        ingredientNames = request.getParameterValues("ingredientName");

        instructions = request.getParameterValues("instruction");
    }

    public Recipe createRecipe() {
        Recipe recipeToInsert = new Recipe(name, description, category, user);
        GenericDao recipeDao = new GenericDao(Recipe.class);

        int id = recipeDao.insert(recipeToInsert);
        Recipe newRecipe = (Recipe) recipeDao.getById(id);

        GenericDao ingredientDao = new GenericDao(Ingredient.class);
        for (int i = 0; i < ingredientNames.length; i++) {
            ingredientDao.insert(new Ingredient(ingredientNames[i], units[i], quantityTops.get(i),
                    quantityBottoms.get(i), newRecipe));
        }

        GenericDao instructionDao = new GenericDao(Instruction.class);
        for (int i = 0; i < instructions.length; i++) {
            instructionDao.insert(new Instruction(i + 1, instructions[i], newRecipe));
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

        for (int i = 0; i < ingredientNames.length; i++) {
            ingredientDao.insert(new Ingredient(ingredientNames[i], units[i], quantityTops.get(i),
                    quantityBottoms.get(i), recipeToUpdate));
        }

        GenericDao instructionDao = new GenericDao(Instruction.class);
        for (Instruction instruction : recipeToUpdate.getInstructions()) {
            instructionDao.delete(instruction);
        }

        for (int i = 0; i < instructions.length; i++) {
            instructionDao.insert(new Instruction(i + 1, instructions[i], recipeToUpdate));
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
}