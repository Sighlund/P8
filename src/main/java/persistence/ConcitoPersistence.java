package persistence;

import model.CalculationModel;
import model.ConcitoItemModel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ConcitoPersistence {

    public static SessionFactory conSessionFactory;

    public static void setup(){
        //Laver forbindelse til databasen med CalculationModel som den class den arbejder med
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(ConcitoItemModel.class);
        StandardServiceRegistryBuilder conBuild = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory conSession = configuration.buildSessionFactory(conBuild.build());
    }

    public static void exit(){
        conSessionFactory.close();
    }

    public static void create(){
        //TODO
    }

    public static void update(){
        //TODO
    }

    public static void delete(){
        //TODO
    }

    public static void main(String[] args){

        ConcitoPersistence.setup();
        ConcitoPersistence.exit();
    }
}


