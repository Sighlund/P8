package persistence;

import model.FoodItemModel;
import model.IngredientModel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class IngredientPersistence {

    public static SessionFactory ingredientSessionFactory;

    public static void setup(){
        //Laver forbindelse til databasen med CalculationModel som den class den arbejder med
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(IngredientModel.class);
        StandardServiceRegistryBuilder ingredientBuild = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory ingredientSession = configuration.buildSessionFactory(ingredientBuild.build());
    }

    public static void exit(){
        ingredientSessionFactory.close();
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

        IngredientPersistence.setup();
        IngredientPersistence.exit();
    }
}
