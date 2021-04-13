package yolo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

// Søren har skrevet noget fed kode
public class App {
    public static void main(String[] args){

        modelClassTestAnne();



    }

    public static void databaseTestMads() {
        FoodDescriptorModel MadEksempel = new FoodDescriptorModel();
        MadEksempel.setName("Eksempel");
        MadEksempel.setSupplier("Firma");
        MadEksempel.setItemNumber(250);
        //MadEksempel.setPrimaryCategory("Kød");
        //MadEksempel.setSecondaryCategory("Gaming");
        //MadEksempel.setConcitoId(4);

        System.out.println("Trying to create a test connection");
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(FoodDescriptorModel.class);
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sf = configuration.buildSessionFactory(ssrb.build());

        Session session = sf.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(MadEksempel);

        transaction.commit();

        session.save(MadEksempel);

        System.out.println("It worked! Very nice))");
    }

    public static void modelClassTestAnne(){

        //Creating dummy concitoItem
        ConcitoItemModel concitoItem = new ConcitoItemModel();
        concitoItem.setCategory("Kød");
        concitoItem.setName("Medister");
        concitoItem.setSubcategory("Svin");
        concitoItem.setCo2PrKg(35.0);

        //Creating dummy ingredient
        IngredientModel ingredient1 = new IngredientModel();
        ingredient1.setContoItem(concitoItem);
        ingredient1.setPercentage(100.0);

        //Creating dummy food descriptor
        FoodDescriptorModel foodDescriptor = new FoodDescriptorModel();
        foodDescriptor.setName("Medister");
        ArrayList<IngredientModel> ingredientList = new ArrayList<IngredientModel>();
        ingredientList.add(ingredient1);
        foodDescriptor.setIngredientList(ingredientList);

        //Creating dummy food item 1
        FoodItemModel foodItem = new FoodItemModel();
        foodItem.setFoodDescriptor(foodDescriptor);
        foodItem.setVolume(12.0);

        //Creating dummy food item 2
        FoodItemModel foodItem2 = new FoodItemModel();
        foodItem2.setFoodDescriptor(foodDescriptor);
        foodItem2.setVolume(14.0);

        //Creating dummy calculation
        CalculationModel calculation1 = new CalculationModel();
        ArrayList<FoodItemModel> foodItemList1 = new ArrayList<>();
        foodItemList1.add(foodItem);
        foodItemList1.add(foodItem2);
        calculation1.setFoodItemList(foodItemList1);

        System.out.println("Total kg: " + calculation1.calcTotalKg());

    }
}
