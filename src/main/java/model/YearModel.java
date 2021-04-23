package model;

import javax.persistence.*;
import java.util.List;

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

    private Integer year;

    private List<QuarterModel> quarterList;


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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<QuarterModel> getQuarterList() {
        return quarterList;
    }

    public void setQuarterList(List<QuarterModel> quarterList) {
        this.quarterList = quarterList;
    }


    // --- Instance methods ---

}
