//Video references: https://youtu.be/uh5R7D_vFto and https://youtu.be/LQlwTIayyl4

import javafx.beans.property.SimpleStringProperty;

/**
 * Class used to create the objects that will be used to populate our viewList
 */
public class ViewListItemDataInsertionPage {

    private SimpleStringProperty productName;
    private SimpleStringProperty primaryGroup;
    private SimpleStringProperty secondaryGroup;
    private SimpleStringProperty volumeOfProduct;
    private SimpleStringProperty co2prkiloValue;
    private SimpleStringProperty totalCo2ForItem;

    //Constructor called every time user presses 'Tilføj vare' button.
    public ViewListItemDataInsertionPage(String productName,
                                         String primaryGroup,
                                         String secondaryGroup,
                                         String volumeOfProduct,
                                         String co2prkiloValue,
                                         String totalCo2ForItem) {
        this.productName = new SimpleStringProperty(productName);
        this.primaryGroup = new SimpleStringProperty(primaryGroup);
        this.secondaryGroup = new SimpleStringProperty(secondaryGroup);
        this.volumeOfProduct = new SimpleStringProperty(volumeOfProduct);
        this.co2prkiloValue = new SimpleStringProperty(co2prkiloValue);
        this.totalCo2ForItem = new SimpleStringProperty(totalCo2ForItem);
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

    public String getPrimaryGroup() {
        return primaryGroup.get();
    }

    public SimpleStringProperty primaryGroupProperty() {
        return primaryGroup;
    }

    public void setPrimaryGroup(String primaryGroup) {
        this.primaryGroup.set(primaryGroup);
    }

    public String getSecondaryGroup() {
        return secondaryGroup.get();
    }

    public SimpleStringProperty secondaryGroupProperty() {
        return secondaryGroup;
    }

    public void setSecondaryGroup(String secondaryGroup) {
        this.secondaryGroup.set(secondaryGroup);
    }

    public String getVolumeOfProduct() {
        return volumeOfProduct.get();
    }

    public SimpleStringProperty volumeOfProductProperty() {
        return volumeOfProduct;
    }

    public void setVolumeOfProduct(String volumeOfProduct) {
        this.volumeOfProduct.set(volumeOfProduct);
    }

    public String getCo2prkiloValue() {
        return co2prkiloValue.get();
    }

    public SimpleStringProperty co2prkiloValueProperty() {
        return co2prkiloValue;
    }

    public void setCo2prkiloValue(String co2prkiloValue) {
        this.co2prkiloValue.set(co2prkiloValue);
    }

    public String getTotalCo2ForItem() {
        return totalCo2ForItem.get();
    }

    public SimpleStringProperty totalCo2ForItemProperty() {
        return totalCo2ForItem;
    }

    public void setTotalCo2ForItem(String totalCo2ForItem) {
        this.totalCo2ForItem.set(totalCo2ForItem);
    }
}
