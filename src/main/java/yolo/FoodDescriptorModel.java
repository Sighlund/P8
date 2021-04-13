package yolo;

import javax.persistence.*;

@Entity
@Table(name = "descriptor")
public class FoodDescriptorModel {
@Id
@Column(name = "id")
@GeneratedValue(
        strategy = GenerationType.IDENTITY
)

    private Integer id;
    private String name;
    private String supplier;
    private Integer itemNumber;
    private ArrayList<Ingredient> ingredientList;
    

    public FoodDescriptorModel() {
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

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    /**
     * Method that adds ingredient to the ingredient list
     * @param ingredient the ingredient to be added
     */
    public void addIngredient(Ingredient ingredient) {
        this.ingredientList.add(ingredient);
    }

    /**
     * Method that removes ingredient from ingredient list
     * @param ingredient the ingredient to be removed
     */
    public void removeIngredient(Ingredient ingredient) {
        this.ingredientList.remove(ingredient);
    }

    /**
     * Method that returns the corrected category based on the associated ingredients
     * @return
     */
    public String getCorrectedCategory() {
        //TODO
    }

    /**
     * Method that returns the corrected subcategory based on the associated ingredients
     * @return
     */
    public String getCorrectedSubcategory() {
        //TODO
    }

}
