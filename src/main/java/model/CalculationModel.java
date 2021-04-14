package model;

import java.time.LocalDate;
import java.util.ArrayList;

/***
 * The CalculationModel class implements the model layer of Calculation.
 * A CaluclationModel object represents a collection of purchased food items.
 * The volume and total CO2 can be calculated from the Calculation object.
 */
public class CalculationModel {
    private Integer id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private ArrayList<FoodItemModel> foodItemList;
    private KitchenModel kitchen;

    // Year, quarter, month er ikke en del af calculation endnu


    public CalculationModel() {
    }

    // Getter and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public ArrayList<FoodItemModel> getFoodItemList() {
        return foodItemList;
    }

    public void setFoodItemList(ArrayList<FoodItemModel> foodItemList) {
        this.foodItemList = foodItemList;
    }

    public KitchenModel getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenModel kitchen) {
        this.kitchen = kitchen;
    }

    /**
     * Method that calculates total CO2 by adding co2 values for all associated food items
     * @return total CO2 in kg
     */
    public double calcTotalCo2() {
        double total = 0;

        // Iterate over all food items to calculate individual CO2 and add it to total
        for (int i = 0; i < this.foodItemList.size(); i++) {
            total += this.foodItemList.get(i).calcCo2();
        }

        return total;
    }

    /**
     * Method that calculates total kg of purchased food items by adding volumens for all associated food items
     * @return total volume in kg
     */
    public double calcTotalKg() {
        double total = 0;

        // Iterate over all food items to get individual volume and add to total
        for (int i = 0; i < this.foodItemList.size(); i++) {
            total += this.foodItemList.get(i).getVolume();
        }

        return total;
    }

    /**
     * Method that saves the current calculation instance to the database
     */
    public void saveToDb() {
        //TODO
    }

    /**
     * Method that adds given food item to the list of food items
     * @param foodItem food item to be added
     */
    public void addFoodItem(FoodItemModel foodItem) {
        //TODO
    }

    /**
     * Method that removes the given food item from the calcualtion's list of food items
     * @param fooditem food item to be removed
     */
    public void removeFoodItem(FoodItemModel fooditem) {
        //TODO
    }

}
