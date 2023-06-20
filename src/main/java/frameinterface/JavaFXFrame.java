package frameinterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXFrame extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Willkommensseite");

        // Benutzername
        Label usernameLabel = new Label("Benutzername:");
        TextField usernameTextField = new TextField();

        // E-Mail
        Label emailLabel = new Label("E-Mail:");
        TextField emailTextField = new TextField();

        // Passwort
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        // Anmelden-Button
        Button loginButton = new Button("Anmelden");
        loginButton.setOnAction(e -> login());
        loginButton.getStyleClass().add("login-button"); // CSS-Klasse für den Button hinzufügen

        // Informationen VBox
        VBox infoVBox = new VBox();
        infoVBox.getStyleClass().add("info-vbox"); // CSS-Klasse für die VBox hinzufügen
        infoVBox.getChildren().addAll(usernameLabel, usernameTextField, emailLabel, emailTextField, passwordLabel,
                passwordField, loginButton);

        // Geräte-Tabelle
        TableView<String[]> deviceTable = new TableView<>();
        deviceTable.getStyleClass().add("device-table"); // CSS-Klasse für die Tabelle hinzufügen

        TableColumn<String[], String> typeColumn = new TableColumn<>("Typ");
        TableColumn<String[], String> nameColumn = new TableColumn<>("Name");
        TableColumn<String[], String> dateFromColumn = new TableColumn<>("Datum von");
        TableColumn<String[], String> dateToColumn = new TableColumn<>("Datum bis");
        TableColumn<String[], String> renterNameColumn = new TableColumn<>("Name-Mieter");
        TableColumn<String[], String> statusColumn = new TableColumn<>("Status");

        deviceTable.getColumns().addAll(typeColumn, nameColumn, dateFromColumn, dateToColumn, renterNameColumn,
                statusColumn);

        // Geräte-Tabelle VBox
        VBox deviceTableVBox = new VBox();
        deviceTableVBox.getStyleClass().add("device-table-vbox"); // CSS-Klasse für die VBox hinzufügen
        deviceTableVBox.getChildren().addAll(new Label("Geräteübersicht:"), deviceTable);

        // Karten VBox
        VBox cardContainerVBox = new VBox();
        cardContainerVBox.getStyleClass().add("card-container-vbox"); // CSS-Klasse für die VBox hinzufügen
        cardContainerVBox.getChildren().addAll(new Label("Geräteübersicht (Karten):"), createCard("Gerät 1",
                "Weitere Informationen zum Gerät 1."), createCard("Gerät 2",
                "Weitere Informationen zum Gerät 2."));

        // Haupt-Layout
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root"); // CSS-Klasse für das BorderPane hinzufügen
        root.setTop(new Label("Willkommen zur Ausleih-App!"));
        root.setCenter(infoVBox);
        root.setLeft(deviceTableVBox);
        root.setRight(cardContainerVBox);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("../Designe/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void login() {
        // Aktion für den Anmelden-Button
    }

    private VBox createCard(String deviceName, String deviceInfo) {
        VBox card = new VBox();
        card.getStyleClass().add("card");

        Label nameLabel = new Label(deviceName);
        Label infoLabel = new Label(deviceInfo);

        card.getChildren().addAll(nameLabel, infoLabel);

        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
