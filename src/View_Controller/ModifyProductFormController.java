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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This class is the controller for the modify product view.
 * @author Jose Alvarez Pulido */
public class ModifyProductFormController implements Initializable {

    @FXML
    private Label errorLabel;
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
    /** partListBuffer is a temporary list buffer for the products that will be added. */
    private ObservableList<Part> partListBuffer = FXCollections.observableArrayList();
    public Product newProduct;
    public int productIndex;
    /** This method adds parts the partListBuffer. the buffer is added to associated parts when save button is pressed. */
    @FXML
    void addModButton(ActionEvent event)
    {
        Part partSelected = modTopTV.getSelectionModel().getSelectedItem();
        if(partSelected != null)
            partListBuffer.add(partSelected);
    }
    /** This method removes parts from the partListBuffer.*/
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
    /** This method is for the save button.
     * try catch exception handling
     * if statements to handle logical errors.*/
    @FXML
    void modSaveButton(ActionEvent event) throws IOException
    {
        try
        {
            int id = Integer.parseInt(modProdIdText.getText());
            String name = modProdNameText.getText();
            int inv = Integer.parseInt(modProductInvText.getText());
            double price = Double.parseDouble(modProdPriceText.getText());
            int max = Integer.parseInt(modProdMaxText.getText());
            int min = Integer.parseInt(modProdMinText.getText());
            if(inv > max){errorLabel.setText("inventory cannot be greater than the maximum\n");}
            else if(min > max){errorLabel.setText("inventory maximum cannot be less than the minimum\n");}
            else if(min > inv){errorLabel.setText("inventory cannot be less than the minimum\n");}
            else
            {
                if(partsTotalPrice() > price){errorLabel.setText("parts cost more than the product");}
                else
                {
                    newProduct = new Product(id,name,price,inv,min,max);
                    for(Part modPart: partListBuffer)
                    {
                        newProduct.addAssociatedParts(modPart);
                    }
                    Inventory.updateProduct(productIndex,newProduct);
                    Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
                    Scene goBack = new Scene(goBackParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(goBack);
                    window.show();
                }
            }
        }
        catch (NumberFormatException e)
        {
            errorLabel.setText("Exception: "+e.getLocalizedMessage());
        }
    }
    /**
     * cancel button method used to go back to main form without passing any Objects
     */
    @FXML
    public void cancelButtonPushed(ActionEvent actionEvent) throws IOException
    {
        Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
        Scene goBack = new Scene(goBackParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(goBack);
        window.show();
    }
    /** This method is used to send products from main form table view.*/
    public void sendProduct(Product selectedProduct)
    {
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
    /** This method passes the index of the product sent*/
    public void sendIndex(int index){
        productIndex = index;
    }
    /** method used to initialize table columns and table views. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

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

        errorLabel.setText("");


    }
    /** searchHandler used to search for parts in the upper table view. */
    public void searchHandler(ActionEvent event) {
        String search = modProdSearch.getText();
        ObservableList<Part> searchPart = Inventory.lookupPart(search);
        if (searchPart.size() == 0) {
            try {
                int id = Integer.parseInt(search);
                Part part = Inventory.lookupPart(id);
                if (part != null) {
                    searchPart.add(part);
                }
                modTopTV.getSelectionModel().select(Inventory.lookupPart(id));

            } catch (NumberFormatException ignore) {

            }
        }
        modTopTV.setItems(searchPart);
    }
    /** calculates the total price in the part buffer. */
    public double partsTotalPrice()
    {
        double partsTotalPrice = 0;
        for(Part part: partListBuffer)
        {
            partsTotalPrice += part.getPrice();
        }
        return  partsTotalPrice;
    }

}
