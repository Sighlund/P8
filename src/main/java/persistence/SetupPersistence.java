package persistence;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SetupPersistence {
/*
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
        SessionFactory sf = configuration.buildSessionFactory(ssrb.build());

        Session s = sf.openSession();
        s.beginTransaction();
        s.getTransaction().commit();
        s.close();
    }
*/
    private static final SessionFactory sf = setSessionFactory();

    private static SessionFactory setSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(CalculationModel.class)
                    .addAnnotatedClass(ConcitoItemModel.class)
                    .addAnnotatedClass(FoodDescriptorModel.class)
                    .addAnnotatedClass(FoodItemModel.class)
                    .addAnnotatedClass(IngredientModel.class).
                    addAnnotatedClass(KitchenModel.class);

            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            return configuration.buildSessionFactory(ssrb.build());
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSf() {
        return sf;
    }

    public static void exit(){
        getSf().close();
    }



        //SetupPersistence.sessionFactory = sessionFactory;



    public static void create(){
        KitchenModel madEksempel = new KitchenModel();
        madEksempel.setName("Test");
        madEksempel.setId(1);

        Session s = sf.openSession();
        //session.beginTransaction();
        //session.getTransaction().commit();
        //sf.close();

    }

}
