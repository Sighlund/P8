import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.util.StringConverter;
import model.*;
import org.controlsfx.control.textfield.TextFields;
import persistence.*;

import javax.persistence.NoResultException;

public class DataInsertionPageController implements Initializable {

    //Configuring tableView table:
    @FXML private TableView<ViewListItemDataInsertionPage> insertionPageTableView;
    //Creating each column, telling which parent and input datatype it has.
    @FXML private TableColumn<ViewListItemDataInsertionPage, String> productNameColumn;
    @FXML private TableColumn<ViewListItemDataInsertionPage, String> primaryGroupColumn;
    @FXML private TableColumn<ViewListItemDataInsertionPage, String> secondaryGroupColumn;
    @FXML private TableColumn<ViewListItemDataInsertionPage, Double> volumeOfProductColumn;
    @FXML private TableColumn<ViewListItemDataInsertionPage, Double> co2prkiloValueColumn;
    @FXML private TableColumn<ViewListItemDataInsertionPage, Double> totalCo2ForItemColumn;

    private List<FoodItemModel> foodItemList = new ArrayList<>();

    /*
    Button that adds the chosen product to the list of items that the system must calculate.
    The button may not add item to the list before a chosen product and a weight has been provided.
     */
    public void addProductToList(){
        //Todo Kommentarer
        //TODO Error Handling: 'Tilføj vare' button cannot be pressed if these 2 conditions are not met: 1) Volume input must only take doubles. 2) AutoCompleteTextField  must not take input that doesn't exist in database. Has to inform user of specific problem.

        String productNameString = autoCompleteTextField.getText();
        Double volumeWeightInput = Double.valueOf(volumeKiloTextField.getText());
        FoodDescriptorModel foodDescriptor = FoodDescriptorPersistence.getDescriptorByName(productNameString);
        FoodItemModel f = new FoodItemModel(volumeWeightInput, foodDescriptor);
        foodItemList.add(f);
        //We get all items from the table as a list, and we add the new item to the list
        insertionPageTableView.getItems().add(new ViewListItemDataInsertionPage(
                f.getName(), f.getCategory(), f.getSubcategory(), f.getVolume(), f.calcCo2PrKg(),f.calcCo2()));
    }

    /**
     * This method collects information about a calculation from the different input fields and saves it in the DB
     * Through a cascading the foodItems are also saved in the DB
     * The calculationModel is saved in the calculation table and foodItem is saved in the foodItem table
     */
    public void createCalc(){

        ArrayList<FoodItemModel> foodItems = new ArrayList<>(foodItemList);

        CalculationModel calculation = new CalculationModel(
                choiceboxChooseQuarter.getValue(),
                choiceboxChooseYear.getValue(),
                foodItems,
                choiceboxChooseKitchen.getValue());
        //CalculationPersistence.addCalc(calculation);
    }

    //Video followed when creating autoCompleteTextField: https://www.youtube.com/watch?v=SkXYg3M0hOQ
    @FXML
    private TextField autoCompleteTextField;

    @FXML
    private TextField volumeKiloTextField;

    //Injecting related .fxml, in order to identify components in the .fxml by their ID.
    //In this case, 'choiceboxChooseKitchen' is the chosen ID of a ChoiceBox in the dataInsertionPage.fxml
    @FXML
    private ChoiceBox<KitchenModel> choiceboxChooseKitchen;
    @FXML
    private ChoiceBox<YearModel> choiceboxChooseYear;
    @FXML
    private ChoiceBox<Integer> choiceboxChooseQuarter;

    //This method initializes a controller after its root element has already been processed.
    //I think this means that this method is needed to 'update' the choiceboxes with options,
    //since the page and choiceboxes has already been loaded.
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Each ChoiceBox is filled with the created options.
        //choiceboxChooseKitchen.getItems().addAll(kitchenChoiceboxOptions.getName);
        choiceboxChooseQuarter.setItems(QuarterPersistence.listQuarter());

        choiceboxChooseYear.setItems(YearPersistence.listYear());
        choiceboxChooseYear.setConverter(YearModel.getStringConverter());
        choiceboxChooseKitchen.setItems(KitchenPersistence.listKitchen());
        choiceboxChooseKitchen.setConverter(KitchenModel.getStringConverter());

        //The autoCompleteTextField is filled with possible suggestions.
        //The list of suggestions needs to be dynam based on the current input.
        //
        //TODO
        TextFields.bindAutoCompletion(autoCompleteTextField, getFoodDescriptorNames());

