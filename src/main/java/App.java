import model.TestModel;
import persistence.CalculationPersistence;
import persistence.ConcitoPersistence;
import persistence.KitchenPersistence;
import persistence.SetupPersistence;

// Søren har skrevet noget fed kode

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("frontPage"));
        stage.setTitle("👨‍🍳👨‍🍳👨‍🍳Den store Bagedyst sæsonafslutning😎😎😎");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static TestModel testModel = new TestModel();

    public static void main(String[] args) throws Exception{


        testModel.constructorTest();
        SetupPersistence.getSf();
        //KitchenPersistence.create();
        CalculationPersistence.read();
        //ConcitoPersistence.create();
        launch();

    }
}
