import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculationPageController implements Initializable {

    //Categories represent foodcategories and volume represent CO2e.
    //These are used as datainputs for charts and should be replaced with real data
    String CategoryA = "Kød";
    int VolumeA = 60;
    String CategoryB = "Grønsager";
    int VolumeB = 25;
    String CategoryC = "Fisk";
    int VolumeC = 15;

    //Labels (visual text that shows Co2e). Should be replaced with real data
    @FXML
    private Label CO2TotLabel;
    @FXML
    private Label CO2PrKgLabel;

    String CO2Tot = "22";
    String CO2PrKg = "10";



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //calls methods to build charts
        buildPieChart();
        buildBarChart();

        //set value for labels
        this.CO2TotLabel.setText(CO2Tot);
        this.CO2PrKgLabel.setText(CO2PrKg);
    }

    //Pie chart
    @FXML
    PieChart MyPieChart;

    public void buildPieChart(){
        //Builds pieChart and stores data
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(CategoryA, VolumeA),
                        new PieChart.Data(CategoryB, VolumeB),
                        new PieChart.Data(CategoryC, VolumeC));
        MyPieChart.setData(pieChartData);
    }

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

    public void updateData(String categoryA, int volumeA, String categoryB, int volumeB, String categoryC, int volumeC){
        CategoryA = categoryA;
        VolumeA = volumeA;
        CategoryB = categoryB;
        VolumeB = volumeB;
        CategoryC = categoryC;
        VolumeC = volumeC;
    }

    //functions that switch scenes. connected to buttons
    FrontPageController Calculation = new FrontPageController();
    public void switchToSceneFrontPage(ActionEvent event) throws IOException {
        Calculation.switchToScene(event, "frontPage.fxml");
    }
    public void switchToDataInsertionPage(ActionEvent event) throws IOException {
        Calculation.switchToScene(event, "dataInsertionPage.fxml");
    }

}

