package Klasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {
    public boolean compareLoginData(String inputUsername, String inputEmail, String inputPassword) {
        boolean loginSuccessful = false;
        // Hier erfolgt die Überprüfung der Anmeldedaten
        // CSV-Datei laden
        String line = "";
        String cvsSplitBy = ",";
        String csvFile = "src/main/resources/benutzerdatenbank.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                System.out.println("Data: " + line);
                String[] rowData = line.split(cvsSplitBy);
                String username = rowData[0];
                String email = rowData[1];
                String password = rowData[2];
                if (inputUsername.equals(username) && inputEmail.equals(email) && inputPassword.equals(password)) {
                    // Anmeldung erfolgreich
                    loginSuccessful = true;
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return loginSuccessful;
    }
}