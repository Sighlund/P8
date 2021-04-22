package persistence;

import model.CalculationModel;
import model.ConcitoItemModel;
import org.hibernate.Session;

import java.util.List;

/**
 * The ConcitoPersistence class enables data exchange between the application and the SQL database.
 * It has 3 different Hibernate operations that allows for data transfer between SQL elements and Java objects.
 */
public class ConcitoPersistence {

    /**
     * Method to get all of the concito objects in the database
     * @return Returns a list of all the concito objects in the database
     */
    public static List<ConcitoItemModel> listCon(){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects
        List<ConcitoItemModel> list = session.createQuery("SELECT a from ConcitoItemModel a", ConcitoItemModel.class).getResultList();
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning list of objects retrieved from the database
        return list;
    }

    /**
     * Method for finding a concito object by ID
     * @param id The ID of the concito object that is being searched for
     * @return Returning the concito object that was searched for through the ID
     */
    public static ConcitoItemModel getConById(Integer id){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        ConcitoItemModel concitoItemModel = session.find(ConcitoItemModel.class, id);
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning the found object
        return concitoItemModel;
    }

    /**
     * Method for deleting a concito object by ID
     * @param id The ID of the concito object that is being searched for
     */
    public static void deleteCon(Integer id){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        ConcitoItemModel concitoItemModel = session.find(ConcitoItemModel.class, id);
        //Deleting the object that was found
        session.delete(concitoItemModel);
        //Closing session
        SetupPersistence.closeSession(session);
    }

}


