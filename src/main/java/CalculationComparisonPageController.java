import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import persistence.ConcitoPersistence;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CalculationComparisonPageController implements Initializable {

    @FXML
    private StackedBarChart<String, Number> MyStackedBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //calls methods to build charts
        buildStackedBarChart();
    }

    List<String> arr = ConcitoPersistence.getDistinctCategories();
    String Category1 = arr.get(0);
    String Category2 = arr.get(1);
    String Category3 = arr.get(2);
    String Category4 = arr.get(3);
    String Category5 = arr.get(4);
    String Category6 = arr.get(5);
    String Category7 = arr.get(6);
    String Category8 = arr.get(7);
    String Category9 = arr.get(8);
    String Category10 = arr.get(9);
    String Category11 = arr.get(10);
    String Category12 = arr.get(11);
    String Category13 = arr.get(12);


    public void buildStackedBarChart(){

        XYChart.Series<String, Number> Category1 = new XYChart.Series<>();
        Category1.setName(arr.get(0));

        Category1.getData().add(new XYChart.Data<>("enhed1", 8));
        Category1.getData().add(new XYChart.Data<>("enhed2", 8));

        XYChart.Series<String, Number> Category2 = new XYChart.Series<>();
        Category2.setName(arr.get(1));

        Category2.getData().add(new XYChart.Data<>("enhed1", 8));
        Category2.getData().add(new XYChart.Data<>("enhed2", 8));

        MyStackedBarChart.getData().addAll(Category1, Category2);
    }

    int comparisonCalcId;
    //Receives information from HistoryPageController
    public void getInformation(int calcid) {
        this.comparisonCalcId=calcid;
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
