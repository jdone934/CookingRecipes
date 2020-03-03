package edu.matc.entity;

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
    private String description;

    /**
     * Instantiates a new Image.
     */
    public Image() {
    }

    /**
     * Instantiates a new Image.
     *
     * @param filepath    the filepath
     * @param description the description
     */
    public Image(String filepath, String description) {
        this.filepath = filepath;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(filepath, image.filepath) &&
                Objects.equals(description, image.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filepath, description);
    }
}
