import customException.DescriptorPercentageException;
import customException.IngredientPercentageException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class controls the RegisterNewProductPage in the GUI. All actions performed on the page will be called here.
 */

public class RegisterNewProductPageController implements Initializable {

    //Creating instances of the FXML elements
    @FXML private TableView<ViewListRegisterPage> registerPageTableView;
    @FXML private TableColumn<ViewListRegisterPage, String> ingredientsColumn;
    @FXML private TableColumn<ViewListRegisterPage, String> percentageColumn;
    @FXML private TextField autoCompleteTextField;
    @FXML private TextField percentageTextField;
    @FXML private TextField descriptorName;
    @FXML private TextField itemNumber;
    @FXML private Text descriptorSavedAlert;


    /**
     * This ArrayList is declared outside any methods, so it can be used in multiple
     */
    public ArrayList<IngredientModel> ingredientList = new ArrayList<>();

    /**
     * This method adds ingredient(s) to the TableView. It takes the user input and converts it into a new list of
     * Ingredient objects. It allows users to input commas or dots as the decimal seperator, and presents the numbers
     * in the TableView with a comma as the decimal seperator.
     * @throws IngredientPercentageException Is used to throw an exception if user tries to create an ingredient
     * with 0 or less % or over 100% of a given concitoItem.
     */
    public void addIngredient() throws IngredientPercentageException {
        //Creating a string that gets the text that the user wrote in the autoCompleteTextField
        String ingredientNameString = autoCompleteTextField.getText();

        //Getting the concitoItem object, corresponding to the concitoItem that was written in the autoCompleteTextField,
        //from the database
        ConcitoItemModel concitoItem = ConcitoPersistence.getConcitoByName(ingredientNameString);

        //Converts any commas in the percetageTextField to a dot (this is done because we cannot save to the DB, if
        //a comma is used as the decimal seperator)
        String commaConvert = percentageTextField.getText().replace(',', '.');

        //Storing the input value from the percetageTextField in a Double variable
        Double volumePercentageInput = Double.valueOf(commaConvert);

        //Checking if the percentage that the user entered is equal to or less than 0 or greater than 100, as that is
        //not allowed. Throws exception if it is.
        if (volumePercentageInput > 100 || volumePercentageInput <= 0){
            throw new IngredientPercentageException("Ingredient percentage must be greater than 0 and not greater than 100");
        }

        //Creating a new ingredient object using a constructor where we input the volume and the concitoItem that the
        //user entered
        IngredientModel ingredient = new IngredientModel(volumePercentageInput, concitoItem);

        //Adds the ingredient to a list.
        ingredientList.add(ingredient);

        //Changes the decimal separator back into a comma for presentation in the TableView
        String percentageComma = format(ingredient.getPercentage()).replace('.',',');

        //Defining what is added to the tableview
        registerPageTableView.getItems().add(new ViewListRegisterPage(ingredient.getContoItem().getName(), percentageComma));
    }

    /**
     * This method is used to present numbers in the TableView with a maximum of two decimals.
     * @param d The method takes a double as the argument, as that is the data type we use in the TableView.
     * @return Returns the format string.
     */
    private String format(Double d){
        //Creating a new DecimalFormat, where there are a maximum of two decimals
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        //Taking the argument and converting it to a string using the DecimalFormat we just created
        String format = numberFormat.format(d);
        //Returning the format
        return format;
    }

    /**
     * This method is used to create new descriptors. It takes the list of ingredients that the user gave as input,
     * and the user specified name. It also checks whether the list of ingredients has a total percentage of 100. If
     * it does not it throws an error.
     * @throws DescriptorPercentageException This custom error is thrown if the total ingredients percentage is not 100.
     */
    public void createNewDescriptor() throws DescriptorPercentageException {
        //Creating a new foodDescriptor based on the foodDescriptor name entered by the user and the ingredientList
        //in the table view
        FoodDescriptorModel foodDescriptor = new FoodDescriptorModel(
                descriptorName.getText(), ingredientList);
        //Creating a variable of type Double
        Double percentage = 0.0;
        //Iterating over the individual ingredient in the ingredient list and updating the percentage variable every
        // time
        for (int i = 0; i < ingredientList.size(); i++) {
            percentage += ingredientList.get(i).getPercentage();
        }
        //Check if the percentage is anything other than 100% as that is not allowed. Throws an exception if it is.
        if (percentage != 100.0) {
            throw new DescriptorPercentageException("Ingredients % must equal 100");

        } else {
            //If the total percentage is 100 than we save the foodDescriptor to the database
            FoodDescriptorPersistence.addDescriptor(foodDescriptor);
        }
    }

    /**
     * Initializes the page, creating the TableView and defining the autocompletion.
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Gives the autoCompleteTextField an autocomplete effect based on the names of the concito items in the DB.
        TextFields.bindAutoCompletion(autoCompleteTextField, getConcitoNames());

        //Creates the two columns in the TableView.
        ingredientsColumn.setCellValueFactory(new PropertyValueFactory<ViewListRegisterPage, String>("ingredients"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<ViewListRegisterPage, String>("amountIngredient"));
    }

    /**
     * This method fetches all the concito names in the DB and saves them in a List
     * @return Returns a List of all the concito name in the DB.
     */
    public List<String> getConcitoNames() {
        //Fetches a list of the names of all concito items in the database and saves them in a list of strings.
        List<String> list = ConcitoPersistence.listConcitoName();
        //Returns the list of concito names
        return list;
    }

