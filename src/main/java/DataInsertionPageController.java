import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import model.*;
import org.controlsfx.control.textfield.AutoCompletionBinding;
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
    @FXML private TableColumn<ViewListItemDataInsertionPage, String> volumeOfProductColumn;
    @FXML private TableColumn<ViewListItemDataInsertionPage, String> co2prkiloValueColumn;
    @FXML private TableColumn<ViewListItemDataInsertionPage, String> totalCo2ForItemColumn;

    // Property that holds list of food items to be stored with the calculation
    private List<FoodItemModel> foodItemList = new ArrayList<>();

    // Property that holds list of food descriptor names that can be searched for in autocompletion field
    private List<String> foodDescriptorNames = new ArrayList<>();

    // Property to hold autocompletion binding on autocomplete text field
    private AutoCompletionBinding autoCompletionBinding;

    /**
     * Method called on button press that adds the chosen product to the list of items that the system must calculate.
     */
    public void addProductToList(){
        //Make a String variable that stores the current content of the autoCompleteTextField.
        String productNameString = autoCompleteTextField.getText();

        //Initialises foodDescriptor object and makes call to persistance layer, to get the descriptor by the provided name.
        //If the provided name doesn't match, an exception is thrown. It is caught in the method that called addProductToList().
        FoodDescriptorModel foodDescriptor = FoodDescriptorPersistence.getDescriptorByName(productNameString);

        String commaConvert = volumeKiloTextField.getText().replace(',', '.');

        //Make a Double variable that stores the current content of the volumeKiloTextField.
        Double volumeWeightInput = Double.valueOf(commaConvert);

        FoodItemModel f = new FoodItemModel(volumeWeightInput, foodDescriptor);
        foodItemList.add(f);

        String volumeComma = format(f.getVolume()).replace('.', ',');
        Double co2PrKg = f.calcCo2PrKg();
        Double calcCo2 = f.calcCo2();
        String co2PrKgComma = format(co2PrKg).replace('.',',');
        String calcCo2Comma = format(calcCo2).replace('.',',');
        //We get all items from the table as a list (Because viewTable is stupid, and can't just append without getting the list first xd)
        //and we add the new item to the list
        insertionPageTableView.getItems().add(new ViewListItemDataInsertionPage(
                f.getName(), f.getCategory(), f.getSubcategory(), volumeComma, co2PrKgComma, calcCo2Comma));
    }

    private String format(Double d){
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String format = numberFormat.format(d);
        return format;
    }

    private CalculationModel calculation;

    /**
     * This method collects information about a calculation from the different input fields and saves it in the DB
     * Through a cascading the foodItems are also saved in the DB
     * The calculationModel is saved in the calculation table and foodItem is saved in the foodItem table
     */
    public void createCalc() {

        //TODO kig på det her, hvis i vil. Vi kan ikke få det til at virke(Søren og Mads)
//        Se også CalculationPersistence linje 80, hvor kaldet til DB bliver lavet
//        CalculationPersistence.getCalcFromChoicebox(
//                choiceboxChooseKitchen.getValue().getId(),
//                choiceboxChooseQuarter.getValue(),
//                choiceboxChooseYear.getValue().getId());

        ArrayList<FoodItemModel> foodItems = new ArrayList<>(foodItemList);

        CalculationModel calculation = new CalculationModel(
                choiceboxChooseQuarter.getValue(),
                choiceboxChooseYear.getValue(),
                foodItems,
                choiceboxChooseKitchen.getValue());
        //CalculationPersistence.addCalc(calculation);
        this.calculation = calculation;
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


    /**
     * This method initializes a controller after its root element has already been processed.
     * I think this means that this method is needed to keep content in the view pages updated visually.
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Each ChoiceBox is filled with the created options.
        //choiceboxChooseKitchen.getItems().addAll(kitchenChoiceboxOptions.getName);
        choiceboxChooseQuarter.setItems(QuarterPersistence.listQuarter());
        choiceboxChooseYear.setItems(YearPersistence.listYear());
        choiceboxChooseYear.setConverter(YearModel.getStringConverter());
        choiceboxChooseKitchen.setItems(KitchenPersistence.listKitchen());
        choiceboxChooseKitchen.setConverter(KitchenModel.getStringConverter());

        // Update list of possible food descriptor names
        updateFoodDescriptorNames();

        // Create autoCompletionBinding object to bind the autoCompleteTextField
        // with possible solutions (food descriptor names)
        autoCompletionBinding = TextFields.bindAutoCompletion(autoCompleteTextField, foodDescriptorNames);

        //TableView stuff goes here. Each column is told which that they are going to hold object of type PropertyValueFactory(S, T).
        //S - The type of the class contained within the TableView.items list.
        //T - The type of the class contained within the TableColumn cells.
        //The strings provided at the end of the PropertyValueFactories are references to the class properties of ViewListItemDataInsertionPage.
        //read more at: //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/cell/PropertyValueFactory.html
        productNameColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("productName"));
        primaryGroupColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("primaryGroup"));
        secondaryGroupColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("secondaryGroup"));
        volumeOfProductColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("volumeOfProduct"));
        co2prkiloValueColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("co2prkiloValue"));
        totalCo2ForItemColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("totalCo2ForItem"));

        insertionPageTableView.setPlaceholder(new Text("Tilføj varer ved at finde en passende fødevaretype i søgefeltet og angive indkøbt vægt"));
    }

    /**
     * Retrieves list of all food descriptor names from the database
     *
     * @return list of Strings with names for all food descriptors in the database
     */
    public List<String> getFoodDescriptorNames() {
        List<String> list = FoodDescriptorPersistence.listDescriptorName();
        return list;
    }

    /**
     * Auxiliary method to update list of food descriptor names
     * Is called when Register New Product window is closed
     */
    private void updateFoodDescriptorNames() {
        foodDescriptorNames = getFoodDescriptorNames();
    }

    //Methods being called when clicking the 'Tilføj vare' button in the system

    /**
     * Method is called when "Tilføj vare" button is clicked. It calls the addProductToList method,
     * clears the autoCompleteTextField and clears the volumeKiloTextField
     *
     * @param e -
     */
    public void addProductToListMethodCalls(ActionEvent e) {
        try {
            addProductToList();
            autoCompleteTextField.clear();
            volumeKiloTextField.clear();
        }
        //Catches exception caused when name doesn't match anything in database when running what is in the 'try'
        catch (NoResultException exception) {
            exception.printStackTrace();
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Navnet på varen blev ikke fundet i databasen. Tjek at navnet er korrekt");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }

        //This catches every type of exception. In this case, we only expect the 'angiv kg' field to be problematic.
        catch (Exception exception) {
            exception.printStackTrace();
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Feltet 'angiv kg' må kun indholde tal, og kommatal skal bruge '.' i stedet for ','");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }


    }

    /**
     * Removes all rows from the TableView
     *
     * @param e -
     */
    public void resetCalculationTable(ActionEvent e) {
        ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();

        if (errorHandlingCollection.confirmChoicePopup("Er du sikker på du vil rydde alt?")) {
            insertionPageTableView.getItems().clear();
            foodItemList.removeAll(foodItemList);
            choiceboxChooseKitchen.getSelectionModel().clearSelection();
            choiceboxChooseYear.getSelectionModel().clearSelection();
            choiceboxChooseQuarter.getSelectionModel().clearSelection();
        }
        errorHandlingCollection = null;
    }

    /**
     * Removes the selected row from the TableView
     *
     * @param e -
     */
    public void removeSelectedRow(ActionEvent e) {
        ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();

        try {
            if (errorHandlingCollection.confirmChoicePopup("Er du sikker på du vil fjerne denne række?")) {
                foodItemList.removeIf(n -> (n.getName().equals(insertionPageTableView.getSelectionModel().getSelectedItem().getProductName())));
                insertionPageTableView.getItems().remove(insertionPageTableView.getSelectionModel().getSelectedItem());
            }
        }
        catch (NullPointerException exception){
            System.out.println(exception + ". Exception thrown because no row was selected for deletion. It doesn't break anything. Move along.");
        }
        finally {
            errorHandlingCollection = null;
        }
    }

    // Attribute to hold the secondary stage for the "Registrer ny vare" window
    private Stage registerNewPStage;

    /**
     * Event handler for the button "Registrer ny vare"
     * Opens a modal window to enter details about the product and save in database
     * Contents based on: https://www.codota.com/code/java/methods/javafx.stage.Stage/initModality
     * Last visited: April 22th 2021.
     *
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

            //https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html#initModality-javafx.stage.Modality-
            //Set stage to have the modality of WINDOW_MODAL.
            //The stage blocks input events from being delivered to all windows from its owner (parent) to its root. Its root is the closest ancestor window without an owner.
            stage.initModality(Modality.WINDOW_MODAL);
            //initOwner specifies the owner Window for this stage. In this case we set dataInsertionPage to be the owner.
            //This one 'locks' the user to the window, so they can't click elsewhere.
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    // Fetch all food descriptor names from database and update list of food descriptors to be displayed
                    updateFoodDescriptorNames();
                    // Dispose previous binding of autoCompletionTextField
                    autoCompletionBinding.dispose();
                    // Create new binding for autoCompletionTextField with updated food descriptor names
                    autoCompletionBinding = TextFields.bindAutoCompletion(autoCompleteTextField, foodDescriptorNames);
                    // Close stage/window
                    registerNewPStage.close();
                }
            });

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
    public void switchToCalculationPage(ActionEvent event) {
        //try {
            createCalc();
            App.getCalculationController().updateCalculationView(calculation);
            App.switchScene(App.getCalculationPageParent());
            //If the calculation made it through, we update the state keeping track of whether the current calculation has been saved or not, to false.
            CalculationPageController.setCalculationSaved(false);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
//            //We call upon the method which creates a popup with the provided string.
//            errorHandlingCollection.basicErrorPopup("fejl", "Husk at vælge 'Afdeling', 'År' og 'Kvartal'");
//            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
//            errorHandlingCollection = null;
       //}
    }

        /**
         * Event handler for the buttons "Tilbage" and "Start".
         * Switches to the front page
         * @param event action event from button element
         */
        public void switchToFrontMenuPage (ActionEvent event){
            App.switchScene(App.getFrontPageParent());
        }

    }