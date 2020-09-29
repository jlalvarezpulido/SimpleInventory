package View_Controller;

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
   public ToggleGroup inheritedGroupModify;
   public RadioButton inHouseRadioButtonModify;
   public RadioButton outsourcedRadioButtonModify;
   public Label inheritedLabelModify;

    /**
     * Declarations for text Fields
     */
    public TextField partIdTextModify;
    public TextField partNameTextModify;
    public TextField partInvTextModify;
    public TextField partPriceCostTextModify;
    public TextField partMaxTextModify;
    public TextField partInheritedTextModify;
    public TextField partMinTextModify;




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
        inHouseRadioButtonModify.setSelected(true);

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
    public void modifyPartSaveButtonPushed(){

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
