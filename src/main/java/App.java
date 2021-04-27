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

    private static Parent calculationPageParent;
    private static Parent dataInsertionPageParent;
    private static Parent frontPageParent;
    private static Parent historyPageParent;
    private static Parent registerNewProductPageParent;

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
        calculationPageParent = FXMLLoader.load(getClass().getResource("calculationPage.fxml"));
        dataInsertionPageParent = FXMLLoader.load(getClass().getResource("dataInsertionPage.fxml"));
        frontPageParent = FXMLLoader.load(getClass().getResource("frontPage.fxml"));
        historyPageParent = FXMLLoader.load(getClass().getResource("history.fxml"));
        registerNewProductPageParent = FXMLLoader.load(getClass().getResource("registerNewProductPage.fxml"));
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("frontPage"));
        stage.setTitle("👨‍🍳👨‍🍳👨‍🍳Den store Bagedyst sæsonafslutning😎😎😎");
        Image icon = new Image("https://github.com/Sighlund/P8/blob/main/src/main/resources/img/Logo.PNG?raw=true");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    public static void switchScene(Parent page){
        scene.setRoot(page);
        stage.setScene(scene);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws Exception{
        launch();
    }
}
