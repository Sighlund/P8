import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FrontPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    //Functions to switch between scenes
    public void switchToScene(ActionEvent event, String page) throws IOException {
        root = FXMLLoader.load(getClass().getResource(page));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneDataInsertion(ActionEvent event) throws IOException {
        switchToScene(event, "dataInsertionPage.fxml");
    }

    public void switchToSceneHistory(ActionEvent event2) throws IOException {
        switchToScene(event2, "history.fxml");
    }

    public void switchToCalculationPage(ActionEvent event3) throws IOException {
        switchToScene(event3, "dataInsertionPage.fxml");
    }

    public void switchToDataInsertionPage(ActionEvent event4) throws IOException {
        switchToScene(event4, "dataInsertionPage.fxml");
    }

}


