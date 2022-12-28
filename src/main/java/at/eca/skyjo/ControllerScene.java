package at.eca.skyjo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerScene implements Initializable {

    private String viewRules = "/at/eca/skyjo/fxml/sceneRules.fxml";
    private String viewTwoPlayer = "/at/eca/skyjo/fxml/sceneTwoPlayer.fxml";
    private String viewThreePlayer = "/at/eca/skyjo/fxml/sceneThreePlayer.fxml";
    private String viewFourPlayer = "/at/eca/skyjo/fxml/sceneFourPlayer.fxml";
    private Game game;


    @FXML
    private ChoiceBox<Integer> numberOfPlayer;
    @FXML
    private Label choiceBoxLabel;



    public void buttonRulesChangeScene(ActionEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewRules));
            Parent viewRules = loader.load();
            Scene sceneRules = new Scene(viewRules);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneRules);
            window.show();


    }

    public void buttonStartGame(ActionEvent event) throws IOException {

        if (numberOfPlayer.getValue() == 2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewTwoPlayer));
            Parent viewRules = loader.load();
            Scene sceneRules = new Scene(viewRules);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneRules);
            window.show();
        } else if (numberOfPlayer.getValue() == 3) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewThreePlayer));
            Parent viewRules = loader.load();
            Scene sceneRules = new Scene(viewRules);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneRules);
            window.show();
        } else if (numberOfPlayer.getValue() == 4) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewFourPlayer));
            Parent viewRules = loader.load();
            Scene sceneRules = new Scene(viewRules);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneRules);
            window.show();
        }

    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfPlayer.getItems().addAll(2, 3, 4, 5, 6, 7, 8);
        numberOfPlayer.setValue(2);


    }



}