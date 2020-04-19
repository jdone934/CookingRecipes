package edu.matc.entity;

import org.apache.logging.log4j.core.config.Order;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

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
    @OrderBy("recipeRank ASC")
    private Set<Instruction> instructions = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<FavoritedRecipe> userFavorites = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    private Image image;

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(Image image) {
        this.image = image;
    }

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
    public Set<Instruction> getInstructions() {
        return instructions;
    }

    /**
     * Sets recipe instructions.
     *
     * @param instructions the recipe instructions
     */
    public void setInstructions(Set<Instruction> instructions) {
        this.instructions = instructions;
    }

    /**
     * Gets recipe ingredients.
     *
     * @return the recipe ingredients
     */
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Sets recipe ingredients.
     *
     * @param ingredients the recipe ingredients
     */
    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets user favorites.
     *
     * @return the user favorites
     */
    public Set<FavoritedRecipe> getUserFavorites() {
        return userFavorites;
    }

    /**
     * Sets user favorites.
     *
     * @param userFavorites the user favorites
     */
    public void setUserFavorites(Set<FavoritedRecipe> userFavorites) {
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
