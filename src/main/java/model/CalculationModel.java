package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private LocalDate dateFrom;

    private LocalDate dateTo;

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
     * @param dateFrom the start date for the time period
     * @param dateTo the end date for the time period
     * @param foodItemList the list of purchased food items for the calculation
     * @param kitchen the associated kitchen for the calculation
     */
    public CalculationModel(LocalDate dateFrom,
                            LocalDate dateTo,
                            Integer quarter,
                            YearModel year,
                            ArrayList<FoodItemModel> foodItemList,
                            KitchenModel kitchen) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.quarter = quarter;
        this.year = year;
        this.foodItemList = foodItemList;
        this.kitchen = kitchen;
    }

    public CalculationModel(Integer quarter,
                            YearModel year,
                            ArrayList<FoodItemModel> foodItemList,
                            KitchenModel kitchen){
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
