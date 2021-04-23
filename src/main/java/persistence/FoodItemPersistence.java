package persistence;

import model.FoodDescriptorModel;
import model.FoodItemModel;
import org.hibernate.Session;

import java.util.List;

/**
 * The FoodItemPersistence class enables data exchange between the application and the SQL database.
 * It has 5 different Hibernate operations that allows for data transfer between SQL elements and Java objects.
 */

public class FoodItemPersistence {

    /**
     * Method for adding a calculation object to the database
     * @param item The foodItem object that is to be added
     */
    public static void addItem(FoodItemModel item) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Saving object to database
        session.save(item);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method for updating foodItem in the database
     * @param item The foodItem object that is to be added
     */
    public static void updateItem(FoodItemModel item) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Updating object in database
        session.update(item);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method to get all of the foodItems objects in the database
     * @return Returning a list of all the foodItem objects in the database
     */
    public static List<FoodItemModel> listItem() {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects
        List<FoodItemModel> list = session.createQuery("SELECT a from FoodItemModel a", FoodItemModel.class).getResultList();
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning list of objects retrieved from the database
        return list;
    }

    /**
     * Method for finding a foodItem object by ID
     * @param id The ID of the foodItem object that is being searched for
     * @return Returning the foodItem object that was searched for through the ID
     */
    public static FoodItemModel getItemById(Integer id) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        FoodItemModel item = session.find(FoodItemModel.class, id);
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning the found object
        return item;
    }

    /**
     * Method for deleting a foodItem object by ID
     * @param id The ID of the foodItem object that is being searched for
     */
    public static void deleteItem(Integer id) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        FoodItemModel item = session.find(FoodItemModel.class, id);
        //Deleting the object that was found
        session.delete(item);
        //Closing session
        SetupPersistence.closeSession(session);
    }
}
