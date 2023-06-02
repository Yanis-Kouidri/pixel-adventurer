package main;

import java.awt.*;

import gameengine.application.*;
import gameengine.characters.controller.CharacterController;
import gameengine.characters.model.Character;
import gameengine.characters.model.Entity;
import gameengine.characters.view.EntityView;
import gameengine.gameloop.model.GameLoop;
import gameengine.utils.model.Utils;

import javax.swing.*;

import static gameengine.utils.model.Constants.CHARACTER_LENGHT;

public class Main {
    public static void main(String[] args) {
        PixelAdventure game = new PixelAdventure();
        game.run();
    }

}

class PixelAdventure extends GameLoop {

    private MenuPanel menuPanel;
    private PanelMediator panelMediator;

    private GamePanel gamePanel;

    private Entity mainCharacter;		//the main character object
    private EntityView entityView;		//the view that need to be displayed on the window
    private Image image;
    private CharacterController mainCharacterController;

    private int updatePerSecond = 30;

    /**
     * a constructor.
     */
    public PixelAdventure() {
        panelMediator = new PanelMediator();
        gamePanel = new GamePanel(panelMediator);
        menuPanel = new MenuPanel(panelMediator);

        image = Utils.getImage("character/mainCharacter.png");

        mainCharacter = Character.createInstance();						//we want to display the main character so we create it
        entityView = new EntityView(mainCharacter, image);		//we now specify that we want to create a view of this character
        mainCharacterController = new CharacterController(mainCharacter);

        ApplicationWindow.createInstance(menuPanel);
        gamePanel.addlayeredPanel(entityView, JLayeredPane.PALETTE_LAYER);

        entityView.setBounds(0,0, CHARACTER_LENGHT, CHARACTER_LENGHT); //

        ApplicationWindow.getFrame().addKeyListener(mainCharacterController);
    }

    @Override
    protected void processGameLoop() {
        logger.info("Gameloop starts");
        while (isGameRunning()) {
            processInput(updatePerSecond);
            update(); // Model Update
            render(); // View update
        }
    }

    @Override
    protected void render() {
        entityView.updateLocation();
    }

    protected void update() {
        mainCharacterController.update();
    }

}
