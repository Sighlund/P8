package persistence;

import model.IngredientModel;
import model.KitchenModel;
import org.hibernate.Session;

import java.util.List;

public class KitchenPersistence {

//work in progress

    public static void addKitchen(KitchenModel kitchen) {
        Session session = SetupPersistence.getSession();
        session.save(kitchen);
        SetupPersistence.closeSession(session);
    }

    public static void updateKitchen(KitchenModel kitchen) {
        Session session = SetupPersistence.getSession();
        session.update(kitchen);
        SetupPersistence.closeSession(session);
    }

    public static List<KitchenModel> listKitchen() {
        Session session = SetupPersistence.getSession();
        List<KitchenModel> list = session.createQuery("SELECT a from KitchenModel a", KitchenModel.class).getResultList();
        SetupPersistence.closeSession(session);
        return list;
    }

    public static KitchenModel getKitchenById(Integer id) {
        Session session = SetupPersistence.getSession();
        KitchenModel kitchen = session.find(KitchenModel.class, id);
        SetupPersistence.closeSession(session);
        return kitchen;
    }

    public static void deleteKitchen(Integer id) {
        Session session = SetupPersistence.getSession();
        KitchenModel kitchen = session.find(KitchenModel.class, id);
        session.delete(kitchen);
        SetupPersistence.closeSession(session);
    }
}
