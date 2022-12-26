package at.eca.skyjo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTwoPlayer implements Initializable {
    private String viewStart = "/at/eca/skyjo/fxml/sceneStart.fxml";

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Deck deck = new Deck();
    }
}
