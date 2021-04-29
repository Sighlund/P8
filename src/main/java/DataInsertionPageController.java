import javafx.application.Platform;
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
import java.net.URL;
import java.util.*;

import javafx.util.StringConverter;
import model.*;
import org.controlsfx.control.textfield.TextFields;
import persistence.*;

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
        CalculationPersistence.addCalc(calculation);
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
    //TODO lav javaDoc
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
        getFoodDescriptorNames();

        //TableView stuff goes here
        productNameColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("productName"));
        primaryGroupColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("primaryGroup"));
        secondaryGroupColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("secondaryGroup"));
        volumeOfProductColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("volumeOfProduct"));
        co2prkiloValueColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("co2prkiloValue"));
        totalCo2ForItemColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("totalCo2ForItem"));

        insertionPageTableView.setItems(getItemsForList());
    }

    //TODO Lav JavaDoc
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

    /**
     * Fetches name of all FoodDescriptors in the database. Method is called in the initialize method
     */
    public void getFoodDescriptorNames(){
        TextFields.bindAutoCompletion(autoCompleteTextField, FoodDescriptorPersistence.listDescriptorName());
    }

    /**
     * Method is called when "Tilføj vare" button is clicked. It calls the addProductToList method,
     * clears the autoCompleteTextField and clears the volumeKiloTextField
     * @param e -
     */
    public void addProductToListMethodCalls(ActionEvent e){
        addProductToList();
        autoCompleteTextField.clear();
        volumeKiloTextField.clear();
    }

    /**
     * Removes all rows from the TableView
     * @param e -
     */
    public void resetCalculationTable(ActionEvent e){
        insertionPageTableView.getItems().clear();
        foodItemList.removeAll(foodItemList);
    }

    /**
     * Removes the selected row from the TableView
     * @param e -
     */
    public void removeSelectedRow(ActionEvent e) {
        foodItemList.removeIf(n -> (n.getName().equals(insertionPageTableView.getSelectionModel().getSelectedItem().getProductName())));
        insertionPageTableView.getItems().remove(insertionPageTableView.getSelectionModel().getSelectedItem());
    }



    // Attribute to hold the secondary stage for the "Registrer ny vare" window
    private Stage registerNewPStage;

    // TODO - @Bjørn, kopierer vi stadig content fra den hjemmeside? "Ja, kopierer er måske lidt voldsomt sagt,
    //  strukturen kommer derfra" siger Bjørn
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
            //Det laver et modal vindue i.e. det åbner stage i et nyt vindue.
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



}
