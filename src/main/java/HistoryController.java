import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    //ListView refers to lists containing year, quarter, unit and saved calculations on the History page
    @FXML
    private ListView<String> yearList = new ListView<>();
    @FXML
    private ListView<String> quarterList = new ListView<>();
    @FXML
    //unitList should contain units
    private ListView<String> unitList = new ListView<>();
    @FXML
    //calcHistory should contain previous calculations
    private ListView<String> calcHistory = new ListView();

    //Arrays is filled with year, quarter
    String[] years = {"2020","2021","2022","2023","2024","2025"};
    String[] quarters = {"Q1","Q2","Q3","Q4"};
    //units has to be filled with units
    String [] units = {};
    // New calculations should be added
    String [] calculations = {};

    //labels are textfields that opdates with chosen year and quarter
    @FXML
    private Label chosenYear;
    @FXML
    private Label chosenYearQ;
    @FXML

    //test labels for storing chosen year and quarter
    private Label Aar;
    @FXML
    private Label Kvartal;

    //used to opdate labels
    String currentYear;
    String currentYearQ;

    //Initialiseres listViews
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        yearList.getItems().addAll(years);
        quarterList.getItems().addAll(quarters);
        unitList.getItems().addAll(units);
        calcHistory.getItems().addAll(calculations);

        //Opdateres filter label with year
        yearList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                currentYear = yearList.getSelectionModel().getSelectedItem();
                chosenYear.setText("valgte år: " + currentYear);
                Aar.setText(currentYear);
            }
        });
        //Opdateres filter label with quarter
        quarterList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                currentYearQ = quarterList.getSelectionModel().getSelectedItem();
                chosenYearQ.setText("År: " + currentYear + " Kvartal: " + currentYearQ);
                Kvartal.setText(currentYearQ);
            }
        });
    }

    //testfunction used to print chosen year and quarter
    public void printKnap(ActionEvent event) throws IOException {
        System.out.println(Aar.getText() + " " + Kvartal.getText());
    }

    // TODO - kan slettes?
    private Stage stage;
    private Scene scene;
    private Parent root;

    // TODO - kan slettes?
    //Functions to switch between scenes
    FrontPageController History = new FrontPageController();

    /**
     * Event handler for the button "Start".
     * Switches to the front page.
     * @param event action event from the button element
     */
    public void switchToSceneFrontPage(ActionEvent event){
        App.switchScene(App.getFrontPageParent());
    }

    }



