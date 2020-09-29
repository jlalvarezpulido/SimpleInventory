/**
 * @author Jose Alvarez Pulido
 *
 */
package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

    Stage MainApplication;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
        MainApplication = primaryStage;
        MainApplication.setScene(new Scene(root));
        MainApplication.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}