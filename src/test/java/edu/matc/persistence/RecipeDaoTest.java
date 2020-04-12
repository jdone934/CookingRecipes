package edu.matc.persistence;

import edu.matc.entity.Recipe;
import edu.matc.entity.Users;
import edu.matc.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeDaoTest {
    GenericDao recipeDao;
    GenericDao userDao;

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
}
