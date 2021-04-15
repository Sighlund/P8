package model;

/**
 * The IngredientModel class implements ingredients based on elements from 'Den Store Klimadatabase' represented by
 * the ConcitoItemModel class.
 * An ingredient holds information about the associated concito element as well as percentage amount.
 */
public class IngredientModel {
    private Integer concitoId;
    private Double percentage;
    private ConcitoItemModel contoItem;

    public IngredientModel() {
        //TODO
    }

    // Getters and setters
    public Integer getConcitoId() {
        return concitoId;
    }

    public void setConcitoId(Integer concitoId) {
        this.concitoId = concitoId;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public ConcitoItemModel getContoItem() {
        return contoItem;
    }

    public void setContoItem(ConcitoItemModel contoItem) {
        this.contoItem = contoItem;
    }
}
