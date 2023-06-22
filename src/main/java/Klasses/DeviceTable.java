package Klasses;

import javafx.collections.ObservableList;

import java.util.List;

public class DeviceTable {
    private ObservableList<Device> items;

    public void enableDeviceInteraction() {
        // Hier kannst du den Code hinzufügen, der die Interaktion mit den Ausleihe-geräten ermöglicht
        // zum Beispiel: Event Listener für Klick ereignisse auf Geräte hinzufügen und entsprechende Aktionen ausführen
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
        // Hier erfolgt die Ausleihe-aktion für das angegebene Gerät
        // zum Beispiel: Aktualisierung der Geräteinformationen in der Datenbank, Änderung des Gerätestatus usw.

        // Beispielkonsolen ausgabe
        //System.out.println("Das Gerät '" + deviceName + "' wurde von " + inputUsername + " ausgeliehen.");
    }

    public void setItems(ObservableList<Device> items) {
        this.items = items;
    }

    public ObservableList<Device> getItems() {
        return items;
    }
}
