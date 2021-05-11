package persistence;

//Import needed classes
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CalculationModel;
import model.KitchenModel;
import model.YearModel;
import netscape.javascript.JSObject;
import org.hibernate.Session;

import javax.persistence.NamedNativeQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Iterator;
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
        SetupPersistence.closeSession(session);
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
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning the found object
        return calculationModel;
    }

    /**
     * Method for checking if a calculation exists containing the user input from the DataInsertionPage
     * @param kitchen Takes the chosen kitchen as an Integer as that is how it is stored in the DB
     * @param quarter Takes the chosen quarter
     * @param year Takes the chosen year
     * @return Returns the calculation object if a calculation with the user input exists. Otherwise it returns null
     */
    public static CalculationModel getCalcFromChoicebox(Integer kitchen, Integer quarter, Integer year){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Creating an SQL query that selects the id of a calculation where all the parameters from the argument list
        //is present
        String sql = "select id from calculation where id = id AND kitchenId = :kitchen AND quarter = :quarter AND yearId = :year";
        //Converting the SQL query string into an actual query
        Query query = session.createNativeQuery(sql);
        //Setting paramters for the variables in the SQL string
        query.setParameter("kitchen", kitchen);
        query.setParameter("quarter", quarter);
        query.setParameter("year", year);
        //Saving the query result in a list of objects, because that is the return type
        List<Object> result = query.getResultList();
        //Creating a calculation object and setting it to null
        CalculationModel calculation = null;
        //Closing the session
        SetupPersistence.closeSession(session);

        //Iterating over the results' size. If the query did not find any calculation containing the parameters from the
        //argument list then the size will be zero, and it will skip this for loop. If the query did find an calculation
        //id then it will loop through the for loop
        for(int i=0; i<result.size();i++){
            //Creating a Interger to store the id from calculation object that was found in the query
            Integer id = (Integer) result.get(i);
            //Querying the DB for the calculation object with the ID that was found by the query and saving it in the
            //calculation object
            calculation = getCalcById(id);
        }
        //Returning the calculation object. If a calculation was found then the object that was found will be store in
        //this calculation object. If the query did not find anything this will be null
        return calculation;

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
