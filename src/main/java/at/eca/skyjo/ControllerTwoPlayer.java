package at.eca.skyjo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTwoPlayer implements Initializable {
    private String viewStart = "/at/eca/skyjo/fxml/sceneStart.fxml";
    private Game gameForTwo = new Game(2);


    @FXML
    private ImageView deckImgFaceDown;

    @FXML
    private ImageView deckImgFaceUp;

    @FXML
    public void buttonGoBackStartScene(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewStart));
        Parent startView = loader.load();
        Scene sceneStart = new Scene(startView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneStart);
        window.show();

    }

    public int clickP1C0(ActionEvent event) throws IOException {
        return 0;
    }

    public Card clickDeckButton(ActionEvent event) throws IOException{
        return gameForTwo.getDeck();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Deck deck = new Deck();
    }
}
