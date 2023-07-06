package Klasses;

import javafx.collections.ObservableList;

public class DeviceTable {
    private ObservableList<Device> items;
    public void setItems(ObservableList<Device> items) {
        this.items = items;
    }

    public ObservableList<Device> getItems() {
        return items;
    }
}