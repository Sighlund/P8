
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
    public void switchToScene(ActionEvent event, Parent page) throws IOException {
        root = page;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneDataInsertion(ActionEvent event){
        App.switchScene(App.getDataInsertionPageParent());
    }

    public void switchToSceneHistory(ActionEvent event2){
        App.switchScene(App.getHistoryPageParent());
    }

}


