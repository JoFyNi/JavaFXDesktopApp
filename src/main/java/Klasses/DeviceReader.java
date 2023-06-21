package Klasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DeviceReader {
    public Object getDevices() {
        // CSV-Datei laden
        String line = "";
        String cvsSplitBy = ",";
        String csvFile = "src/main/resources/deviceDatenbank.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                System.out.println("Data: " + line);
                String[] rowData = line.split(cvsSplitBy);
                String typ = rowData[0];
                String name = rowData[1];
                String fromDate = rowData[2];
                String toDate = rowData[3];
                String status = rowData[4];

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
