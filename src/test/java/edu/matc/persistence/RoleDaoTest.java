package edu.matc.persistence;

import edu.matc.entity.FavoritedRecipe;
import edu.matc.entity.Role;
import edu.matc.entity.Users;
import edu.matc.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoleDaoTest {
    GenericDao roleDao;
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

        roleDao = new GenericDao(Role.class);
        userDao = new GenericDao(Users.class);
    }

    /**
     * Verify successful retrieval of a Role
     */
    @Test
    void getByIdSuccess() {
        Role retrievedRole = (Role) roleDao.getById(1);
        Users user = (Users) userDao.getById(1);
        Role expectedRole = new Role(user, "admin", "jdone934", 1);

        assertEquals(expectedRole, retrievedRole);
    }

    /**
     * Verify successful insert of a Role
     */
    @Test
    void insertSuccess() {
        Users user = (Users) userDao.getById(2);
        Role newRole = new Role(user, "user", "lktucker", 2);
        int id = roleDao.insert(newRole);

        assertNotEquals(0,id);
        Role insertedRole = (Role) roleDao.getById(id);
        assertEquals(newRole, insertedRole);
    }

    /**
     * Verify successful update of a Role
     */
    @Test
    void updateSuccess() {
        Users user = (Users) userDao.getById(1);
        Role expectedRole = new Role(user, "user", "jdone934", 1);

        Role roleToUpdate = (Role) roleDao.getById(1);
        roleToUpdate.setRoleName(expectedRole.getRoleName());
        roleDao.saveOrUpdate(roleToUpdate);
        Role roleAfterUpdate = (Role) roleDao.getById(1);
        assertEquals(expectedRole, roleAfterUpdate);
    }

    /**
     * Verify successful delete of Role
     */
    @Test
    void deleteSuccess() {
        roleDao.delete(roleDao.getById(1));
        assertNull(roleDao.getById(1));
    }

    /**
     * Verify successful retrieval of all Role
     */
    @Test
    void getAllSuccess() {
        List<Role> roles = roleDao.getAll();
        assertEquals(1, roles.size());
    }
}
