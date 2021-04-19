package model;

import javax.persistence.*;
import java.util.ArrayList;



public class KitchenModel {

    private Integer id;
    private String name;
    //private ArrayList<CalculationModel> calcList;

    public KitchenModel() {
    }

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

    //public ArrayList<CalculationModel> getCalcList() {
      //  return calcList;
    //}

    //public void setCalcList(ArrayList<CalculationModel> calcList) {
        //this.calcList = calcList;
    //}
}
