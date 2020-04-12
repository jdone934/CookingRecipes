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
    private int rank;
    @NotNull
    private String description;
    @Column(name = "image_id")
    private int imageId;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private int recipeId;

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
     * @param rank        the rank
     * @param description the description
     * @param recipeId    the recipe id
     */
    public Instruction(int rank, String description, int recipeId) {
        this.rank = rank;
        this.description = description;
        this.recipeId = recipeId;
    }

    /**
     * Instantiates a new Instruction.
     *
     * @param rank        the rank
     * @param description the description
     * @param imageId     the image id
     * @param recipeId    the recipe id
     */
    public Instruction(int rank, String description, int imageId, int recipeId) {
        this.rank = rank;
        this.description = description;
        this.imageId = imageId;
        this.recipeId = recipeId;
    }

    /**
     * Gets rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    public void setRank(int rank) {
        this.rank = rank;
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
    public int getImageId() {
        return imageId;
    }

    /**
     * Sets image id.
     *
     * @param imageId the image id
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     * Gets recipe id.
     *
     * @return the recipe id
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Sets recipe id.
     *
     * @param recipeId the recipe id
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
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
                "rank=" + rank +
                ", description='" + description + '\'' +
                ", imageId=" + imageId +
                ", recipeId=" + recipeId +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return rank == that.rank &&
                imageId == that.imageId &&
                recipeId == that.recipeId &&
                id == that.id &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, description, imageId, recipeId, id);
    }
}
