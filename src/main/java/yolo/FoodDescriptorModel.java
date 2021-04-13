package yolo;

import javax.persistence.*;
import java.util.ArrayList;

/***
@Entity
@Table(name = "descriptor")
*/

public class FoodDescriptorModel {
/***
 @Id
@Column(name = "id")

    @GeneratedValue(
        strategy = GenerationType.IDENTITY
)
 */

    private Integer id;
    private String name;
    private String supplier;
    private Integer itemNumber;
    private ArrayList<IngredientModel> ingredientList;
    

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

    public ArrayList<IngredientModel> getIngredientList() {
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
     * Method that returns the corrected category based on the associated ingredients
     * @return
     */
    public String getCorrectedCategory() {
        //TODO
        String category = "";
        return category;
    }

    /**
     * Method that returns the corrected subcategory based on the associated ingredients
     * @return
     */
    public String getCorrectedSubcategory() {
        //TODO
        String subcategory = "";
        return subcategory;
    }

}
