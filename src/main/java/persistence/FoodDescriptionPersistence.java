package persistence;

import model.ConcitoItemModel;
import model.FoodDescriptorModel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class FoodDescriptionPersistence {

    public static SessionFactory descriptSessionFactory;

    public static void setup(){
        //Laver forbindelse til databasen med CalculationModel som den class den arbejder med
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(FoodDescriptorModel.class);
        StandardServiceRegistryBuilder desriptBuild = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory descriptSession = configuration.buildSessionFactory(desriptBuild.build());
    }

    public static void exit(){
        descriptSessionFactory.close();
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

        FoodDescriptionPersistence.setup();
        FoodDescriptionPersistence.exit();
    }

}
