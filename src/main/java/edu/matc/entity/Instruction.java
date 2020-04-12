package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The type Instruction
 *
 * @author Jacob Doney
 */
@Entity(name = "Instruction")
@Table(name = "instruction")
public class Instruction {
    @NotNull
    private int recipeRank;
    @NotNull
    private String description;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    private Recipe recipe;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    /**
     * Instantiates a new Instruction.
     */
    public Instruction() {

    }

    /**
     * Instantiates a new Instruction.
     *
     * @param recipeRank        the recipeRank
     * @param description the description
     * @param recipe      the recipe
     */
    public Instruction(int recipeRank, String description, Recipe recipe) {
        this.recipeRank = recipeRank;
        this.description = description;
        this.recipe = recipe;
    }

    /**
     * Instantiates a new Instruction.
     *
     * @param recipeRank        the recipeRank
     * @param description the description
     * @param recipe      the recipe
     * @param id          the id
     */
    public Instruction(int recipeRank, String description, Recipe recipe, int id) {
        this.recipeRank = recipeRank;
        this.description = description;
        this.recipe = recipe;
        this.id = id;
    }

    /**
     * Instantiates a new Instruction.
     *
     * @param recipeRank        the recipeRank
     * @param description the description
     * @param image     the image
     * @param recipe      the recipe
     */
    public Instruction(int recipeRank, String description, Image image, Recipe recipe) {
        this.recipeRank = recipeRank;
        this.description = description;
        this.image = image;
        this.recipe = recipe;
    }

    /**
     * Instantiates a new Instruction.
     *
     * @param recipeRank        the recipeRank
     * @param description the description
     * @param image     the image
     * @param recipe      the recipe
     * @param id          the id
     */
    public Instruction(int recipeRank, String description, Image image, Recipe recipe, int id) {
        this.recipeRank = recipeRank;
        this.description = description;
        this.image = image;
        this.recipe = recipe;
        this.id = id;
    }

    /**
     * Gets recipeRank.
     *
     * @return the recipeRank
     */
    public int getRecipeRank() {
        return recipeRank;
    }

    /**
     * Sets recipeRank.
     *
     * @param recipeRank the recipeRank
     */
    public void setRecipeRank(int recipeRank) {
        this.recipeRank = recipeRank;
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
     * Gets image id.
     *
     * @return the image id
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets image id.
     *
     * @param image the image id
     */
    public void setImage(Image image) {
        this.image = image;
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

    @Override
    public String toString() {
        return "Instruction{" +
                "recipeRank=" + recipeRank +
                ", description='" + description + '\'' +
                ", imageId=" + image +
                ", recipeId=" + recipe +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return recipeRank == that.recipeRank &&
                image == that.image &&
                recipe.getId() == that.recipe.getId() &&
                id == that.id &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeRank, description, image, recipe, id);
    }
}
