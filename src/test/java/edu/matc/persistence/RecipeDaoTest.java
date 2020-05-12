package edu.matc.persistence;

import edu.matc.entity.*;
import edu.matc.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeDaoTest {
    GenericDao recipeDao;
    GenericDao userDao;
    GenericDao instructionDao;
    GenericDao imageDao;
    GenericDao favoriteDao;
    GenericDao ingredientDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        recipeDao = new GenericDao(Recipe.class);
        userDao = new GenericDao(Users.class);
        instructionDao = new GenericDao(Instruction.class);
        imageDao = new GenericDao(Image.class);
        favoriteDao = new GenericDao(FavoritedRecipe.class);
        ingredientDao = new GenericDao(Ingredient.class);
    }

    /**
     * Verify successful retrieval of a Recipe
     */
    @Test
    void getByIdSuccess() {
        Recipe retrievedRecipe = (Recipe) recipeDao.getById(1);
        Users createdByUser = (Users) userDao.getById(1);
        Recipe expectedRecipe = new Recipe("Doney's BBQ Pork", createdByUser, 1);
        assertEquals(expectedRecipe, retrievedRecipe);
    }

    /**
     * Verify successful insert of a Recipe
     */
    @Test
    void insertSuccess() {
        Users createdByUser = (Users) userDao.getById(3);
        Recipe newRecipe = new Recipe("Zach's Pizza", createdByUser);
        int id = recipeDao.insert(newRecipe);
        assertNotEquals(0,id);
        Recipe insertedRecipe = (Recipe) recipeDao.getById(id);
        assertEquals(newRecipe, insertedRecipe);
    }

    /**
     * Verify successful update of a Recipe
     */
    @Test
    void updateSuccess() {
        Recipe recipeToUpdate = (Recipe) recipeDao.getById(1);
        recipeToUpdate.setName("Not Doney's BBQ Pork");
        recipeDao.saveOrUpdate(recipeToUpdate);
        Recipe recipeAfterUpdate = (Recipe) recipeDao.getById(1);
        Users createdByUser = (Users) userDao.getById(1);
        Recipe expectedRecipe = new Recipe("Not Doney's BBQ Pork", createdByUser, 1);
        assertEquals(expectedRecipe, recipeAfterUpdate);
    }

    /**
     * Verify successful delete of Recipe
     */
    @Test
    void deleteSuccess() {
        recipeDao.delete(recipeDao.getById(2));
        assertNull(recipeDao.getById(2));
    }

    /**
     * Verify successful retrieval of all Recipes
     */
    @Test
    void getAllSuccess() {
        List<Recipe> recipes = recipeDao.getAll();
        assertEquals(2, recipes.size());
    }

    /**
     * Verify Instruction deletion on deletion of Recipe
     */
    @Test
    void deleteInstructionSuccess() {
        recipeDao.delete(recipeDao.getById(1));
        assertNull(instructionDao.getById(1));
        assertNull(instructionDao.getById(2));
    }

    /**
     * Verify Image deletion on deletion of Recipe
     */
    @Test
    void deleteImageSuccess() {
        Recipe recipeToDelete = (Recipe) recipeDao.getById(1);
        Image imageToInsert = (Image) imageDao.getById(1);
        imageToInsert.setRecipe(recipeToDelete);
        recipeDao.saveOrUpdate(recipeToDelete);
        recipeDao.delete(recipeToDelete);
        assertNull(imageDao.getById(1));
    }

    /**
     * Verify Favorite deletion on deletion of Recipe
     */
    @Test
    void deleteFavoriteSuccess() {
        Recipe recipeToDelete = (Recipe) recipeDao.getById(1);
        recipeDao.delete(recipeToDelete);
        FavoritedRecipe fav = (FavoritedRecipe) favoriteDao.getById(1);
        assertNull(favoriteDao.getById(1));
    }

    /**
     * Verify Ingredient deletion on deletion of Recipe
     */
    @Test
    void deleteIngredientsSuccess() {
        Recipe recipeToDelete = (Recipe) recipeDao.getById(1);
        recipeDao.delete(recipeToDelete);
        Ingredient ing = (Ingredient) ingredientDao.getById(1);
        assertNull(ing);
    }
}
