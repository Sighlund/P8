import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.CalculationModel;

import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.ResourceBundle;

public class CalculationComparisonPageController implements Initializable {

    // Stacked bar chart and its x- and y-axis
    @FXML
    private StackedBarChart<String, Number> stackedBarChart;
    @FXML
    private CategoryAxis stackedBarXAxis;
    @FXML
    private NumberAxis stackedBarYAxis;


    // Simple bar chart and its x- and y-axis
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private CategoryAxis barXaxis;
    @FXML
    private NumberAxis barYAxis;


    @FXML
    private MenuButton diagramMenuBtn;
    @FXML
    private MenuItem co2PMenuItem;
    @FXML
    private MenuItem co2KgMenuItem;
    @FXML
    private MenuItem volPMenuItem;
    @FXML
    private MenuItem volKgMenuItem;
    @FXML
    private MenuItem co2PrKgMenuItem;


    // Property to hold observable list of all displayed series in the stacked bar chart
    private ObservableList<XYChart.Series<String, Number>> displayedSeries;

    // Property to hold list of calculations to be displayed in bar chart
    private List<CalculationModel> calcs = new ArrayList<>();

    // Property to hold list of categories to be displayed in bar chart
    private List<String> categories = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    /**
     * Receives information from history controller
     * Is called whenever the user switches from the history scene to the comparison scene
     * @param calcs a list of the calculations to be displayed for comparison
     */
    public void getInformation(ObservableList<CalculationModel> calcs) {
        // Update property holding all calculations to be displayed
        this.calcs=calcs;

        // Build the stacked bar chart
        buildStackedBarChart();

        // Display values from calculation(s) as CO2 values in percentage for each category
        // by calling event handler for that specific menu item in menu button
        showCo2P(new ActionEvent());
    }


    /**
     * Builds the stacked bar chart and adds data
     */
    private void buildStackedBarChart(){
        // Clear any previous data
        stackedBarChart.getData().clear();

        // Set label for y-axis
        stackedBarXAxis.setLabel("Køkken og periode");

        // Set gap for the bar chart
        stackedBarChart.setCategoryGap(100);

        // Set animation to false (it messes with the data when changing data sets)
        stackedBarChart.setAnimated(false);

        // Call private method to create series and add all series to the chart
        addSeriesToChart();

        // Store reference to all the displayed series
        this.displayedSeries = stackedBarChart.getData();
    }


    /**
     * Creates all series for the stacked bar chart based on
     * categories present in all calculations to be displayed
     * and adds them to the stacked bar chart
     */
    private void addSeriesToChart(){
        // Get list of categories to display as stacked bars (x-axis)
        categories = getAllCategories();

        // Iterate over list of categories
        for (String cat : categories) {

            // Create a new series for each category, each taking a String and a Number value
            XYChart.Series<String, Number> series = new XYChart.Series<>();

            // Set name of the new series to equal the current category
            series.setName(cat);

            // Add the new series (category) with all the data values to the stacked bar chart
            stackedBarChart.getData().add(series);
        }
    }


    /**
     * Sets the data values for each series in the stacked bar chart
     * based on the chosen option
     *
     * @param option the values, the user would like to have displayed
     *               default = CO2 subtotal values in percentage for each category
     *               2 = CO2 subtotal values in kg for each category
     *               3 = Volume subtotal values in percentage for each category
     *               4 = Volume subtotal values in kg for each category
     */
    private void setSeriesData(int option){

        // Ensure that the simple bar chart is hidden
        if (barChart.isVisible()){
            barChart.setVisible(false);
        }

        // Iterate over the series in the stacked bar chart
        for (XYChart.Series<String, Number> s : displayedSeries){

            // Clear previous data in the current series
            s.getData().clear();

            // Iterate over all calculations to be displayed
            for (CalculationModel calc : calcs){

                // Create hash table to store values for the current calculation
                Hashtable<String, Double> ht;

                // Get hash table with data to display from the calculation based on the chosen option
                // Default, get hash table with CO2 percentage values for each category
                if (option == 2){
                    ht = calc.getCategoriesCo2KgHt(); // Gets CO2 subtotal values in kg for each category
                }
                else if (option == 3){
                    ht = calc.getCategoriesVolPercentagesHt(); // Gets volume subtotal values in percentage for each category
                }
                else if (option == 4) {
                    ht = calc.getCategoriesVolKgHt(); // Gets volume subtotal values in kg for each category
                }
                else {
                    ht = calc.getCategoriesCo2PercentagesHt(); // Gets CO2 subtotal values in percentage for each category
                }

                // Get set of keys in the hash table to be able
                // to access the categories and their values
                Set<String> keys = ht.keySet();

                // If the current category is present in the current calculation add a new Data object to the series
                // using name of kitchen, quarter, year as category name
                // and the corresponding hash table value as data value
                if (keys.contains(s.getName())) {
                    s.getData().add(new XYChart.Data<>(
                            (calc.getKitchen().toString() + " " +
                                    calc.getQuarter() + ". kvartal " +
                                    calc.getYear().toString()),
                            ht.get(s.getName())));
                }
            }
        }

        // Ensure that the stacked bar chart is visible
        if (!stackedBarChart.isVisible()){
            stackedBarChart.setVisible(true);
        }
    }


