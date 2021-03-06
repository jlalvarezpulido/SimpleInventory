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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the controller for the main form.
 * @author Jose Alvarez Pulido*/
public class MainFormController implements Initializable{

    @FXML
    private Label errorLabel;
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
        errorLabel.setText("");//used to display error messages.

    }

    /**
     * resultPartHandler sets the tableview for only searched parts.
     * @param event This Event is triggered when the Search bar is pressed enter.
     * Uses a try catch when searching for ID values which are numeric and are passed by the function.
     * Before using the try catch I ran into errors where error message will pop up in the terminal. The error was that String search was used to lookupPart by the id which is an int.
     * With the try catch that error is no longer displayed.
     * I could implement this better separating the methods or using a separate buttons to search for IDs and String names.
     * A try catch will still be needed for the ID search however.*/
    public void resultPartHandler(ActionEvent event)
    {
        errorLabel.setText("");//resets the error label.
        String search = partLookUp.getText();
        ObservableList<Part> parts = Inventory.lookupPart(search);
        if(parts.size() == 0)
        {
            try
            {
                int id = Integer.parseInt(search);
                Part part = Inventory.lookupPart(id);
                if(part != null)
                {
                    parts.add(part);
                }
                partTableView.getSelectionModel().select(Inventory.lookupPart(id));
            }
            catch (NumberFormatException ignore)
            {
                errorLabel.setText("Not Found");
            }
        }
        partTableView.setItems(parts);
    }
    /**
     * resultProductHandler sets the tableview for only searched parts.
     * @param event This Event is triggered when the Search bar is pressed enter.
     * Uses a try catch when searching for ID values which are numeric and are passed by the function.
     * Before using the try catch I ran into errors where error message will pop up in the terminal. The error was that String search was used to lookupPart by the id which is an int.
     * With the try catch that error is no longer displayed.
     * I could implement this better separating the methods or using a separate buttons to search for IDs and String names.
     * A try catch will still be needed for the ID search however.*/
    public void resultProductHandler(ActionEvent event)
    {
        errorLabel.setText("");//resets the error label.
        String search = productLookUp.getText();
        ObservableList<Product> products = Inventory.lookupProduct(search);
        if(products.size() == 0)
        {
            try
            {
                int id = Integer.parseInt(search);
                Product product = Inventory.lookupProduct(id);
                if(product!= null)
                {
                    products.add(product);
                }
                productTableView.getSelectionModel().select(Inventory.lookupProduct(id));
            }
            catch (NumberFormatException e)
            {
                errorLabel.setText("Not found");
            }
        }
        productTableView.setItems(products);
    }

    /**
     * Main Forum buttons used to ChangeViews.
     * add parts button.
     * @param actionEvent This event is triggered when the button is pressed.
     * @throws IOException This is needed to change views.
     */
    public void addPartsButton(ActionEvent actionEvent) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartFormView.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    /**
     * modify parts button.
     * @param actionEvent This event is triggered when the button is pressed.
     * @throws IOException This exception is needed to change between views.
     * The FXML Loader is made an instance of. This lets me pass values from one Controller to the other.
     * The controller is declared as loader.getController()
     * This allows the controller to use methods that are not in this class and therefore pass the information.
     * I had many issues with this method at first.
     * I encountered logical errors when nothing was selected in the Table View.
     * It was corrected by making an instance of FXML loader and declaring the controller as loader.getController().
     * Additionally I added a error label to display to the uer when nothing is being selected.
     */
    public void modifyPartsButton(ActionEvent actionEvent) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyPartFormView.fxml"));
        loader.load();

        ModifyPartFormController modifyPartController = loader.getController();

        Part partSent = partTableView.getSelectionModel().getSelectedItem();
        int indexSent = partTableView.getSelectionModel().getSelectedIndex();
        if(partSent != null) // I had errors occur because nothing was selected.
        {
            modifyPartController.sendPart(partSent);
            modifyPartController.getPartIndex(indexSent);
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Parent modifyPartParent = loader.getRoot();
            Scene modifyPartScene = new Scene(modifyPartParent);
            window.setScene(modifyPartScene);
            window.show();
        }
        else
            {
                errorLabel.setText("No Part Selected for modification\n");
            }
    }

    /**
     *add  products button.
     * @param actionEvent Event is triggered when the button is pushed.
     * @throws IOException Is needed to change views.
     */
    public void addProductsButton(ActionEvent actionEvent) throws IOException{
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/View_Controller/AddProductFormView.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    /**
     * modify parts button.
     * @param actionEvent This event is triggered when the button is pressed.
     * @throws IOException This exception is needed to change between views.
     * The FXML Loader is made an instance of. This lets me pass values from one Controller to the other.
     * The controller is declared as loader.getController()
     * This allows the controller to use methods that are not in this class and therefore pass the information.
     * I had many issues with this method at first.
     * I encountered logical errors when nothing was selected in the Table View.
     * It was corrected by making an instance of FXML loader and declaring the controller as loader.getController().
     */
    public void modifyProductsButton(ActionEvent actionEvent) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyProductFormView.fxml"));
        loader.load();
        ModifyProductFormController modifyProductController = loader.getController();
        // this is to check that the selected Product is not a null
        Product productSent = productTableView.getSelectionModel().getSelectedItem();
        int indexSent = productTableView.getSelectionModel().getSelectedIndex();
        if(productSent != null)
        {
            modifyProductController.sendProduct(productSent);
            modifyProductController.sendIndex(indexSent);
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Parent modifyProductParent = loader.getRoot();
            Scene modifyProductScene = new Scene(modifyProductParent);
            window.setScene(modifyProductScene);
            window.show();
        }
        else
        {
            errorLabel.setText("No product Selected for modification\n");
        }
    }
    
    /**
     * Main Forum buttons used to delete Objects.
     * @param actionEvent This event is triggered when the delete button is pressed.
     * Some errors that I encountered where getting the associated list and using it so that the items is not deleted.
     * This was solved by looking using the products getAllAssociatedParts() method. Knowing that this was an observable list I knew I could get its size using the .size().
     * I made sure that if it was greater than zero an error message will display and nothing else would be done.
     *  Else I would prompt the delete box prompt and carryout the deletion using the static Inventory .deleteProduct.
     */
    public void deleteProductsButton(ActionEvent actionEvent)
    {
        boolean deleteProduct;
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if(selectedProduct != null)
        {
            if(selectedProduct.getAllAssociatedParts().size() > 0)
            {
                errorLabel.setText("This product has associated parts");
            }
            else
            {
                deleteProduct = Prompt.textBox("Delete Product","Are you sure you want to delete this product?");
                if(deleteProduct)
                {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
        else
        {
            errorLabel.setText("No product selected for deletion\n");
        }
    }

    /**
     * delete parts button used to delete Parts.
     * After solving the problem from the deleteProductsButton implementing a similar structure here was easy.
     * @param actionEvent Is triggered when the delete button is pressed.
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
        else
        {
            errorLabel.setText("No part selected for deletion\n");
        }
    }

    /**
     * button to exit the application.
     * I encountered an error when trying to close the stage. That is because the stage is set in Main and Main is not the controller for the view.
     * The way I solved this issue was by going to the source using .getSource and casting a Node to SOURCE.
     * I then got the scene from the SOURCE and the window. and closed the stage.
     * @param actionEvent Event is triggered when the exit button is pressed.
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
