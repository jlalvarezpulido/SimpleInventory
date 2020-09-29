package View_Controller;

import javafx.event.ActionEvent;
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
public class AddPartFormController implements Initializable {

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
    //text fields
    public TextField partIdTextAdd;
    public TextField partInvTextAdd;
    public TextField partPriceCostTextAdd;
    public TextField partMaxTextAdd;
    public TextField partInheritedTextAdd;
    public TextField partMinTextAdd;
    public TextField partNameTextAdd;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * radio button initialized
         * set the default radio button
         * and link the toggle group
         */
        inheritedLabelAdd.setText("Machine ID");
        inheritedGroupAdd = new ToggleGroup();
        this.inHouseRadioButtonAdd.setToggleGroup(inheritedGroupAdd);
        this.outsourcedRadioButtonAdd.setToggleGroup(inheritedGroupAdd);
        inHouseRadioButtonAdd.setSelected(true);



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
    public void addPartSaveButtonPushed(){

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
}
