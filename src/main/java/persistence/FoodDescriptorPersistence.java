package persistence;

import model.ConcitoItemModel;
import model.FoodDescriptorModel;
import model.IngredientModel;
import org.hibernate.Session;

public class FoodDescriptorPersistence {

//Work in progress

    public static void create(){
        //TODO
        Session create = SetupPersistence.getSf().openSession();
        create.beginTransaction();
        create.getTransaction().commit();
        ConcitoItemModel concitoItem = create.find(ConcitoItemModel.class,1);
        IngredientModel ingredient = new IngredientModel(100.0, concitoItem);
        FoodDescriptorModel foodDescriptor = new FoodDescriptorModel("Test");
        foodDescriptor.addIngredient(ingredient);
        create.save(foodDescriptor);
        create.close();

    }

    public static void update(){
        //TODO
    }

    public static void read(){
        //TODO
    }

    public static void delete(){
        //TODO
    }



}
