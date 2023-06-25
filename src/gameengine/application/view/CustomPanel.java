package gameengine.application.view;

import javax.swing.*;

/** Creates a custom panel object link with the unique applicationWindow to all CustomPanel classes
 * and the PanelMediator if we need to switch panels.
 * @author Eric YU
 */
public abstract class CustomPanel extends JPanel {
    protected static ApplicationWindow aW; // The Application window
    protected PanelMediator pm; // The Panel Mediator to communicate with other panels

    /**
     * Constructs a CustomPanel with a panelMediator
     * @param pm
     */
    public CustomPanel(PanelMediator pm) {
        super();
        this.pm = pm;
    }

    /**
     * Adds the Application window as a static attribute.
     * Only used once for all classes.
     * @param aW the app window
     */
    public void addAppWindow(ApplicationWindow aW) {
        this.aW = aW;
    }
}
