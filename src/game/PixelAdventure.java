package src.game;

import java.awt.Image;

import src.gameengine.application.ApplicationWindow;
import src.gameengine.application.GamePanel;
import src.gameengine.application.MenuPanel;
import src.gameengine.application.PanelMediator;

/**
 * The PixelAdventure class is the starting point of the application, it permit
 * to create a window and launch the game.
 * 
 * @author Thomas Gruner
 *
 */
public class PixelAdventure {

    private GamePanel gamePanel; // the view that need to be displayed on the window
    private MenuPanel menuPanel;
    private PanelMediator panelMediator;

    /**
     * a constructor.
     */
    public PixelAdventure() {
        panelMediator = new PanelMediator();
        gamePanel = new GamePanel(panelMediator);
        menuPanel = new MenuPanel(panelMediator);
        ApplicationWindow.createInstance(menuPanel); // and we add it to the window
    }

}