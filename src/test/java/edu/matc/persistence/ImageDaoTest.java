package edu.matc.persistence;

import edu.matc.entity.Image;
import edu.matc.entity.Instruction;
import edu.matc.entity.Recipe;
import edu.matc.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageDaoTest {
    GenericDao imageDao;
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

        imageDao = new GenericDao(Image.class);
        recipeDao = new GenericDao(Recipe.class);
        instructionDao = new GenericDao(Instruction.class);
    }

    /**
     * Verify successful retrieval of a Image
     */
    @Test
    void getByIdSuccess() {
        Image retrievedImage = (Image) imageDao.getById(1);
        Recipe recipe = (Recipe) recipeDao.getById(1);
        Image expectedImage = new Image("catLightning.png", "cat shooting lightning from his paws", recipe, 1);
        assertEquals(expectedImage, retrievedImage);
    }

    /**
     * Verify successful insert of a Image
     */
    @Test
    void insertSuccess() {
        Image newImage = new Image("ironThrone.png", "the Iron Throne from Game of Thrones");
        int id = imageDao.insert(newImage);
        assertNotEquals(0,id);
        Image insertedImage = (Image) imageDao.getById(id);
        assertEquals(newImage, insertedImage);
    }

    /**
     * Verify successful update of a Image
     */
    @Test
    void updateSuccess() {
        Recipe recipe = (Recipe) recipeDao.getById(1);
        Image expectedImage = new Image("new filepath", "new description", recipe, 1);
        Image imageToUpdate = (Image) imageDao.getById(1);
        imageToUpdate.setFilepath(expectedImage.getFilepath());
        imageToUpdate.setDescription((expectedImage.getDescription()));
        imageDao.saveOrUpdate(imageToUpdate);
        Image imageAfterUpdate = (Image) imageDao.getById(1);
        assertEquals(expectedImage, imageAfterUpdate);
    }

    /**
     * Verify successful delete of Image
     */
    @Test
    void deleteSuccess() {
        imageDao.delete(imageDao.getById(2));
        assertNull(imageDao.getById(2));
    }

    /**
     * Verify successful retrieval of all Images
     */
    @Test
    void getAllSuccess() {
        List<Image> images = imageDao.getAll();
        assertEquals(2, images.size());
    }
}
