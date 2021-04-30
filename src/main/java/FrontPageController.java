
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.CalculationPersistence;

import java.io.IOException;

public class FrontPageController {

    // TODO - kan også slettes
    private Stage stage;
    private Scene scene;
    private Parent root;

    //TODO - kan slettes
    //Functions to switch between scenes
    public void switchToScene(ActionEvent event, Parent page) throws IOException {
        root = page;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Event handler for the button "Tilføj varer til beregning".
     * Switches to the data insertion page.
     * @param event action event from the button element
     */
    public void switchToSceneDataInsertion(ActionEvent event){
        App.switchScene(App.getDataInsertionPageParent());
    }

    /**
     * Event handler for the button "Historik".
     * Switches to the history page.
     * Update the table view with the current list of calculations from the database.
     * @param event2 action event from the button element
     */
    public void switchToSceneHistory(ActionEvent event2){
        App.switchScene(App.getHistoryPageParent());
        App.getHistoryController().updateTableView();
    }

}


