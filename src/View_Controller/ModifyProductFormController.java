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

public class ModifyProductFormController implements Initializable {
    @FXML
    private TextField modProdIdText;

    @FXML
    private TextField modProdNameText;

    @FXML
    private TextField modProductInvText;

    @FXML
    private TextField modProdPriceText;

    @FXML
    private TextField modProdMaxText;

    @FXML
    private TextField modProdMinText;

    @FXML
    private TextField modProdSearch;

    @FXML
    private TableView<Part> modTopTV;

    @FXML
    private TableColumn<Part, Integer> modTopIdCol;

    @FXML
    private TableColumn<Part, String> modTopNameCol;

    @FXML
    private TableColumn<Part, Integer> modTopInvCol;

    @FXML
    private TableColumn<Part, Double> modTopPriceCol;

    @FXML
    private TableView<Part> modBottomTV;

    @FXML
    private TableColumn<Part, Integer> modBotIdCol;

    @FXML
    private TableColumn<Part, String> modBotNameCol;

    @FXML
    private TableColumn<Part, Integer> modBotInvCol;

    @FXML
    private TableColumn<Part, Double> modBotPriceCol;

    private ObservableList<Part> partListBuffer = FXCollections.observableArrayList();
    public Product newProduct;
    public int productIndex;

    @FXML
    void addModButton(ActionEvent event) {
        Part partSelected = modTopTV.getSelectionModel().getSelectedItem();
        if(partSelected != null)
            partListBuffer.add(partSelected);
    }

    @FXML
    void modRemoveAssociationButton(ActionEvent event) {
        boolean deletePart;
        Part partSelected = modBottomTV.getSelectionModel().getSelectedItem();
        if(partSelected != null)
        {
            deletePart = Prompt.textBox("Removing Association","Are You sure you want to remove this association?");
            if(deletePart)
            {
                partListBuffer.remove(partSelected);
            }
        }
    }

    @FXML
    void modSaveButton(ActionEvent event) throws IOException{
        int id = Integer.parseInt(modProdIdText.getText());
        String name = modProdNameText.getText();
        int inv = Integer.parseInt(modProductInvText.getText());
        double price = Double.parseDouble(modProdPriceText.getText());
        int max = Integer.parseInt(modProdMaxText.getText());
        int min = Integer.parseInt(modProdMinText.getText());
        newProduct = new Product(id,name,price,inv,max,min);
        Inventory.updateProduct(productIndex,newProduct);
        for(Part modPart: partListBuffer)
        {
            newProduct.addAssociatedParts(modPart);
        }
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


    public void sendProduct(Product selectedProduct){
        modProdIdText.setText(String.valueOf(selectedProduct.getId()));
        modProdNameText.setText(selectedProduct.getName());
        modProdPriceText.setText(String.valueOf(selectedProduct.getPrice()));
        modProductInvText.setText(String.valueOf(selectedProduct.getStock()));
        modProdMinText.setText(String.valueOf(selectedProduct.getMin()));
        modProdMaxText.setText(String.valueOf(selectedProduct.getMax()));
        for(Part part: selectedProduct.getAllAssociatedParts())
        {
            partListBuffer.add(part);
        }

    }
    public void sendIndex(int index){
        productIndex = index;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modTopTV.setItems(Inventory.getAllParts());
        modBottomTV.setItems(partListBuffer);
        //initialize columns in modTopTV table View
        modTopIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modTopNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modTopInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modTopPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        //initialize columns in modBottomTV table view
        modBotIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modBotNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modBotInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modBotPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
