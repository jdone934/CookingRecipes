package edu.matc.persistence;

import edu.matc.entity.Instruction;
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

public class InstructionDaoTest {
    GenericDao recipeDao;
    GenericDao instructionDao;

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

        instructionDao = new GenericDao(Instruction.class);
        recipeDao = new GenericDao(Recipe.class);
    }

    /**
     * Verify successful retrieval of a Recipe
     */
    @Test
    void getByIdSuccess() {
        Instruction retrievedInstruction = (Instruction) instructionDao.getById(1);
        Recipe recipe = (Recipe) recipeDao.getById(1);
        Instruction expectedInstruction = new Instruction(1, "First, place the roast in the roaster and fill iwth water until the water covers about half the pork. Cook on LOW for about 10 hours.",
                                                            recipe, 1);
        assertEquals(expectedInstruction, retrievedInstruction);
    }

    /**
     * Verify successful insert of a Recipe
     */
    @Test
    void insertSuccess() {
        Recipe recipe = (Recipe) recipeDao.getById(1);
        Instruction newInstruction = new Instruction(3, "new description", recipe);
        int id = instructionDao.insert(newInstruction);
        assertNotEquals(0,id);
        Instruction insertedInstruction = (Instruction) instructionDao.getById(id);
        assertEquals(newInstruction, insertedInstruction);
    }
//
//    /**
//     * Verify successful update of a Recipe
//     */
//    @Test
//    void updateSuccess() {
//        Instruction instructionToUpdate = (Instruction) recipeDao.getById(2);
//        Recipe newRecipe = recipeDao.getById(2)
//        instructionToUpdate.setRecipe(newRecipe);
//        Recipe recipeAfterUpdate = (Recipe) recipeDao.getById(1);
//        Users createdByUser = (Users) userDao.getById(1);
//        Recipe expectedRecipe = new Recipe("Doney's BBQ Pork", createdByUser, 1);
//        assertEquals(expectedRecipe, recipeAfterUpdate);
//    }
//
//    /**
//     * Verify successful delete of Recipe
//     */
//    @Test
//    void deleteSuccess() {
//        recipeDao.delete(recipeDao.getById(2));
//        assertNull(recipeDao.getById(2));
//    }
//
//    /**
//     * Verify successful retrieval of all Recipes
//     */
//    @Test
//    void getAllSuccess() {
//        List<Recipe> recipes = recipeDao.getAll();
//        assertEquals(2, recipes.size());
//    }
}
