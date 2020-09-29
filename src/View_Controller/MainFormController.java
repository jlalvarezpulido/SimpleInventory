package View_Controller;
import Main.Prompt;
import Models.Part;
import Models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainFormController implements Initializable{
    /**
     *Declarations for text fields
     * search
     */
    public TextField searchPartText;
    public TextField searchProductText;

    /**
     *Declarations for Table View       had an error with primitive types
     */
    //part tableview
    public TableView<Part> partTableView;
    public TableColumn<Part, Integer> partIdCol; //Integer non primitive
    public TableColumn<Part, String> partNameCol;
    public TableColumn<Part, Integer> partInvCol;
    public TableColumn<Part, Double> partPriceCostCol;
    //product table view
    public TableView<Product> productTableView;
    public TableColumn<Product, Integer> productIdCol;
    public TableColumn<Product, String> productNameCol;
    public TableColumn<Product, Integer> productInvCol;
    public TableColumn<Product, Double> productPriceCostCol;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Main Forum buttons used to ChangeViews
     *add parts button
     */
    public void addPartsButton(ActionEvent actionEvent) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartFormView.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        //This line gets the stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    /**
     *modify parts button
     */
    public void modifyPartsButton(ActionEvent actionEvent) throws IOException{
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyPartFormView.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);
        //This line gets the stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(modifyPartScene);
        window.show();
    }

    /**
     *add  products button
     */
    public void addProductsButton(ActionEvent actionEvent) throws IOException{
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/View_Controller/AddProductFormView.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        //This line gets the stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    /**
     *modify products button
     */
    public void modifyProductsButton(ActionEvent actionEvent) throws IOException{
        Parent modifyProductParent = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyProductFormView.fxml"));
        Scene modifyProductScene = new Scene(modifyProductParent);
        //This line gets the stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(modifyProductScene);
        window.show();
    }
    
    /**
     * Main Forum buttons used to delete Objects
     * delete products button
     */
    public void deleteProductsButton(ActionEvent actionEvent) {
    }

    /**
     * delete parts button
     */
    public void deletePartsButton(ActionEvent actionEvent) {
    }

    /**
     *
     * button to exit the application
     */

    public void exitApplicationButtonPushed(ActionEvent actionEvent) {
        boolean exitConfirm;//using the Prompt class to return a bool to prompt a second window to exit
        exitConfirm = Prompt.textBox("Exit Screen","Are you sure you want to exit?");
        if(exitConfirm){
            //Since the stage is set and launched in the Main class I needed to go to the source to do a stage.close()
            //This code goes to the source Node defines the stage from there and does a stage.close()
            final Node SOURCE = (Node) actionEvent.getSource();
            final Stage STAGE = (Stage) SOURCE.getScene().getWindow();
            STAGE.close();
        }
    }

}
