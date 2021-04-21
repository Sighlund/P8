package persistence;

import model.CalculationModel;
import org.hibernate.Session;

public class CalculationPersistence {
    //Work in progress


    public static void create(){
        //TODO

    }

    public static void update(){
        //TODO
    }

    public static void read(){
        Session read = SetupPersistence.getSf().openSession();
        read.beginTransaction();
        read.getTransaction().commit();
        CalculationModel gaming = read.find(CalculationModel.class, 1);
        System.out.println(gaming.calcAveCO2prKg());
        System.out.println(gaming.calcTotalCo2());
        System.out.println(gaming.calcTotalKg());
        read.close();
    }

    public static void delete(){
        //TODO
    }


}
