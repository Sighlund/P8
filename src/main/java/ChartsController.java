import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChartsController implements Initializable {

    @FXML
    PieChart MyPieChart;

    String CategoryA = "Kød";
    int VolumeA = 60;
    String CategoryB = "Grønsager";
    int VolumeB = 25;
    String CategoryC = "Fisk";
    int VolumeC = 15;

    @FXML
    private Label CO2TotLabel;
    @FXML
    private Label CO2PrKgLabel;

    String CO2Tot = "22";
    String CO2PrKg = "10";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(CategoryA, VolumeA),
                        new PieChart.Data(CategoryB, VolumeB),
                        new PieChart.Data(CategoryC, VolumeC));
        MyPieChart.setData(pieChartData);

        this.CO2TotLabel.setText(CO2Tot);
        this.CO2PrKgLabel.setText(CO2PrKg);
    }

    FrontPageController Charts = new FrontPageController();

    public void switchToSceneFrontPage(ActionEvent event) throws IOException {
        Charts.switchToScene(event, "frontPage.fxml");
    }
}