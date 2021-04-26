import javafx.scene.image.Image;
import model.*;
import persistence.*;

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

    private static TestModel testModel = new TestModel();

    public static void main(String[] args) throws Exception{


        //testModel.constructorTest();
        //SetupPersistence.getSf();
        //KitchenPersistence.create();
        //CalculationPersistence.read();
        //ConcitoPersistence.create();
        //FoodDescriptorPersistence.create();
        //CalculationModel calc = CalculationPersistence.getCalcById(1);
        //KitchenPersistence.listKitchen();

//        FoodDescriptorModel foodDescriptorModel = FoodDescriptorPersistence.getDescriptorById(1);
//        FoodItemModel foodItemModel = new FoodItemModel(25.0, foodDescriptorModel);
//        KitchenModel kitchenModel = KitchenPersistence.getKitchenById(1);
//        CalculationModel calculationModel = new CalculationModel(kitchenModel);
//        calculationModel.addFoodItem(foodItemModel);
//        CalculationPersistence.addCalc(calculationModel);

//        ConcitoItemModel concitoItem = ConcitoPersistence.getConById(1);
//        IngredientModel ingredient = new IngredientModel(100.0,concitoItem);
//        FoodDescriptorModel foodDescriptor = new FoodDescriptorModel("Oksedreng");
//        foodDescriptor.addIngredient(ingredient);
//        FoodDescriptorPersistence.addDescriptor(foodDescriptor);

        //FoodDescriptorPersistence.deleteDescriptor(575);

        //FoodDescriptorPersistence.deleteDescriptor(569);

        //FoodDescriptorModel test = FoodDescriptorPersistence.getDescriptorById(557);

        //System.out.println(test.getIngredientList().get(0).getFoodDescriptor().toString());

//        YearModel yearModel = new YearModel();
//        yearModel.setYear(2021);
//        QuarterModel quarterModel = new QuarterModel();
//        quarterModel.setQuarter(1);
//        yearModel.addQuarter(quarterModel);
//        YearPersistence.addYear(yearModel);


        launch();

    }
}
