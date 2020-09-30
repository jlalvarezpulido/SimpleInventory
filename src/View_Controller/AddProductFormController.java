package View_Controller;

import Models.Inventory;
import Models.Part;
import Models.Product;
import javafx.collections.FXCollections;
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

public class AddProductFormController implements Initializable {
    @FXML
    private TextField productIdAddText;
    @FXML
    private TextField nameProductAddText;
    @FXML
    private TextField invProductAddText;
    @FXML
    private TextField priceProductAddText;
    @FXML
    private TextField maxProductAddText;
    @FXML
    private TextField minProductAddText;
    @FXML
    private TextField searchAddProductText;
    @FXML
    private TableView<Part> defaultProductInvTB;
    @FXML
    private TableColumn<Part, Integer> partIdProductCol;
    @FXML
    private TableColumn<Part, String> partNameProductCol;
    @FXML
    private TableColumn<Part, Integer> InvProductCol;
    @FXML
    private TableColumn<Part, Double> priceProductCol;
    @FXML
    private TableView<Part> addedProductTB;
    @FXML
    private TableColumn<Part, Integer> addedIdCol;
    @FXML
    private TableColumn<Part, String> addedPartCol;
    @FXML
    private TableColumn<Part, Integer> addedInvCol;
    @FXML
    private TableColumn<Part, Double> addedPriceCol;

    public Product newProduct;

    @FXML
    public void addPartToProductButton(ActionEvent event) {
        Part partSelected = defaultProductInvTB.getSelectionModel().getSelectedItem();

    }

    @FXML
    void removeAssociationButton(ActionEvent event) {

    }

    @FXML
    public void saveProductButton(ActionEvent event) throws IOException {

        int id = Integer.parseInt(productIdAddText.getText());
        String name = nameProductAddText.getText();
        double price = Double.parseDouble(priceProductAddText.getText());
        int inv = Integer.parseInt(invProductAddText.getText());
        int max = Integer.parseInt(maxProductAddText.getText());
        int min = Integer.parseInt(minProductAddText.getText());
        newProduct = new Product(id,name,price,inv,max,min);
        Inventory.addProduct(newProduct);



        Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
        Scene goBack = new Scene(goBackParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(goBack);
        window.show();





    }

    /**
     * cancel button method used to go back to main form without passing any Objects
     */
    @FXML
    public void cancelButtonPushed(ActionEvent actionEvent) throws IOException {
        Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
        Scene goBack = new Scene(goBackParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(goBack);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultProductInvTB.setItems(Inventory.getAllParts());
        //initialize columns parts view
        partIdProductCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameProductCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvProductCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceProductCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        //initialize columns associated parts view
        addedIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addedPartCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addedInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addedPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));



    }

}
