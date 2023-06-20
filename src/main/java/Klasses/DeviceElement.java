package Klasses;

public class DeviceElement {
    private String name;
    private DeviceClickListener listener;

    public DeviceElement(String name) {
        this.name = name;
    }

    public void addEventListener(DeviceClickListener listener) {
        this.listener = listener;
    }

    public void onClick() {
        if (listener != null) {
            listener.onClick(name);
        }
    }
}
