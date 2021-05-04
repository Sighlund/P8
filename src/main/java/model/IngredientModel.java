package model;

import javax.persistence.*;

/**
 * The IngredientModel class implements ingredients based on elements from 'Den Store Klimadatabase' represented by
 * the ConcitoItemModel class.
 * An ingredient holds information about the associated concito element as well as percentage amount.
 *
 * The class is mapped using Hibernate JPA.
 * For more information, see https://docs.jboss.org/hibernate/stable/annotations/reference/en/html/entity.html#entity-mapping
 */

// Maps the class as an entity to the table 'ingredient' in the database
@Entity
@Table(name = "ingredient")
public class IngredientModel {

    // --- Properties ---
    // Primary key for the entity
    @Id
    @Column
    // Generates a unique value for every identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double percentage;

    // Maps a many-to-one relation between ingredient and concitoItem using 'concitoItemId' as foreign key
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concitoItemId", referencedColumnName = "id")
    private ConcitoItemModel contoItem;

    // Maps a many-to-one relation between ingredient and foodDescriptor using 'foodDescriptorId' as foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodDescriptorId", referencedColumnName = "id")
    private FoodDescriptorModel foodDescriptor;


    // --- Constructors ----
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
    public IngredientModel(Double percentage, ConcitoItemModel contoItem) {
        this.percentage = percentage;
        this.contoItem = contoItem;
    }


    // --- Getters and setters ---

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

    public FoodDescriptorModel getFoodDescriptor() {
        return foodDescriptor;
    }

    public void setFoodDescriptor(FoodDescriptorModel foodDescriptor) {
        this.foodDescriptor = foodDescriptor;
    }
}
