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
    /** This method adds parts the partListBuffer. the buffer is added to associated parts when save button is pressed.
     * @param event is triggered when the button is pushed. */
    @FXML
    void addModButton(ActionEvent event)
    {
        Part partSelected = modTopTV.getSelectionModel().getSelectedItem();
        if(partSelected != null)//exception handling.
            partListBuffer.add(partSelected);
    }
    /** This method removes parts from the partListBuffer.
     * @param event Is triggered when the button is pushed.
     * Since this is a deletion a prompt is displayed and part is removed from the buffer. */
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
     * cancel button method used to go back to main form without passing any Objects.
     * @param actionEvent Is triggered when the button is pressed.
     * @throws IOException Is needed to change the view back to main screen.
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
    /**
     * This method is used to send products from main form table view.
     * @param selectedProduct The product that is carried over from the Main screen Product Table View.
     * I had trouble sending over the associated parts, I originally had the botom table view display the associated parts.
     * However I ran into an issue when removing parts. Since I deleted them using the product method it would be saved even if the cancel button was pressed.
     * Thus causing a logical error. I fixed this by implementing a similar list as in the addProductsController.
     * The solution was another partListBuffer. So in order to send them over I used an enhanced for loop. to add all the parts to the buffer.
     * So no changes would be made to the AssociatedPartsList unless the Save button was pressed because I would just replace the list at run time when save button is pressed.
     */
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
    /** This method passes the index of the product sent
     * @param index this is index sent from the main screen of the product that was being sent over. The index refers to its place in the Observable list.*/
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
    /** searchHandler used to search for parts in the upper table view.
     * @param event This Event is triggered when the Search bar is pressed enter.
     * Uses a try catch when searching for ID values which are numeric and are passed by the function.
     * Before using the try catch I ran into errors where error message will pop up in the terminal. The error was that String search was used to lookupPart by the id which is an int.
     * With the try catch that error is no longer displayed.
     * I could implement this better separating the methods or using a seperate buttons to search for IDs and String names.
     * A try catch will still be needed for the ID search however.*/
    public void searchHandler(ActionEvent event) {
        errorLabel.setText("");
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

            } catch (NumberFormatException e) {
                errorLabel.setText("Not Found");
            }
        }
        modTopTV.setItems(searchPart);
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
