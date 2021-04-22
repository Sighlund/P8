package persistence;

//Import needed classes
import model.CalculationModel;
import org.hibernate.Session;

import java.util.List;

public class CalculationPersistence {

    //Method for adding a calculation object to the database
    public static void addCalc(CalculationModel calc){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Saving object to database
        session.save(calc);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    //Method for updating object in the database
    public static void updateCalc(CalculationModel calc){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Updating object in database
        session.update(calc);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    //Method to get all of the calculation objects in the database
    public static List<CalculationModel> listCalc(){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects
        List<CalculationModel> list = session.createQuery("SELECT a from CalculationModel a", CalculationModel.class).getResultList();
        //Closing session
        SetupPersistence.closeSession(session);
        //Returning list of objects retrieved from the database
        return list;
    }

    //Method for finding a calculation object by ID
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

    //Method for deleting a calculation object by ID
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
