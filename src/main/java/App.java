import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

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
    private static Parent comparisonParent;

    // References to controllers
    private static HistoryController historyController;
    private static CalculationComparisonPageController comparisonController;
    private static CalculationPageController calculationController;
    private static DataInsertionPageController dataInsertionController;

    //References to the current active stage and scene are held here.
    private static Stage stage;
    private static Scene scene;

    // --- Getters ----
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
    public static Parent getComparisonParent() {
        return comparisonParent;
    }

    public static HistoryController getHistoryController() {
        return historyController;
    }
    public static CalculationComparisonPageController getComparisonController() {
        return comparisonController;
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
        frontPageParent = FXMLLoader.load(getClass().getResource("frontPage.fxml"));
        registerNewProductPageParent = FXMLLoader.load(getClass().getResource("registerNewProductPage.fxml"));

        // For the history view, we also need the controller
        // Creates FXML object to retrieve both FXML and controller
        // Stores references to both
        FXMLLoader loader = new FXMLLoader();
        historyPageParent = loader.load(getClass().getResource("history.fxml").openStream());
        historyController = loader.getController();

        // For the comparison view, we also need the controller
        // Creates FXML object to retrieve both FXML and controller
        // Stores references to both
        FXMLLoader loader2 = new FXMLLoader();
        comparisonParent = loader2.load(getClass().getResource("comparison.fxml").openStream());
        comparisonController = loader2.getController();
        // Store reference to scene parent in controller
        comparisonController.setThisFxml(comparisonParent);

        // For the calculation view, we also need the controller
        // Creates FXML object to retrieve both FXML and controller
        // Stores references to both
        FXMLLoader loader3 = new FXMLLoader();
        calculationPageParent = loader3.load(getClass().getResource("calculationPage.fxml").openStream());
        calculationController = loader3.getController();

        // For the data insertion view, we also need the controller
        // Creates FXML object to retrieve both FXML and controller
        // Stores references to both
        FXMLLoader loader4 = new FXMLLoader();
        dataInsertionPageParent = loader4.load(getClass().getResource("dataInsertionPage.fxml").openStream());
        dataInsertionController = loader4.getController();
        // Store reference to scene parent in controller
        dataInsertionController.setThisFxml(dataInsertionPageParent);
    }

    /**
     * Implements abstract method inherited from Application
     * Starts the application. Sets stage, scene and root to hold contents.
     * @param stage primary stage provided by this.launch()
     */
    @Override
    public void start(Stage stage){
        scene = new Scene(frontPageParent);
        stage.setTitle("CO2 Beregner");
        Image icon = new Image("https://github.com/Sighlund/P8/blob/main/src/main/resources/img/Logo.PNG?raw=true");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);

        //Updates the current active stage to equal the one filled in this method.
        this.stage = stage;
    }

    /**
     * Main method, launches the application
     * @param args command line arguments
     */
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

    /**
     *Method being called when user triggers a WindowEvent. In this case, we use it when the user tries to exit the application.
     * @param event in this case, event is the user trying to close the window.
     */
    private void closeWindowEvent(WindowEvent event) {
        System.out.println("User exited the application. System checks whether current calculation has been saved. If not, popup shows.");
        ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
        errorHandlingCollection.checkIfCalculationIsSavedPopup(event);
        errorHandlingCollection = null;
    }


}
