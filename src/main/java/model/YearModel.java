package model;

import javafx.util.StringConverter;

import javax.persistence.*;

/**
 * The YearModel class implements years. A calculation is always linked to a specific year.
 *
 * The class is mapped using Hibernate JPA.
 * For more information, see https://docs.jboss.org/hibernate/stable/annotations/reference/en/html/entity.html#entity-mapping
 */

// Maps the class as an entity to the table 'year' in the database
@Entity
@Table(name = "year")
public class YearModel {

    // --- Properties ---
    // Primary key for the entity
    @Id
    @Column(name = "id")
    // Generates a unique value for every identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // --- Constructors ----

    /**
     * Empty constructor
     */
    public YearModel() {
    }

    // --- Getters and setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer year) {
        this.id = id;
    }

    // --- Static methods ---

    /**
     * Returns a StringConverter object that converts a year object to a
     * String representing the year (id) - i.e. "2020".
     * @return a StringConverter object
     */
    public static StringConverter<YearModel> getStringConverter() {
        // Create instance of StringConverter and declare class anonymously to implement abstract methods
        StringConverter<YearModel> stringConverter = new StringConverter<YearModel>() {
            @Override
            public String toString(YearModel year) {
                if(year != null) {
                    return year.getId().toString();
                }
                return "";
            }

            @Override
            public YearModel fromString(String s) {
                return null;
            }
        };

        // Return stringConverter
        return stringConverter;
    }

}
