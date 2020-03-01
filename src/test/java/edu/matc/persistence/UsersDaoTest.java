package edu.matc.persistence;

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

public class UsersDaoTest {
    UsersDao dao;

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

        dao = new UsersDao();
    }

    /**
     * Verify successful retrieval of a Users
     */
    @Test
    void getByIdSuccess() {
        Users retrievedUser = dao.getById(1);
        Users expectedUser = new Users("jdone934", "AWESOME PASSWORD", "jdone934@hotmail.com",
                            "Jacob", "Doney", "admin");

        assertEquals(expectedUser, retrievedUser);
    }

    /**
     * Verify successful insert of a Users
     */
    @Test
    void insertSuccess() {
        Users newUser = new Users("myhero34", "IM A SAILOR", "sdoney@gmail.com", "Sam", "Doney", "user");
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        Users insertedUser = dao.getById(id);
        assertEquals(newUser, insertedUser);
    }

    /**
     * Verify successful update of a Users
     */
    @Test
    void updateSuccess() {
        Users expectedUser = new Users("jdone934", "AWESOME PASSWORD", "jdone934@hotmail.com",
                "Joseph", "Doney", "admin");
        Users userToUpdate = dao.getById(1);
        userToUpdate.setFirstName(expectedUser.getFirstName());
        dao.saveOrUpdate(userToUpdate);
        Users userAfterUpdate = dao.getById(1);
        assertEquals(expectedUser, userAfterUpdate);
    }

    /**
     * Verify successful delete of Users
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Verify successful retrieval of all Userss
     */
    @Test
    void getAllSuccess() {
        List<Users> users = dao.getAll();
        assertEquals(4, users.size());
    }
}
