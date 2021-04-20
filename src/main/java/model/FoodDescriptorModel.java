package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The FoodDescriptorModel class implements food descriptors that stores generic information about purchased food items.
 * A FoodDescriptorModel object can be associated with a number of food items.
 * It also contains a number of ingredients which are based on elements from 'Den Store Klimadatabase'.
 */


@Entity
@Table(name = "fooddescriptor")
public class FoodDescriptorModel {
 @Id
@Column(name = "id")
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
)
    private Integer id;
    private String name;
    private String supplier;
    @Column(name = "number")
    private Integer itemNumber;
    @OneToMany
    @JoinColumn(name = "foodDescriptorId", referencedColumnName = "id")
    private List<IngredientModel> ingredientList = new ArrayList<>();
    // TODO - sum ingredient percentages must always equal 100 - skal måske varetages i controller lag?

    /**
     * Empty constructor
     */
    public FoodDescriptorModel() {

    }

    /**
     * Constructs a food descriptor with the given name, supplier name, item number, and associated list of ingredients
     * @param name the name of the food product the descriptor describes
     * @param supplier the name of the supplier from which the food product comes
     * @param itemNumber the supplier item number for the product
     * @param ingredientList the list of ingredients the product is made up of
     */
    public FoodDescriptorModel(String name, String supplier, Integer itemNumber, ArrayList<IngredientModel> ingredientList) {
        this.name = name;
        this.supplier = supplier;
        this.itemNumber = itemNumber;
        this.ingredientList = ingredientList;
    }


    /**
     * Constructs a food descriptor with the given name and list of associated ingredients.
     * Supplier and item number are set to null
     * @param name the name of the food product the descriptor describes
     * @param ingredientList the list of ingredients the product is made up of
     */
    public FoodDescriptorModel(String name, ArrayList<IngredientModel> ingredientList) {
        this(name, null, null, ingredientList);
    }

    /**
     * Constructs a food descriptor with the given name
     * Supplier, item number, and list of ingredients are set to null
     * @param name the name of the food product the descriptor describes
     */
    public FoodDescriptorModel(String name) {
        this(name, null, null, new ArrayList<IngredientModel>());
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public List<IngredientModel> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<IngredientModel> ingredientList) {
        this.ingredientList = ingredientList;
    }

    /**
     * Method that adds ingredient to the ingredient list
     * @param ingredient the ingredient to be added
     */
    public void addIngredient(IngredientModel ingredient) {
        this.ingredientList.add(ingredient);
    }


    /**
     * Method that removes ingredient from ingredient list
     * @param ingredient the ingredient to be removed
     */
    public void removeIngredient(IngredientModel ingredient) {
        this.ingredientList.remove(ingredient);
    }


    /**
     * Method that returns the corrected category based on the associated ingredients.
     * The main ingredient (highest percentage) for the food descriptor will determine the category.
     * @return the category for the main ingredient in the food descriptor
     */
    public String getCorrectedCategory() {
        IngredientModel mainIngredient = findMainIngredient();
        String correctedCategory = "";

        // Get category of main ingredient
        if (mainIngredient != null) {
            correctedCategory = mainIngredient.getContoItem().getCategory();
        }

        // Return the corrected category
        return correctedCategory;
    }

    /**
     * Method that returns the corrected subcategory based on the associated ingredients.
     * The main ingredient will determine the category.
     * If no subcategory, "Ikke angivet" is returned.
     * @return the subcategory for the main ingredient in the food descriptor, "Ikke angivet" if no subcategory
     */
    public String getCorrectedSubcategory() {
        IngredientModel mainIngredient = findMainIngredient();
        String correctedSubCategory = "Ikke angivet";

        // Get subcategory of main ingredient if subcategory is filled out
        if (mainIngredient != null) {
            if (mainIngredient.getContoItem().getSubcategory() != null) {
                correctedSubCategory = mainIngredient.getContoItem().getSubcategory();
            }
        }

        // Return the corrected subcategory
        return correctedSubCategory;
    }


    /**
     * Private method that returns the main ingredient.
     * If only one ingredient is associated, that ingredient is returned.
     * If two or more ingredients are associated, the ingredient with the highest percentage is returned.
     * If two or more ingredients with the same percentage are associated, the ingredient with the highest
     * CO2 pr Kg value is returned.
     * @return the main ingredient for the food descriptor
     */
    private IngredientModel findMainIngredient() {
        // Variable to store the main ingredient to be returned
        IngredientModel mainIngredient;

        // If only one ingredient, store ingredient to be returned
        if (ingredientList.size() == 1) {
            mainIngredient = ingredientList.get(0);
        }
        // If two or more ingredients
        else {
            // Temporary variable to store potential main ingredients
            ArrayList<IngredientModel> mainIngredients = new ArrayList<>();

            // Temporary variable to store the percentage for the ingredient(s) with the highest percentage
            double mainPercentage = 0;

            // Iterate over ingredients to find ingredient with highest percentage
            for (int i = 0; i < ingredientList.size(); i++) {
                double currentPercentage = ingredientList.get(i).getPercentage();

                // If current ingredient has a higher percentage,
                // wipe the temporary list of main ingredients and store the ingredient
                if (currentPercentage > mainPercentage) {
                    mainIngredients.clear();
                    mainIngredients.add(ingredientList.get(i));

                    // Update percentage for main ingredient
                    mainPercentage = ingredientList.get(i).getPercentage();
                }
                // Else if current ingredient percentage is equal to the percentage of the hitherto main ingredient
                // add current ingredient to list of main ingredients
                else if (currentPercentage == mainPercentage) {
                    mainIngredients.add(ingredientList.get(i));
                }
            }

            // If only one main ingredient, store ingredient to be returned
            if (mainIngredients.size() == 1) {
                mainIngredient = mainIngredients.get(0);
            }
            // Else if two or more main ingredients, find the ingredient(s) with highest CO2 pr kg value
            // If two or more have the same value, store the first
            else {
                double tempCo2PrKg = 0;
                IngredientModel tempMainIngredient = null;

                for (int i = 0; i < mainIngredients.size(); i++) {
                    if (mainIngredients.get(i).getContoItem().getCo2PrKg() >= tempCo2PrKg) {
                        // Update highest CO2 pr Kg value
                        tempCo2PrKg = mainIngredients.get(i).getContoItem().getCo2PrKg();

                        // Update temporary ingredient variable
                        tempMainIngredient = mainIngredients.get(i);
                    }
                }

                // Update main ingredient
                mainIngredient = tempMainIngredient;
            }
        }

        return mainIngredient;
    }

}
