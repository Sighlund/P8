
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
     * Switches to the history page.
     * @param event2 action event from the button element
     */
    public void switchToSceneHistory(ActionEvent event2){
        App.switchScene(App.getHistoryPageParent());
    }

}


