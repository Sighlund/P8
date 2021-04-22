package persistence;

import model.CalculationModel;
import model.ConcitoItemModel;
import org.hibernate.Session;

import java.util.List;

public class ConcitoPersistence {

//Work in progress

    public static List<ConcitoItemModel> listCon(){
        Session session = SetupPersistence.getSession();
        List<ConcitoItemModel> list = session.createQuery("SELECT a from ConcitoItemModel a", ConcitoItemModel.class).getResultList();
        SetupPersistence.closeSession(session);
        return list;
    }

    public static ConcitoItemModel getConById(Integer id){
        Session session = SetupPersistence.getSession();
        ConcitoItemModel concitoItemModel = session.find(ConcitoItemModel.class, id);
        SetupPersistence.closeSession(session);
        return concitoItemModel;
    }

    public static void deleteCon(Integer id){
        Session session = SetupPersistence.getSession();
        ConcitoItemModel concitoItemModel = session.find(ConcitoItemModel.class, id);
        session.delete(concitoItemModel);
        SetupPersistence.closeSession(session);
    }

}


