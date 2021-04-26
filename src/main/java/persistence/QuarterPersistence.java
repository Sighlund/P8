package persistence;

import model.CalculationModel;
import model.QuarterModel;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuarterPersistence {

//    /**
//     * Adds a Quarter object to the database
//     * @param quarter The Quarter object that is to be added
//     */
//    public static void addQuarter(QuarterModel quarter){
//        //Creating session
//        Session session = SetupPersistence.getSession();
//        //Saving object to database
//        session.save(quarter);
//        //Closing session
//        SetupPersistence.closeSession(session);
//    }
//
//    /**
//     * Updating a Quarter object in the database
//     * @param quarter The Quarter object that is to be added
//     */
//    public static void updateQuarter(QuarterModel quarter){
//        //Creating session
//        Session session = SetupPersistence.getSession();
//        //Updating object in database
//        session.update(quarter);
//        //Closing session
//        SetupPersistence.closeSession(session);
//    }

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

//
//    /**
//     * Method for finding a quarter object by ID
//     * @param id The ID of the quarter object that is being searched for
//     * @return Returns the object that is found through the ID
//     */
//    public static QuarterModel getQuarterById(Integer id){
//        //Creating session
//        Session session = SetupPersistence.getSession();
//        //Searching the database for the object with the provided ID
//        QuarterModel quarterModel = session.find(QuarterModel.class, id);
//        //Closing session
//        SetupPersistence.closeSession(session);
//        //Returning the found object
//        return quarterModel;
//    }
//
//    /**
//     * Method for deleting a quarter object by ID
//     * @param id The ID of the quarter object that is being searched for
//     */
//    public static void deleteQuarter(Integer id){
//        //Creating session
//        Session session = SetupPersistence.getSession();
//        //Searching the database for the object with the provided ID
//        QuarterModel quarterModel = session.find(QuarterModel.class, id);
//        //Deleting the object that was found
//        session.delete(quarterModel);
//        //Closing session
//        SetupPersistence.closeSession(session);
//    }
//
}
