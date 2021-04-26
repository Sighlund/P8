//This jar needs to be put in libs https://nexus.gluonhq.com/nexus/content/repositories/releases/com/gluonhq/charm-glisten/6.0.6/

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

import model.KitchenModel;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import persistence.FoodDescriptorPersistence;
import persistence.KitchenPersistence;
import persistence.SetupPersistence;

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




    /*
    Button that adds the chosen product to the list of items that the system must calculate.
    The button may not add item to the list before a chosen product and a weight has been provided.
     */
    public void addProductToList(){
        System.out.println("this button must add product to list");
        //Todo;
        String productNameString = autoCompleteTextField.getText();
        Double volumeWeightInput = Double.valueOf(volumeKiloTextField.getText());
        ViewListItemDataInsertionPage newItemForList = new ViewListItemDataInsertionPage(productNameString,
                                                                                         volumeWeightInput); //ADD THE REST OF VALUES AS WELL //TODO
        //We get all items from the table as a list, and we add the new item to the list
        insertionPageTableView.getItems().add(newItemForList);

    }

    //Video followed when creating autoCompleteTextField: https://www.youtube.com/watch?v=SkXYg3M0hOQ
    @FXML private TextField autoCompleteTextField;

    //private AutoCompletionBinding<String> autoCompleteTextFieldBindingString;
    //private String[] _listOfSuggestionsForAutoCompleteTextField = {"Gamer","Gaamer","GAMER","gamer","stor Gamer","Gaaaaamer","","Gamerrrr"};
    //private Set<String> listOfSuggestionsForAutoCompleteTextField = new HashSet<>(Arrays.asList(_listOfSuggestionsForAutoCompleteTextField));

    @FXML private TextField volumeKiloTextField;

    //Injecting related .fxml, in order to identify components in the .fxml by their ID.
    //In this case, 'choiceboxChooseKitchen' is the chosen ID of a ChoiceBox in the dataInsertionPage.fxml
    @FXML private ChoiceBox<String> choiceboxChooseKitchen;
    @FXML private ChoiceBox<String> choiceboxChooseYear;
    @FXML private ChoiceBox<String> choiceboxChooseQuarter;

    //Creating arrays of Strings that will contain the 'options' for each ChoiceBox.
    //The suggestions needs to be dynamically created based on the related objects
    //TODO



    //private KitchenModel kitchenChoiceboxOptions = new KitchenModel("Gug", 1);
    private String[] yearChoiceboxOptions = {"one", "two", "three"};
    private String[] quarterChoiceboxOptions = {"1", "2", "3"};
    private String[] kitchenChoiceboxOptions = {"1", "2", "3"};

    //KitchenPersistence.ListKitchens()

    //This method initializes a controller after its root element has already been processed.
    //I think this means that this method is needed to 'update' the choiceboxes with options,
    //since the page and choiceboxes has already been loaded.
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Each ChoiceBox is filled with the created options.
        //choiceboxChooseKitchen.getItems().addAll(kitchenChoiceboxOptions.getName);
        choiceboxChooseYear.getItems().addAll(yearChoiceboxOptions);
        choiceboxChooseQuarter.getItems().addAll(quarterChoiceboxOptions);
        choiceboxChooseKitchen.getItems().addAll(kitchenChoiceboxOptions);

        //The autoCompleteTextField is filled with possible suggestions.
        //The list of suggestions needs to be dynam based on the current input.
        TextFields.bindAutoCompletion(autoCompleteTextField, getFoodDescriptorNames());


        //TableView stuff goes here
        //TODO
        productNameColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("productName"));
        //primaryGroupColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("primaryGroup"));
        //secondaryGroupColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("secondaryGroup"));
        volumeOfProductColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("volumeOfProduct"));  //Does this have to be 'String'?
        //co2prkiloValueColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("co2prkiloValue"));   //Does this have to be 'String'?
        //totalCo2ForItemColumn.setCellValueFactory(new PropertyValueFactory<ViewListItemDataInsertionPage, String>("totalCo2ForItem"));  //Does this have to be 'String'?

        insertionPageTableView.setItems(getItemsForList());

    }

        public ObservableList<ViewListItemDataInsertionPage> getItemsForList(){
            ObservableList<ViewListItemDataInsertionPage> itemList = FXCollections.observableArrayList();
            itemList.add(new ViewListItemDataInsertionPage("Minecraftgamer", 35.0));
            itemList.add(new ViewListItemDataInsertionPage("FortniteGamer", 45.0));
            itemList.add(new ViewListItemDataInsertionPage("CSGOGamer", 65.0));
            itemList.add(new ViewListItemDataInsertionPage("WoWGamer", 150.0));

            return itemList;

        }



    //This method prints the selected values of the choiceboxes as a concatenated String.
    //This method shoulod be updated, so that it can be called to find the selected choices,
    //and pass these along to the model.
    //TODO
    public void getSelectedValuesOfChoiceBoxes(){
        String[] selectedValueOfChoiceBoxes =
                {choiceboxChooseKitchen.getValue()
                + choiceboxChooseYear.getValue()
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


    //This method can be used to get the input value of the volume text field.
    public void getSelectedValueOfVolumeKiloTextField(){
        //make if statement, that if the input contains anything else than numbers,
        //give an error and don't allow method to continue.
        double valueOfVolumeInput = Double.parseDouble(volumeKiloTextField.getText());
        System.out.println(valueOfVolumeInput);
    }

    //Methods being called when clicking the 'Tilføj vare' button in the system
    public void addProductToListMethodCalls(ActionEvent e){
        addProductToList();
        getSelectedValueOfVolumeKiloTextField();
    }



    private Stage stage;
    private Scene scene;
    private Parent root;


    /*
    This method is called when 'Register new product button' is pressed.
    The method must open the registerNewProductPage.fxml in a new window.
    Contents copied from: https://www.codota.com/code/java/methods/javafx.stage.Stage/initModality
    Last visited: April 22th 2021.
     */
    public void openRegisterNewProductOverlay(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("registerNewProductPage.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Registrer en ny vare");
        Image icon = new Image("https://github.com/Sighlund/P8/blob/main/src/main/resources/img/Logo.PNG?raw=true");
        stage.getIcons().add(icon);
        stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner(((Node)event.getSource()).getScene().getWindow() ); //This one 'locks' the user to the window, so they can't click elsewhere.
        stage.show();
    }


    public void switchToCalculationPage(ActionEvent event) throws IOException {
        getSelectedValuesOfChoiceBoxes(); //Simply prints the currently selected values of the ChoiceBoxes.
        root = FXMLLoader.load(getClass().getResource("calculationPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFrontMenuPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("frontPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
