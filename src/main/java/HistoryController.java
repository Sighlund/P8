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
    @FXML
    private TableView<CalculationModel> tableView;

    @FXML
    private TableColumn<CalculationModel, YearModel> year;

    @FXML
    private TableColumn<CalculationModel, Integer> quarter;

    @FXML
    private TableColumn<CalculationModel, KitchenModel> kitchen;

    @FXML
    private TableColumn<CalculationModel, Integer> id;

    @FXML
    private ChoiceBox<KitchenModel> choiceboxChooseKitchenHis;
    @FXML
    private ChoiceBox<YearModel> choiceboxChooseYearHis;
    @FXML
    private ChoiceBox<Integer> choiceboxChooseQuarterHis;

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
        id.setCellValueFactory(new PropertyValueFactory<CalculationModel, Integer>("id"));
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

    int calcid;
    //private List<FoodItemModel> calcItemList = new ArrayList<>();

    //Gets information from selected row
    public void getSelected(MouseEvent event){
        CalculationModel selected = tableView.getSelectionModel().getSelectedItem();
        this.calcid=selected.getId();
        //this.calcItemList = selected.getFoodItemList();
    }

    //TODO - kommentarer og cleanup
    //Sends calculationid to CalculationPageController
    @FXML
    public void sendToCalculationComparisonPageControllerAction(ActionEvent event) throws IOException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("comparison.fxml"));
        //Parent root = loader.load();

        //CalculationComparisonPageController calculationComparisonPageController = loader.getController();

        //calculationComparisonPageController.getInformation(calcid);
        //TODO - måske vi bare bør ændre til at den sender calc objektet og ikke id'et
        App.getComparisonController().getInformation(calcid);
        App.switchScene(App.getComparisonParent());
        /*
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        */
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
     * Event handler for the button "Start".
     * Switches to the front page.
     * @param event action event from the button element
     */
    public void switchToSceneFrontPage(ActionEvent event){
        App.switchScene(App.getFrontPageParent());
    }

    }



