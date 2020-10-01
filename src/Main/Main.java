
package Main;

import Models.*;
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
        Part a1 = new InHouse(1,"a1",2.99,10,5,100,101);
        Inventory.addPart(a1);
        Part b1 = new Outsourced(2,"b1",1.99,10,1,5,"3M");
        Inventory.addPart(b1);
        Part c1 = new InHouse(3,"c1", 5.99,15,1,29,1234);
        Inventory.addPart(c1);
        Product bike = new Product(1,"Bike",100.00,5,1,10);
        Inventory.addProduct(bike);
        Product unicycle = new Product(2,"Unicycle",199.99, 2, 1,5);
        Inventory.addProduct(unicycle);
        Product tricycle = new Product(3,"Tricycle", 159.99,10,1,15);
        Inventory.addProduct(tricycle);
        launch(args);
    }
}

