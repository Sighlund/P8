package persistence;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SetupPersistence {

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

}
