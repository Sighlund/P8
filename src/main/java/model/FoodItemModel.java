package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class FoodItemModel implements purchased food items at Madservice Aalborg.
 * A food item is always associated with a food descriptor which holds information about the product.
 * The food item itself holds information about its volume in kg.
 *
 * The class is mapped using Hibernate JPA.
 * For more information, see https://docs.jboss.org/hibernate/stable/annotations/reference/en/html/entity.html#entity-mapping)
 */

// Maps the class as an entity to the table 'fooditem' in the database
@Entity
@Table(name = "fooditem")
public class FoodItemModel {

    // --- Properties ---
    // Primary key for the entity
    @Id
    @Column(name = "id")
    // Generates a unique value for every identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double volume;

    // Maps a many-to-one relation between foodItem and foodDescriptor using 'foodDescriptorId' as foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodDescriptorId", referencedColumnName = "id")
    private FoodDescriptorModel foodDescriptor;

    // Maps a many-to-one relation between foodItem and calculation using 'calculationId' as foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calculationId", referencedColumnName = "id")
    private CalculationModel calculation;


    // --- Constructors ----
    /**
     * Empty constructor
     */
    public FoodItemModel() {

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


    // --- Getters and setters ---
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

    public CalculationModel getCalculation() {
        return calculation;
    }

    public void setCalculation(CalculationModel calculation) {
        this.calculation = calculation;
    }


    // --- Instance methods ---
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
        List<IngredientModel> ingredients = foodDescriptor.getIngredientList();

        // Iterate over ingredients in list to calculate weighted CO2 and add to total
        for (int i = 0; i < ingredients.size(); i++) {
            percentage = (ingredients.get(i).getPercentage() / 100);
            co2PrKg = ingredients.get(i).getContoItem().getCo2PrKg();
            total += volume*percentage*co2PrKg;
        }

        return total;
    }

    /**
     * Method that returns the corrected category for the food item.
     * The corrected category is determined by the main ingredient in the associated food descriptor.
     * @return the name of the primary category for the food item
     */
    public String getCategory() {
        return foodDescriptor.getCorrectedCategory();
    }

    /**
     * Method that returns the corrected subcategory for the food item.
     * The corrected subcategory is determined by the main ingredient in the associated food descriptor.
     * @return the name of the subcategory for the food item
     */
    public String getSubcategory() {
        return foodDescriptor.getCorrectedSubcategory();
    }

    /**
     * Method that returns the name of the food item based on the descriptor name
     * @return the name of the food item
     */
    public String getName() {
        return foodDescriptor.getName();
    }

    /**
     * Method that calculates CO2 pr Kg for the food item
     * @return CO2 pr kg product, in kg
     */
    public double calcCo2PrKg() {
        return (calcCo2() / volume);
    }


}
