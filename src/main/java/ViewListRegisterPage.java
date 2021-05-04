import javafx.beans.property.SimpleStringProperty;

public class ViewListRegisterPage {

    private SimpleStringProperty ingredients;
    private Double amountIngredient;

    public ViewListRegisterPage(String ingredients, Double amountIngredient){
        this.ingredients = new SimpleStringProperty(ingredients);
        this.amountIngredient = amountIngredient;
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

    public Double getAmountIngredient() {
        return amountIngredient;
    }

    public void setAmountIngredient(Double amountIngredient) {
        this.amountIngredient = amountIngredient;
    }


//    public void basicErrorPopup(String errorMessage){
//        //must create a basic popup informing user of problem. Must display the error message.
//        System.out.println(errorMessage);
//        //App.basicErrorPopup("Navnet på varen findes ikke i databasen. Tjek at navnet er korrekt");
//    }
}
