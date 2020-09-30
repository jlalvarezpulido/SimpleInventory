package View_Controller;

import Main.Prompt;
import Models.Inventory;
import Models.Part;
import Models.Product;
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
    @FXML private TableColumn<Part, Double> partTablePriceCol;
    //product table view
    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, Integer> productTableIdCol;
    @FXML private TableColumn<Product, String> productTableNameCol;
    @FXML private TableColumn<Product, Integer> productTableInvCol;
    @FXML private TableColumn<Product, Double> productTablePriceCol;

    private ObservableList<Part> partsInvSearch;
    private ObservableList<Product> productsInvSearch;

    /**
     * Initializes the Main form class
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //This sets the Inventory lists to the appropriate table views
        partTableView.setItems(Inventory.getAllParts());
        productTableView.setItems(Inventory.getAllProducts());

        //Setting the Cell Factory Values so they could match the Setters and Getters of the Classes
        partTableIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partTableNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partTableInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partTablePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTableIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productTableNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productTableInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productTablePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //select get selection to highlight search
       // partTableView.getSelectionModel().select(Inventory.lookupPart(1));
        //productTableView.getSelectionModel().select(Inventory.lookupProduct(1));


    }


    /**
     * Main Forum buttons used to ChangeViews
     *add parts button
     */
    public void addPartsButton(ActionEvent actionEvent) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartFormView.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    /**
     *modify parts button
     */
    public void modifyPartsButton(ActionEvent actionEvent) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyPartFormView.fxml"));
        loader.load();

        ModifyPartFormController modifyPartController = loader.getController();
        modifyPartController.sendPart(partTableView.getSelectionModel().getSelectedItem());
        modifyPartController.getPartIndex(partTableView.getSelectionModel().getSelectedIndex());
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent modifyPartParent = loader.getRoot();
        Scene modifyPartScene = new Scene(modifyPartParent);
        window.setScene(modifyPartScene);
        window.show();

    }

    /**
     *add  products button
     */
    public void addProductsButton(ActionEvent actionEvent) throws IOException{
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/View_Controller/AddProductFormView.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    /**
     *modify products button
     * defined the fxml loader to load the modifyProduct controller to hand off information between controllers.
     */
    public void modifyProductsButton(ActionEvent actionEvent) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyProductFormView.fxml"));
        loader.load();

        ModifyProductFormController modifyProductController = loader.getController();
        modifyProductController.sendProduct(productTableView.getSelectionModel().getSelectedItem());
        modifyProductController.sendIndex(productTableView.getSelectionModel().getSelectedIndex());
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent modifyProductParent = loader.getRoot();
        Scene modifyProductScene = new Scene(modifyProductParent);
        window.setScene(modifyProductScene);
        window.show();
    }
    
    /**
     * Main Forum buttons used to delete Objects
     * delete products button
     */
    public void deleteProductsButton(ActionEvent actionEvent)
    {
        boolean deleteProduct;
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if(selectedProduct != null)
        {
            deleteProduct = Prompt.textBox("Delete Product","Are you sure you want to delete this product?");
            if(deleteProduct)
            {
                Inventory.deleteProduct(selectedProduct);
            }
        }
    }

    /**
     * delete parts button
     */
    public void deletePartsButton(ActionEvent actionEvent)
    {
        boolean deletePart;
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if(selectedPart != null)
        {
            deletePart = Prompt.textBox("Delete Part","Are you sure you want to delete this part?");
            if(deletePart)
            {
                Inventory.deletePart(selectedPart);
            }
        }
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
