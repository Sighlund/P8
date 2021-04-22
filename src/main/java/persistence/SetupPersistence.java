package persistence;

//Import needed classes
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SetupPersistence {

    //Creating a Sessionfactory object, which is defined in the setSessionFactory method
    private static final SessionFactory sf = setSessionFactory();

    //Creating a session factory
    private static SessionFactory setSessionFactory() {
        //Using try because it can throw errors
        try {
            //Creating a config for the sessionfactory
            Configuration configuration = new Configuration();
            //Specifying what the config file is called
            configuration.configure("hibernate.cfg.xml");
            //Specifying which classes should be used in the sessionfactory
            configuration.addAnnotatedClass(CalculationModel.class)
                    .addAnnotatedClass(ConcitoItemModel.class)
                    .addAnnotatedClass(FoodDescriptorModel.class)
                    .addAnnotatedClass(FoodItemModel.class)
                    .addAnnotatedClass(IngredientModel.class)
                    .addAnnotatedClass(KitchenModel.class);

            //Creating a Sessionfactory through the StandardRegistryBuilder with the config we created above
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            //Returning a sessionfactory that is used on the object defined in the beginning of the class
            return configuration.buildSessionFactory(ssrb.build());
            //Error handling
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    //Method to get the sessionfactory
    private static SessionFactory getSf() {
        return sf;
    }

    //Method to get a session from the sessionfactory
    public static Session getSession(){
        Session session = getSf().openSession();
        session.beginTransaction();
        return session;
    }

    //Method to close a session
    public static void closeSession(Session session){
        session.getTransaction().commit();
        session.close();
    }

    //Method to close the Sessionfactory
    public static void exit(){
        getSf().close();
    }
}
