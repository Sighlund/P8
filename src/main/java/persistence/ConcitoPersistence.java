package persistence;

import model.CalculationModel;
import model.ConcitoItemModel;
import org.hibernate.Session;

import java.util.List;

public class ConcitoPersistence {

    //Method to get all of the concito objects in the database
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

    //Method for finding a concito object by ID
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

    //Method for deleting a concito object by ID
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


