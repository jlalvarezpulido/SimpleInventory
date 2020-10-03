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
/** Class for the Add Product view.
 * @author Jose Alvarez Pulido */
public class AddProductFormController implements Initializable {

    @FXML
    private Label errorLabel;
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
    /** part list buffer is a buffer list for the associated parts. */
    private ObservableList<Part> partListBuffer = FXCollections.observableArrayList();
    public Product newProduct;
    static int count = Inventory.getAllProducts().size() + 1;
    /** adds parts to the bufferList.
     * @param event this event is triggered the moment the button is pushed. */
    @FXML//adds products with a not null exceptions
    public void addPartToProductButton(ActionEvent event) {
        Part partSelected = defaultProductInvTB.getSelectionModel().getSelectedItem();
        if(partSelected != null)
            partListBuffer.add(partSelected);
    }
    /** Removes parts from buffer List.
     * @param event The even is triggered at the press of the button. */
    @FXML//removes associated parts
    void removeAssociationButton(ActionEvent event) {
        boolean deletePart;
        Part partSelected = addedProductTB.getSelectionModel().getSelectedItem();
        if(partSelected != null)
        {
            deletePart = Prompt.textBox("Removing Association","Are You sure you want to remove this association?");
            if(deletePart)//if prompt is clicked Ok, part is removed from the buffer.
            {
                partListBuffer.remove(partSelected);
            }
        }
    }
    /**
     * Save button method used to save the data.
     * The try catch is used on this method to catch runtime errors where a string could be inputted into a text field that is expecting another data type.
     * create a part using wrapper methods to get the text and form it.
     * and create the product and add it to Inventory.
     * @throws IOException used to change the view when pressed.
     * @param event The event represents a press of the button.
     *              In the future I would implement many try catches for each particular assignment so that a specific message would be outputted for each type of runtime errors
     */
    @FXML
    public void saveProductButton(ActionEvent event) throws IOException {
        try
        {
            /*
          auto generate IDs
         */
            int id = count;
            String name = nameProductAddText.getText();
            double price = Double.parseDouble(priceProductAddText.getText());
            int inv = Integer.parseInt(invProductAddText.getText());
            int max = Integer.parseInt(maxProductAddText.getText());
            int min = Integer.parseInt(minProductAddText.getText());
            if(inv > max){errorLabel.setText("inventory cannot be greater than the maximum\n");}
            else if(min > max){errorLabel.setText("inventory maximum cannot be less than the minimum\n");}
            else if(min > inv){errorLabel.setText("inventory cannot be less than the minimum\n");}
            else
            {
                if(partsTotalPrice() > price){errorLabel.setText("parts cost more than the product");}
                else
                    {
                        newProduct = new Product(id,name,price,inv,min,max);
                        for(Part addPart: partListBuffer)
                        {
                            newProduct.addAssociatedParts(addPart);
                        }
                        Inventory.addProduct(newProduct);
                        Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
                        Scene goBack = new Scene(goBackParent);
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(goBack);
                        window.show();
                        genId();
                    }
            }
        }
        catch (NumberFormatException e)
        {
            errorLabel.setText("Exception: "+e.getLocalizedMessage());
        }
    }

    /**
     * cancel button method used to go back to main form without passing any Objects.
     * @param actionEvent This event is triggered when the button is pushed.
     * @throws IOException this is used to change views.
     */
    @FXML
    public void cancelButtonPushed(ActionEvent actionEvent) throws IOException {
        Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
        Scene goBack = new Scene(goBackParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(goBack);
        window.show();
    }


    /** method used to initialize table columns and table views.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultProductInvTB.setItems(Inventory.getAllParts());
        addedProductTB.setItems(partListBuffer);
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
        // label used to display error messages.
        errorLabel.setText("");
    }
    /** searchHandler used to search for parts in the upper table view.
     * @param event This Event is triggered when the Search bar is pressed enter.
     * Uses a try catch when searching for ID values which are numeric and are passed by the function.
     * Before using the try catch I ran into errors where error message will pop up in the terminal. The error was that String search was used to lookupPart by the id which is an int.
     * With the try catch that error is no longer displayed.
     * I could implement this better separating the methods or using a seperate buttons to search for IDs and String names.
     * A try catch will still be needed for the ID search however.*/
    public void searchHandler(ActionEvent event)
    {
        errorLabel.setText("");
        String search = searchAddProductText.getText();
        ObservableList<Part> searchPart = Inventory.lookupPart(search);
        if(searchPart.size() == 0)
        {
            try
            {
                int id = Integer.parseInt(search);
                Part part = Inventory.lookupPart(id);
                if(part != null)//if not null
                {
                    searchPart.add(part);
                }
                defaultProductInvTB.getSelectionModel().select(Inventory.lookupPart(id));//sets the table view to the new one, no values are lost.
            }
            catch (NumberFormatException e)
            {
                errorLabel.setText("Not Found");
            }
        }
        defaultProductInvTB.setItems(searchPart);
    }
    /**
     * Generates an ID and its adds it count. count is static.
     * At first I simply would add one to the size of the list.
     * This caused a logical error when a product was deleted and the size was less than value of the IDs.
     * This error made it possible for two products to have the same ID thus not meeting the requirement.
     * In the future I would like to create a that both generates the ID and sets it in the creation of the product.
     */
    public void genId(){
       ++count;
    }
    /** calculates the total price in the part buffer.
     * @return The price of all the parts added up in the part buffer.
     * Using a for loop to make sure that each part is counted.*/
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
