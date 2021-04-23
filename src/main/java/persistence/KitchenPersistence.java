package persistence;

import model.IngredientModel;
import model.KitchenModel;
import org.hibernate.Session;

import java.util.List;

/**
 * The KitchenPersistence class enables data exchange between the application and the SQL database.
 * It has 5 different Hibernate operations that allows for data transfer between SQL elements and Java objects.
 */

public class KitchenPersistence {

    /**
     * Method for adding a kitchen object to the database
     * @param kitchen The kitchen object that is to be added
     */
    public static void addKitchen(KitchenModel kitchen) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Saving object to database
        session.save(kitchen);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method for updating object in the database
     * @param kitchen The kitchen object that is to be updated
     */
    public static void updateKitchen(KitchenModel kitchen) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Updating object in database
        session.update(kitchen);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method to get all of the kitchen objects in the database
     * @return Returns a list of all the kitchen objects in the database
     */
    public static List<KitchenModel> listKitchen() {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects
        List<KitchenModel> list = session.createQuery("SELECT a from KitchenModel a", KitchenModel.class).getResultList();
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning list of objects retrieved from the database
        return list;
    }

    /**
     * Method for finding a kitchen object by ID
     * @param id The ID of the kitchen object that is being searched for
     * @return Returning the kitchen object that was searched for through the ID
     */
    public static KitchenModel getKitchenById(Integer id) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        KitchenModel kitchen = session.find(KitchenModel.class, id);
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning the found object
        return kitchen;
    }

    /**
     * Method for deleting a kitchen object by ID
     * @param id The ID of the kitchen object that is being searched for
     */
    public static void deleteKitchen(Integer id) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        KitchenModel kitchen = session.find(KitchenModel.class, id);
        //Deleting the object that was found
        session.delete(kitchen);
        //Closing session
        SetupPersistence.closeSession(session);
    }
}
