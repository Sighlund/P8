package persistence;

import model.CalculationModel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.management.Descriptor;

public class CalculationPersistence {

    public static SessionFactory calcSessionFactory;

    public static void setup(){
        //Laver forbindelse til databasen med CalculationModel som den class den arbejder med
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(CalculationModel.class);
        StandardServiceRegistryBuilder calcBuild = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory calcSession = configuration.buildSessionFactory(calcBuild.build());
    }

    public static void exit(){
        calcSessionFactory.close();
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

        CalculationPersistence.setup();
        CalculationPersistence.exit();
    }
}
