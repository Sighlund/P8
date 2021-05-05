import javafx.beans.property.SimpleStringProperty;

public class ViewListRegisterPage {

    private SimpleStringProperty ingredients;
    private SimpleStringProperty amountIngredient;

    public ViewListRegisterPage(String ingredients, String amountIngredient){
        this.ingredients = new SimpleStringProperty(ingredients);
        this.amountIngredient = new SimpleStringProperty(amountIngredient);
    }

    //TODO overvej at rydde op i disse, så de der ikke bruges fjernes.
    public String getIngredients() {
        return ingredients.get();
    }

    public SimpleStringProperty ingredientsProperty() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients.set(ingredients);
    }

    public String getAmountIngredient() {
        return amountIngredient.get();
    }

    public SimpleStringProperty AmountIngredientProperty() {
        return amountIngredient;
    }

    public void setAmountIngredient(String amountIngredient) {
        this.amountIngredient.set(amountIngredient);
    }
}
