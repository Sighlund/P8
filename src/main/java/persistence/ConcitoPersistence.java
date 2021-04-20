package persistence;

import model.CalculationModel;
import model.ConcitoItemModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ConcitoPersistence {

//Work in progress

    public static void create(){
        //TODO
        ConcitoItemModel concitoItemModel = new ConcitoItemModel("test", 30.0, "test", "test");
        Session create = SetupPersistence.getSf().openSession();
        create.beginTransaction();
        create.persist(concitoItemModel);
        create.getTransaction().commit();
        create.close();
    }

    public static void update(){
        //TODO
    }

    public static void read(){
        //TODO
    }

    public static void delete(){
        //TODO
    }


}