    /**
     * This method is used to give the "tilføj ingrediens" buttom in the GUI some functionality. It calls the
     * addIngredient method and clears the input fields related to the input to make room for more input.
     * This is also where exceptions caused by addIngredient method is caught
     * @param e
     */
    public void addIngredientButton(ActionEvent e) {
        //The try catch is used, because we except that this code could create exceptions and we want to handle these.
        try {
            //Calls the addIngredient method
            addIngredient();
            //Clears the autoCompleteTextField of any user input
            autoCompleteTextField.clear();
            //Clears the percentageTextField of any user input
            percentageTextField.clear();
            //Catches an exception if the % of the ingredient added is not greater then 0 or greater than 100
        } catch (IngredientPercentageException exception){
            //Prints the error in the console
            exception.printStackTrace();
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "%-mængde i varen skal være større end 0 og ikke større end 100");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }
        //Catches a noResultException. Here it is triggered if the user tries to add an ingredient that is not in the DB.
        catch (NoResultException exception) {
            //Prints the error in the console
            exception.printStackTrace();
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Navnet på ingrediensen blev ikke fundet i databasen. Tjek at navnet er korrekt");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }
        //This catches every other type of exception. In this case, we only expect the '%-indhold i varen' field to be problematic.
        catch (Exception exception) {
            //Prints the error in the console
            exception.printStackTrace();
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Feltet '%-indhold i varen' må kun indholde tal, og kommatal skal bruge '.' i stedet for ','");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
        }
    }

    /**
     * This method is used to give the "Registrer varen i databasen" some functionality. It calls the
     * createNewDescriptor method and clears all the text input fields and the TableView in the GUI
     * to make room for at new foodDescriptor.
     * It also alerts the user if the save was successful by displaying a message for a couple of seconds.
     * This is also where errors caused by createNewDescriptor is caught.
     * @param e
     */
    public void saveInDatabase(ActionEvent e) {
        //The try catch is used, because we except that this code could create exceptions and we want to handle these.
        try {
            //Calls the createNewDescriptor method
            createNewDescriptor();
            //Sets the "gemt" alert message to true
            descriptorSavedAlert.setVisible(true);
            //Clears the percentageTextField
            percentageTextField.clear();
            //Clears the descriptorName text field
            descriptorName.clear();
            //Clears the autoCompleteTextField
            autoCompleteTextField.clear();
            //Clears the itemNumber text field
            itemNumber.clear();
            //Clears the ingredientList
            ingredientList.clear();
            //Clears the TableView
            registerPageTableView.getItems().clear();
            //Call the removeSavedAlert method
            removeSavedAlert();
            //Catches a DescriptorPercentageException. This is a custom exception that is thrown, if the total %
            //of ingredients does not equal 100
        } catch (DescriptorPercentageException exception) {
            //Prints the error in the console
            exception.printStackTrace();
            //Instantiating an object which has the error handling methods
            ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();
            //We call upon the method which creates a popup with the provided string.
            errorHandlingCollection.basicErrorPopup("fejl", "Varens ingredienser skal tilsammen udgøre 100%");
            //Once the object has served its purpose, we assign it null, so that it will be cleaned by garbage collector.
            errorHandlingCollection = null;
            //Catches an exception. Here we expect the exception to be because a foodDescriptor with this name
            //already exists in the DB
        } catch (Exception exception) {
            //Prints the error in the console
            exception.printStackTrace();
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
        //Creates a new TimerTask that will set the "gemt" alert message to false after the TimerTask is over
        TimerTask task = new TimerTask() {
            public void run() {
                descriptorSavedAlert.setVisible(false);
            }
        };
        //Creates a new timer that will be used to define how long the "gemt" alert message will be shown
        //Daemon makes the timer low priority, aka a daemon thread, which means that when the application is closed
        //the JVM forces this method to end (This is what the "true" in the parenthesis means)
        Timer timer = new Timer(true);
        //The timer is set to countdown 3000 milliseconds aka 3 seconds.
        long delay = 3000L;
        //Starts the timer
        timer.schedule(task, delay);
    }

    /**
     * Removes all rows from the TableView
     * @param e
     */
    public void resetIngredientTable(ActionEvent e) {
        //Creating a new modal window to prompt the user for confirmation of clearing the ingredientsTable
        ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();

        //Creating the prompt and defining what is written in the prompt
        if (errorHandlingCollection.confirmChoicePopup("Er du sikker på du vil rydde alle ingredienser?")) {
            //Clearing the TableView
            registerPageTableView.getItems().clear();
            //Clearing the ingredientList that is associated with the TableView
            ingredientList.removeAll(ingredientList);
        }
        //Setting the errorhandling object to null, so the JVM garbage collector deletes it.
        errorHandlingCollection = null;
    }

    /**
     * Removes the selected row from the TableView
     * @param e -
     */
    public void removeSelectedRow(ActionEvent e) {
        //Creating a new modal window to prompt the user for confirmation of clearing a row in the ingredientsTable
        ErrorHandlingCollection errorHandlingCollection = new ErrorHandlingCollection();

        //Creating the prompt and defining what is written in the prompt
        if (errorHandlingCollection.confirmChoicePopup("Er du sikker på du vil fjerne denne række?")) {
            //Removing the chosen ingredient from the ingredientList
            ingredientList.removeIf(n -> (n.getContoItem().getName().equals(registerPageTableView.getSelectionModel().getSelectedItem().getIngredients())));
            //Removing the chosen ingredient from the TableView
            registerPageTableView.getItems().remove(registerPageTableView.getSelectionModel().getSelectedItem());
        }
        //Setting the errorhandling object to null, so the JVM garbage collector deletes it.
        errorHandlingCollection = null;
    }
}