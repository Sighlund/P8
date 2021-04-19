package persistence;

import model.KitchenModel;
import org.hibernate.Session;

public class KitchenPersistence {

//work in progress

    public static void create(){
        KitchenModel madEksempel = new KitchenModel();
        madEksempel.setName("Test1");

        Session s = SetupPersistence.getSf().openSession();
        s.beginTransaction();
        s.getTransaction().commit();
        s.save(madEksempel);
        s.close();
    }

    public static void update(){
        //TODO
    }

    public static void delete(){
        //TODO
    }

}
