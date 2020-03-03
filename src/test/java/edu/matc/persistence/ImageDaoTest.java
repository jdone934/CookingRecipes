package edu.matc.persistence;

import edu.matc.entity.Image;
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
    GenericDao dao;

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

        dao = new GenericDao(Image.class);
    }

    /**
     * Verify successful retrieval of a Image
     */
    @Test
    void getByIdSuccess() {
        Image retrievedImage = (Image) dao.getById(1);
        Image expectedImage = new Image("catLightning.png", "cat shooting lightning from his paws");
        assertEquals(expectedImage, retrievedImage);
    }

    /**
     * Verify successful insert of a Image
     */
    @Test
    void insertSuccess() {
        Image newImage = new Image("ironThrone.png", "the Iron Throne from Game of Thrones");
        int id = dao.insert(newImage);
        assertNotEquals(0,id);
        Image insertedImage = (Image) dao.getById(id);
        assertEquals(newImage, insertedImage);
    }

    /**
     * Verify successful update of a Image
     */
    @Test
    void updateSuccess() {
        Image expectedImage = new Image("new filepath", "new description");
        Image imageToUpdate = (Image) dao.getById(1);
        imageToUpdate.setFilepath(expectedImage.getFilepath());
        imageToUpdate.setDescription((expectedImage.getDescription()));
        dao.saveOrUpdate(imageToUpdate);
        Image imageAfterUpdate = (Image) dao.getById(1);
        assertEquals(expectedImage, imageAfterUpdate);
    }

    /**
     * Verify successful delete of Image
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(2));
        assertNull(dao.getById(2));
    }

    /**
     * Verify successful retrieval of all Images
     */
    @Test
    void getAllSuccess() {
        List<Image> images = dao.getAll();
        assertEquals(2, images.size());
    }
}
