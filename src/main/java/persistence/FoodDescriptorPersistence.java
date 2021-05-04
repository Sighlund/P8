package persistence;

import model.FoodDescriptorModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * The FoodDescriptorPersistence class enables data exchange between the application and the SQL database.
 * It has 5 different Hibernate operations that allows for data transfer between SQL elements and Java objects.
 */

public class FoodDescriptorPersistence {

    /**
     * Method for adding a foodDescriptor object to the database
     * @param descriptor The foodDescriptor object that is to be added
     */
    public static void addDescriptor(FoodDescriptorModel descriptor){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Saving object to database
        session.save(descriptor);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method for updating object in the database
     * @param descriptor the foodDescriptor object that is to be updated
     */
    public static void updateDescriptor(FoodDescriptorModel descriptor){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Updating object in database
        session.update(descriptor);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method to get all of the foodDescriptor objects in the database
     * @return Returning a list of all the foodDescriptor objects in the databse
     */
    public static List<FoodDescriptorModel> listDescriptor(){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects
        List<FoodDescriptorModel> list = session.createQuery("SELECT a from FoodDescriptorModel a", FoodDescriptorModel.class).getResultList();
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning list of objects retrieved from the database
        return list;
    }

    public static List<String> listDescriptorName(){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects' names
        List<String> list = session.createQuery("select c.name from FoodDescriptorModel c").list();
        SetupPersistence.closeSession(session);
        //Return list of objects' names retrieved from the database
        return list;
    }

    //Method returns a FoodDescriptorModel when the provided String name matches an existing descriptor from the database.
    public static FoodDescriptorModel getDescriptorByName(String name){
        Session session = SetupPersistence.getSession();
        String hql = "from FoodDescriptorModel c where c.name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        //Returns a FoodDescriptor object based on the result found
        FoodDescriptorModel foodDescriptorModel = (FoodDescriptorModel) query.getSingleResult();
        SetupPersistence.closeSession(session);
        return foodDescriptorModel;
    }

    /**
     * Method for finding a foodDescriptor object by ID
     * @param id The ID of the foodDescriptor object that is being searched for
     * @return Returning the foodDescriptor object that was searched for through the ID
     */
    public static FoodDescriptorModel getDescriptorById(Integer id){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        FoodDescriptorModel descriptor = session.find(FoodDescriptorModel.class, id);
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning the found object
        return descriptor;
    }

    /**
     * Method for deleting a foodDescriptor object by ID
     * @param id The ID of the foodDescriptor object that is being searched for
     */
    public static void deleteDescriptor(Integer id){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        FoodDescriptorModel descriptor = session.find(FoodDescriptorModel.class, id);
        //Deleting the object that was found
        session.delete(descriptor);
        //Closing session
        SetupPersistence.closeSession(session);
    }
}
