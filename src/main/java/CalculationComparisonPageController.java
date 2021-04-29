import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class CalculationComparisonPageController {



    @FXML
    private StackedBarChart<String, Number> MyStackedBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public void buildStackedBarChart(){
        XYChart.Series<String, Number> data2 = new XYChart.Series<>();
        data2.setName("data2");

        data2.getData().add(new XYChart.Data<>("jan", 300));
        data2.getData().add(new XYChart.Data<>("feb", 400));
        data2.getData().add(new XYChart.Data<>("mar", 500));

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.setName("data");

        data.getData().add(new XYChart.Data<>("jan", 200));
        data.getData().add(new XYChart.Data<>("feb", 300));
        data.getData().add(new XYChart.Data<>("mar", 400));

        MyStackedBarChart.getData().addAll(data, data2);
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
