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
    public static List<KitchenModel> readAll(){
        //TODO
        Session session = SetupPersistence.getSession();
        return session.createQuery("SELECT a from KitchenModel a", KitchenModel.class).getResultList();
    }

    public static void delete(){
        //TODO
    }

}
