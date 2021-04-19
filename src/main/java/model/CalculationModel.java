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

    /**
     * Constructs a calculation with an empty list of food items
      */
    public CalculationModel() {
        this.foodItemList = new ArrayList<>();
    }

    /**
     * Constructs a calculation with the given time period, list of food items, and associated kitchen.
     * @param dateFrom the start date for the time period
     * @param dateTo the end date for the time period
     * @param foodItemList the list of purchased food items for the calculation
     * @param kitchen the associated kitchen for the calculation
     */
    public CalculationModel(LocalDate dateFrom, LocalDate dateTo, ArrayList<FoodItemModel> foodItemList, KitchenModel kitchen) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.foodItemList = foodItemList;
        this.kitchen = kitchen;
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
     * If no food items are added yet, 0.0 is returned
     * @return total CO2 in kg
     */
    public double calcTotalCo2() {
        double total = 0.0;

        // Iterate over all food items to calculate individual CO2 and add it to total
        for (int i = 0; i < this.foodItemList.size(); i++) {
            total += this.foodItemList.get(i).calcCo2();
        }

        return total;
    }

    /**
     * Method that calculates total kg of purchased food items by adding volumes for all associated food items
     * If no food items are added yet, 0.0 is returned
     * @return total volume in kg
     */
    public double calcTotalKg() {
        double total = 0.0;

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
        //TODO - save calculation to Db. Skal nok varetages af persistence lag?
    }

    /**
     * Method that adds given food item to the list of food items
     * @param foodItem food item to be added
     */
    public void addFoodItem(FoodItemModel foodItem) {
        if (foodItem != null) {
            foodItemList.add(foodItem);
        }
    }

    /**
     * Method that removes the given food item from the calcualtion's list of food items
     * @param foodItem food item to be removed
     */
    public void removeFoodItem(FoodItemModel foodItem) {
        if (foodItem != null) {
            foodItemList.remove(foodItem);
        }
    }

    /**
     * Method that returns averaged CO2e pr Kg purchased food
     * @return average kg CO2e pr kg purchased food
     */
    public double calcAveCO2prKg() {
        return (calcTotalCo2() / calcTotalKg());
    }


}
