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
        // Update list of displayed calculations by fetching all calculations from the database
        calcList = CalculationPersistence.listCalc();

        choiceboxChooseQuarterHis.setItems(QuarterPersistence.listQuarter());
        choiceboxChooseYearHis.setItems(YearPersistence.listYear());
        choiceboxChooseYearHis.setConverter(YearModel.getStringConverter());
        choiceboxChooseKitchenHis.setItems(KitchenPersistence.listKitchen());
        choiceboxChooseKitchenHis.setConverter(KitchenModel.getStringConverter());

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

        // Set cell value factories for RIGHT table view
        yearRight.setCellValueFactory(new PropertyValueFactory<CalculationModel, YearModel>("year"));
        quarterRight.setCellValueFactory(new PropertyValueFactory<CalculationModel, Integer>("quarter"));
        kitchenRight.setCellValueFactory(new PropertyValueFactory<CalculationModel, KitchenModel>("kitchen"));

        // Set placeholder for RIGHT table view
        tableViewRight.setPlaceholder(new Text("Tilføj beregninger fra listen til venstre for at sammenligne"));

        tableViewRight.setItems(calcsForComp);

        tableViewRight.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }



    //Gets information from selected row
    public void getSelected(MouseEvent event){
        this.selectedCalcs = tableView.getSelectionModel().getSelectedItems();
    }

    //TODO - kommentarer og cleanup (Alle)
    //Sends calculationid to CalculationPageController
    @FXML
    public void sendToCalculationComparisonPageControllerAction(ActionEvent event) throws IOException {
        App.getComparisonController().getInformation(calcsForComp);
        App.switchScene(App.getComparisonParent());
    }


    /**
     * Updates the table view
     */
    public void updateTableView(){
        // Update reference to list of all calculations
        calcList = CalculationPersistence.listCalc();

        // Update tableview by passing list of calculations
        tableView.setItems(calcList);
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


    public void addCalcToChosenTable(){
        // Add all selected calculations from table view
        // to list of calculations in table view (chosen)
        calcsForComp.addAll(selectedCalcs);

    }

    //Button '<- Fjern' used for removing the chosen calculation from the table of chosenCalcs
    //TODO Anne
    public void removeCalcFromChosenTable(){
        ObservableList<CalculationModel> calcs = tableViewRight.getSelectionModel().getSelectedItems();

        calcsForComp.removeAll(calcs);
    }

}



