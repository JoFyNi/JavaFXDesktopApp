package configuration;

import Klasses.Device;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private static Config instance;
    private String cssPath;
    private String fxmlPath;
    public List<Device> devices = new ArrayList<Device>();
    private Config() {
        // Private Konstruktor, um direkte Instanziierung zu verhindern
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getCssPath() {
        return cssPath;
    }

    public void setCssPath(String cssPath) {
        this.cssPath = cssPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }
}

    // CSS_PATH = "src/main/resources/style.css"
    // FXML_PATH = "src/main/resources/com/example/javafxdesktopapp/frame-view.fxml"


