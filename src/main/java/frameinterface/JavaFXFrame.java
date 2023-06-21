package frameinterface;

import Klasses.LoginController;
import Klasses.DeviceTable;
import Klasses.Device;
import configuration.Config;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class JavaFXFrame extends Application {
    private final LoginController loginController = new LoginController();
    private final DeviceTable deviceTable = new DeviceTable();
    private final Device device = new Device();
    //
    Label usernameLabel;
    Label emailLabel;
    Label passwordLabel;
    TextField usernameTextField;
    TextField emailTextField;
    PasswordField passwordField;


    @Override
    public void start(Stage primaryStage) throws IOException {
        Config config = Config.getInstance();

        // Setzen der Werte für die Variablen in der Config-Klasse
        config.setCssPath("src/main/resources/style.css");
        config.setFxmlPath( "src/main/resources/com/example/javafxdesktopapp/frame-view.fxml");

        // Zugriff auf die Pfade über das config-Objekt
        String cssPath = config.getCssPath();
        String fxmlPath = config.getFxmlPath();

        primaryStage.setTitle("Willkommensseite");

        // FXML-Datei laden und Controller-Verbindung herstellen
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setController(this); // Controller mit der aktuellen Klasse verbinden

        // Benutzername
        usernameLabel = new Label("Benutzername:");
        usernameTextField = new TextField();

        // E-Mail
        emailLabel = new Label("E-Mail:");
        emailTextField = new TextField();

        // Passwort
        passwordLabel = new Label("Password:");
        passwordField = new PasswordField();

        // Anmelden-Button
        Button loginButton = new Button("Anmelden");
        loginButton.setOnAction(e -> login(usernameTextField.getText(), emailTextField.getText(), passwordField.getText())); //
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

        // geräte aus CSV datei auslesen und auflisten
        deviceTable.getItems();

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
        root.setTop(new Label("Willkommen zur Ausleihe!"));
        root.setCenter(infoVBox);
        root.setLeft(deviceTableVBox);
        root.setRight(cardContainerVBox);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("style.css");     // com.sun.javafx.css.StyleManager loadStylesheetUnPrivileged       WARNUNG: Resource "src/main/resources/style.css" not found.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCard(String deviceName, String deviceInfo) {
        VBox card = new VBox();
        card.getStyleClass().add("card");

        Label nameLabel = new Label(deviceName);
        Label infoLabel = new Label(deviceInfo);

        card.getChildren().addAll(nameLabel, infoLabel);

        return card;
    }

    private void login(String inputUsername, String inputEmail, String inputPassword) {
        // Aktion für den Anmelden-Button
        if (loginController.compareLoginData(inputUsername, inputEmail, inputPassword)) {
            // Anmeldung erfolgreich
            System.out.println("login successful");
            handleSuccessfulLogin();
            // Anmeldedaten speichern für das schnelle Ausleihen von geräten
            // enable DeviceTable
            deviceTable.enableDeviceInteraction();
        } else if (!loginController.compareLoginData(inputUsername, inputEmail, inputPassword)) {
            System.out.println("login data do not match");
            handleFailedLogin();
        }
    }

    private void handleSuccessfulLogin() {
        // Eingabefelder grün markieren
        setTextFieldColor("green-outline");
    }

    private void handleFailedLogin() {
        // Eingabefelder rot markieren
        setTextFieldColor("red-outline");
    }

    private void setTextFieldColor(String cssClass) {
        usernameTextField.getStyleClass().add(cssClass);
        emailTextField.getStyleClass().add(cssClass);
        passwordField.getStyleClass().add(cssClass);

        // timer wie lange die felder farbig markiert werden (Duration)
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            usernameTextField.getStyleClass().remove(cssClass);
            emailTextField.getStyleClass().remove(cssClass);
            passwordField.getStyleClass().remove(cssClass);
        });
        pause.play();
    }
    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void loginButtonClicked() {
        System.out.println("Anmeldebutton wurde geklickt!");
        // Fügen Sie hier Ihre Anmelde-Logik hinzu
    }
}
