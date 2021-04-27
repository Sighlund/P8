package persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The QuarterPersistence class implements a mock persistence service layer as there is no Quarter entity in the database.
 * It has only one method which returns an observable list of all possible quarters (1, 2, 3, 4).
 */

public class QuarterPersistence {

    /**
     * Method to get all quarters in an observable list
     * @return Returns an observable list of all quarters
     */
    public static ObservableList<Integer> listQuarter(){
        //Create a list of four quarters
        ObservableList<Integer> list = FXCollections.observableArrayList(1,2,3,4);
        //Returning list of quarters retrieved from the above code
        return list;
    }
}
