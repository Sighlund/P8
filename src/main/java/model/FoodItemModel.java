package model;

/**
 * The class FoodItemModel implements purchased food items at Madservice Aalborg.
 * A food item is always associated with a food descriptor which holds information about the product.
 * The food item itself holds information about its volume in kg.
 */
public class FoodItemModel {
    private Integer id;
    private Double volume;
    private Double co2;
    private FoodDescriptorModel foodDescriptor;

    public FoodItemModel() {
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

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public FoodDescriptorModel getFoodDescriptor() {
        return foodDescriptor;
    }

    public void setFoodDescriptor(FoodDescriptorModel foodDescriptor) {
        this.foodDescriptor = foodDescriptor;
    }

    /**
     * Method that calculates total CO2 emission for the food item
     * @return total CO2 in kg
     */
    public double calcCo2() {
        //TODO

        double co2 = 0;
        return co2;
    }
}
