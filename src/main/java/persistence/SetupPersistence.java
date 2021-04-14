package persistence;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SetupPersistence {

    public static SessionFactory sessionFactory;

    public static void setup(){
        //Laver forbindelse til databasen med CalculationModel som den class den arbejder med
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(CalculationModel.class)
                .addAnnotatedClass(ConcitoItemModel.class)
                .addAnnotatedClass(FoodDescriptorModel.class)
                .addAnnotatedClass(FoodItemModel.class)
                .addAnnotatedClass(IngredientModel.class).
                addAnnotatedClass(KitchenModel.class);

        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory session = configuration.buildSessionFactory(ssrb.build());
    }

    public static void exit(){
        sessionFactory.close();
    }
}
