import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculationPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToDataInsertionPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("dataInsertionPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
