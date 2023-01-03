package at.eca.skyjo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class SkyjoGameMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/eca/skyjo/fxml/sceneStart.fxml"));
        Parent root = loader.load();
        Scene sceneStart = new Scene(root);

        stage.setTitle("E.C.A Skyjo Game");
        Image logoIcon = new Image("/at/eca/skyjo/img/logo.png");
        stage.getIcons().add(logoIcon);
        stage.setScene(sceneStart);
        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }

}