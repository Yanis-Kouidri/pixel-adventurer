package gameengine.application;

import javax.swing.*;

public abstract class CustomPanel extends JPanel {
    protected ApplicationWindow aW;
    protected PanelMediator pm;

    public CustomPanel(PanelMediator pm) {
        super();
        this.pm = pm;
    }

    public void addAppWindow(ApplicationWindow aW) {
        this.aW = aW;
    }
}
