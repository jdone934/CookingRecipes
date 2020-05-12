package edu.matc.persistence;

import edu.matc.entity.FavoritedRecipe;
import edu.matc.entity.Users;
import edu.matc.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersDaoTest {
    GenericDao userDao;
    GenericDao favoriteDao;

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

        userDao = new GenericDao(Users.class);
        favoriteDao = new GenericDao(FavoritedRecipe.class);
    }

    /**
     * Verify successful retrieval of a Users
     */
    @Test
    void getByIdSuccess() {
        Users retrievedUser = (Users) userDao.getById(1);
        Users expectedUser = new Users("jdone934", "password", "jdone934@hotmail.com",
                            "Jacob", "Doney", 1);

        assertEquals(expectedUser, retrievedUser);
    }

    /**
     * Verify successful insert of a Users
     */
    @Test
    void insertSuccess() {
        Users newUser = new Users("myhero34", "IM A SAILOR", "sdoney@gmail.com", "Sam", "Doney");
        int id = userDao.insert(newUser);
        assertNotEquals(0,id);
        newUser.setId(id);
        Users insertedUser = (Users) userDao.getById(id);
        assertEquals(newUser, insertedUser);
    }

    /**
     * Verify successful update of a Users
     */
    @Test
    void updateSuccess() {
        Users expectedUser = new Users("jdone934", "password", "jdone934@hotmail.com",
                "Joseph", "Doney");
        Users userToUpdate = (Users) userDao.getById(1);
        userToUpdate.setFirstName(expectedUser.getFirstName());
        userDao.saveOrUpdate(userToUpdate);
        Users userAfterUpdate = (Users) userDao.getById(1);
        assertEquals(expectedUser, userAfterUpdate);
    }

    /**
     * Verify successful delete of Users
     */
    @Test
    void deleteSuccess() {
        userDao.delete(userDao.getById(3));
        assertNull(userDao.getById(3));
    }

    /**
     * Verify successful retrieval of all Userss
     */
    @Test
    void getAllSuccess() {
        List<Users> users = userDao.getAll();
        assertEquals(4, users.size());
    }

    /**
     * Verify Favorite deletion on deletion of Recipe
     */
    @Test
    void deleteFavoriteSuccess() {
        Users userToDelete = (Users) userDao.getById(1);
        userDao.delete(userToDelete);
        FavoritedRecipe fav = (FavoritedRecipe) favoriteDao.getById(1);
        assertNull(favoriteDao.getById(1));
    }



    /**
     * Verify search by property like
     */
    @Test
    void searchByLastNameLikeSuccess() {
        List<Users> users = userDao.getByPropertyLike("lastName", "doney");
        assertEquals(1, users.size());
        Users retrievedUser = users.get(0);
        Users expectedUser = (Users) userDao.getById(1);
        assertEquals(expectedUser, retrievedUser);
    }

    /**
     * Verify search by property like for username
     */
    @Test
    void searchByUsernameLikeSuccess() {
        List<Users> users = userDao.getByPropertyLike("username", "jdone934");
        assertEquals(1, users.size());
        Users retrievedUser = users.get(0);
        Users expectedUser = (Users) userDao.getById(1);
        assertEquals(expectedUser, retrievedUser);
    }

    /**
     * Verify successful retrieval of user by property equal
     */
    @Test
    void getByPropertyEqual() {
        List<Users> users = userDao.findByPropertyEqual("username", "jdone934");
        assertEquals(1, users.size());
        Users retrievedUser = users.get(0);
        Users expectedUser = (Users) userDao.getById(1);
        assertEquals(expectedUser, retrievedUser);
    }

    /**
     * Verify successful retrieval of user by property equal using map
     */
    @Test
    void getByPropertyEqualMap() {
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("username", "jdone934");
        List<Users> users = userDao.findByPropertyEqual(searchMap);
        assertEquals(1, users.size());
        Users retrievedUser = users.get(0);
        Users expectedUser = (Users) userDao.getById(1);
        assertEquals(expectedUser, retrievedUser);
    }
}
