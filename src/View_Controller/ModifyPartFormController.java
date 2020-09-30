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
 * @author Jose Alvarez Pulido
 */
public class ModifyPartFormController implements Initializable {
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
    public Part part;

    public void sendPart(Part selectedPart) {
        partIndex = (selectedPart.getId()) - 1;
        part = selectedPart;
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
        }
        if(selectedPart instanceof Outsourced)
        {
            partInheritedTextModify.setText(((Outsourced) selectedPart).getCompanyName());
            outsourcedRadioButtonModify.setSelected(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * radio button initialized
         * set the default radio button
         * and link the toggle group
         */
        inheritedLabelModify.setText("Machine ID");
        inheritedGroupModify = new ToggleGroup();
        this.inHouseRadioButtonModify.setToggleGroup(inheritedGroupModify);
        this.outsourcedRadioButtonModify.setToggleGroup(inheritedGroupModify);

    }

    /**
     * cancel button method used to go back to main form without passing any Objects
     */
    public void cancelButtonPushed(ActionEvent actionEvent) throws IOException {
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
    public void modifyPartSaveButtonPushed()
    {
        //Inventory.updatePart(partIndex, part);
    }


    /**
     * Radio button selection method
     * controls the label name
     */
    public void changeRadioButtonModify(){
        if(this.inheritedGroupModify.getSelectedToggle().equals(this.inHouseRadioButtonModify)){
            inheritedLabelModify.setText("Machine ID");
        }
        if(this.inheritedGroupModify.getSelectedToggle().equals(this.outsourcedRadioButtonModify)){
            inheritedLabelModify.setText("Company Name");
        }
    }

}
