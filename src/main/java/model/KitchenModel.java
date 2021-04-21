package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The KitchenModel class implements the different kitchen units at Madservice Aalborg.
 */
@Entity
@Table(name = "kitchen")
public class KitchenModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String name;
//    @OneToMany
//    @JoinColumn(name = "kitchenId", referencedColumnName = "id")
//    private List<CalculationModel> calcList;

    /**
     * Empty constructor
     */
    public KitchenModel() {

    }

    //TODO - constructor overloading mangler

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public ArrayList<CalculationModel> getCalcList() {
        return calcList;
    }

    public void setCalcList(ArrayList<CalculationModel> calcList) {
        this.calcList = calcList;
    }
    */

    //public static List<String> getString(){
        //TODO
    //}


}