    /**
     * Gets all categories to present in bar chart based on the calculations to be displayed
     * @return a list of Strings with all categories to display
     */
    private List<String> getAllCategories(){

        // Create list to hold all categories for all calculations to be displayed
        List<String> allCategories = new ArrayList<>();

        // Iterate over all calculations to be displayed
        for (CalculationModel c : calcs){

            // Get categories in the current calculation
            List<String> categories = c.getCategories();

            // Iterate over those categories
            for (String cat : categories){

                // If the category is not already represented in the list of categories
                // for all calculations combined, add it to the list
                if(!allCategories.contains(cat)){
                    allCategories.add(cat);
                }
            }
        }

        // Return list with all distinct categories for all calculations combined
        return allCategories;
    }


    /**
     * Builds and displays simple bar chart showing average CO2e pr Kg food for each calculation
     */
    private void buildBarChart() {

        // Set stacked bar chart to not visible
        stackedBarChart.setVisible(false);

        // Set x- and y-axis
        barXaxis.setLabel("Køkken og periode");
        barYAxis.setLabel("Kg CO2e pr kg fødevare");

        // Set gap between displayed categories on the x-axis
        barChart.setCategoryGap(100);

        // Clear any previous data in the chart
        barChart.getData().clear();

        // Create one new series and set name
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Gennemsnitlig kg Co2 pr. kg fødevare");

        // Iterate over displayed calculations
        for (CalculationModel c : calcs){
            // Add new data object to the series for each calculation
            // using kitchen, quarter, and year category name
            // and calculated CO2 value as data value
            series.getData().add(new XYChart.Data<>(
                    ((c.getKitchen().toString() + " " +
                            c.getQuarter() + ". kvartal " +
                            c.getYear().toString())),
                    c.calcAveCO2prKg()));
        }

        // Add the series data to bar chart
        barChart.getData().add(series);

        // Set bar chart to be visible
        barChart.setVisible(true);
    }



    /**
     * Event handler for menu bar item: "Procent" TODO anne
     * Updates all series with new data, displaying percentages for CO2 for each category
     * @param event
     */
    @FXML
    void showCo2P(ActionEvent event) {
        setSeriesData(1);
        stackedBarYAxis.setLabel("Procentdel af samlet CO2e for perioden");
        diagramMenuBtn.setText(co2PMenuItem.getText());
    }


    /**
     * Event handler for menu bar item: "Kg Co2" TODO anne
     * Updates all series with new data, displaying CO2 totals for each category
     * @param event
     */
    @FXML
    void showCo2Kg(ActionEvent event) {
        setSeriesData(2);
        stackedBarYAxis.setLabel("Kg CO2e");
        diagramMenuBtn.setText(co2KgMenuItem.getText());
    }

    /**
     * Event handler for TODO anne
     * @param event
     */
    @FXML
    void showVolP(ActionEvent event) {
        setSeriesData(3);
        stackedBarYAxis.setLabel("Procentdel af samlet vægt for perioden");
        diagramMenuBtn.setText(volPMenuItem.getText());
    }

    /**
     * Event handler for TODO anne
     * @param event
     */
    @FXML
    void showVolKg(ActionEvent event) {
        setSeriesData(4);
        stackedBarYAxis.setLabel("Kg fødevare");
        diagramMenuBtn.setText(volKgMenuItem.getText());
    }

    /**
     * Event handler for TODO anne
     * @param event
     */
    @FXML
    void showCo2PrKg(ActionEvent event) {
        buildBarChart();
        diagramMenuBtn.setText(co2PrKgMenuItem.getText());
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
    public void switchToHistoryPage(ActionEvent event){
        App.switchScene(App.getHistoryPageParent());
    }

}
