import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuItem;
import model.CalculationModel;

import java.net.URL;
import java.util.*;

public class CalculationComparisonPageController implements Initializable {

    @FXML
    private StackedBarChart<String, Number> MyStackedBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    @FXML
    private MenuItem co2PMenuItem;

    @FXML
    private MenuItem coKgMenuItem;

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
    }


    /**
     * Builds the stacked bar chart
     */
    private void buildStackedBarChart(){
        // Clear any previous data
        MyStackedBarChart.getData().clear();

        // Set label for y-axis and showTotals as false
        yAxis.setLabel("Procent af samlet CO2e for hele indkøbet");
        barChartOpt = 1;

        // Set label for x-axis
        xAxis.setLabel("Køkken og periode");

        // Set gap for the bar chart
        MyStackedBarChart.setCategoryGap(100);

        // Call private method to create series, add data to them, and add all series to the chart
        addSeriesToChart();

        // Store reference to all the displayed series
        this.displayedSeries = MyStackedBarChart.getData();

        // Add data to all series (default is percentage values)
        setSeriesData();

        // Set animation to false (it messes with the data when changing from percentage to totals and vice versa)
        MyStackedBarChart.setAnimated(false);

    }


    /**
     * Creates all series for the stacked bar chart based on
     * categories present in all calculations to be displayed
     * and adds them to the stacked bar chart
     */
    private void addSeriesToChart(){
        // Get categories to display as stacked bars
        categories = getAllCategories();

        // Iterate over list of categories
        for (String cat : categories) {

            // Create a new series for each category, each taking a String and a Number value
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

            // Set name of the new series to equal the current category
            series.setName(cat);

            // Add the new series (category) with all the data values to the stacked bar chart
            MyStackedBarChart.getData().add(series);
        }
    }


    /**
     * Sets the data values for each series in the stacked bar chart
     */
    private void setSeriesData(){
        // Iterate over the displayed series
        for (XYChart.Series<String, Number> s : displayedSeries){

            // Clear previous data in the current series
            s.getData().clear();

            // Iterate over all calculations to be displayed
            for (CalculationModel calc : calcs){

                // Create hash table to store values for the current calculation
                Hashtable<String, Double> ht = calc.getCategoriesCo2PercentagesHt();

                // If showTotals is selected: get hash table with total values for kg CO2 for each category
                // Else, get hash table with percentage values for each category
                if (barChartOpt == 2){
                    ht = calc.getCategoriesCo2KgHt();
                }
                else if (barChartOpt == 3){
                    //TODO show vol p
                }
                else if (barChartOpt == 4) {
                    // TODO show vol kg
                }
                else {
                    ht = calc.getCategoriesCo2PercentagesHt();
                }

                // Get set of keys in the hash table to be able
                // to access the categories and their values
                Set<String> keys = ht.keySet();

                // If the current category is present in the current calculation create a new Data object
                // with name of kitchen, quarter, year as name
                // and the corresponding hash table value as data value
                if (keys.contains(s.getName())) {
                    s.getData().add(new XYChart.Data<>(
                            (calc.getKitchen().toString() + " " + calc.getQuarter() + ". kvartal " + calc.getYear().toString()),
                            ht.get(s.getName())));
                }
            }
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




    // State to determine whether to show totals or percentages in stacked bar chart
    //private boolean showTotals = false;


    // 1 = Co2 percentage
    // 2 = Co2 kg
    // 3 = Volume percentage
    // 4 = Volume kg
    // 5 = Kg Co2 pr kg volume
    private int barChartOpt = 1;


    /**
     * Event handler for menu bar item: "Procent"
     * Updates all series with new data, displaying percentages for CO2 for each category
     * @param event
     */
    @FXML
    void showCo2P(ActionEvent event) {
        barChartOpt = 1;
        setSeriesData();
        yAxis.setLabel("Procent af samlet CO2e for hele indkøbet");
    }


    /**
     * Event handler for menu bar item: "Kg Co2"
     * Updates all series with new data, displaying CO2 totals for each category
     * @param event
     */
    @FXML
    void showCo2Kg(ActionEvent event) {
        barChartOpt = 2;
        setSeriesData();
        yAxis.setLabel("Kg CO2e");
    }


    @FXML
    void showVolKg(ActionEvent event) {

    }

    @FXML
    void showVolP(ActionEvent event) {

    }

    @FXML
    void showCo2PrKg(ActionEvent event) {

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
