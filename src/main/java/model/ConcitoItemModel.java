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

    public ConcitoItemModel() {
        //TODO
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
