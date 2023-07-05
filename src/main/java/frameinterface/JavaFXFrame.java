package frameinterface;

import Klasses.Device;
import Klasses.DeviceTable;
import Klasses.LoginController;
import configuration.Config;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.*;

public class JavaFXFrame extends Application {
    private final LoginController loginController = new LoginController();
    private final DeviceTable deviceTable = new DeviceTable();
    private Device device;
    private final ObservableList<Device> devices = FXCollections.observableArrayList();
    private final TableView<Device> deviceViewer = new TableView<>(devices);
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

        // Geräte-Tabelle VBox
        VBox deviceTableVBox = new VBox();
        deviceTableVBox.getStyleClass().add("device-table-vbox"); // CSS-Klasse für die VBox hinzufügen
        deviceTableVBox.getChildren().addAll(new Label("Geräteübersicht:"), deviceViewer);

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

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("style.css");     // com.sun.javafx.css.StyleManager loadStylesheetUnPrivileged       WARNUNG: Resource "src/main/resources/style.css" not found.

        deviceViewer.setOnMouseClicked(this::handle);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void handle(MouseEvent mouseEvent) {
        try {
            int selectedIndex = deviceViewer.getSelectionModel().getSelectedIndex();
            Device selectedDevice = deviceViewer.getSelectionModel().getSelectedItem();

            // Konsolen ausgaben können im späteren Verlauf entfernt werden
            System.out.println("\nTyp = " + devices.get(selectedIndex).getTyp());
            System.out.println("Name = " + devices.get(selectedIndex).getName());
            System.out.println("Nummer = " + devices.get(selectedIndex).getNumber());
            System.out.println("Von = " + devices.get(selectedIndex).getFromDate());
            System.out.println("Bis = " + devices.get(selectedIndex).getToDate());
            System.out.println("Status = " + devices.get(selectedIndex).getStatus() + "\n");

            showDialog(selectedDevice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDataFromCSV() {
        if (deviceTable.getItems() != null) {
            deviceTable.getItems().clear();
        }

        String line = "";
        String csvFile = "src/main/resources/deviceDatenbank.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                System.out.println("Data: " + line);
                String[] rowData = line.split(",");
                String typ = rowData[0];
                String name = rowData[1];
                String number = rowData[2];
                String fromDate = rowData[3];
                String toDate = rowData[4];
                String status = rowData[5];
                device = new Device(typ, name, number, fromDate, toDate, status);
                devices.add(device);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        deviceTable.setItems(devices);
    }

    private VBox createCard(String deviceName, String deviceInfo) {
        VBox card = new VBox();
        card.getStyleClass().add("card");

        Label nameLabel = new Label(deviceName);
        Label infoLabel = new Label(deviceInfo);

        card.getChildren().addAll(nameLabel, infoLabel);

        return card;
    }

    private void createDeviceTable() {
        // Geräte-Tabelle
        TableColumn<Device, String> typeColumn = new TableColumn<>("Typ");
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typProperty());

        TableColumn<Device, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Device, String> numberColumn = new TableColumn<>("Number");
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());

        TableColumn<Device, String> fromDateColumn = new TableColumn<>("Datum von");
        fromDateColumn.setCellValueFactory(cellData -> cellData.getValue().fromDateProperty());

        TableColumn<Device, String> toDateColumn = new TableColumn<>("Datum bis");
        toDateColumn.setCellValueFactory(cellData -> cellData.getValue().toDateProperty());

        TableColumn<Device, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        deviceViewer.setPrefWidth(550); // Setzen Sie die gewünschte Breite des TableView-Elements
        deviceViewer.getStyleClass().add("device-table"); // CSS-Klasse für die Tabelle hinzufügen
        deviceViewer.getColumns().addAll(typeColumn, nameColumn, numberColumn, fromDateColumn, toDateColumn, statusColumn);

        deviceViewer.setEditable(true);

        loadDataFromCSV();
    }

    private void login(String inputUsername, String inputEmail, String inputPassword) {
        // Aktion für den Anmelden-Button
        if (loginController.compareLoginData(inputUsername, inputEmail, inputPassword)) {
            // Anmeldung erfolgreich
            System.out.println("login successful");
            createDeviceTable();
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

    private void showDialog(Device selectedDevice) {
        if (selectedDevice.getStatus().equals("Verfügbar")) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Gerät mieten");
            dialog.setHeaderText("Gerät mieten - " + selectedDevice.getName());

            // Erstellen der Dialogelemente
            DatePicker fromDatePicker = new DatePicker();
            DatePicker toDatePicker = new DatePicker();

            GridPane gridPane = new GridPane();
            gridPane.add(new Label("Von:"), 0, 0);
            gridPane.add(fromDatePicker, 1, 0);
            gridPane.add(new Label("Bis:"), 0, 1);
            gridPane.add(toDatePicker, 1, 1);

            dialog.getDialogPane().setContent(gridPane);

            // Hinzufügen der Dialogbuttons
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            // Überprüfen, ob der Benutzer OK geklickt hat
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    String fromDate = fromDatePicker.getValue().toString();
                    String toDate = toDatePicker.getValue().toString();
                    // Führen Sie hier die Aktion für die Mietung aus
                    // ...

                    // Update CSV file with new data
                    selectedDevice.setFromDate(fromDate);
                    selectedDevice.setToDate(toDate);
                    updateCSV(selectedDevice); // Call the method to update the CSV file

                    // Geben Sie die ausgewählten Daten zurück
                    return new Pair<>(fromDate, toDate);
                }
                return null;
            });

            // Anzeigen des Dialogs und Verarbeiten der Ergebnisse
            dialog.showAndWait().ifPresent(result -> {
                String fromDate = result.getKey();
                String toDate = result.getValue();
                // Hier können Sie die ausgewählten Daten verwenden, um die Mietaktion durchzuführen
            });
        }
    }

    private void updateCSV(Device selectedDevice) {
        String csvFile = "src/main/resources/deviceDatenbank.csv";
        String tempFile = "src/main/resources/temp.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             FileWriter fw = new FileWriter(tempFile)) {

            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(",");
                String number = rowData[2];

                if (selectedDevice.getNumber().equals(number)) {
                    // Update the fromDate and toDate values
                    rowData[3] = selectedDevice.getFromDate();
                    rowData[4] = selectedDevice.getToDate();
                }

                fw.write(String.join(",", rowData) + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Rename the temporary file to the original file
        File originalFile = new File(csvFile);
        File temp = new File(tempFile);
        if (temp.renameTo(originalFile)) {
            System.out.println("CSV file updated successfully.");
        } else {
            System.out.println("Failed to update the CSV file.");
        }

        loadDataFromCSV(); // Reload the data from the CSV file
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
