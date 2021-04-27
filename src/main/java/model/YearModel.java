package model;

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

}
