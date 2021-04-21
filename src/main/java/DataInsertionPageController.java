//Denne jar skal i libs https://nexus.gluonhq.com/nexus/content/repositories/releases/com/gluonhq/charm-glisten/6.0.6/

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DataInsertionPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /*
    Button that adds the chosen product to the list of items that the system must calculate.
    The button may not add item to the list before a chosen product and a weight has been provided.
     */
    public void addProductToList(ActionEvent e){
        System.out.println("this button must add product to list");
        //Todo;
    }




    public void switchToCalculationPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("calculationPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }



}
