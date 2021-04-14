package persistence;

import model.IngredientModel;
import model.KitchenModel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class KitchenPersistence {

    public static SessionFactory kitchenSessionFactory;

    public static void setup(){
        //Laver forbindelse til databasen med CalculationModel som den class den arbejder med
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(KitchenModel.class);
        StandardServiceRegistryBuilder kitchenBuild = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory kitchenSession = configuration.buildSessionFactory(kitchenBuild.build());
    }

    public static void exit(){
        kitchenSessionFactory.close();
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

        KitchenPersistence.setup();
        KitchenPersistence.exit();
    }
}
