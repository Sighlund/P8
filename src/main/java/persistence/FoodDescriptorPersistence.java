package persistence;

import model.ConcitoItemModel;
import model.FoodDescriptorModel;
import model.IngredientModel;
import org.hibernate.Session;

public class FoodDescriptorPersistence {

//Work in progress

    public static void create(){
        //TODO
        Session session = SetupPersistence.getSession();
        //ConcitoItemModel concitoItem = create.find(ConcitoItemModel.class,1);
        //IngredientModel ingredient = new IngredientModel(100.0, concitoItem);
        //FoodDescriptorModel foodDescriptor = new FoodDescriptorModel("Test");
        //foodDescriptor.addIngredient(ingredient);
        //create.save(foodDescriptor);
        //create.close();
        FoodDescriptorModel foodDescriptor = new FoodDescriptorModel("Snydt");
        ConcitoItemModel concitoItem = session.find(ConcitoItemModel.class,1);
        IngredientModel ingredient = new IngredientModel(100.0, concitoItem);
        ingredient.setFoodDescriptor(foodDescriptor);
        foodDescriptor.addIngredient(ingredient);
        session.save(foodDescriptor);
        session.close();



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
