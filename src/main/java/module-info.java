module at.eca.skyjoapp.skyjogameeca {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens at.eca.skyjoapp.skyjogameeca to javafx.fxml;
    exports at.eca.skyjoapp.skyjogameeca;
}