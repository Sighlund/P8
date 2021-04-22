package persistence;

import model.ConcitoItemModel;
import model.FoodItemModel;
import model.IngredientModel;
import org.hibernate.Session;

import java.util.List;

public class IngredientPersistence {

    //Method for adding an Ingredient object to the database
    public static void addIngredient(IngredientModel ingredient) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Saving object to database
        session.save(ingredient);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    //Method for updating object in the database
    public static void updateIngredient(IngredientModel ingredient) {
        //Creating session
        Session session = SetupPersistence.getSession();
        //Updating object in database
        session.update(ingredient);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    //Method to get all of the ingredient objects in the database
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

    //Method for finding a ingredient object by ID
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

    //Method for deleting a ingredient object by ID
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
