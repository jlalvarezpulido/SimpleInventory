
package Main;

import Models.InHouse;
import Models.Inventory;
import Models.Part;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Jose Alvarez Pulido
 *
 */
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
        Part a1 = new InHouse(1,"Part A1",2.99,10,5,100,101);
        Inventory.addPart(a1);
        launch(args);
    }
}

