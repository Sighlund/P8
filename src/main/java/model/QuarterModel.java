package model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class QuarterModel {


    // --- Properties ---
    // Primary key for the entity
    @Id
    @Column(name = "id")
    // Generates a unique value for every identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quarter;

    private YearModel year;

    private List<CalculationModel> calcList = new ArrayList<>();


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


}
