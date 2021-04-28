import persistence.KitchenPersistence;

import java.util.ArrayList;
import java.util.List;

//TODO - kan vi slette denne klasse?

public class ControllerTest {

    private KitchenPersistence kitchenPersistence;

    List<String> units = getKitchenString();
    public static List<String> getKitchenString(){
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < KitchenPersistence.listKitchen().size(); i++) {
            names.add(KitchenPersistence.listKitchen().get(i).getName());
        }
        return names;
    }
}
