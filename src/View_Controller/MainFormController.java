package View_Controller;
import Main.Prompt;
import Models.Inventory;
import Models.Part;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainFormController implements Initializable{

    Inventory test = new Inventory();
    /**
     *Declarations for text fields
     * search
     */
    @FXML private TextField partLookUp;
    @FXML private TextField productLookUp;
    /**
     *Declarations for Table View
     */
    //part tableview
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partTableIdCol;
    @FXML private TableColumn<Part, String> partTableNameCol;
    @FXML private TableColumn<Part, Integer> partTableInvCol;
    //product table view
    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, Integer> productTableIdCol;
    @FXML private TableColumn<Product, String> productTableNameCol;
    @FXML private TableColumn<Product, Integer> productTableInvCol;

    private ObservableList<Part> partsInv;
    private ObservableList<Product> productsInv;
    private ObservableList<Part> partsInvSearch;
    private ObservableList<Product> productsInvSearch;


    /**
     * Initializes the Main form class
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partTableView.setItems(Inventory.getAllParts());

        partTableIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partTableNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partTableInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productTableIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productTableNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productTableInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }

    private void createPartsList(){
        partsInv.setAll(test.getAllParts());
        partTableView.setItems(partsInv);
        partTableView.refresh();
    }
    private void createProductsList()
    {
        //productsInv.setAll(test.getAllProducts());
        productTableView.setItems(Inventory.getAllProducts());
        productTableView.refresh();
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
