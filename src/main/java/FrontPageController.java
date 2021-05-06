
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.CalculationPersistence;

import java.io.IOException;

public class FrontPageController {



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
     * Update the table view with the current list of calculations from the database.
     * Switches to the history page.
     * @param event2 action event from the button element
     */
    public void switchToSceneHistory(ActionEvent event2){
        // Update information in the table view displaying available calculations
        App.getHistoryController().updateTableView();

        // Switch to the history pages
        App.switchScene(App.getHistoryPageParent());
    }

}


