/**
 * The "main" package contains the entry point for the application and other related classes.
 * It provides the core functionality for running the program and initializing necessary components.
 */

package main;

import java.awt.*;

import gameengine.application.view.*;
import gameengine.characters.controller.CharacterController;
import gameengine.characters.model.Character;
import gameengine.characters.model.Collisions;
import gameengine.characters.view.EntityView;
import gameengine.gameloop.model.GameLoop;
import gameengine.map.model.Map;
import gameengine.map.model.MapArray;
import gameengine.map.model.MapType;
import gameengine.map.model.Tile;
import gameengine.map.view.MapPanel;
import gameengine.map.view.Tileset;
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

    private Character mainCharacter;		//the main character object
    private EntityView entityView;		//the view that need to be displayed on the window
    private Image mainCharacterImage, backgroundImage;

    private CharacterController mainCharacterController;

    private int updatePerSecond = 30;

    /**
     * a constructor.
     */
    public PixelAdventure() {
        panelMediator = new PanelMediator();
        gamePanel = new GamePanel(panelMediator);
        menuPanel = new MenuPanel(panelMediator);

        mainCharacterImage = Utils.getImage("character/mainCharacter.png");
        String tileSetPath = "src/gameassets/map/tileset/testTileset.png"; // Change String to Image or BufferedImage
        String backgroudImagePath = "src/gameassets/map/images/testBackground.png"; // Change String to Image or BufferedImage

        mainCharacter = Character.createInstance();						//we want to display the main character so we create it
        entityView = new EntityView(mainCharacter, mainCharacterImage);		//we now specify that we want to create a view of this character
        mainCharacterController = new CharacterController(mainCharacter);

        Tile emptyTile = new Tile(0, "empty", false);
        Tile surfaceTile = new Tile(1, "grass", true);
        Tile undergroundTile = new Tile(2, "dirt", true);
        MapType testMapType = new MapType("testType", emptyTile, surfaceTile, undergroundTile);
        Map testMap = new Map("testMap", testMapType, 300, 100, 15.0, 0.1);
        Tile[][] array = testMap.getMapArray();
        MapArray.printMapForTest(array, testMap.getMapWidth(), testMap.getMapHeight());
        Tileset set = new Tileset(tileSetPath);
        System.out.println(surfaceTile.getTileName());

        MapPanel mapPanel = new MapPanel(testMap, set, backgroudImagePath);

        ApplicationWindow.createInstance(menuPanel);
        gamePanel.addlayeredPanel(entityView, JLayeredPane.PALETTE_LAYER);
        gamePanel.addlayeredPanel(mapPanel, JLayeredPane.DEFAULT_LAYER);

        entityView.setBounds(0,0, CHARACTER_LENGHT, CHARACTER_LENGHT); //
        mapPanel.setBounds(0,0, ApplicationWindow.getFrame().getWidth(), ApplicationWindow.getFrame().getHeight());
        //mapPanel.repaint();

        ApplicationWindow.getFrame().addKeyListener(mainCharacterController);
        
        //setting up the map into the Collisions class as attribute
        Collisions.setMap(testMap);
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
