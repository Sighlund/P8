package persistence;

import model.ConcitoItemModel;
import model.FoodItemModel;
import model.IngredientModel;
import org.hibernate.Session;

import java.util.List;

public class IngredientPersistence {

//Work in progress

    public static void addIngredient(IngredientModel ingredient) {
        Session session = SetupPersistence.getSession();
        session.save(ingredient);
        SetupPersistence.closeSession(session);
    }

    public static void updateIngredient(IngredientModel ingredient) {
        Session session = SetupPersistence.getSession();
        session.update(ingredient);
        SetupPersistence.closeSession(session);
    }

    public static List<IngredientModel> listIngredient() {
        Session session = SetupPersistence.getSession();
        List<IngredientModel> list = session.createQuery("SELECT a from IngredientModel a", IngredientModel.class).getResultList();
        SetupPersistence.closeSession(session);
        return list;
    }

    public static IngredientModel getIngredientById(Integer id) {
        Session session = SetupPersistence.getSession();
        IngredientModel ingredient = session.find(IngredientModel.class, id);
        SetupPersistence.closeSession(session);
        return ingredient;
    }

    public static void deleteIngredient(Integer id) {
        Session session = SetupPersistence.getSession();
        IngredientModel ingredient = session.find(IngredientModel.class, id);
        session.delete(ingredient);
        SetupPersistence.closeSession(session);
    }
}
