package Klasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Device {
    private final StringProperty typ;
    private final StringProperty name;
    private final StringProperty number;
    private final StringProperty fromDate;
    private final StringProperty toDate;
    private final StringProperty status;

    public Device(String typ, String name, String number, String fromDate, String toDate, String status) {
        this.typ = new SimpleStringProperty(typ);
        this.name = new SimpleStringProperty(name);
        this.number = new SimpleStringProperty(number);
        this.fromDate = new SimpleStringProperty(fromDate);
        this.toDate = new SimpleStringProperty(toDate);
        this.status = new SimpleStringProperty(status);
    }

    // Getter für die Eigenschaften
    public String getTyp() {
        return typ.get();
    }

    public String getName() {
        return name.get();
    }

    public String getNumber() {
        return number.get();
    }

    public String getFromDate() {
        return fromDate.get();
    }

    public String getToDate() {
        return toDate.get();
    }

    public String getStatus() {
        return status.get();
    }

    // StringProperty-Getter für die TableView
    public StringProperty typProperty() {
        return typ;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty numberProperty() {
        return number;
    }

    public StringProperty fromDateProperty() {
        return fromDate;
    }

    public StringProperty toDateProperty() {
        return toDate;
    }

    public StringProperty statusProperty() {
        return status;
    }
}
