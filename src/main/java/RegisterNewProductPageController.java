import customException.DescriptorPercentageException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;
import org.controlsfx.control.textfield.TextFields;
import persistence.ConcitoPersistence;
import persistence.FoodDescriptorPersistence;
import persistence.KitchenPersistence;

import javax.persistence.NoResultException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterNewProductPageController implements Initializable {

    @FXML
    private TableView<ViewListRegisterPage> registerPageTableView;
    @FXML
    private TableColumn<ViewListRegisterPage, String> ingredientsColumn;
    @FXML
    private TableColumn<ViewListRegisterPage, Double> percentageColumn;


    @FXML
    private TextField autoCompleteTextField;

    @FXML
    private TextField percentageTextField;

    @FXML
    private TextField descriptorName;

    @FXML
    private TextField itemNumber;
    @FXML
    private Text descriptorSavedAlert;

    public ArrayList<IngredientModel> ingredientList = new ArrayList<>();

    public void addIngredient() {
        String ingredientNameString = autoCompleteTextField.getText();
        Double volumePercentageInput = Double.valueOf(percentageTextField.getText());
        ConcitoItemModel concitoItem = ConcitoPersistence.getConcitoByName(ingredientNameString);
        IngredientModel ingredient = new IngredientModel(volumePercentageInput, concitoItem);
        ingredientList.add(ingredient);
        registerPageTableView.getItems().add(new ViewListRegisterPage(ingredient.getContoItem().getName(), ingredient.getPercentage()));
    }


    public void createNewDescriptor() throws DescriptorPercentageException {
        FoodDescriptorModel foodDescriptor = new FoodDescriptorModel(
                descriptorName.getText(), ingredientList);
        Double percentage = 0.0;
        for (int i = 0; i < ingredientList.size(); i++) {
            percentage += ingredientList.get(i).getPercentage();
        }
        System.out.println(percentage);
        if (percentage != 100.0) {
            throw new DescriptorPercentageException("Ingredients % must equal 100");
        } else {
            FoodDescriptorPersistence.addDescriptor(foodDescriptor);
            System.out.println(foodDescriptor.getName());
            System.out.println(ingredientList.get(0).getContoItem().getName());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TextFields.bindAutoCompletion(autoCompleteTextField, getConcitoNames());

        ingredientsColumn.setCellValueFactory(new PropertyValueFactory<ViewListRegisterPage, String>("ingredients"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<ViewListRegisterPage, Double>("amountIngredient"));

        registerPageTableView.setItems(getItemsForList());
    }

    public ObservableList<ViewListRegisterPage> getItemsForList() {
        ObservableList<ViewListRegisterPage> itemList = FXCollections.observableArrayList();
        return itemList;
    }

    public List<String> getConcitoNames() {
//        ArrayList<String> list = new ArrayList<String>();
//        for (int i = 0; i < FoodDescriptorPersistence.listDescriptor().size(); i++) {
//            list.add(FoodDescriptorPersistence.listDescriptor().get(i).getName());
//        }
        List<String> list = ConcitoPersistence.listConcitoName();
        return list;
    }


    public void addIngredientButton(ActionEvent e) {
        try {
            addIngredient();
            autoCompleteTextField.clear();
            percentageTextField.clear();
        } catch (NoResultException exception) {
            System.out.println(exception);
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Navnet på ingrediensen blev ikke fundet i databasen. Tjek at navnet er korrekt");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }
        //This catches every other type of exception. In this case, we only expect the '%-indhold i varen' field to be problematic.
        catch (Exception exception) {
            System.out.println(exception);
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Feltet '%-indhold i varen' må kun indholde tal, og kommatal skal bruge '.' i stedet for ','");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }
    }


    public void saveInDatabase(ActionEvent e) {
        try {
            createNewDescriptor();
            descriptorSavedAlert.setVisible(true);
            //percentageTextField.clear();
            descriptorName.clear();
            autoCompleteTextField.clear();
            itemNumber.clear();
            ingredientList.clear();
            registerPageTableView.getItems().clear();
            removeSavedAlert();
        } catch (DescriptorPercentageException exception) {
            System.out.println(exception);
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Varens ingredienser skal tilsammen udgøre 100%");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        } catch (Exception exception) {
            System.out.println(exception);
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "En vare med dette navn findes allerede. Vælg et andet navn");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }
    }

    /**
     * Removes the save alert after 3 seconds (the message is made invisible)
     */
    public void removeSavedAlert() {
        TimerTask task = new TimerTask() {
            public void run() {
                descriptorSavedAlert.setVisible(false);
            }
        };
        //Daemon makes the timer low priority, aka a daemon thread, which means that when the application is closed
        //the JVM forces this method to end
        Timer timer = new Timer(true);
        long delay = 3000L;
        timer.schedule(task, delay);
    }

    /**
     * Removes all rows from the TableView
     * @param e -
     */
    public void resetIngredientTable(ActionEvent e) {
        registerPageTableView.getItems().clear();
        ingredientList.removeAll(ingredientList);
    }

    /**
     * Removes the selected row from the TableView
     * @param e -
     */
    public void removeSelectedRow(ActionEvent e) {
        ingredientList.removeIf(n -> (n.getContoItem().getName().equals(registerPageTableView.getSelectionModel().getSelectedItem().getIngredients())));
        registerPageTableView.getItems().remove(registerPageTableView.getSelectionModel().getSelectedItem());
    }

}