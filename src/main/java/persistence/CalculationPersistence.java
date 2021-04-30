package persistence;

//Import needed classes
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CalculationModel;
import org.hibernate.Session;

import java.util.List;

/**
 * The CalculationPersistence class enables data exchange between the application and the SQL database.
 * It has 5 different Hibernate operations that allows for data transfer between SQL elements and Java objects.
 */
public class CalculationPersistence {

    /**
     * Adds a calculation object to the database
     * @param calc The calculation object that is to be added
     */
    public static void addCalc(CalculationModel calc){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Saving object to database
        session.save(calc);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Updating a calculation object in the database
     * @param calc The calculation object that is to be added
     */
    public static void updateCalc(CalculationModel calc){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Updating object in database
        session.update(calc);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method to get all of the calculation objects in the database
     * @return Returns an oberservable list of all the calculation objects in the database
     */
    public static ObservableList<CalculationModel> listCalc(){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects, also fetching associated kitchen and year objects
        List<CalculationModel> list = session.createQuery("SELECT a from CalculationModel a JOIN fetch a.kitchen JOIN fetch a.year", CalculationModel.class).getResultList();
        ObservableList<CalculationModel> calculations = FXCollections.observableArrayList(list);
        //Returning list of objects retrieved from the database
        return calculations;
    }

    /**
     * Method for finding a calculation object by ID
     * @param id The ID of the calculation object that is being searched for
     * @return Returns the object that is found through the ID
     */
    public static CalculationModel getCalcById(Integer id){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        CalculationModel calculationModel = session.find(CalculationModel.class, id);
        //Returning the found object
        return calculationModel;
    }

    /**
     * Method for deleting a calculation object by ID
     * @param id The ID of the calculation object that is being searched for
     */
    public static void deleteCalc(Integer id){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        CalculationModel calculationModel = session.find(CalculationModel.class, id);
        //Deleting the object that was found
        session.delete(calculationModel);
        //Closing session
        SetupPersistence.closeSession(session);
    }
}
