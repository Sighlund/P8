package persistence;

import model.FoodDescriptorModel;
import model.FoodItemModel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class FoodItemPersistence {

    public static SessionFactory itemSessionFactory;

    public static void setup(){
        //Laver forbindelse til databasen med CalculationModel som den class den arbejder med
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(FoodItemModel.class);
        StandardServiceRegistryBuilder itemBuild = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory itemSession = configuration.buildSessionFactory(itemBuild.build());
    }

    public static void exit(){
        itemSessionFactory.close();
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

        FoodItemPersistence.setup();
        FoodItemPersistence.exit();
    }
}
