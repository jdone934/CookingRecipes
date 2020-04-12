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
        Instruction expectedInstruction = new Instruction(1, "First, place the roast in the roaster and fill with water until the water covers about half the pork. Cook on LOW for about 10 hours.",
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

    /**
     * Verify successful update of a Recipe
     */
    @Test
    void updateSuccess() {
        Instruction instructionToUpdate = (Instruction) instructionDao.getById(2);
        Recipe newRecipe = (Recipe) recipeDao.getById(2);
        instructionToUpdate.setRecipe(newRecipe);
        instructionDao.saveOrUpdate(instructionToUpdate);
        Instruction instructionAfterUpdate = (Instruction) instructionDao.getById(2);
        Instruction expectedInstruction = new Instruction(2, "Then, remove the roast form the heat and shred it, discarding as much fat as you can. You can then place the meat back into the roaster.",
                                                            newRecipe, 2);
        assertEquals(expectedInstruction, instructionAfterUpdate);
    }

    /**
     * Verify successful delete of Instruction
     */
    @Test
    void deleteSuccess() {
        instructionDao.delete(instructionDao.getById(2));
        assertNull(instructionDao.getById(2));
    }

    /**
     * Verify successful retrieval of all Instructions
     */
    @Test
    void getAllSuccess() {
        List<Instruction> instructions = instructionDao.getAll();
        assertEquals(2, instructions.size());
    }
}
