package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Favorite recipes users.
 */
@Entity(name = "FavoritedRecipe")
@Table(name = "favorite_recipes_users")
public class FavoritedRecipe {

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    /**
     * Instantiates a new Favorited recipe.
     */
    public FavoritedRecipe() {
    }

    /**
     * Instantiates a new Favorited recipe.
     *
     * @param user   the user
     * @param recipe the recipe
     */
    public FavoritedRecipe(Users user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    /**
     * Instantiates a new Favorited recipe.
     *
     * @param user   the user
     * @param recipe the recipe
     * @param id     the id
     */
    public FavoritedRecipe(Users user, Recipe recipe, int id) {
        this.user = user;
        this.recipe = recipe;
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets user.
     *
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(Users user) {
        this.user = user;
    }


    /**
     * Gets recipe.
     *
     * @return the recipe
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Sets recipe.
     *
     * @param recipe the recipe
     */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "FavoritedRecipe{" +
                "user=" + user +
                ", recipe=" + recipe +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritedRecipe that = (FavoritedRecipe) o;
        return id == that.id &&
                user.getId() == that.user.getId() &&
                recipe.getId() ==that.recipe.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, recipe, id);
    }
}