package persistence;

import model.CalculationModel;
import model.ConcitoItemModel;
import model.KitchenModel;
import org.hibernate.Session;

import java.util.List;

public class CalculationPersistence {
    //Work in progress


    public static void addCalc(CalculationModel calc){
        Session session = SetupPersistence.getSession();
        session.save(calc);
        SetupPersistence.closeSession(session);
    }

    public static void updateCalc(CalculationModel calc){
        Session session = SetupPersistence.getSession();
        session.update(calc);
        SetupPersistence.closeSession(session);
    }

    public static List<CalculationModel> listCalc(){
        Session session = SetupPersistence.getSession();
        List<CalculationModel> list = session.createQuery("SELECT a from CalculationModel a", CalculationModel.class).getResultList();
        SetupPersistence.closeSession(session);
        return list;
    }

    public static CalculationModel getCalcById(Integer id){
        Session session = SetupPersistence.getSession();
        CalculationModel calculationModel = session.find(CalculationModel.class, id);
        SetupPersistence.closeSession(session);
        return calculationModel;
    }

    public static void deleteCalc(Integer id){
        Session session = SetupPersistence.getSession();
        CalculationModel calculationModel = session.find(CalculationModel.class, id);
        session.delete(calculationModel);
        SetupPersistence.closeSession(session);
    }
}
