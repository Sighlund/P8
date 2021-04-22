package persistence;

import model.CalculationModel;
import model.ConcitoItemModel;
import model.FoodDescriptorModel;
import model.IngredientModel;
import org.hibernate.Session;

import java.util.List;

public class FoodDescriptorPersistence {

//Work in progress

    public static void addDescriptor(FoodDescriptorModel descriptor){
        Session session = SetupPersistence.getSession();
        session.save(descriptor);
        SetupPersistence.closeSession(session);
    }

    public static void updateDescriptor(FoodDescriptorModel descriptor){
        Session session = SetupPersistence.getSession();
        session.update(descriptor);
        SetupPersistence.closeSession(session);
    }

    public static List<FoodDescriptorModel> listDescriptor(){
        Session session = SetupPersistence.getSession();
        List<FoodDescriptorModel> list = session.createQuery("SELECT a from FoodDescriptorModel a", FoodDescriptorModel.class).getResultList();
        SetupPersistence.closeSession(session);
        return list;
    }

    public static FoodDescriptorModel getDescriptorById(Integer id){
        Session session = SetupPersistence.getSession();
        FoodDescriptorModel descriptor = session.find(FoodDescriptorModel.class, id);
        SetupPersistence.closeSession(session);
        return descriptor;
    }

    public static void deleteDescriptor(Integer id){
        Session session = SetupPersistence.getSession();
        FoodDescriptorModel descriptor = session.find(FoodDescriptorModel.class, id);
        session.delete(descriptor);
        SetupPersistence.closeSession(session);
    }
}
