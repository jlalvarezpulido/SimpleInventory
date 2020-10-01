package View_Controller;

import Models.InHouse;
import Models.Inventory;
import Models.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the add part form. implements initialize.
 * @author Jose Alvarez Pulido
 */
public class AddPartFormController implements Initializable {

    @FXML private Label errorLabel;
    /**
     * Declarations for the radio buttons and toggle group
     */
    public ToggleGroup inheritedGroupAdd;
    public RadioButton inHouseRadioButtonAdd;
    public RadioButton outsourcedRadioButtonAdd;
    public Label inheritedLabelAdd;
    /**
     * Declarations for text Fields
     */
    public TextField partIdTextAdd;
    public TextField partInvTextAdd;
    public TextField partPriceCostTextAdd;
    public TextField partMaxTextAdd;
    public TextField partInheritedTextAdd;
    public TextField partMinTextAdd;
    public TextField partNameTextAdd;
    /**
     * one way of generating unique IDs
     * starts at the count of the size of all parts
     * then adds 1 each time the genID method is used
     */
    static int count = Inventory.getAllParts().size() + 1;
    /** Initializes radio buttons and labels*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        /*
          radio button initialized
          set the default radio button
          and link the toggle group
         */
        inheritedLabelAdd.setText("Machine ID");
        inheritedGroupAdd = new ToggleGroup();
        this.inHouseRadioButtonAdd.setToggleGroup(inheritedGroupAdd);
        this.outsourcedRadioButtonAdd.setToggleGroup(inheritedGroupAdd);
        inHouseRadioButtonAdd.setSelected(true);
        errorLabel.setText("");

    }
    /**
     * Save button method used to save the data.
     * create a part using wrapper methods to get the text and form it.
     * distinguish whether the part is inHouse or Outsourced with the if statements.
     * and create the part and add it to Inventory.
     */
    @FXML
    public void addPartSaveButtonPushed(ActionEvent event) throws IOException
    {
        //exception handling.
        try
        {
            // genID method is used everytime the button is successful
            //count is static value
            int id = count;
            String name = partNameTextAdd.getText();
            double price = Double.parseDouble(partPriceCostTextAdd.getText());
            int inv = Integer.parseInt(partInvTextAdd.getText());
            int min = Integer.parseInt(partMinTextAdd.getText());
            int max = Integer.parseInt(partMaxTextAdd.getText());
            if(inv > max){errorLabel.setText("inventory cannot be greater than the maximum\n");}
            else if(min > max){errorLabel.setText("inventory maximum cannot be less than the minimum\n");}
            else if(min > inv){errorLabel.setText("inventory cannot be less than the minimum\n");}
            else {
                if (inHouseRadioButtonAdd.isSelected())
                {
                    int machineId = Integer.parseInt(partInheritedTextAdd.getText());
                    Inventory.addPart(new InHouse(id, name, price, inv, min, max, machineId)); //create in house part
                }
                if (outsourcedRadioButtonAdd.isSelected())
                {
                    String companyName = partInheritedTextAdd.getText();
                    Inventory.addPart(new Outsourced(id, name, price, inv, min, max, companyName)); //create outsourced part
                }
                //Return to the main menu screen after saving
                Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
                Scene goBack = new Scene(goBackParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(goBack);
                window.show();
                genId(); // only counts up after successfully added product
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
    public void cancelButtonPushed(ActionEvent actionEvent) throws IOException {
        Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
        Scene goBack = new Scene(goBackParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(goBack);
        window.show();
    }
    /**
     * Radio button selection method
     * controls the label name
     */
    public void changeRadioButton(){
        if(this.inheritedGroupAdd.getSelectedToggle().equals(this.inHouseRadioButtonAdd)){
            inheritedLabelAdd.setText("Machine ID");
        }
        if(this.inheritedGroupAdd.getSelectedToggle().equals(this.outsourcedRadioButtonAdd)){
            inheritedLabelAdd.setText("Company Name");
        }
    }

    /**
     * Generates an ID and its adds it count. count is static.
     */
     public void genId()
     {
         ++count;
     }
}
