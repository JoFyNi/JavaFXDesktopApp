package frameinterface;

import Klasses.Device;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;

public class TableEventHandler extends TableCell implements EventHandler<MouseEvent> {
    private final ObservableList<Device> devices = FXCollections.observableArrayList();

    @Override
    public void handle(MouseEvent t) {
        TableCell c = (TableCell) t.getSource();
        int index = c.getIndex();
        System.out.println("Typ = " + devices.get(index).getTyp());
        System.out.println("Name = " + devices.get(index).getName());
        System.out.println("Nummer = " + devices.get(index).getNumber());
        System.out.println("Von = " + devices.get(index).getFromDate());
        System.out.println("Bis = " + devices.get(index).getToDate());
        System.out.println("Status = " + devices.get(index).getStatus());
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}