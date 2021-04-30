import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CalculationModel;
import persistence.CalculationPersistence;
import persistence.SetupPersistence;

import java.io.IOException;

/**
 * The App class holds the main method of the application.
 * It extends JavaFX Application and launches the application.
 */
public class App extends Application {

    //roots being declared, which will later hold .fxml files.
    private static Parent calculationPageParent;
    private static Parent dataInsertionPageParent;
    private static Parent frontPageParent;
    private static Parent historyPageParent;
    private static Parent registerNewProductPageParent;

    private static CalculationPageController calculationController;

    //References to the current active stage and scene are held here.
    private static Stage stage;
    private static Scene scene;

    // --- Getters and setters ----
    public static Parent getDataInsertionPageParent() {
        return dataInsertionPageParent;
    }
    public static Parent getCalculationPageParent() {
        return calculationPageParent;
    }
    public static Parent getFrontPageParent() {
        return frontPageParent;
    }
    public static Parent getHistoryPageParent() { return historyPageParent; }
    public static Parent getRegisterNewProductPageParent() {
        return registerNewProductPageParent;
    }

    public static CalculationPageController getCalculationController() {
        return calculationController;
    }

    /**
     * Overrides the init() method inherited from Application.
     * Loads all .fxml files into memory and stores static references to them
     * Calls this.start()
     * @throws IOException if FXLLoader fails to load the resource exception is thrown and application shuts down
     */
    @Override
    public void init() throws IOException{
        //We fill the roots with respective .fxml files from harddrive.
        dataInsertionPageParent = FXMLLoader.load(getClass().getResource("dataInsertionPage.fxml"));
        frontPageParent = FXMLLoader.load(getClass().getResource("frontPage.fxml"));
        historyPageParent = FXMLLoader.load(getClass().getResource("history.fxml"));
        registerNewProductPageParent = FXMLLoader.load(getClass().getResource("registerNewProductPage.fxml"));

        // For the calculation view, we also need the controller
        // Creates FXML object to retrieve both FXML and controller
        // Stores references to both
        FXMLLoader loader3 = new FXMLLoader();
        calculationPageParent = loader3.load(getClass().getResource("calculationPage.fxml").openStream());
        calculationController = loader3.getController();
    }

    /**
     * Implements abstract method inherited from Application
     * Starts the application. Sets stage, scene and root to hold contents.
     * @param stage primary stage provided by this.launch()
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

    /**
     * Main method, launches the application
     * @param args command line arguments
     */
    public static void main(String[] args){
        CalculationModel calc = CalculationPersistence.getCalcById(1);
        System.out.println(calc.getFoodItemList().get(0).getFoodDescriptor().getIngredientList().get(0).getContoItem().getName());
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
