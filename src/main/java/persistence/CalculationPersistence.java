package persistence;

//Import needed classes
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CalculationModel;
import model.KitchenModel;
import model.YearModel;
import netscape.javascript.JSObject;
import org.hibernate.Session;

import javax.persistence.NamedNativeQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The CalculationPersistence class enables data exchange between the application and the SQL database.
 * It has 5 different Hibernate operations that allows for data transfer between SQL elements and Java objects.
 */
public class CalculationPersistence {

    /**
     * Adds a calculation object to the database
     * @param calc The calculation object that is to be added
     */
    public static void addCalc(CalculationModel calc){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Saving object to database
        session.save(calc);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Updating a calculation object in the database
     * @param calc The calculation object that is to be added
     */
    public static void updateCalc(CalculationModel calc){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Updating object in database
        session.update(calc);
        //Closing session
        SetupPersistence.closeSession(session);
    }

    /**
     * Method to get all of the calculation objects in the database
     * @return Returns an oberservable list of all the calculation objects in the database
     */
    public static ObservableList<CalculationModel> listCalc(){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Querying database for all objects, also fetching associated kitchen and year objects
        List<CalculationModel> list = session.createQuery("SELECT a from CalculationModel a JOIN fetch a.kitchen JOIN fetch a.year", CalculationModel.class).getResultList();
        ObservableList<CalculationModel> calculations = FXCollections.observableArrayList(list);
        SetupPersistence.closeSession(session);
        //Returning list of objects retrieved from the database
        return calculations;

    }

    /**
     * Method for finding a calculation object by ID
     * @param id The ID of the calculation object that is being searched for
     * @return Returns the object that is found through the ID
     */
    public static CalculationModel getCalcById(Integer id){
        //Creating session
        Session session = SetupPersistence.getSession();
        //Searching the database for the object with the provided ID
        CalculationModel calculationModel = session.find(CalculationModel.class, id);
        SetupPersistence.closeSession(session);
        //Returning the found object
        return calculationModel;
    }


    //TODO problemet her hænger sammen med DataInsertionPageController linje 96(Der er en TODO der, hvis det ikke er
    //på linje 96 mere. Vi (Søren og Mads) kan ikke få det til at virke, så feel free to try
//    public static void getCalcFromChoicebox(Integer kitchen, Integer quarter, Integer year){
//        //Creating session
//        Session session = SetupPersistence.getSession();
//        String sql = "select id from calculation where id = '%' AND kitchenId = :kitchen AND quarter = :quarter AND yearId = :year";
//        Query query = session.createNativeQuery(sql, CalculationModel.class);
//        query.setParameter("kitchen", kitchen);
//        query.setParameter("quarter", quarter);
//        query.setParameter("year", year);
//        //CalculationModel calculationModel = (CalculationModel) ;
//        System.out.println(query.getResultList());
//    }

    public static void test(){
        Session session = SetupPersistence.getSession();
        String sql = "select id from calculation where id = id and quarter = 4 AND kitchenId = 1 AND yearId = 2020";
        Query query = session.createNativeQuery(sql);
        System.out.println(query.getResultList());
        List<Object> result = query.getResultList();
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<result.size();i++){
            Integer test = (Integer) result.get(i);
            System.out.println(getCalcById(test).getId());
        }
    }

    /**
     * Method for deleting a calculation object by ID
     * @param id The ID of the calculation object that is being searched for
     */
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
