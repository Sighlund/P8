package model;

import javax.persistence.*;

/**
 * The IngredientModel class implements ingredients based on elements from 'Den Store Klimadatabase' represented by
 * the ConcitoItemModel class.
 * An ingredient holds information about the associated concito element as well as percentage amount.
 */
@Entity
@Table(name = "ingredient")
public class IngredientModel {
    @Id
    @Column
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private Double percentage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concitoItemId", referencedColumnName = "id")
    private ConcitoItemModel contoItem;

    /**
     * Empty constructor
     */
    public IngredientModel() {

    }

    /**
     * Constructs an ingredient with the given amount in percentage and associated concitoItem
     * @param percentage amount of the concito item in percentage
     * @param contoItem the associated concito item
     */
    public IngredientModel( Double percentage, ConcitoItemModel contoItem) {
        this.percentage = percentage;
        this.contoItem = contoItem;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
