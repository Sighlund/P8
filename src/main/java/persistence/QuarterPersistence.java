package persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuarterPersistence {

    /**
     * Method to get all of the Quarter objects
     * @return Returns a list of all the Quarter objects
     */
    public static List<Integer> listQuarter(){
        //Create a list of four quarters
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4));
        //Returning list of quarters retrieved from the above code
        return list;
    }
}
