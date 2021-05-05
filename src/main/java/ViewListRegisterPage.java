import javafx.beans.property.SimpleStringProperty;

public class ViewListRegisterPage {

    private SimpleStringProperty ingredients;
    private SimpleStringProperty amountIngredient;

    public ViewListRegisterPage(String ingredients, String amountIngredient){
        this.ingredients = new SimpleStringProperty(ingredients);
        this.amountIngredient = new SimpleStringProperty(amountIngredient);
    }

    public String getIngredients() {
        return ingredients.get();
    }
}
