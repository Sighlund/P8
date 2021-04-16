package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

/**
 * The TestModel class is used for implementing auxiliary methods for testing the model classes.
 */

public class TestModel {

    public void modelClassTestAnne(){

        /* Her laves en testberegning ud fra indkøb af:
         * - 12 kg 'Medister med svesker'
         * - 14 kg 'Medister med svesker'
         * - 10 kg 'Medister'
         * - 15 kg 'Medister med kartofler og svesker'
         *
         * Lige nu er den meget lang og kringlet, fordi jeg ikke har arbejdet med constructors endnu.
         * Stay tuned for cleaner test code...
         */

        //Creating dummy concitoItem
        ConcitoItemModel concitoItem = new ConcitoItemModel();
        concitoItem.setCategory("Kød");
        concitoItem.setName("Medister");
        concitoItem.setSubcategory("Svin");
        concitoItem.setCo2PrKg(30.0);

        //Creating dummy concitoItem2
        ConcitoItemModel concitoItem2 = new ConcitoItemModel();
        concitoItem2.setCategory("Konserves");
        concitoItem2.setName("Svesker");
        //concitoItem2.setSubcategory("Tørret frugt");
        concitoItem2.setCo2PrKg(10.0);

        //Creating dummy concitoItem3
        ConcitoItemModel concitoItem3 = new ConcitoItemModel();
        concitoItem3.setCategory("Frugt/Grønt");
        concitoItem3.setName("Kartofler");
        concitoItem3.setSubcategory("Rodfrugter");
        concitoItem3.setCo2PrKg(10.0);


        //Creating dummy ingredient from concito item
        IngredientModel ingredient1 = new IngredientModel();
        ingredient1.setContoItem(concitoItem);
        ingredient1.setPercentage(90.0);

        //Creating dummy ingredient from concito item 2
        IngredientModel ingredient2 = new IngredientModel();
        ingredient2.setContoItem(concitoItem2);
        ingredient2.setPercentage(10.0);

        //Creating dummy food descriptor
        FoodDescriptorModel foodDescriptor = new FoodDescriptorModel();
        foodDescriptor.setName("Medister med svesker");
        ArrayList<IngredientModel> ingredientList = new ArrayList<IngredientModel>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        foodDescriptor.setIngredientList(ingredientList);


        //Creating dummy ingredient from concito item 3 100 % medister
        IngredientModel ingredient3 = new IngredientModel();
        ingredient3.setContoItem(concitoItem);
        ingredient3.setPercentage(100.0);

        // Creating dummy food descriptor 2 - ren medister
        FoodDescriptorModel foodDescriptor2 = new FoodDescriptorModel();
        foodDescriptor2.setName("Medister");
        ArrayList<IngredientModel> ingredientList2 = new ArrayList<IngredientModel>();
        ingredientList2.add(ingredient3);
        foodDescriptor2.setIngredientList(ingredientList2);


        //Creating dummy ingredient from concito item - 40 % medister
        IngredientModel ingredient4 = new IngredientModel();
        ingredient4.setContoItem(concitoItem);
        ingredient4.setPercentage(40.0);

        //Creating dummy ingredient from concito item - 40 % kartofler
        IngredientModel ingredient5 = new IngredientModel();
        ingredient5.setContoItem(concitoItem3);
        ingredient5.setPercentage(40.0);

        //Creating dummy ingredient from concito item - 20 % svesker
        IngredientModel ingredient6 = new IngredientModel();
        ingredient6.setContoItem(concitoItem2);
        ingredient6.setPercentage(20.0);

        // Creating dummy food descriptor 3 - medister med kartofler of svesker
        FoodDescriptorModel foodDescriptor3 = new FoodDescriptorModel();
        foodDescriptor3.setName("Medister med kartofler og svesker");
        ArrayList<IngredientModel> ingredientList3 = new ArrayList<IngredientModel>();
        ingredientList3.add(ingredient4);
        ingredientList3.add(ingredient5);
        ingredientList3.add(ingredient6);
        foodDescriptor3.setIngredientList(ingredientList3);


        //Creating dummy food item 1 - 12 kg medister med svesker
        FoodItemModel foodItem = new FoodItemModel();
        foodItem.setFoodDescriptor(foodDescriptor);
        foodItem.setVolume(12.0);

        //Creating dummy food item 2 - 14 kg medister med svesker
        FoodItemModel foodItem2 = new FoodItemModel();
        foodItem2.setFoodDescriptor(foodDescriptor);
        foodItem2.setVolume(14.0);

        //Creating dummy food item 3 - 10 kg medister
        FoodItemModel foodItem3 = new FoodItemModel();
        foodItem3.setFoodDescriptor(foodDescriptor2);
        foodItem3.setVolume(10.0);

        // Createing dummy food item 4 - 15kg medister med kartofler og svesker
        FoodItemModel foodItem4 = new FoodItemModel();
        foodItem4.setFoodDescriptor(foodDescriptor3);
        foodItem4.setVolume(15.0);


        //Creating dummy calculation
        CalculationModel calculation1 = new CalculationModel();
        calculation1.addFoodItem(foodItem);
        calculation1.addFoodItem(foodItem2);
        calculation1.addFoodItem(foodItem3);
        calculation1.addFoodItem(foodItem4);

        //Calling the methods to be tested
        System.out.println("Products: ");
        for (int i = 0; i < calculation1.getFoodItemList().size(); i++) {
            FoodItemModel item = calculation1.getFoodItemList().get(i);
            String name = item.getFoodDescriptor().getName();
            double co2 = item.calcCo2();
            double kg = item.getVolume();
            String category = item.getFoodDescriptor().getCorrectedCategory();
            String subCategory = item.getFoodDescriptor().getCorrectedSubcategory();
            System.out.println(i+1 + ": " +
                    name + " | " +
                    category + " | " +
                    subCategory + " | " +
                    kg + " kg | " +
                    co2 + " kg Co2");
        }
        System.out.println("Total kg: " + calculation1.calcTotalKg());
        System.out.println("Total Co2: " + calculation1.calcTotalCo2());

        System.out.println("Removing product 2 from calculation");
        calculation1.removeFoodItem(foodItem2);

        System.out.println("Products: ");
        for (int i = 0; i < calculation1.getFoodItemList().size(); i++) {
            FoodItemModel item = calculation1.getFoodItemList().get(i);
            String name = item.getFoodDescriptor().getName();
            double co2 = item.calcCo2();
            double kg = item.getVolume();
            String category = item.getFoodDescriptor().getCorrectedCategory();
            String subCategory = item.getFoodDescriptor().getCorrectedSubcategory();
            System.out.println(i+1 + ": " +
                    name + " | " +
                    category + " | " +
                    subCategory + " | " +
                    kg + " kg | " +
                    co2 + " kg Co2");
        }
        System.out.println("Total kg: " + calculation1.calcTotalKg());
        System.out.println("Total Co2: " + calculation1.calcTotalCo2());

    }


    public void databaseTestMads() {
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
}
