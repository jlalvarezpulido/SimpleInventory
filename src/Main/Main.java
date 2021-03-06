
package Main;

import Models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Initiates the Application, sets Stage. One of the main issues that I came across was declaring observable list. My aha moment was when I reliazed it could be declared as an observable array list.
 * I've learned a lot from this project and one of the main things that I would fix for future projects would be to use the same naming method for all my variables and sticking to it within all my classes.
 * Another thing I would fix would be to add Javadoc comments in the moment and not waiting to do it after I'm finished. It would save alot of time and it would be helpful to look at the comment and remeber what I did after the fact.
 * @author Jose Alvarez Pulido
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
/** This is the main method launches the application. It initialize some preloaded values in the Inventory parts list and products lists.
 * @param args this is initiates the main method */
    public static void main(String[] args) {
        Part a1 = new InHouse(1,"Brakes",12.99,15,5,20,101);
        Inventory.addPart(a1);
        Part b1 = new Outsourced(2,"Tire",14.99,15,1,20,"Schwinn");
        Inventory.addPart(b1);
        Part c1 = new InHouse(3,"Rim", 56.99,15,1,20,1234);
        Inventory.addPart(c1);
        Product bike = new Product(1,"Giant Bicycle",299.99,15,1,30);
        Inventory.addProduct(bike);
        Product unicycle = new Product(2,"Scott Bicycle",199.99, 15, 1,20);
        Inventory.addProduct(unicycle);
        Product tricycle = new Product(3,"GT Tricycle", 159.99,14,1,15);
        Inventory.addProduct(tricycle);
        launch(args);
    }
}

