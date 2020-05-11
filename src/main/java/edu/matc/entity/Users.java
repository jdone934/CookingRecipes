package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a user.
 *
 * @author Jacob Doney
 */
@Entity(name = "Users")
@Table(name = "users")
public class Users {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @OneToMany(mappedBy = "createdByUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Recipe> createdRecipes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<FavoritedRecipe> favoriteRecipes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public Users() {
    }


    /**
     * Instantiates a new Users.
     *
     * @param username  the user name
     * @param password  the password
     * @param email     the email
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Users(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Instantiates a new Users.
     *
     * @param username  the user name
     * @param password  the password
     * @param email     the email
     * @param firstName the first name
     * @param lastName  the last name
     * @param id        the id
     */
    public Users(String username, String password, String email, String firstName, String lastName, int id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets user name.
     *
     * @param username the user name
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets recipes created by user.
     *
     * @return the created recipes
     */
    public Set<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    /**
     * Sets recipes created by user.
     *
     * @param createdRecipes the created recipes
     */
    public void setCreatedRecipes(Set<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }

    /**
     * Gets favorite recipes.
     *
     * @return the favorite recipes
     */
    public Set<FavoritedRecipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    /**
     * Sets favorite recipes.
     *
     * @param favoriteRecipes the favorite recipes
     */
    public void setFavoriteRecipes(Set<FavoritedRecipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return username.equals(users.username) &&
                password.equals(users.password) &&
                email.equals(users.email) &&
                Objects.equals(firstName, users.firstName) &&
                Objects.equals(lastName, users.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, firstName, lastName);
    }
}