        //TableView stuff goes here
        //TODO
        productNameColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("productName"));
        primaryGroupColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("primaryGroup"));
        secondaryGroupColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("secondaryGroup"));
        volumeOfProductColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("volumeOfProduct"));
        co2prkiloValueColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("co2prkiloValue"));
        totalCo2ForItemColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("totalCo2ForItem"));

        insertionPageTableView.setItems(getItemsForList());
    }

    public ObservableList<ViewListItemDataInsertionPage> getItemsForList(){
        ObservableList<ViewListItemDataInsertionPage> itemList = FXCollections.observableArrayList();
        return itemList;
    }

    //This method prints the selected values of the choiceboxes as a concatenated String.
    //This method shoulod be updated, so that it can be called to find the selected choices,
    //and pass these along to the model.
    //TODO
    public void getSelectedValuesOfChoiceBoxes(){
        String[] selectedValueOfChoiceBoxes =
                {choiceboxChooseKitchen.getSelectionModel().getSelectedItem().getName() + " "
                        + choiceboxChooseYear.getSelectionModel().getSelectedItem().getId() + " "
                + choiceboxChooseQuarter.getValue()};
        System.out.println(Arrays.toString(selectedValueOfChoiceBoxes));
    }

    public List<String> getFoodDescriptorNames(){
//        ArrayList<String> list = new ArrayList<String>();
//        for (int i = 0; i < FoodDescriptorPersistence.listDescriptor().size(); i++) {
//            list.add(FoodDescriptorPersistence.listDescriptor().get(i).getName());
//        }
        List<String> list = FoodDescriptorPersistence.listDescriptorName();
        return list;
    }


    //TODO Slet denne method? tror ikke den bruges til noget
    //This method can be used to get the input value of the volume text field.
    public void getSelectedValueOfVolumeKiloTextField(){
        //make if statement, that if the input contains anything else than numbers,
        //give an error and don't allow method to continue.
        double valueOfVolumeInput = Double.parseDouble(volumeKiloTextField.getText());
        System.out.println(valueOfVolumeInput);
    }

    //Methods being called when clicking the 'Tilføj vare' button in the system
    public void addProductToListMethodCalls(ActionEvent e){
        try {
        addProductToList();
        getSelectedValueOfVolumeKiloTextField();
        autoCompleteTextField.clear();
        volumeKiloTextField.clear();
        }
        //Catches exception caused when name doesn't match anything in database when running what is in the 'try'
        catch (NoResultException exception){
            System.out.println(exception);
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Navnet på varen blev ikke fundet i databasen. Tjek at navnet er korrekt");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }
        //This catches every other type of exception. In this case, we only expect the 'angiv kg' field to be problematic.
        catch (Exception exception){
            System.out.println(exception);
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Feltet 'angiv kg' må kun indholde tal, og kommatal skal bruge '.' i stedet for ','");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }

    }


    // Attribute to hold the secondary stage for the "Registrer ny vare" window
    private Stage registerNewPStage;

    // TODO - @Bjørn, kopierer vi stadig content fra den hjemmeside?
    /**
     * Event handler for the button "Registrer ny vare"
     * Opens a modal window to enter details about the product and save in database
     *
     * Contents copied from: https://www.codota.com/code/java/methods/javafx.stage.Stage/initModality
     * Last visited: April 22th 2021.
     * @param event action event from button element
     */
    public void openRegisterNewProductOverlay(ActionEvent event) {
        // If register new product window hasn't been opened before
        if (registerNewPStage == null) {
            // Create new stage, set scene with fxml root, set title
            Stage stage = new Stage();
            stage.setScene(new Scene(App.getRegisterNewProductPageParent()));
            stage.setTitle("Registrer en ny vare");

            // Add icon to stage
            Image icon = new Image("https://github.com/Sighlund/P8/blob/main/src/main/resources/img/Logo.PNG?raw=true");
            stage.getIcons().add(icon);

            // Set stage to be a modal window //TODO - @Bjørn, hvad gør det her egentlig?
            stage.initModality(Modality.WINDOW_MODAL);
            //stage.initOwner(((Node)event.getSource()).getScene().getWindow() ); //This one 'locks' the user to the window, so they can't click elsewhere.

            // Update reference to the stage
            registerNewPStage = stage;
        }

        // Show stage
        registerNewPStage.show();
    }


    /**
     * Event handler for the button "Udregn".
     * Switches to the calculation page.
     * @param event action event from button element
     */
    public void switchToCalculationPage(ActionEvent event){
        //TODO Error Handling: button must check to see if user has chosen kitchen, year and quarter,
        // and the list of items must not be empty.
        createCalc();
        App.switchScene(App.getCalculationPageParent());
    }

    /**
     * Event handler for the buttons "Tilbage" and "Start".
     * Switches to the front page
     * @param event action event from button element
     */
    public void switchToFrontMenuPage(ActionEvent event){
        App.switchScene(App.getFrontPageParent());
    }



    //TODO: 'Ryd Felter' Button must prompt user to confirm or cancel their choice.

}
