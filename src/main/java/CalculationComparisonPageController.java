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
import java.util.List;
import java.util.ResourceBundle;

public class CalculationComparisonPageController implements Initializable {

    @FXML
    private StackedBarChart<String, Number> MyStackedBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    String enhed = "enhed1";
    String enhed2 = "enhed2";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //calls methods to build the stacked bar chart
    }

    //Categories used for stacking the bar chart
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

    //Building StackedBarChart
    public void buildStackedBarChart(String enhed){
        XYChart.Series<String, Number> Category1 = new XYChart.Series<>();
        Category1.setName(arr.get(0));
        Category1.getData().add(new XYChart.Data<>(enhed, 7));
        //Category1.getData().add(new XYChart.Data<>("enhed2", 8));

        XYChart.Series<String, Number> Category2 = new XYChart.Series<>();
        Category2.setName(arr.get(1));
        Category2.getData().add(new XYChart.Data<>(enhed, 9));
        //Category2.getData().add(new XYChart.Data<>("enhed2", 8));

        XYChart.Series<String, Number> Category3 = new XYChart.Series<>();
        Category3.setName(arr.get(2));
        Category3.getData().add(new XYChart.Data<>(enhed, 14));
        //Category3.getData().add(new XYChart.Data<>("enhed2", 9));

        XYChart.Series<String, Number> Category4 = new XYChart.Series<>();
        Category4.setName(arr.get(3));
        Category4.getData().add(new XYChart.Data<>(enhed, 7));
        //Category4.getData().add(new XYChart.Data<>("enhed2", 8));

        XYChart.Series<String, Number> Category5 = new XYChart.Series<>();
        Category5.setName(arr.get(4));
        Category5.getData().add(new XYChart.Data<>(enhed, 9));
        //Category5.getData().add(new XYChart.Data<>("enhed2", 8));

        XYChart.Series<String, Number> Category6 = new XYChart.Series<>();
        Category6.setName(arr.get(5));
        Category6.getData().add(new XYChart.Data<>(enhed, 8));
        //Category6.getData().add(new XYChart.Data<>("enhed2", 8));

        XYChart.Series<String, Number> Category7 = new XYChart.Series<>();
        Category7.setName(arr.get(6));
        Category7.getData().add(new XYChart.Data<>(enhed, 13));
        //Category7.getData().add(new XYChart.Data<>("enhed2", 8));

        XYChart.Series<String, Number> Category8 = new XYChart.Series<>();
        Category8.setName(arr.get(7));
        Category8.getData().add(new XYChart.Data<>(enhed, 8));
        //Category8.getData().add(new XYChart.Data<>("enhed2", 8));

        XYChart.Series<String, Number> Category9 = new XYChart.Series<>();
        Category9.setName(arr.get(8));
        Category9.getData().add(new XYChart.Data<>(enhed, 8));
        //Category9.getData().add(new XYChart.Data<>("enhed2", 12));

        XYChart.Series<String, Number> Category10 = new XYChart.Series<>();
        Category10.setName(arr.get(9));
        Category10.getData().add(new XYChart.Data<>(enhed, 5));
        //Category10.getData().add(new XYChart.Data<>("enhed2", 5));

        XYChart.Series<String, Number> Category11 = new XYChart.Series<>();
        Category11.setName(arr.get(10));
        Category11.getData().add(new XYChart.Data<>(enhed, 6));
        //Category11.getData().add(new XYChart.Data<>("enhed2", 7));

        XYChart.Series<String, Number> Category12 = new XYChart.Series<>();
        Category12.setName(arr.get(11));
        Category12.getData().add(new XYChart.Data<>(enhed, 10));
        //Category12.getData().add(new XYChart.Data<>("enhed2", 12));

        XYChart.Series<String, Number> Category13 = new XYChart.Series<>();
        Category13.setName(arr.get(12));
        Category13.getData().add(new XYChart.Data<>(enhed, 1));
        //Category13.getData().add(new XYChart.Data<>("enhed2", 0));

        MyStackedBarChart.setCategoryGap(200);
        MyStackedBarChart.getData().addAll(Category1, Category2, Category3, Category4, Category5, Category6, Category7, Category8, Category9, Category10, Category11, Category12, Category13);
    }

    //protected void addSeries(){
     //   XYChart.Series<String, Number> enhed3 = new XYChart.Series<>();
      //  MyStackedBarChart.
    //}

    int calc;
    //Receives information from HistoryPageController
    public void getInformation(int calcid) {
        this.calc=calcid;
        MyStackedBarChart.getData().clear();
        buildStackedBarChart(enhed);
        buildStackedBarChart(enhed2);
        System.out.println(CalculationPersistence.getCalcById(calcid));
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
