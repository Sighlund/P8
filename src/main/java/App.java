import javafx.scene.image.Image;

// Søren har skrevet noget fed kode

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.FoodDescriptorPersistence;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    //roots being declared, which will later hold .fxml files.
    private static Parent calculationPageParent;
    private static Parent dataInsertionPageParent;
    private static Parent frontPageParent;
    private static Parent historyPageParent;
    private static Parent registerNewProductPageParent;

    //References to the current active stage and scene are held here.
    private static Stage stage;
    private static Scene scene;

    public static Parent getDataInsertionPageParent() {
        return dataInsertionPageParent;
    }
    public static Parent getCalculationPageParent() {
        return calculationPageParent;
    }
    public static Parent getFrontPageParent() {
        return frontPageParent;
    }
    public static Parent getHistoryPageParent() {
        return historyPageParent;
    }
    public static Parent getRegisterNewProductPageParent() {
        return registerNewProductPageParent;
    }


    @Override
    public void init() throws IOException{
        //We fill the roots with respective .fxml files from harddrive.
        calculationPageParent = FXMLLoader.load(getClass().getResource("calculationPage.fxml"));
        dataInsertionPageParent = FXMLLoader.load(getClass().getResource("dataInsertionPage.fxml"));
        frontPageParent = FXMLLoader.load(getClass().getResource("frontPage.fxml"));
        historyPageParent = FXMLLoader.load(getClass().getResource("history.fxml"));
        registerNewProductPageParent = FXMLLoader.load(getClass().getResource("registerNewProductPage.fxml"));
    }

    /**
     * Starts the application. Sets stage, scene and root to hold contents.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage){
        scene = new Scene(frontPageParent);
        stage.setTitle("👨‍🍳👨‍🍳👨‍🍳Den store Bagedyst sæsonafslutning😎😎😎");
        Image icon = new Image("https://github.com/Sighlund/P8/blob/main/src/main/resources/img/Logo.PNG?raw=true");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        //Updates the current active stage to equal the one filled in this method.
        this.stage = stage;
    }

    public static void main(String[] args){
        launch();
    }

    /**
     * Auxiliary method to switch scenes from controllers. The controllers pass respective root.
     * @param page the Parent root to be switched to
     */
    public static void switchScene(Parent page){
        scene.setRoot(page);
        stage.setScene(scene);
    }
}
