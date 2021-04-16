package model;

/**
 * The ConcitoItemModel class implements food items available in 'Den Store Klimadatabase' from Concito.
 * The attribute category is based on Concito's data.
 * The attribute subcategory is based on Madservice Aalborgs categorizations.
 */
public class ConcitoItemModel {
    private Integer id;
    private String name;
    private Double co2PrKg;
    private String category;
    private String subcategory;

    /**
     * Empty constructor
     */
    public ConcitoItemModel() {

    }

    /**
     * Constructs a concito item with the given name, CO2 pr Kg value, category, and subcategory names
     * @param name the name of the concito item from Den Store Klimadatabase
     * @param co2PrKg CO2e in Kg pr Kg product
     * @param category name of the primary category form Den Store Klimadatabase
     * @param subcategory name of the subcategory from Madservice Aalborg's own categorization
     */
    public ConcitoItemModel(String name, Double co2PrKg, String category, String subcategory) {
        this.name = name;
        this.co2PrKg = co2PrKg;
        this.category = category;
        this.subcategory = subcategory;
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

    public Double getCo2PrKg() {
        return co2PrKg;
    }

    public void setCo2PrKg(Double co2PrKg) {
        this.co2PrKg = co2PrKg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
