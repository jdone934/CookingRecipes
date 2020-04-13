package edu.matc.persistence;

import edu.matc.entity.FavoritedRecipe;
import edu.matc.entity.Ingredient;
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

public class FavoritedRecipeDaoTest {
    GenericDao dao;
    GenericDao recipeDao;
    GenericDao userDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(FavoritedRecipe.class);
        recipeDao = new GenericDao(Recipe.class);
        userDao = new GenericDao(Users.class);
    }

    /**
     * Verify successful retrieval of an Favorite
     */
    @Test
    void getByIdSuccess() {
        FavoritedRecipe retrievedFavorite = (FavoritedRecipe) dao.getById(1);
        Recipe recipe = (Recipe) recipeDao.getById(1);
        Users user = (Users) userDao.getById(1);
        FavoritedRecipe expectedFavorite = new FavoritedRecipe(user, recipe, 1);
        assertEquals(expectedFavorite, retrievedFavorite);
    }

    /**
     * Verify successful insert of an Favorite
     */
    @Test
    void insertSuccess() {
        Recipe recipe = (Recipe) recipeDao.getById(1);
        Users user = (Users) userDao.getById(2);
        FavoritedRecipe newFavorite = new FavoritedRecipe(user, recipe);
        int id = dao.insert(newFavorite);
        assertNotNull(id);
        FavoritedRecipe expectedFavorite = new FavoritedRecipe(user, recipe, 6);
        FavoritedRecipe retrievedFavorite = (FavoritedRecipe) dao.getById(id);
        assertEquals(expectedFavorite, retrievedFavorite);
    }

    /**
     * Verify successful update of an Favorite
     */
    @Test
    void updateSuccess() {
        FavoritedRecipe favoriteToUpdate = (FavoritedRecipe) dao.getById(2);
        Users newUser = (Users) userDao.getById(2);
        favoriteToUpdate.setUser(newUser);
        dao.saveOrUpdate(favoriteToUpdate);
        FavoritedRecipe expectedFavorite = new FavoritedRecipe(newUser, (Recipe) recipeDao.getById(1), 2);
        FavoritedRecipe retrievedFavorite = (FavoritedRecipe) dao.getById(2);
        assertEquals(expectedFavorite, retrievedFavorite);
    }

    /**
     * Verify successful delete of Favorite
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Verify successful retrieval of all Ingredient
     */
    @Test
    void getAllSuccess() {
        List<FavoritedRecipe> favorites = dao.getAll();
        assertEquals(5, favorites.size());
    }
}
