import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

//This class is simply a collection of methods used when we are catching Exceptions.
//Instantiate this an object of this class in your 'catch', and apply the wanted methods.
//At the end of the catch, remember to 'kill' the object by pointing the reference to 'null'.
//Apply different methods from this class when handling different errors.
public class ErrorHandlingCollection {


    /**
     * Creates a basic popup informing user of problem. Displays the error message.
     * @param title Title of the popup
     * @param errorMessage Errormessage containing information for the user.
     */
    public void basicErrorPopup(String title, String errorMessage){
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


    /**
     * Video reference: https://www.youtube.com/watch?v=mH3USLWhqr4
     * Basic popup used for confirming or canceling a choice. Returns bool.
     * @param promptText Should be passed to the method in order to prompt user correct information.
     * @return returns a bool. Can be used with if-statement in order to branch scenario in different paths.
     */
    public boolean confirmChoicePopup(String promptText){
        //Create alert object with CONFIRMATION type.
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type);

        //Configuring the alert information
        alert.setTitle("Bekræft valg");
        alert.getDialogPane().setHeaderText(promptText);
        //alert.getDialogPane().setContentText(promptText);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Bekræft");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Fortryd");

        //When alert.showAndWait() is called, it opens the window with the 2 buttons associated with AlertType CONFIRMATION.
        //Once the user clicks one of the buttons, the ButtonType of that button is stored inside 'result'.
        //There are only 2 types of buttons in this case, 'OK' and 'CANCEL'.
        //Depending on what is clicked, the return is either true or false.
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                return true;
            }
            else if (result.get() == ButtonType.CANCEL){
                return false;
            }
            else{
                return false;
            }
    }


}
