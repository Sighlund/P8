package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Maps the class as an entity to the table 'quarter' in the database
@Entity
@Table(name = "quarter")
public class QuarterModel {

    // --- Properties ---
    // Primary key for the entity
    @Id
    @Column(name = "id")
    // Generates a unique value for every identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quarter;

    // Maps a many-to-one relation between quarter and year using 'yearId' as foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yearId", referencedColumnName = "id")
    private YearModel year;

    // Maps a one-to-many relation between quarter and calculation using 'quarterId' as foreign key
    // Cascades all Hibernate actions from the quarter entity to its related calculations
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "quarterId", referencedColumnName = "id")
    private List<CalculationModel> calcList = new ArrayList<>(); //TODO: er new arraylist nødvendigt her?


    // --- Constructors ----
    /**
     * Empty constructor
     */
    public QuarterModel() {
    }


    // --- Getters and setters ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public YearModel getYear() {
        return year;
    }

    public void setYear(YearModel year) {
        this.year = year;
    }

    public List<CalculationModel> getCalcList() {
        return calcList;
    }

    public void setCalcList(List<CalculationModel> calcList) {
        this.calcList = calcList;
    }


    // --- Instance methods ---
    /**
     * Method that adds the given calculation to the quarter's list of
     * associated calculations
     * @param calc calculation object to be added
     */
    public void addCalc(CalculationModel calc) {
        if (calc != null) {
            calcList.add(calc);
        }
    }

    /**
     * Method that removes the given calculation from the quarter's list
     * of associated calculations
     * @param calc the calcualation to be removed
     */
    public void removeCalc(CalculationModel calc) {
        if (calc != null) {
            calcList.remove(calc);
        }
    }


}
