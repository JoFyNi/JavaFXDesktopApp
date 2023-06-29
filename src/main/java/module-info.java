module com.example.javafxdesktopapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.javafxdesktopapp to javafx.fxml;
    exports frameinterface; // Exportiere das Paket frameinterface
}