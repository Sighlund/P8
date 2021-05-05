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

    //ids for tableview
    @FXML private TableView<CalculationModel> tableView;

    @FXML private TableColumn<CalculationModel, KitchenModel> kitchen;
    @FXML private TableColumn<CalculationModel, YearModel> year;
    @FXML private TableColumn<CalculationModel, Integer> quarter;

    @FXML private ChoiceBox<KitchenModel> choiceboxChooseKitchenHis;
    @FXML private ChoiceBox<YearModel> choiceboxChooseYearHis;
    @FXML private ChoiceBox<Integer> choiceboxChooseQuarterHis;

    // Attribute to hold list of calculations to be displayed in table view
    private ObservableList<CalculationModel> calcList;

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

    }

    private ObservableList<CalculationModel> selectedCalcs;

    //Gets information from selected row
    public void getSelected(MouseEvent event){
        this.selectedCalcs = tableView.getSelectionModel().getSelectedItems();
    }

    //TODO - kommentarer og cleanup (Alle)
    //Sends calculationid to CalculationPageController
    @FXML
    public void sendToCalculationComparisonPageControllerAction(ActionEvent event) throws IOException {
        App.getComparisonController().getInformation(selectedCalcs);
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

    public void clearChosenFilters(){
        //TODO Lav knap som fjerner de valgte filtre, så choiceboxene ikke har noget selected.
        // Skal også genindlæse calcs og vise i viewTable.
    }

    //Button 'Tilføj ->' used for adding the chosen calculation to the table of chosenCalcs
    //TODO Anne
    public void addCalcToChosenTable(){

    }

    //Button '<- Fjern' used for removing the chosen calculation from the table of chosenCalcs
    //TODO Anne
    public void removeCalcFromChosenTable(){

    }

    }



