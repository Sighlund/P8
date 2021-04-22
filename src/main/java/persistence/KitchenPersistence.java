package persistence;

import model.KitchenModel;
import org.hibernate.Session;

import java.util.List;

public class KitchenPersistence {

//work in progress

    public static void create(){
        //TODO
    }

    public static void update(){
        //TODO
    }
    public static List<KitchenModel> listKitchen(){
        Session session = SetupPersistence.getSession();
        List<KitchenModel> list = session.createQuery("SELECT a from KitchenModel a", KitchenModel.class).getResultList();
        SetupPersistence.closeSession(session);
        return list;
    }

    public static void delete(){
        //TODO
    }

}
