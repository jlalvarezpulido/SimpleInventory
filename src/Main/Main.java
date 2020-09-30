
package Main;

import Models.InHouse;
import Models.Inventory;
import Models.Part;
import Models.Product;
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
        MainApplication.setTitle("Jose's Inventory System");
        MainApplication.show();

    }

    public static void main(String[] args) {
        Part a1 = new InHouse(1,"Part A1",2.99,10,5,100,101);
        Inventory.addPart(a1);
        Product bike = new Product(1,"Bike",100.00,5,1,10);
        Inventory.addProduct(bike);
        launch(args);
    }
}

