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
    @FXML
    private ChoiceBox<KitchenModel> choiceboxChooseKitchenHis;
    @FXML
    private ChoiceBox<YearModel> choiceboxChooseYearHis;
    @FXML
    private ChoiceBox<Integer> choiceboxChooseQuarterHis;

    // Table view with available calculations (left)
    @FXML
    private TableView<CalculationModel> tableView;
    @FXML
    private TableColumn<CalculationModel, YearModel> year;
    @FXML
    private TableColumn<CalculationModel, Integer> quarter;
    @FXML
    private TableColumn<CalculationModel, KitchenModel> kitchen;

    // Table view with selected calculations for comparison (right)
    @FXML
    private TableView<CalculationModel> tableViewRight;
    @FXML
    private TableColumn<CalculationModel, KitchenModel> kitchenRight;
    @FXML
    private TableColumn<CalculationModel, YearModel> yearRight;
    @FXML
    private TableColumn<CalculationModel, Integer> quarterRight;


    // Attribute to hold list of calculations to be displayed in left table view
    private ObservableList<CalculationModel> calcList;

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
    public void updateTableView(){
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


    //TODO Bjørn
    //Button 'Anvend Filtre' used for applying the chosen filters from the choiceBoxes.
    public void applyChosenFilters(){
        //Printing this for fun. Remove later.
        System.out.println(choiceboxChooseKitchenHis.getValue());
        System.out.println(choiceboxChooseQuarterHis.getValue());
        System.out.println(choiceboxChooseYearHis.getValue());


    //TODO: - Knappen skal:
        //		- Genindlæse hele listen
        //		- Fjerne alle som ikke opfylder:
        //			- Hvis køkken er valgt, fjern alle som ikke har dette køkken.
        //			- Hvis år er valgt, fjern alle som ikke har dette år.
        //			- Hvis Periode er valgt, fjern alle som ikke har denne periode.
    }


    /**
     * Event handler for button "Tilføj"
     * Adds all selected calculations from the left table view to the right table view
     */
    public void addCalcToChosenTable(){
        // Add all selected calculations from table view
        // to list of calculations in table view (chosen)
        calcsForComp.addAll(selectedCalcs);

        if (calcsForComp.size() > 4){
            // TODO throw exception, der giver fejlmeddelse om,
            //  at der maks må tilføjes 4 calculations til tabellen (vi har ikke plads til mere på comparison)
            //  Bjørn/Mads/Søren? :D
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



