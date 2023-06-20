module com.example.javafxdesktopapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.javafxdesktopapp to javafx.fxml;
    exports frameinterface; // Exportieren Sie das Paket frameinterface
}