package persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.YearModel;
import org.hibernate.Session;

import java.time.Year;
import java.util.List;

/**
 * The YearPersistence class enables data exchange between the application and the SQL database.
 * It has 5 different Hibernate operations that allows for data transfer between SQL elements and Java objects.
 */

public class YearPersistence {

    /**
     * Adds a year object to the database
     * @param year The year object that is to be added
     */
    public static void addYear(YearModel year){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Saving object to database
        session.save(year);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Updating a year object in the database
     * @param year The year object that is to be added
     */
    public static void updateYear(YearModel year){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Updating object in database
        session.update(year);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method to get all of the year objects in the database
     * @return Returns a list of all the year objects in the database
     */
    public static ObservableList<YearModel> listYear(){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects
        List<YearModel> query = session.createQuery("SELECT a from YearModel a", YearModel.class).getResultList();
        //Closing session
        ObservableList<YearModel> years = FXCollections.observableArrayList(query);
        SetupPersistence.closeSession(session);
        //Returning list of objects retrieved from the database
        return years;
    }

    /**
     * Method for finding a year object by ID
     * @param id The ID of the year object that is being searched for
     * @return Returns the object that is found through the ID
     */
    public static YearModel getYearById(Integer id){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        YearModel yearModel = session.find(YearModel.class, id);
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning the found object
        return yearModel;
    }

    /**
     * Method for deleting a year object by ID
     * @param id The ID of the year object that is being searched for
     */
    public static void deleteYear(Integer id){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        YearModel yearModel = session.find(YearModel.class, id);
        //Deleting the object that was found
        session.delete(yearModel);
        //Closing session
        SetupPersistence.closeSession(session);
    }
}
