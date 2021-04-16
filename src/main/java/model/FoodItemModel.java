package model;

import java.util.ArrayList;

/**
 * The class FoodItemModel implements purchased food items at Madservice Aalborg.
 * A food item is always associated with a food descriptor which holds information about the product.
 * The food item itself holds information about its volume in kg.
 */
public class FoodItemModel {
    private Integer id;
    private Double volume;
    private FoodDescriptorModel foodDescriptor;

    /**
     * Empty constructor
     */
    public FoodItemModel() {
        //TODO
    }

    /**
     * Constructs a FoodItemModel object with given volume and food descriptor
     * @param volume amount of purchased product in Kg
     * @param foodDescriptor associated food descriptor object
     */
    public FoodItemModel(Double volume, FoodDescriptorModel foodDescriptor) {
        this.volume = volume;
        this.foodDescriptor = foodDescriptor;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public FoodDescriptorModel getFoodDescriptor() {
        return foodDescriptor;
    }

    public void setFoodDescriptor(FoodDescriptorModel foodDescriptor) {
        this.foodDescriptor = foodDescriptor;
    }

    /**
     * Method that calculates total CO2 emission for the food item using a weighted CO2-pr-kg
     * based on the ingredients in the item
     * @return total CO2 in kg
     */
    public double calcCo2() {
        double total = 0;
        double percentage;
        double co2PrKg;

        // Get list of ingredients from the associated food descriptor object
        ArrayList<IngredientModel> ingredients = foodDescriptor.getIngredientList();

        // Iterate over ingredients in list to calculate weighted CO2 and add to total
        for (int i = 0; i < ingredients.size(); i++) {
            percentage = (ingredients.get(i).getPercentage() / 100);
            co2PrKg = ingredients.get(i).getContoItem().getCo2PrKg();
            total += volume*percentage*co2PrKg;
        }

        return total;
    }

    public String getCategory() {
        //TODO
        String category = "";
        return category;
    }

    public String getSubcategory() {
        //TODO
        String subcategory = "";
        return subcategory;
    }
}
