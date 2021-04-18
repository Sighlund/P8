import model.TestModel;
import persistence.CalculationPersistence;
import persistence.SetupPersistence;

// Søren har skrevet noget fed kode
public class App {

    private static TestModel testModel = new TestModel();

    public static void main(String[] args){

        testModel.modelClassTestAnne();
        SetupPersistence.setup();
        SetupPersistence.create();




    }



}
