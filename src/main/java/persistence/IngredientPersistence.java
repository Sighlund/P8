package persistence;

import model.IngredientModel;
import org.hibernate.Session;

import java.util.List;

/**
 * The IngredientPersistence class enables data exchange between the application and the SQL database.
 * It has 5 different Hibernate operations that allows for data transfer between SQL elements and Java objects.
 */

public class IngredientPersistence {

    /**
     * Method for adding an Ingredient object to the database
     * @param ingredient The ingredient object that is to be added
     */
    public static void addIngredient(IngredientModel ingredient) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Saving object to database
        session.save(ingredient);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method for updating object in the database
     * @param ingredient The ingredient object that is to be updated
     */
    public static void updateIngredient(IngredientModel ingredient) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Updating object in database
        session.update(ingredient);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method to get all of the ingredient objects in the database
     * @return Returning all of the ingredient objects in the database
     */
    public static List<IngredientModel> listIngredient() {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects
        List<IngredientModel> list = session.createQuery("SELECT a from IngredientModel a", IngredientModel.class).getResultList();
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning list of objects retrieved from the database
        return list;
    }

    /**
     * Method for finding a ingredient object by ID
     * @param id The ID of the ingredient object that is being searched for
     * @return Returning the ingredient object that was searched for through the ID
     */
    public static IngredientModel getIngredientById(Integer id) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        IngredientModel ingredient = session.find(IngredientModel.class, id);
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning the found object
        return ingredient;
    }

    /**
     * Method for deleting a ingredient object by ID
     * @param id The ID of the ingredient object that is being searched for
     */
    public static void deleteIngredient(Integer id) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        IngredientModel ingredient = session.find(IngredientModel.class, id);
        //Deleting the object that was found
        session.delete(ingredient);
        //Closing session
        SetupPersistence.closeSession(session);
    }
}
