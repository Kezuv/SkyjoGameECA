package at.eca.skyjo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerRules {



    private String viewStart = "/at/eca/skyjo/fxml/sceneStart.fxml";

    @FXML
    public void buttonGoBackStartScene(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewStart));
        Parent startView = loader.load();
        Scene sceneStart = new Scene(startView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneStart);
        window.show();

    }


}
