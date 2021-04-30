package persistence;

import model.ConcitoItemModel;
import model.FoodDescriptorModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
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
        //Returning list of objects retrieved from the database
        return list;
    }

    //Method returning a List<String> object containing all the names of Concito Items.
    public static List<String> listConcitoName(){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects' names
        List<String> list = session.createQuery("select c.name from ConcitoItemModel c").list();
        //Return list of objects' names retrieved from the database
        return list;
    }

    //Method that will return the ConcitoItemModel object that matches the String Name provided.
    public static ConcitoItemModel getConcitoByName(String name){
        Session session = SetupPersistence.getSession();
        String hql = "from ConcitoItemModel c where c.name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        //If the provided name doesn't match a result in the database, it will throw an exception.
        //The exception is caught by the method that called getConcitoByName().
        ConcitoItemModel concitoItemModel = (ConcitoItemModel) query.getSingleResult();
        //Returns the object if there was a match.
        return concitoItemModel;
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

    /**
     * Method to get all distinct categories from the current concito items in the database
     * @return a list of distinct category names as strings
     */
    public static List<String> getDistinctCategories(){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the category names, selecting only distinct category names
        List<String> categories = session.createQuery("select distinct c.category from ConcitoItemModel c").list();
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning list of category names
        return categories;
    }

}


