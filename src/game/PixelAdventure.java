package game;

import java.awt.*;
import java.awt.image.ComponentColorModel;

import gameengine.application.ApplicationWindow;
import gameengine.application.GamePanel;
import gameengine.application.MenuPanel;
import gameengine.application.PanelMediator;
import gameengine.characters.model.Entity;
import gameengine.characters.model.Character;
import gameengine.characters.view.EntityView;
import gameengine.utils.model.Utils;

import javax.swing.*;

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


    private Entity mainCharacter;		//the main character object
    private EntityView entityView;		//the view that need to be displayed on the window
    private Image image;

    /**
     * a constructor.
     */
    public PixelAdventure() {
        panelMediator = new PanelMediator();
        gamePanel = new GamePanel(panelMediator);
        menuPanel = new MenuPanel(panelMediator);

        image = Utils.getImage("mainCharacter.png");
        mainCharacter = Character.createInstance();						//we want to display the main character so we create it
        entityView = new EntityView(mainCharacter, 0, 0, image);		//we now specify that we want to create a view of this character
        entityView.setBounds(800,500,64,64); // TODO: Find an another method for that
        ApplicationWindow.createInstance(menuPanel); // and we add it to the window

        gamePanel.addlayeredPanel(entityView, JLayeredPane.PALETTE_LAYER);
    }

}
