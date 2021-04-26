package persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuarterPersistence {

    /**
     * Method to get all of the Quarter objects
     * @return Returns a list of all the Quarter objects
     */
    public static ObservableList<Integer> listQuarter(){
        //Create a list of four quarters
        ObservableList<Integer> list = FXCollections.observableArrayList(1,2,3,4);
        //Returning list of quarters retrieved from the above code
        return list;
    }
}
