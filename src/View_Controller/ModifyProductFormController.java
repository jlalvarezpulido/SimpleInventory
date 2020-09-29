package View_Controller;

import Models.Part;
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

    @FXML
    void addModButton(ActionEvent event) {

    }

    @FXML
    void modRemoveAssociationButton(ActionEvent event) {

    }

    @FXML
    void modSaveButton(ActionEvent event) {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
