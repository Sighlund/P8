package model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.Collections;
import java.util.Set;


/***
 * The CalculationModel class implements the model layer of Calculation.
 * A CaluclationModel object represents a collection of purchased food items.
 * A calculation is always associated to a quarter as well as holds references to a year and a kitchen.
 * The volume and total CO2 can be calculated from the Calculation object.
 *
 * The class is mapped using Hibernate JPA.
 * For more information, see https://docs.jboss.org/hibernate/stable/annotations/reference/en/html/entity.html#entity-mapping
 */

// Maps the class as an entity to the table 'calculation' in the database
@Entity
@Table(name = "calculation")
public class CalculationModel {

    // --- Properties ---
    // Primary key for the entity
    @Id
    @Column(name = "id")
    // Generates a unique value for every identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quarter;

    // Maps a many-to-one relation between calculation and year using 'year.id' as foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yearId", referencedColumnName = "id")
    private YearModel year;

    // Maps a many-to-one relation between calculation and kitchen using 'kitchenId' as foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchenId", referencedColumnName = "id")
    private KitchenModel kitchen;

    // Maps a one-to-many relation between calculation and foodItem using 'calculationId' as foreign key
    // Cascades all Hibernate actions from the calculation entity to its related foodItems
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "calculationId", referencedColumnName = "id")
    private List<FoodItemModel> foodItemList = new ArrayList<>();


    // --- Constructors ----
    /**
     * Constructs a calculation with an empty list of food items
      */
    public CalculationModel() {
        this.foodItemList = new ArrayList<>();
    }

    /**
     * Constructs a calculation with the given time period, list of food items, and associated kitchen.
     * @param foodItemList the list of purchased food items for the calculation
     * @param kitchen the associated kitchen for the calculation
     */
    public CalculationModel(Integer quarter,
                            YearModel year,
                            ArrayList<FoodItemModel> foodItemList,
                            KitchenModel kitchen) {
        this.quarter = quarter;
        this.year = year;
        this.foodItemList = foodItemList;
        this.kitchen = kitchen;
    }

    /**
     * Constructs a calculation with the given associated kitchen
     * @param kitchen the associated kitchen
     */
    public CalculationModel(KitchenModel kitchen){
        this.kitchen = kitchen;
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

    public List<FoodItemModel> getFoodItemList() {
        return foodItemList;
    }

    public void setFoodItemList(List<FoodItemModel> foodItemList) {
        this.foodItemList = foodItemList;
    }

    public KitchenModel getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenModel kitchen) {
        this.kitchen = kitchen;
    }

    // --- Instance methods ---
    /**
     * Method that calculates total CO2 by adding co2 values for all associated food items
     * If no food items are added yet, 0.0 is returned
     * @return total CO2 in kg
     */
    public Double calcTotalCo2() {
        // Create variable to hold total CO2
        Double total = 0.0;

        // Iterate over all food items to calculate individual CO2 and add to total
        for (FoodItemModel foodItemModel : this.foodItemList) {
            total += foodItemModel.calcCo2();
        }

        return total;
    }

    /**
     * Method that calculates total kg of purchased food items by adding volumes for all associated food items
     * If no food items are added yet, 0.0 is returned
     * @return total volume in kg
     */
    public Double calcTotalKg() {
        Double total = 0.0;

        // Iterate over all food items to get individual volume and add to total
        for (FoodItemModel foodItemModel : this.foodItemList) {
            total += foodItemModel.getVolume();
        }

        return total;
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
    public Double calcAveCO2prKg() {
        return (calcTotalCo2() / calcTotalKg());
    }


    /**
     * Gets hash table with all categories in the calculation and associated CO2
     * as a percentage og the total CO2 for the calculation
     * @return hash table with categories as keys and percentages as values
     */
    public Hashtable<String, Double> getCategoriesCo2PercentagesHt(){
        // Create empty hash table
        Hashtable ht = new Hashtable<String, Double>();

        // Get list of categories
        List<String> categories = getCategories();

        // Iterate over all categories
        for (int i = 0; i < categories.size(); i++){

            // Create double to hold subtotal
            Double subtotal = getCo2SubtotalForCategory(categories.get(i));

            // Add category and CO2 percentage to the hash table
            // using category as key and percentage as value
            ht.put(categories.get(i), (subtotal/calcTotalCo2()*100));
        }
        return ht;
    }


    /**
     * Gets hash table with Co2 subtotal values for all subcategories
     * in the given category
     * @return a hash table with the category as key and CO2e value in kg
     */
    public Hashtable<String, Double> getSubcategoriesCo2KgHt(String category){

        // Create empty hash table
        Hashtable ht = new Hashtable<String, Double>();

        // Get list of subcategories
        List<String> subcategories = getSubcategories(category);

        // Iterate over all subcategories
        for (String sc : subcategories){

            // Create double to hold subtotal for subcategory
            Double subtotal = getSubTotalForSubcategory(sc);

            // Add category and CO2 value to the hash table
            // using category as key and CO2 as value
            ht.put(sc, subtotal);

        }

        return ht;
    }

    /**
     * Gets subtotals for each category represented in the calculation
     * @return a hashtable with the category as key and subtotal as value
     */
    public Hashtable<String, Double> getCategoriesCo2KgHt(){
        // Create empty hash table
        Hashtable ht = new Hashtable<String, Double>();

        // Get list of categories
        List<String> categories = getCategories();

        // Iterate over all categories
        for (int i = 0; i < categories.size(); i++){

            // Create double to hold subtotal
            Double subtotal = getCo2SubtotalForCategory(categories.get(i));

            // Add category and CO2 percentage to the hash table
            // using category as key and subtotal as value
            ht.put(categories.get(i), subtotal);
        }
        return ht;
    }

    /**
     * Gets a list of Strings with all categories present in the calculation
     * @return list of category names
     */
    public List<String> getCategories(){
        // Create empty list
        List<String> list = new ArrayList<>();

        // Iterate over food items looking for categories
        for (int i = 0; i < foodItemList.size(); i++){

            // Add category to list, if it isn't already in the list
            if (!list.contains(foodItemList.get(i).getCategory())){
                list.add(foodItemList.get(i).getCategory());
            }
        }
        // Sort the list alphabetically
        Collections.sort(list);

        return list;
    }

    /**
     * Gets all subcategories for a specific category
     * @param category the category to get subcategories for
     * @return a list of subcategories
     */
    private List<String> getSubcategories(String category){
        List<String> list = new ArrayList<>();

        for (FoodItemModel f : foodItemList){
            if (category.equals(f.getCategory())){
                if (!list.contains(f.getSubcategory())){
                    list.add(f.getSubcategory());
                }
            }
        }

        return list;
    }

    /**
     * Gets the CO2 subtotal for a specific category
     * @param category the category to get the subtotal for
     * @return CO2e in kg
     */
    private Double getCo2SubtotalForCategory(String category){

        // Create double to hold temporary subtotal
        Double subtotal = 0.0;

        // Iterate over all food items
        for (int j = 0; j < foodItemList.size(); j++){

            // If category for current food item equals current category
            // add CO2 for food item to subtotal for the category
            if (foodItemList.get(j).getCategory().equals(category)){
                subtotal += foodItemList.get(j).calcCo2();
            }
        }

        return subtotal;
    }

    private Double getSubTotalForSubcategory(String subcategory){

        // Create double to hold temporary subtotal
        Double subtotal = 0.0;

        // Iterate over all food items
        for (FoodItemModel f : foodItemList){

            // If subcategory for current food item equals current subcategory
            // add CO2 for food item to subtotal for the subcategory
            if (f.getSubcategory().equals(subcategory)){
                subtotal += f.calcCo2();
            }
        }

        return subtotal;
    }


    public Hashtable<String, Double> getCategoriesVolPercentagesHt(){
        // Create empty hash table
        Hashtable ht = new Hashtable<String, Double>();

        // Get list of categories
        List<String> categories = getCategories();

        // Iterate over all categories
        for (int i = 0; i < categories.size(); i++){

            // Create double to hold subtotal
            Double subtotal = getVolSubtotalForCategory(categories.get(i));

            // Add category and CO2 percentage to the hash table
            // using category as key and subtotal as value
            ht.put(categories.get(i), subtotal/calcTotalKg()*100);
        }
        return ht;
    }

    public Hashtable<String, Double> getCategoriesVolKgHt(){
        // Create empty hash table
        Hashtable ht = new Hashtable<String, Double>();

        // Get list of categories
        List<String> categories = getCategories();

        // Iterate over all categories
        for (int i = 0; i < categories.size(); i++){

            // Create double to hold subtotal
            Double subtotal = getVolSubtotalForCategory(categories.get(i));

            // Add category and CO2 percentage to the hash table
            // using category as key and subtotal as value
            ht.put(categories.get(i), subtotal);
        }
        return ht;
    }


    private Double getVolSubtotalForCategory(String category){

        // Create double to hold temporary subtotal
        Double subtotal = 0.0;

        // Iterate over all food items
        for (int j = 0; j < foodItemList.size(); j++){

            // If category for current food item equals current category
            // add CO2 for food item to subtotal for the category
            if (foodItemList.get(j).getCategory().equals(category)){
                subtotal += foodItemList.get(j).getVolume();
            }
        }

        return subtotal;
    }



    // TODO - optimering Anne
    public Hashtable<String, Double> test(){
        Hashtable ht = getCategoriesVolKgHt();

        Set<String> keys = ht.keySet();

        for (String key : keys){
            ht.put(key, ((Double) ht.get(key) / calcTotalKg()*100));
        }

        return ht;
    }

}
