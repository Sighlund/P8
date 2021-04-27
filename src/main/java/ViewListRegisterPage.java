import javafx.beans.property.SimpleStringProperty;

public class ViewListRegisterPage {

    private SimpleStringProperty ingredients;
    private Double amountIngredient;

    public ViewListRegisterPage(String ingredients, Double amountIngredient){
        this.ingredients = new SimpleStringProperty(ingredients);
        this.amountIngredient = amountIngredient;
    }

    public String getIngredients() {
        return ingredients.get();
    }

    public SimpleStringProperty ingredientsProperty() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients.set(ingredients);
    }

    public Double getAmountIngredient() {
        return amountIngredient;
    }

    public void setAmountIngredient(Double amountIngredient) {
        this.amountIngredient = amountIngredient;
    }
}
