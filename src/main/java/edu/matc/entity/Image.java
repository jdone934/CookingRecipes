package edu.matc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The type Image.
 */
@Entity(name = "Image")
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @NotNull
    private String filepath;

    @OneToOne
    @JoinColumn(name="recipe_id")
    @JsonIgnore
    private Recipe recipe;

    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="instruction_id")
    @JsonIgnore
    private Instruction instruction;

    /**
     * Instantiates a new Image.
     */
    public Image() {
    }

    /**
     * Instantiates a new Image.
     *
     * @param filepath the filepath
     */
    public Image(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Instantiates a new Image.
     *
     * @param filepath the filepath
     * @param recipe   the recipe
     */
    public Image(String filepath, Recipe recipe) {
        this.filepath = filepath;
        this.recipe = recipe;
    }

    /**
     * Instantiates a new Image.
     *
     * @param filepath the filepath
     * @param recipe   the recipe
     * @param id       the id
     */
    public Image(String filepath, Recipe recipe, int id) {
        this.filepath = filepath;
        this.recipe = recipe;
        this.id = id;
    }

    /**
     * Instantiates a new Image.
     *
     * @param filepath    the filepath
     * @param instruction the instruction
     */
    public Image(String filepath, Instruction instruction) {
        this.filepath = filepath;
        this.instruction = instruction;
    }

    /**
     * Instantiates a new Image.
     *
     * @param filepath    the filepath
     * @param instruction the instruction
     * @param id          the id
     */
    public Image(String filepath, Instruction instruction, int id) {
        this.filepath = filepath;
        this.instruction = instruction;
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
     * Gets filepath.
     *
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Sets filepath.
     *
     * @param filepath the filepath
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
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
     * Gets instruction.
     *
     * @return the instruction
     */
    public Instruction getInstruction() {
        return instruction;
    }

    /**
     * Sets instruction.
     *
     * @param instruction the instruction
     */
    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public boolean equals(Object o) {
        int thisForeignKeyId = 0;
        int compareForeignKeyId = 0;

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;

        if (recipe != null) {
            thisForeignKeyId = recipe.getId();
            compareForeignKeyId = image.recipe.getId();
        } else if (instruction != null) {
            thisForeignKeyId = instruction.getId();
            compareForeignKeyId = image.instruction.getId();
        }

        return id == image.id &&
                filepath.equals(image.filepath) &&
                thisForeignKeyId == compareForeignKeyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filepath, recipe, instruction);
    }
}
