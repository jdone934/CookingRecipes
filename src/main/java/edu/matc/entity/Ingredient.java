package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.ws.rs.Consumes;
import java.util.Objects;

/**
 * The type Ingredient.
 */
@Entity(name = "Ingredient")
@Table(name = "ingredient")
public class Ingredient {

    private String name;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    @Column(name = "quantity_numerator")
    private int quantityNumerator;

    @Column(name = "quantity_denominator")
    private int quantityDenominator;

    @ManyToOne
    private Recipe recipe;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    /**
     * Instantiates a new Ingredient.
     */
    public Ingredient() {
    }

    /**
     * Instantiates a new Ingredient.
     *
     * @param name                the name
     * @param unitOfMeasurement   the unit of measurement
     * @param quantityNumerator   the quantity numerator
     * @param quantityDenominator the quantity denominator
     * @param recipe              the recipe
     */
    public Ingredient(String name, String unitOfMeasurement, int quantityNumerator, int quantityDenominator, Recipe recipe) {
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.quantityNumerator = quantityNumerator;
        this.quantityDenominator = quantityDenominator;
        this.recipe = recipe;
    }

    /**
     * Instantiates a new Ingredient.
     *
     * @param name                the name
     * @param unitOfMeasurement   the unit of measurement
     * @param quantityNumerator   the quantity numerator
     * @param quantityDenominator the quantity denominator
     * @param recipe              the recipe
     * @param id                  the id
     */
    public Ingredient(String name, String unitOfMeasurement, int quantityNumerator, int quantityDenominator, Recipe recipe, int id) {
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.quantityNumerator = quantityNumerator;
        this.quantityDenominator = quantityDenominator;
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
     * Gets unit of measurement.
     *
     * @return the unit of measurement
     */
    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    /**
     * Sets unit of measurement.
     *
     * @param unitOfMeasurement the unit of measurement
     */
    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }


    /**
     * Gets quantity numerator.
     *
     * @return the quantity numerator
     */
    public int getQuantityNumerator() {
        return quantityNumerator;
    }

    /**
     * Sets quantity numerator.
     *
     * @param quantityNumerator the quantity numerator
     */
    public void setQuantityNumerator(int quantityNumerator) {
        this.quantityNumerator = quantityNumerator;
    }


    /**
     * Gets quantity denominator.
     *
     * @return the quantity denominator
     */
    public int getQuantityDenominator() {
        return quantityDenominator;
    }

    /**
     * Sets quantity denominator.
     *
     * @param quantityDenominator the quantity denominator
     */
    public void setQuantityDenominator(int quantityDenominator) {
        this.quantityDenominator = quantityDenominator;
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
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                ", quantityNumerator=" + quantityNumerator +
                ", quantityDenominator=" + quantityDenominator +
                ", recipe=" + recipe +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return quantityNumerator == that.quantityNumerator &&
                quantityDenominator == that.quantityDenominator &&
                id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(unitOfMeasurement, that.unitOfMeasurement) &&
                recipe.getId() == that.recipe.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unitOfMeasurement, quantityNumerator, quantityDenominator, recipe, id);
    }
}
