package src.gameengine.application;

import javax.swing.*;
import java.awt.*;

public abstract class CustomPanel extends JPanel {
    protected static ApplicationWindow aW;
    protected static PanelMediator pm;

    public CustomPanel(PanelMediator pm) {
        super();
        this.pm = pm;
    }

    public void addAppWindow(ApplicationWindow aW) {
        this.aW = aW;
    }
}
