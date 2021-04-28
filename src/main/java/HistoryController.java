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
import javafx.stage.Stage;
import model.CalculationModel;
import model.KitchenModel;
import model.YearModel;
import persistence.CalculationPersistence;
import persistence.KitchenPersistence;

import java.io.IOException;
import java.net.URL;
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

   /* public ObservableList<CalculationModel> getCalculations(){
    ObservableList<CalculationModel> calculations = FXCollections.observableArrayList();
        for(int i=0; i<KitchenPersistence.listKitchen().size(); i++) {
        List<CalculationModel> list = KitchenPersistence.listKitchen().get(i).getCalcList();
            for(int j=0; j<list.size(); j++){
                calculations.add(list.get(j));
            }
        }
        return calculations;
    }

    */

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<CalculationModel> list = CalculationPersistence.listCalc();

        id.setCellValueFactory(new PropertyValueFactory<CalculationModel, Integer>("id"));
        year.setCellValueFactory(new PropertyValueFactory<CalculationModel, YearModel>("year"));
        quarter.setCellValueFactory(new PropertyValueFactory<CalculationModel, Integer>("quarter"));
        kitchen.setCellValueFactory(new PropertyValueFactory<CalculationModel, KitchenModel>("kitchen"));

        tableView.setItems(list);

        tableView.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

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



