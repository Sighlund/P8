import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//This class is simply a collection of methods used when we are catching Exceptions.
//Instantiate this an object of this class in your 'catch', and apply the wanted methods.
//At the end of the catch, remember to 'kill' the object by pointing the reference to 'null'.
//Apply different methods from this class when handling different errors.
public class ErrorHandlingCollection {
    public void basicErrorPopup(String title, String errorMessage){
        //Creates a basic popup informing user of problem. Displays the error message.
        System.out.println(errorMessage);

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label label = new Label();
        label.setText(errorMessage);
        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }



}
