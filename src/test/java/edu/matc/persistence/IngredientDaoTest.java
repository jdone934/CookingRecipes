package edu.matc.persistence;

import edu.matc.entity.Ingredient;
import edu.matc.entity.Recipe;
import edu.matc.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientDaoTest {
    GenericDao ingredientDao;
    GenericDao recipeDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        ingredientDao = new GenericDao(Ingredient.class);
        recipeDao = new GenericDao(Recipe.class);
    }

    /**
     * Verify successful retrieval of an Ingredient
     */
    @Test
    void getByIdSuccess() {
        Ingredient retrievedIngredient = (Ingredient) ingredientDao.getById(1);
        Recipe recipe = (Recipe) recipeDao.getById(1);
        Ingredient expectedIngredient = new Ingredient("Pork Roast", "lbs", 3, 1, recipe, 1);
        assertEquals(expectedIngredient, retrievedIngredient);
    }

    /**
     * Verify successful insert of an Ingredient
     */
    @Test
    void insertSuccess() {
        Recipe recipe = (Recipe) recipeDao.getById(1);
        Ingredient newIngredient = new Ingredient("new ingredient", "qty", 5, 3, recipe, 5);
        int id = ingredientDao.insert(newIngredient);
        assertNotNull(id);
        Ingredient retrievedIngredient = (Ingredient) ingredientDao.getById(id);
        assertEquals(newIngredient, retrievedIngredient);
    }

    /**
     * Verify successful update of an Ingredient
     */
    @Test
    void updateSuccess() {
        Ingredient ingredientToUpdate = (Ingredient) ingredientDao.getById(1);
        Recipe newRecipe = (Recipe) recipeDao.getById(2);
        ingredientToUpdate.setRecipe(newRecipe);
        ingredientDao.saveOrUpdate(ingredientToUpdate);
        Ingredient ingredientAfterUpdate = (Ingredient) ingredientDao.getById(1);
        Ingredient expectedIngredient = new Ingredient("Pork Roast", "lbs", 3, 1, newRecipe, 1);
        assertEquals(expectedIngredient, ingredientAfterUpdate);
    }


    /**
     * Verify successful delete of Ingredient
     */
    @Test
    void deleteSuccess() {
        ingredientDao.delete(ingredientDao.getById(3));
        assertNull(ingredientDao.getById(3));
    }

    /**
     * Verify successful retrieval of all Ingredient
     */
    @Test
    void getAllSuccess() {
        List<Ingredient> ingredients = ingredientDao.getAll();
        assertEquals(4, ingredients.size());
    }
}
