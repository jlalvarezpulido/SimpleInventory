package View_Controller;

import Models.InHouse;
import Models.Inventory;
import Models.Outsourced;
import Models.Part;
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
 *  controller class for the modify part view.
 * @author Jose Alvarez Pulido
 */
public class ModifyPartFormController implements Initializable
{
    @FXML private Label errorLabel;
    /**
     *Declarations for the radio buttons and toggle group
     */
   @FXML private ToggleGroup inheritedGroupModify;
   @FXML private RadioButton inHouseRadioButtonModify;
   @FXML private RadioButton outsourcedRadioButtonModify;
   @FXML private Label inheritedLabelModify;

    /**
     * Declarations for text Fields
     */
    @FXML private TextField partIdTextModify;
    @FXML private TextField partNameTextModify;
    @FXML private TextField partInvTextModify;
    @FXML private TextField partPriceCostTextModify;
    @FXML private TextField partMaxTextModify;
    @FXML private TextField partInheritedTextModify;
    @FXML private TextField partMinTextModify;

    public int partIndex;
/** Sends part from the main form to the modify part form. */
    public void sendPart(Part selectedPart)
    {
        partIdTextModify.setText(String.valueOf(selectedPart.getId()));
        partNameTextModify.setText(selectedPart.getName());
        partInvTextModify.setText(String.valueOf(selectedPart.getStock()));
        partPriceCostTextModify.setText(String.valueOf(selectedPart.getPrice()));
        partMaxTextModify.setText(String.valueOf(selectedPart.getMax()));
        partMinTextModify.setText(String.valueOf(selectedPart.getMin()));
        if(selectedPart instanceof InHouse)
        {
            partInheritedTextModify.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
            inHouseRadioButtonModify.setSelected(true);
            inheritedLabelModify.setText("Machine ID");
        }
        if(selectedPart instanceof Outsourced)
        {
            partInheritedTextModify.setText(((Outsourced) selectedPart).getCompanyName());
            outsourcedRadioButtonModify.setSelected(true);
            inheritedLabelModify.setText("Company Name");
        }

    }
    /** Gets the part Index from the sent items in the main form. */
    public void getPartIndex(int index){
        partIndex = index;
    }
    /** method used to initialize radio buttons. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        /*
          radio button initialized
          set the default radio button
          and link the toggle group
         */
        inheritedGroupModify = new ToggleGroup();
        this.inHouseRadioButtonModify.setToggleGroup(inheritedGroupModify);
        this.outsourcedRadioButtonModify.setToggleGroup(inheritedGroupModify);
        errorLabel.setText("");

    }

    /**
     * cancel button method used to go back to main form without passing any Objects
     */
    public void cancelButtonPushed(ActionEvent actionEvent) throws IOException
    {
        Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
        Scene goBack = new Scene(goBackParent);
        //This line gets the stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(goBack);
        window.show();
    }
    /**
     * Save button method used to save the data
     */
    public void modifyPartSaveButtonPushed(ActionEvent actionEvent) throws IOException
    {
        try
        {
            int id = Integer.parseInt(partIdTextModify.getText());
            String name = partNameTextModify.getText();
            double price = Double.parseDouble(partPriceCostTextModify.getText());
            int inv = Integer.parseInt(partInvTextModify.getText());
            int min = Integer.parseInt(partMinTextModify.getText());
            int max = Integer.parseInt(partMaxTextModify.getText());
            if(inv > max){errorLabel.setText("inventory cannot be greater than the maximum\n");}
            else if(min > max){errorLabel.setText("inventory maximum cannot be less than the minimum\n");}
            else if(min > inv){errorLabel.setText("inventory cannot be less than the minimum\n");}
            else
            {
                if(inHouseRadioButtonModify.isSelected())
                {
                    int machineId = Integer.parseInt(partInheritedTextModify.getText());
                    Inventory.updatePart(partIndex, new InHouse(id, name, price, inv, min, max, machineId));
                }
                if(outsourcedRadioButtonModify.isSelected())
                {
                    String companyName = partInheritedTextModify.getText();
                    Inventory.updatePart(partIndex, new Outsourced(id, name ,price, inv, min, max, companyName));
                }
                Parent goBackParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainFormView.fxml"));
                Scene goBack = new Scene(goBackParent);
                //This line gets the stage information
                Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                window.setScene(goBack);
                window.show();
            }
        }
        catch (NumberFormatException e)
        {
            errorLabel.setText("Excemptions: "+ e.getLocalizedMessage());
        }
    }


    /**
     * Radio button selection method
     * controls the label name
     */
    public void changeRadioButtonModify()
    {
        if(this.inheritedGroupModify.getSelectedToggle().equals(this.inHouseRadioButtonModify))
        {
            inheritedLabelModify.setText("Machine ID");
        }
        if(this.inheritedGroupModify.getSelectedToggle().equals(this.outsourcedRadioButtonModify))
        {
            inheritedLabelModify.setText("Company Name");
        }
    }

}
