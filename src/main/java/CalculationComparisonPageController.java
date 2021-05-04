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

    // TODO Skal slettes
    private CalculationModel calc;

    //Building StackedBarChart
    public void buildStackedBarChart(String... args){

        MyStackedBarChart.getData().clear();
        categories.clear();

        Hashtable<String, Double> dict = calc.getCategoriesPercentagesDict();

        Set<String> keys = dict.keySet();

        // Skal ændres! lige nu kun baseret på én calculation
        for (String k : keys){
            categories.add(k);
        }

        // Laver en serie pr. kategori
        for (String c : categories) {
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            series.setName(c);

            // Hvis kategorien er repræsenteret i calculation, lav en data entry
            if (keys.contains(c)) {
                series.getData().add(new XYChart.Data<>(calc.getKitchen().toString(), dict.get(c)));
            }

            MyStackedBarChart.getData().add(series);


            MyStackedBarChart.setCategoryGap(200);
        }
    }

    //Receives information from HistoryPageController
    public void getInformation(CalculationModel calcid) {
        this.calc=calcid;
        //MyStackedBarChart.getData().clear();
        System.out.println(calcid.getKitchen());

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
