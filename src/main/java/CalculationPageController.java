import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CalculationModel;
import model.FoodItemModel;
import persistence.CalculationPersistence;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class CalculationPageController implements Initializable {

    //Boolean state to keep track of whether the calculation has been saved or not. Useful when the user tries to exit without saving.
    //We set it to 'true' by default, because it will be updated to 'false' as soon as a calculation has been made.
    //When a calculation is saved, it is updated to 'true' again.
    //This is done, in order to avoid popup when existing program without having made a calculation.
    private static boolean calculationSaved = true;

    //Getter for checking state of calculationSaved
    public static boolean isCalculationSaved() {
        return calculationSaved;
    }
    //Setter for updating state calculationSaved
    public static void setCalculationSaved(boolean calculationSaved) {
        CalculationPageController.calculationSaved = calculationSaved;
    }

    //Labels that display summary of calculation totals
    @FXML
    private Label CO2TotLabel;
    @FXML
    private Label CO2PrKgLabel;
    @FXML
    private Label VolumeLabel;
    @FXML
    private Label kitchen;
    @FXML
    private Label period;

    //Pie chart
    @FXML
    PieChart MyPieChart;

    // TableView with food items from calculation
    @FXML
    private TableView foodItemsTableView;
    // Table columns
    @FXML
    private TableColumn productNameColumn;
    @FXML
    private TableColumn primaryGroupColumn;
    @FXML
    private TableColumn secondaryGroupColumn;
    @FXML
    private TableColumn volumeOfProductColumn;
    @FXML
    private TableColumn co2prkiloValueColumn;
    @FXML
    private TableColumn totalCo2ForItemColumn;


    // Reference to the calculation to be displayed
    private CalculationModel calculation;


    /**
     * Initializes the fxml and controller on FXML load
     * Happens on application start up
     * @param url tja?
     * @param rb tja?
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyPieChart.setLegendSide(Side.RIGHT);
        MyPieChart.setLabelsVisible(false);
    }

    /**
     * Public method to update the calculation view
     * @param calc calculation to be displayed
     */
    public void updateCalculationView(CalculationModel calc){
        this.calculation = calc;
        buildView();
    }

    private void buildView(){
        // Fill out labels with basic information about the calculation
        this.VolumeLabel.setText(format(calculation.calcTotalKg()) + " kg");
        this.CO2TotLabel.setText(format(calculation.calcTotalCo2())+ " kg CO2e");
        this.CO2PrKgLabel.setText(format(calculation.calcAveCO2prKg()) + " kg CO2e/kg");
        this.kitchen.setText(calculation.getKitchen().toString());
        this.period.setText(calculation.getQuarter() + ". kvartal  " + calculation.getYear().toString());

        // Build pie chart
        buildPieChart();

        // Build pie chart displaying food items
        buildTableView();
    }

    /**
     * Private method to build the pie chart based on values from the calculation object
     */
    private void buildPieChart(){
        // Create empty observable list
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Get hash table with categories present in the calculation and related CO2 in percentage
        Hashtable categories = calculation.getCategoriesPercentagesDict();

        // Get all categories as a set of strings
        Set<String> keys = categories.keySet();

        // Iterate over the categories using keys
        // Get percentage value for the category
        for(String key: keys) {

            // Create new pie chart data object
            // consists of the category (key) and the CO2 percentage (value)
            PieChart.Data data = new PieChart.Data(key, (Double) categories.get(key));

            // Bind the name property of the data object to reflect category name and percentage value
            data.nameProperty().bind(Bindings.concat(data.getName(), " ", Math.round(data.getPieValue()), "%"));

            // Add data object to the observable list of pie chart data
            pieChartData.add(data);
        }

        // Update the data to be displayed in the pie chart
        MyPieChart.setData(pieChartData);

    }

    /**
     * Builds Table View displaying all food items in the calculation
     */
    private void buildTableView(){
        // Clear table for previously displayed food items
        foodItemsTableView.getItems().clear();

        // Each column is told which that they are going to hold object of type PropertyValueFactory<S, T>.
        // S: The class contained in the column (in this case a simplified, proxy class for foodItem)
        // T: The class contained and displayed in a particular cell
        // TODO - duplicated code. Er vi ligeglade?
        productNameColumn.setCellValueFactory(
                new PropertyValueFactory<ViewListItemDataInsertionPage, String>("productName"));
        primaryGroupColumn.setCellValueFactory(
                new PropertyValueFactory<ViewListItemDataInsertionPage, String>("primaryGroup"));
        secondaryGroupColumn.setCellValueFactory(
                new PropertyValueFactory<ViewListItemDataInsertionPage, String>("secondaryGroup"));
        volumeOfProductColumn.setCellValueFactory(
                new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("volumeOfProduct"));
        co2prkiloValueColumn.setCellValueFactory(
                new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("co2prkiloValue"));
        totalCo2ForItemColumn.setCellValueFactory(
                new PropertyValueFactory<ViewListItemDataInsertionPage, Double>("totalCo2ForItem"));

        // Add all food items from the calculation to the table to be displayed
        for (FoodItemModel f : calculation.getFoodItemList()){
            // Uses simplified, proxy class to convert values from the foodItem object to values
            // accepted by the table view
            foodItemsTableView.getItems().add(new ViewListItemDataInsertionPage(
                    f.getName(), f.getCategory(), f.getSubcategory(), f.getVolume(), f.calcCo2PrKg(), f.calcCo2()));
        }
    }

    private String format(Double d){
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String format = numberFormat.format(d);
        return format;
    }

    private void seeDetails(){
        //TODO - se detaljer for enkelt kategori - eventhandler, der ændrer pie chart og table view
        // Get subcategories for specific category from calculation - as hash table
        // build piechart with new hash table
    }

    /**
     * Saves current instance of calculation in the database
     * Returns true, if hibernate call went through
     * @return true if calculation was saved, else false
     */
    public void saveCalculationToDatabase(){
        CalculationPersistence.addCalc(calculation);
        // TODO - skal den ikke kun opdatere, hvis den allerede findes?
        //We update the bool state keeping track of whether the current calculation has been saved, to true.
        //This state is changed back to 'false', once the user pressed 'Udregn' button again.
        CalculationPageController.setCalculationSaved(true);
    }

    /**
     * Event handler for the button "Start".
     * Switches to the front page.
     * @param event action event from the button element
     */
    public void switchToSceneFrontPage(ActionEvent event){
        App.switchScene(App.getFrontPageParent());
    }

    /**
     * Event handler for the button "Tilbage".
     * Switches back to the data insertion page.
     * @param event action event from the button element
     */
    public void switchToDataInsertionPage(ActionEvent event){
        App.switchScene(App.getDataInsertionPageParent());
    }





     /* // TODO slettes? - obsolete
    public void buildPieChart(){
        //Builds pieChart and stores data
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(CategoryA, VolumeA),
                        new PieChart.Data(CategoryB, VolumeB),
                        new PieChart.Data(CategoryC, VolumeC));
        MyPieChart.setData(pieChartData);
    }
     */


    /* //TODO slettes? - bar chart fjernet fra fxml
    //Bar Chart
    @FXML
    private BarChart<?, ?> MyBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public void buildBarChart(){
        //Builds bar chart and stores data
        // Creates the x axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Fødevare Kategori");

        // Creates the y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Co2 pr. kg");

        // Creates the chart
        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series data = new XYChart.Series();
        data.setName("Co2 Kategorier");

        //provide data
        data.getData().add(new XYChart.Data("Category A", VolumeA));
        data.getData().add(new XYChart.Data("Category B", VolumeB));
        data.getData().add(new XYChart.Data("Category C", VolumeC));

        MyBarChart.getData().add(data);

        //visibility is set false as piechart is shown as default
        MyBarChart.setVisible(false);
    }
 */


    //TODO slettet fra interface
    /*
    //NYT
    @FXML
    public CheckBox myCheckBox;

    public void change(ActionEvent event) {
        if (myCheckBox.isSelected()) {
            addDataBarChart("enhed 2");
        }
        else{
            MyBarChart.getData().clear();
            buildBarChart();
            MyBarChart.setVisible(true);
        }
    }

     */

    /*   //TODO slettes? - barchart er fjernet fra fxml
    //NYT
    public void addDataBarChart(String enhednavn){

        //TODO
        //enhed 2 skulle være parameter istedet for hardcodet
        XYChart.Series enhed2 = new XYChart.Series();
        enhed2.setName(enhednavn);

        enhed2.getData().add(new XYChart.Data("Kartoffel", 20));
        enhed2.getData().add(new XYChart.Data("Kød", 30));

        MyBarChart.getData().add(enhed2);
    }
    */

    //TODO slettes? - mulighed for at vælge chart er fjernet fra fxml
    /*
    //function that shows barchart when selected in menu
    public void handleShowBarChart(){
        MyPieChart.setVisible(false);
        MyBarChart.setVisible(true);
    }

    //function that shows piechart when selected in menu
    public void handleShowPieChart(){
        MyPieChart.setVisible(true);
        MyBarChart.setVisible(false);
    }
     */

    /* // TODO slettes - bruges ikke
    public void updateData(String categoryA, int volumeA, String categoryB, int volumeB, String categoryC, int volumeC){
        CategoryA = categoryA;
        VolumeA = volumeA;
        CategoryB = categoryB;
        VolumeB = volumeB;
        CategoryC = categoryC;
        VolumeC = volumeC;
    }
    */

    /* // TODO slettes - calculation objektet sendes direkte fra data insertion til calculation page
    int calculationPagecalcid;
    //Receives information from HistoryPageController
    public void getInformation(int calcid) {
        this.calculationPagecalcid=calcid;
    }
     */

    /*
    //Receives information from HistoryPageController
    public void getInformation(List<FoodItemModel> calcid) {
        this.calcPageItemList=calcid;
    }

    //test to print id
    public void printCalcid(){
        System.out.println(calcPageItemList);
    }
    */

    // TODO slettes? - bruges ikke længere
    //Categories represent foodcategories and volume represent CO2e.
    //These are used as datainputs for charts and should be replaced with real data
    String CategoryA = "Kød";
    int VolumeA = 60;
    String CategoryB = "Grønsager";
    int VolumeB = 25;
    String CategoryC = "Fisk";
    int VolumeC = 15;


}

