import javafx.scene.image.Image;

// Søren har skrevet noget fed kode

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FoodDescriptorModel;
import persistence.FoodDescriptorPersistence;

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
        Image icon = new Image("https://github.com/Sighlund/P8/blob/main/src/main/resources/img/Logo.PNG?raw=true");
        stage.getIcons().add(icon);
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

    public static void main(String[] args) throws Exception{

        FoodDescriptorModel foodDescriptorModel = FoodDescriptorPersistence.getDescriptorByName("Oksekød, tyndsteg med mørbrad, rå");
        System.out.println(foodDescriptorModel.getCorrectedCategory());
        launch();
    }
}
