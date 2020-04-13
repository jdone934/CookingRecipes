package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Recipe.
 */
@Entity(name = "Recipe")
@Table(name = "recipe")
public class Recipe {
    @NotNull
    private String name;
    private String description;
    private String category;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private Users createdByUser;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Instruction> recipeInstructions = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ingredient> recipeIngredients = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<FavoriteRecipesUsers> userFavorites = new HashSet<>();

    /**
     * Instantiates a new Recipe.
     */
    public Recipe() {
    }

    /**
     * Instantiates a new Recipe.
     *
     * @param name          the name
     * @param createdByUser the created by user
     */
    public Recipe(String name, Users createdByUser) {
        this.name = name;
        this.createdByUser = createdByUser;
    }

    /**
     * Instantiates a new Recipe.
     *
     * @param name          the name
     * @param createdByUser the created by user
     * @param id            the recipe id
     */
    public Recipe(String name, Users createdByUser, int id) {
        this.name = name;
        this.createdByUser = createdByUser;
        this.id = id;
    }

    /**
     * Instantiates a new Recipe.
     *
     * @param name          the name
     * @param description   the description
     * @param category      the category
     * @param createdByUser the created by user
     */
    public Recipe(String name, String description, String category, Users createdByUser) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.createdByUser = createdByUser;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets created by user.
     *
     * @return the created by user
     */
    public Users getCreatedByUser() {
        return createdByUser;
    }

    /**
     * Sets created by user.
     *
     * @param createdByUser the created by user
     */
    public void setCreatedByUser(Users createdByUser) {
        this.createdByUser = createdByUser;
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
     * Gets recipe instructions.
     *
     * @return the recipe instructions
     */
    public Set<Instruction> getRecipeInstructions() {
        return recipeInstructions;
    }

    /**
     * Sets recipe instructions.
     *
     * @param recipeInstructions the recipe instructions
     */
    public void setRecipeInstructions(Set<Instruction> recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    /**
     * Gets recipe ingredients.
     *
     * @return the recipe ingredients
     */
    public Set<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    /**
     * Sets recipe ingredients.
     *
     * @param recipeIngredients the recipe ingredients
     */
    public void setRecipeIngredients(Set<Ingredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    /**
     * Gets user favorites.
     *
     * @return the user favorites
     */
    public Set<FavoriteRecipesUsers> getUserFavorites() {
        return userFavorites;
    }

    /**
     * Sets user favorites.
     *
     * @param userFavorites the user favorites
     */
    public void setUserFavorites(Set<FavoriteRecipesUsers> userFavorites) {
        this.userFavorites = userFavorites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id &&
                name.equals(recipe.name) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(category, recipe.category) &&
                createdByUser.getId() == recipe.createdByUser.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, category, createdByUser, id);
    }
}
