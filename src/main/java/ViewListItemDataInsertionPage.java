//Video references: https://youtu.be/uh5R7D_vFto and https://youtu.be/LQlwTIayyl4

import javafx.beans.property.SimpleStringProperty;

public class ViewListItemDataInsertionPage {

    private SimpleStringProperty productName;
    private SimpleStringProperty primaryGroup;
    private SimpleStringProperty secondaryGroup;
    private Double volumeOfProduct;
    private Double co2prkiloValue;
    private Double totalCo2ForItem;

    //Constructor making each item for the viewlist, based on the users input
    //TODO
    //Complete the constructor by making it return an object that has all its properties filled.
    public ViewListItemDataInsertionPage(String productName, Double volumeOfProduct) {
        this.productName = new SimpleStringProperty(productName); //The user inputs a value for this.
        //this.primaryGroup = new SimpleStringProperty(primaryGroup); //Has to be found in the database.
        //this.secondaryGroup = new SimpleStringProperty(secondaryGroup); //Has to be found in the database.
        this.volumeOfProduct = volumeOfProduct;  //The user inputs a value for this.
        //this.co2prkiloValue = co2prkiloValue;   //Has to be found in the database.
        //this.totalCo2ForItem = totalCo2ForItem; //Has to be calculated based on volume and co2prkilovalue of the item.
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public Double getVolumeOfProduct() {
        return volumeOfProduct;
    }

    public void setVolumeOfProduct(Double volumeOfProduct) {
        this.volumeOfProduct = volumeOfProduct;
    }
}
