package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * cancel button method used to go back to main form without passing any Objects
     */
    public void cancelButtonPushed(ActionEvent actionEvent) throws IOException {
        Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
        Scene goBack = new Scene(goBackParent);

        //This line gets the stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(goBack);
        window.show();
    }
}
