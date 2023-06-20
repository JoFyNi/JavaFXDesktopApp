package Klasses;

import java.util.List;

public class LoginController {
    private String inputUsername = "";
    private String inputEmail = "";
    private String inputPassword = "";

    public void login() {
        // Hier erfolgt die Überprüfung der Anmeldedaten
        // Wenn die Anmeldung erfolgreich ist, setze den Wert von inputUsername, inputEmail und inputPassword entsprechend
        inputUsername = View.getInputUsername();
        inputEmail = View.getInputEmail();
        inputPassword = View.getInputPassword();

        // CSV-Datei laden
        String csvData = CSVLoader.loadCSVData("../resources/benutzerdatenbank.csv");
        List<String> dataRows = CSVParser.parseCSVData(csvData);

        // Überprüfung der Anmeldedaten
        boolean loginSuccessful = false;
        for (String row : dataRows) {
            String[] rowData = row.split(",");
            String username = rowData[0];
            String email = rowData[1];
            String password = rowData[2];

            if (inputUsername.equals(username) && inputEmail.equals(email) && inputPassword.equals(password)) {
                // Anmeldung erfolgreich
                handleSuccessfulLogin();
                View.showPopup("Anmeldung erfolgreich"); // Popup anzeigen
                loginSuccessful = true;
                break;
            }
        }

        if (!loginSuccessful) {
            // Anmeldung fehlgeschlagen
            handleFailedLogin();
            View.showPopup("Anmeldung fehlgeschlagen"); // Popup anzeigen
        }
    }

    public static boolean compareLoginInformation(String inputUsername, String inputEmail, String inputPassword) {
        // CSV-Datei laden
        String csvData = CSVLoader.loadCSVData("../resources/benutzerdatenbank.csv");
        List<String> dataRows = CSVParser.parseCSVData(csvData);

        // Überprüfung der Anmeldedaten
        boolean loginSuccessful = false;
        for (String row : dataRows) {
            String[] rowData = row.split(",");
            String username = rowData[0];
            String email = rowData[1];
            String password = rowData[2];

            if (inputUsername.equals(username) && inputEmail.equals(email) && inputPassword.equals(password)) {
                // Anmeldung erfolgreich
                handleSuccessfulLogin();
                View.showPopup("Anmeldung erfolgreich"); // Popup anzeigen
                loginSuccessful = true;
                break;
            }
        }

        if (!loginSuccessful) {
            // Anmeldung fehlgeschlagen
            handleFailedLogin();
            View.showPopup("Anmeldung fehlgeschlagen"); // Popup anzeigen
        }
        return loginSuccessful;
    }

    private static void handleSuccessfulLogin() {
        // Eingabefelder grün markieren
        View.setFieldBorderColor("username", "green");
        View.setFieldBorderColor("email", "green");

        // Popup "Anmeldung erfolgreich" anzeigen
        View.showPopup("Anmeldung Erfolgreich");
    }

    private static void handleFailedLogin() {
        // Eingabefelder rot markieren
        View.setFieldBorderColor("username", "red");
        View.setFieldBorderColor("email", "red");

        // Popup "Anmeldung fehlgeschlagen" anzeigen
        View.showPopup("Anmeldung Fehlgeschlagen");
    }

    public void enableDeviceInteraction() {
        // Hier kannst du den Code hinzufügen, der die Interaktion mit den Ausleihgeräten ermöglicht
        // Zum Beispiel: Event Listener für Klickereignisse auf Geräte hinzufügen und entsprechende Aktionen ausführen
        List<DeviceElement> deviceElements = View.getDeviceElements();

        for (DeviceElement deviceElement : deviceElements) {
            deviceElement.addEventListener(new DeviceClickListener() {
                @Override
                public void onClick(String deviceName) {
                    // Beispielaktion: Gerät ausleihen
                    borrowDevice(deviceName);
                }
            });
        }
    }

    private void borrowDevice(String deviceName) {
        // Hier erfolgt die Ausleihaktion für das angegebene Gerät
        // Zum Beispiel: Aktualisierung der Geräteinformationen in der Datenbank, Änderung des Gerätestatus usw.

        // Beispielkonsolenausgabe
        System.out.println("Das Gerät '" + deviceName + "' wurde von " + inputUsername + " ausgeliehen.");
    }
}

