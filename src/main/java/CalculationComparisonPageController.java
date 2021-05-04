import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import model.CalculationModel;
import persistence.CalculationPersistence;
import persistence.ConcitoPersistence;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class CalculationComparisonPageController implements Initializable {

    @FXML
    private StackedBarChart<String, Number> MyStackedBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //calls methods to build the stacked bar chart
    }

    private List<String> categories = new ArrayList<>();
    private List<CalculationModel> calcs = new ArrayList<>();

    //Building StackedBarChart
    public void buildStackedBarChart(String... args){
        // Clear any previous data
        MyStackedBarChart.getData().clear();
        categories.clear();

        // Get categories to display as stacked bars
        categories = getAllCategories();


        // Iterate over list of categories
        for (String cat : categories) {

            // Create a new series for each category, each taking a String and a Number value
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

            // Set name of new series to equal the current category
            series.setName(cat);

            // Iterate over all calculations to be displayed
            for (CalculationModel c : calcs){

                // Get hash table with percentages for each category in the current calculation
                Hashtable<String, Double> ht = c.getCategoriesPercentagesDict();

                // Get set of keys in the hash table to be able to access the categories present in the calculation
                Set<String> keys = ht.keySet();

                // If the current category is present in the current calculation create a new Data object
                // with the name of the kitchen for the calculation
                // and the percentage value for the current category
                if (keys.contains(cat)) {
                    series.getData().add(new XYChart.Data<>(c.getKitchen().toString(), ht.get(cat)));
                }
            }

            // Add the new series (category) to the stacked bar chart
            MyStackedBarChart.getData().add(series);

        }

        // Set gap for the bar chart
        MyStackedBarChart.setCategoryGap(200);
    }

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

    //Receives information from HistoryPageController
    public void getInformation(ObservableList<CalculationModel> calcs) {
        this.calcs=calcs;
        //MyStackedBarChart.getData().clear();
        //ystem.out.println(calcid.getKitchen());

        buildStackedBarChart();
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
