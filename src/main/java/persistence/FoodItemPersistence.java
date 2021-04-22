package persistence;

import model.FoodDescriptorModel;
import model.FoodItemModel;
import org.hibernate.Session;

import java.util.List;

public class FoodItemPersistence {

//work in progress

    public static void addItem(FoodItemModel item) {
        Session session = SetupPersistence.getSession();
        session.save(item);
        SetupPersistence.closeSession(session);
    }

    public static void updateItem(FoodItemModel item) {
        Session session = SetupPersistence.getSession();
        session.update(item);
        SetupPersistence.closeSession(session);
    }

    public static List<FoodItemModel> listItem() {
        Session session = SetupPersistence.getSession();
        List<FoodItemModel> list = session.createQuery("SELECT a from FoodItemModel a", FoodItemModel.class).getResultList();
        SetupPersistence.closeSession(session);
        return list;
    }

    public static FoodItemModel getItemById(Integer id) {
        Session session = SetupPersistence.getSession();
        FoodItemModel item = session.find(FoodItemModel.class, id);
        SetupPersistence.closeSession(session);
        return item;
    }

    public static void deleteItem(Integer id) {
        Session session = SetupPersistence.getSession();
        FoodItemModel item = session.find(FoodItemModel.class, id);
        session.delete(item);
        SetupPersistence.closeSession(session);
    }
}
