//Video references: https://youtu.be/uh5R7D_vFto and https://youtu.be/LQlwTIayyl4

import javafx.beans.property.SimpleStringProperty;

//TODO kommentarer
public class ViewListItemDataInsertionPage {

    private SimpleStringProperty productName;
    private SimpleStringProperty primaryGroup;
    private SimpleStringProperty secondaryGroup;
    private Double volumeOfProduct;
    private Double co2prkiloValue;
    private Double totalCo2ForItem;

    public ViewListItemDataInsertionPage(String productName,
                                         String primaryGroup,
                                         String secondaryGroup,
                                         Double volumeOfProduct,
                                         Double co2prkiloValue,
                                         Double totalCo2ForItem) {
        this.productName = new SimpleStringProperty(productName);
        this.primaryGroup = new SimpleStringProperty(primaryGroup);
        this.secondaryGroup = new SimpleStringProperty(secondaryGroup);
        //The Math.round is used to round the property values to 2 decimals.
        //Here is how it works:
            // Double value = 12345.67891111
            // value*100.0 = 1234567.891111
            //Math.round(1234567.891111) = 1234568.000000 -> 1234568 (round to nearest value)
            //1234568 / 100 = 12345.68
        this.volumeOfProduct = Math.round(volumeOfProduct * 100.0) / 100.0;
        this.co2prkiloValue = Math.round(co2prkiloValue * 100.0) / 100.0;
        this.totalCo2ForItem = Math.round(totalCo2ForItem * 100.0) / 100.0;
    }


    //TODO overvej at rydde op i disse, så de der ikke bruges fjernes.
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

    public Double getCo2prkiloValue() {
        return co2prkiloValue;
    }

    public void setCo2prkiloValue(Double co2prkiloValue) {
        this.co2prkiloValue = co2prkiloValue;
    }

    public Double getTotalCo2ForItem() {
        return totalCo2ForItem;
    }

    public void setTotalCo2ForItem(Double totalCo2ForItem) {
        this.totalCo2ForItem = totalCo2ForItem;
    }
}
