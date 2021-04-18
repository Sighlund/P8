package persistence;

import model.CalculationModel;
import model.FoodItemModel;
import model.IngredientModel;
import model.KitchenModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;


public class KitchenPersistence {

//work in progress

    public static void create(){
        //TODO
        KitchenModel madEksempel = new KitchenModel();
        madEksempel.setName("Test");

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
