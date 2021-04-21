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
    public static void readAll(){
        //TODO
        Session session = SetupPersistence.getSession();
        List<KitchenModel> list = session.createQuery("SELECT a from KitchenModel a", KitchenModel.class).getResultList();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getName());
        }
    }

    public static void delete(){
        //TODO
    }

}
