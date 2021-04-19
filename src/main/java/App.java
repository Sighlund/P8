import model.TestModel;
import persistence.CalculationPersistence;
import persistence.ConcitoPersistence;
import persistence.KitchenPersistence;
import persistence.SetupPersistence;

// Søren har skrevet noget fed kode
public class App {

    private static TestModel testModel = new TestModel();

    public static void main(String[] args) throws Exception{

        testModel.constructorTest();
        SetupPersistence.getSf();
        //KitchenPersistence.create();
        CalculationPersistence.read();
        //ConcitoPersistence.create();

    }



}
