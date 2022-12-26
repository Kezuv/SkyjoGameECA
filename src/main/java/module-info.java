module at.eca.skyjo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens at.eca.skyjo to javafx.fxml;
    exports at.eca.skyjo;
}