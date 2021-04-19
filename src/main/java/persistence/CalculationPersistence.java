package persistence;

import model.CalculationModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.management.Descriptor;

public class CalculationPersistence {
    //Work in progress


    public static void create(){
        //TODO
    }

    public static void update(){
        //TODO
    }

    public static void read(){
        Session read = SetupPersistence.getSf().openSession();
        read.beginTransaction();
        read.getTransaction().commit();
        CalculationModel gaming = read.find(CalculationModel.class, 1);
        System.out.println(gaming.getKitchen().getName());
    }

    public static void delete(){
        //TODO
    }


}
