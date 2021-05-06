import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javassist.expr.Instanceof;
import model.CalculationModel;
import model.FoodItemModel;
import model.KitchenModel;
import model.YearModel;
import persistence.CalculationPersistence;
import persistence.KitchenPersistence;
import persistence.QuarterPersistence;
import persistence.YearPersistence;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    // Filter choiceboxes
    @FXML private ChoiceBox<KitchenModel> choiceboxChooseKitchenHis;
    @FXML private ChoiceBox<YearModel> choiceboxChooseYearHis;
    @FXML private ChoiceBox<Integer> choiceboxChooseQuarterHis;

    // Table view with available calculations (left)
    @FXML private TableView<CalculationModel> tableView;
    @FXML private TableColumn<CalculationModel, YearModel> year;
    @FXML private TableColumn<CalculationModel, Integer> quarter;
    @FXML private TableColumn<CalculationModel, KitchenModel> kitchen;

    // Table view with selected calculations for comparison (right)
    @FXML private TableView<CalculationModel> tableViewRight;
    @FXML private TableColumn<CalculationModel, KitchenModel> kitchenRight;
    @FXML private TableColumn<CalculationModel, YearModel> yearRight;
    @FXML private TableColumn<CalculationModel, Integer> quarterRight;

    // Attribute to hold list of calculations to be displayed in table view
    private ObservableList<CalculationModel> calcList;


    //TODO Bjørn: Jeg har forsøgt at lave en variable uden for metodernes scope, som skulle holde
    // på en ObservableList<CalculationModel> med alle calcs i systemet. Jeg kunne ikke få det til at virke.
    // Tror enten det var pga. listcalc() er static, og måske fordi ObservableList der returneres, har listeners på sig.
    // Jeg forsøgte at caste skidtet frem og tilbage på alle mulige måder, og benytte mig af andre typer af ArrayList og List.
    //Attribute to hold list of all calculations from database. By having it be an attribute, we only need to load once.
    //private ObservableList<CalculationModel> allCalcsList = CalculationPersistence.listCalc();

    // Attribute to hold list of selected calculations from the left table view
    private ObservableList<CalculationModel> selectedCalcs;

    // Attribute to hold list of calculations in RIGHT table view
    // this list is sent to the comparison page
    private ObservableList<CalculationModel> calcsForComp = FXCollections.observableArrayList(new ArrayList<>());

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Build choice boxes for filtering available calculations
        buildFilterChoiceboxes();

        // Builds the left table view, displaying available calculations
        buildLeftTableView();

        // Builds right table view, displaying the calculations chosen for comparison
        buildRightTableView();
    }

    /**
     * Builds choice boxes for filtering available calculations in the left table view
     */
    private void buildFilterChoiceboxes(){
        choiceboxChooseQuarterHis.setItems(QuarterPersistence.listQuarter());
        choiceboxChooseYearHis.setItems(YearPersistence.listYear());
        choiceboxChooseYearHis.setConverter(YearModel.getStringConverter());
        choiceboxChooseKitchenHis.setItems(KitchenPersistence.listKitchen());
        choiceboxChooseKitchenHis.setConverter(KitchenModel.getStringConverter());
    }

    /**
     * Builds left table view, displaying available calculations
     */
    private void buildLeftTableView(){
        // Set cell value factories for all columns in the table view.
        // Uses a PropertyValueFactory object with the parameterized type CalculationModel
        // and the corresponding property type (i.e Integer).
        // The name of the property in CalculationModel is passed as an argument to the PropertyValueFactory as a String
        year.setCellValueFactory(new PropertyValueFactory<CalculationModel, YearModel>("year"));
        quarter.setCellValueFactory(new PropertyValueFactory<CalculationModel, Integer>("quarter"));
        kitchen.setCellValueFactory(new PropertyValueFactory<CalculationModel, KitchenModel>("kitchen"));

        // Set items of the table view by passing the observable list of calculations
        tableView.setItems(calcList);

        // Set selection mode of the table view to accept multiple selected rows
        tableView.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
    }

    /**
     * Builds right table view, displaying chosen calculations for comparison
     */
    private void buildRightTableView(){
        // Set cell value factories each column
        yearRight.setCellValueFactory(new PropertyValueFactory<CalculationModel, YearModel>("year"));
        quarterRight.setCellValueFactory(new PropertyValueFactory<CalculationModel, Integer>("quarter"));
        kitchenRight.setCellValueFactory(new PropertyValueFactory<CalculationModel, KitchenModel>("kitchen"));

        // Set placeholder for RIGHT table view
        tableViewRight.setPlaceholder(new Text("Tilføj beregninger fra listen til venstre for at sammenligne"));

        // Set list of items for right table view to display
        tableViewRight.setItems(calcsForComp);

        // Set selection model to accept multiple selected rows/objects
        tableViewRight.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Updates the table view
     * Gets called from the front page
     */
    public void updateTableViewAllCalcs(){
        // Update reference to list of all calculations
        calcList = CalculationPersistence.listCalc();
        // Update tableview by passing list of calculations
        tableView.setItems(calcList);
    }

    /**
     * Event handler for left table view
     * Updates list property for currently selected calculations
     * @param event
     */
    public void getSelected(MouseEvent event){
        this.selectedCalcs = tableView.getSelectionModel().getSelectedItems();
    }

    /**
     * Event handler for button "Sammenlign"
     * Sends list of calculations to compare and switches to comparison page
     * @param event
     * @throws IOException
     */
    @FXML
    public void sendToCalculationComparisonPageControllerAction(ActionEvent event) throws IOException {
        App.getComparisonController().getInformation(calcsForComp);
        App.switchScene(App.getComparisonParent());
    }

    /**
     * Event handler for the button "Menu".
     * Switches to the front page.
     * @param event action event from the button element
     */
    public void switchToSceneFrontPage(ActionEvent event){
        App.switchScene(App.getFrontPageParent());
    }



    //Button 'Anvend Filtre' used for applying the chosen filters from the choiceBoxes.
    public void applyChosenFilters(){
        //Gets all calcs from database and stores them in a temporary list.
        ObservableList<CalculationModel> tempCalcList = CalculationPersistence.listCalc();

        //Since we cannot iterate over the tempCalcList and remove items from it as we go, we have to store
        //the objects we want to remove in another temporary list 'toRemoveList' for now.
        //The objects that will be stored in this list, will be removed from tempCalcList after the 'if'-statements below.
        List<CalculationModel> toRemoveList = new ArrayList<>();

        //If the choicebox has any value selected, the if statement is true.
        if (choiceboxChooseKitchenHis.getValue() != null){
            //For every CalculationModel object in our tempCalcList (which contains all calcs saved in the system):
            for (CalculationModel specificCalc: tempCalcList){
                //we check if the selected value of the choicebox DOES NOT match the specific objects kitchen name.
                if (!choiceboxChooseKitchenHis.getValue().toString().equals(specificCalc.getKitchen().getName())){
                    //If it doesn't match, we add the specific object to the toRemoveList, so that we can remove it later.
                    toRemoveList.add(specificCalc);
                }
            }
        }

        //This if statement is much like the above. There are slight differences
        // in the things we check the equality of, since the data types and model methods vary a bit.
        if (choiceboxChooseYearHis.getValue() != null){
            System.out.println(choiceboxChooseYearHis.getValue().getClass());
            for (CalculationModel specificCalc: tempCalcList){
                if (!choiceboxChooseYearHis.getValue().getId().equals(specificCalc.getYear().getId())){
                    System.out.println(tempCalcList);
                    toRemoveList.add(specificCalc);
                    System.out.println(toRemoveList);
                }
            }
        }

        //This if statement is much like the above. There are slight differences
        // in the things we check the equality of, since the data types and model methods vary a bit.
        if (choiceboxChooseQuarterHis.getValue() != null){
            for (CalculationModel specificCalc: tempCalcList){
                //If each specific calcs getKitchen().getName from tempCalcList does not match value of what is in the choiceboxChooseKitchenHis
                if (!choiceboxChooseQuarterHis.getValue().equals(specificCalc.getQuarter())){
                    System.out.println(tempCalcList);
                    toRemoveList.add(specificCalc);
                    System.out.println(toRemoveList);
                }
            }
        }

        //Removes from tempCalcList all of the elements that are contained in the specified collection toRemoveList
        tempCalcList.removeAll(toRemoveList);
        //We set the tableview to show the contents of our now filtered list.
        tableView.setItems(tempCalcList);
    }

    /**
     * Method that will clear the current chosen filters and reload the viewtable with all the calcs in the system.
     */
    public void clearChosenFilters(){
        //Clears the choiceBoxes
        choiceboxChooseKitchenHis.getSelectionModel().clearSelection();
        choiceboxChooseYearHis.getSelectionModel().clearSelection();
        choiceboxChooseQuarterHis.getSelectionModel().clearSelection();
        //Updates the tableview to hold all calcs in system.
        updateTableViewAllCalcs();
    }


    /**
     * Event handler for button "Tilføj"
     * Adds all selected calculations from the left table view to the right table view
     */
    public void addCalcToChosenTable(){
        // Add all selected calculations from table view
        // to list of calculations in table view (chosen)
        calcsForComp.addAll(selectedCalcs);

        //If the list of chosen calculations contains more than 4 calculations,
        //popup with error message is shown, and the calculations are removed from the list again.
        if (calcsForComp.size() > 4){
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            errorHandlingCollection.basicErrorPopup("For mange beregninger", "Der må højst være 4 beregninger i listen over udvalgte beregninger.");
            errorHandlingCollection = null;
            calcsForComp.removeAll(selectedCalcs);
        }
    }

    /**
     * Event handler for button "Fjern"
     * Removes all selected calculations from the right table view
     */
    public void removeCalcFromChosenTable(){
        // Get list of selected calculations to be removed
        ObservableList<CalculationModel> calcs = tableViewRight.getSelectionModel().getSelectedItems();

        // Remove all calculations in the list
        calcsForComp.removeAll(calcs);
    }

}



