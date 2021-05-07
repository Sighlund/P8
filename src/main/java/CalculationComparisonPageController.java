import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.CalculationModel;

import java.lang.reflect.Array;
import java.net.URL;
import java.text.DecimalFormat;
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

    @FXML
    private Button InfoButton1;

    @FXML
    private Button InfoButton2;

    @FXML
    private Button InfoButton3;

    @FXML
    private Button InfoButton4;

    @FXML
    private CheckBox checkbox1;

    @FXML
    private CheckBox checkbox2;

    @FXML
    private CheckBox checkbox3;

    @FXML
    private CheckBox checkbox4;

    @FXML
    private Label LabelKategori1;

    @FXML
    private Label LabelKategori2;

    @FXML
    private Label LabelKategori3;

    @FXML
    private Label LabelKategori4;

    @FXML
    private Label viskategorierLabel2;

    @FXML
    private Label viskategorierLabel3;

    @FXML
    private Label viskategorierLabel4;

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

        // Updates labels with co2prkg, kitchen and quarter
        getBasicTextInfo();

        // Display values from calculation(s) as CO2 values in percentage for each category
        // by calling event handler for that specific menu item in menu button
        showCo2P(new ActionEvent());
    }

    // Arrays used to store information for labels
    String[] calInfoText = new String[4];
    String[] labelCategoryText = new String[4];

    // x is used when assigning basic text to labels
    String x;

    // string used for hover effect
    private static final String backGroundColor="-fx-background-color: #018849";
    private static final String hoverColor="-fx-background-color:  #019849";

    public void getBasicTextInfo(){
        // loops through calcs size and updates labels with co2prkg, kitchen and quarter. LabelCategoryText is set to "" to avoid null error
        for(int i=0; i<calcs.size(); i++) {
            this.x = calcs.get(i).getKitchen().toString()
                    + "\n" + "År " + calcs.get(i).getYear().toString() + ", kvartal " + calcs.get(i).getQuarter().toString() + "\n" + "Co2 pr Kg: " + format(calcs.get(i).calcAveCO2prKg()) + "\n";
        Array.set(calInfoText,i,x);
        labelCategoryText[i]="";
        }

        // buttons are updated with basic text.
        // hover effect is added
        InfoButton1.setText(calInfoText[0]);
        InfoButton1.setOnMouseEntered(e -> InfoButton1.setStyle(hoverColor));
        InfoButton1.setOnMouseExited(e -> InfoButton1.setStyle(backGroundColor));
        InfoButton2.setText(calInfoText[1]);
        InfoButton2.setOnMouseEntered(e -> InfoButton2.setStyle(hoverColor));
        InfoButton2.setOnMouseExited(e -> InfoButton2.setStyle(backGroundColor));
        InfoButton3.setText(calInfoText[2]);
        InfoButton3.setOnMouseEntered(e -> InfoButton3.setStyle(hoverColor));
        InfoButton3.setOnMouseExited(e -> InfoButton3.setStyle(backGroundColor));
        InfoButton4.setText(calInfoText[3]);
        InfoButton4.setOnMouseEntered(e -> InfoButton4.setStyle(hoverColor));
        InfoButton4.setOnMouseExited(e -> InfoButton4.setStyle(backGroundColor));

        // we adjust the labels to the minor 'bug' that new stacked charts are added in the middle
//        if(calcs.size()==3){
//            InfoButton2.setText(calInfoText[2]);
//            InfoButton3.setText(calInfoText[1]);
//        }
//        if(calcs.size()==4){
//            InfoButton2.setText(calInfoText[2]);
//            InfoButton3.setText(calInfoText[3]);
//            InfoButton4.setText(calInfoText[1]);
//        }

        // other than calc 1 is set as non visible unless there is a calculation that matches
        InfoButton2.setVisible(false);
        checkbox2.setVisible(false);
        viskategorierLabel2.setVisible(false);

        InfoButton3.setVisible(false);
        checkbox3.setVisible(false);
        viskategorierLabel3.setVisible(false);

        InfoButton4.setVisible(false);
        checkbox4.setVisible(false);
        viskategorierLabel4.setVisible(false);

        LabelKategori1.setVisible(false);
        LabelKategori2.setVisible(false);
        LabelKategori3.setVisible(false);
        LabelKategori4.setVisible(false);

        if(calcs.size()>1){
            InfoButton2.setVisible(true);
            checkbox2.setVisible(true);
            viskategorierLabel2.setVisible(true);
            if(calcs.size()>2){
                InfoButton3.setVisible(true);
                checkbox3.setVisible(true);
                viskategorierLabel3.setVisible(true);
                if(calcs.size()>3){
                    InfoButton4.setVisible(true);
                    checkbox4.setVisible(true);
                    viskategorierLabel4.setVisible(true);
                }
            }
        }
    }

    // sets decimal number to two. Used when updating label information
    private String format(Double d){
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String format = numberFormat.format(d);
        return format;
    }

    // Following 4 similar functions shows category information if checkbox is checked
    public void Label1Visible(ActionEvent event){
        if(checkbox1.isSelected()) {
            LabelKategori1.setVisible(true);
        }
        else{
            LabelKategori1.setVisible(false);
        }
    }

    public void Label2Visible(ActionEvent event){
        if(checkbox2.isSelected()) {
            LabelKategori2.setVisible(true);
        }
        else{
            LabelKategori2.setVisible(false);
        }
    }

    public void Label3Visible(ActionEvent event){
        if(checkbox3.isSelected()) {
            LabelKategori3.setVisible(true);
        }
        else{
            LabelKategori3.setVisible(false);
        }
    }

    public void Label4Visible(ActionEvent event){
        if(checkbox4.isSelected()) {
            LabelKategori4.setVisible(true);
        }
        else{
            LabelKategori4.setVisible(false);
        }
    }

    // Private property to hold the parent scene for the comparison page
    private Parent thisFxml;

    public void setThisFxml(Parent myFxml) {
        this.thisFxml = myFxml;
    }

    public void switchToCalculationPageCalc1(ActionEvent event) {
            App.getCalculationController().updateCalculationView(calcs.get(0));
            App.getCalculationController().setCameFrom(thisFxml);
            App.switchScene(App.getCalculationPageParent());

    }

    public void switchToCalculationPageCalc2(ActionEvent event) {
        App.getCalculationController().updateCalculationView(calcs.get(1));
        App.getCalculationController().setCameFrom(thisFxml);
        App.switchScene(App.getCalculationPageParent());
    }

    public void switchToCalculationPageCalc3(ActionEvent event) {
        App.getCalculationController().updateCalculationView(calcs.get(2));
        App.getCalculationController().setCameFrom(thisFxml);
        App.switchScene(App.getCalculationPageParent());
    }

    public void switchToCalculationPageCalc4(ActionEvent event) {
        App.getCalculationController().updateCalculationView(calcs.get(3));
        App.getCalculationController().setCameFrom(thisFxml);
        App.switchScene(App.getCalculationPageParent());
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

        getBasicTextInfo();

        // Ensure that the simple bar chart is hidden
        if (barChart.isVisible()){
            barChart.setVisible(false);
        }

        // Iterate over the series in the stacked bar chart
        for (XYChart.Series<String, Number> s : displayedSeries){

            // Clear previous data in the current series
            s.getData().clear();

            int i = 0;

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
                // sets label category text
                if (keys.contains(s.getName())) {
                    s.getData().add(new XYChart.Data<>(
                            (calc.getKitchen().toString() + " " +
                                    calc.getQuarter() + ". kvartal " +
                                    calc.getYear().toString()),
                            ht.get(s.getName())));

                    labelCategoryText[i] = labelCategoryText[i] + s.getName() + ": "+ "\n" + format(ht.get(s.getName()));
                    if(option==1 || option==3 || option==5){
                        labelCategoryText[i] = labelCategoryText[i]+"%"  + "\n";
                    }
                    else{
                        labelCategoryText[i] = labelCategoryText[i]+"kg"  + "\n";
                    }
                }
                i++;
            }
        }

        // assigns label category text
        LabelKategori1.setText(labelCategoryText[0]);
        LabelKategori2.setText(labelCategoryText[1]);
        LabelKategori3.setText(labelCategoryText[2]);
        LabelKategori3.setText(labelCategoryText[3]);
        // we adjust the labels to the minor 'bug' that new stacked charts are added in the middle
        if(calcs.size()==3){
            LabelKategori2.setText(labelCategoryText[2]);
            LabelKategori3.setText(labelCategoryText[1]);
        }
        if(calcs.size()==4){
            LabelKategori2.setText(labelCategoryText[2]);
            LabelKategori3.setText(labelCategoryText[3]);
            LabelKategori4.setText(labelCategoryText[1]);
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
