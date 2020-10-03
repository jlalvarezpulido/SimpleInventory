package Main;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * This class creates a user defined prompt box.
 */
public class Prompt {

    private static boolean promptButtonConfirm;
    /** This method returns a boolean. It is set to true if the OK button is pressed.
     * It is set to false if Cancel button is pressed
     * @return promptButtonConfirm.
     * @param title is the title of the text prompt window.
     * @param message is the message that will be displayed in the prompt window.
     * */
    public static boolean textBox(String title, String message){
        //setting the stage with min width and min height
        Stage prompt = new Stage();
        prompt.initModality(Modality.APPLICATION_MODAL);
        prompt.setMinWidth(250);
        prompt.setMinHeight(150);
        prompt.setTitle(title);

        Label messageLabel = new Label();
        messageLabel.setText(message);
        Button yesButton = new Button("OK");
        Button noButton = new Button("Cancel");

        yesButton.setOnAction(actionEvent -> {
            promptButtonConfirm = true;
            prompt.close();
        });
        noButton.setOnAction((actionEvent -> {
            promptButtonConfirm = false;
            prompt.close();
        }));

        VBox verticalLayout = new VBox(10);
        HBox horizontalLayout = new HBox(5);
        horizontalLayout.getChildren().addAll(yesButton,noButton);
        horizontalLayout.setAlignment(Pos.CENTER);
        verticalLayout.getChildren().addAll(messageLabel,horizontalLayout);
        verticalLayout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(verticalLayout);
        prompt.setScene(scene);
        prompt.showAndWait();

        return promptButtonConfirm;
    }
}