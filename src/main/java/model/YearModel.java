package model;

import javax.persistence.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer year) {
        this.id = id;
    }

}
