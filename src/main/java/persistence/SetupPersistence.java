package persistence;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SetupPersistence {

    private static SessionFactory sessionFactory;

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
        SessionFactory sf = configuration.buildSessionFactory(ssrb.build());

        Session s = sf.openSession();
        s.beginTransaction();
        s.getTransaction().commit();
        s.close();
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(CalculationModel.class)
                .addAnnotatedClass(ConcitoItemModel.class)
                .addAnnotatedClass(FoodDescriptorModel.class)
                .addAnnotatedClass(FoodItemModel.class)
                .addAnnotatedClass(IngredientModel.class).
                addAnnotatedClass(KitchenModel.class);

        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        //SetupPersistence.sessionFactory sf = configuration.buildSessionFactory(ssrb.build());



        //SetupPersistence.sessionFactory = sessionFactory;
    }

    public void exit(){
        sessionFactory.close();
    }

    public static void create(){
        KitchenModel madEksempel = new KitchenModel();
        madEksempel.setName("Test");
        madEksempel.setId(1);

        //Session s = sf.openSession();
        //session.beginTransaction();
        //session.getTransaction().commit();
        //sf.close();

    }

}
