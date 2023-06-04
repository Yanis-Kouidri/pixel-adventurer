/**
 * The "main" package contains the entry point for the application and other related classes.
 * It provides the core functionality for running the program and initializing necessary components.
 */

package main;

import java.awt.*;

import gameengine.application.view.*;
import gameengine.characters.controller.CharacterController;
import gameengine.characters.model.Character;
import gameengine.characters.view.EntityView;
import gameengine.gameloop.model.GameLoop;
import gameengine.inventory.controller.InventoryKeyController;
import gameengine.inventory.model.Inventory;
import gameengine.inventory.view.InventoryBar;
import gameengine.inventory.view.InventoryMenu;
import gameengine.map.model.Map;
/*import gameengine.map.model.MapArray;*/
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
        // Init the firsts elements to Application
        panelMediator = new PanelMediator();
        gamePanel = new GamePanel(panelMediator);
        menuPanel = new MenuPanel(panelMediator);
        ApplicationWindow.createInstance(menuPanel);
        

        // -------------------- Main Character model --------------------
        mainCharacter = Character.createInstance();						//we want to display the main character so we create it

        // -------------------- Main Character view --------------------
        mainCharacterImage = Utils.getImage("character/mainCharacter.png");
        entityView = new EntityView(mainCharacter, mainCharacterImage);		//we now specify that we want to create a view of this character

        // -------------------- Main Character controller --------------------
        mainCharacterController = new CharacterController(mainCharacter);


        // -------------------- Map model --------------------
        Tile emptyTile = new Tile(0, "empty", false);
        Tile surfaceTile = new Tile(1, "grass", true);
        Tile undergroundTile = new Tile(2, "dirt", true);

        MapType testMapType = new MapType("testType", emptyTile, surfaceTile, undergroundTile);
        Map testMap = new Map("testMap", testMapType, 300, 100, 15.0, 0.1);



        // -------------------- Map view --------------------
        String tileSetPath = "src/gameassets/map/tileset/testTileset.png"; // Change String to Image or BufferedImage
        String backgroudImagePath = "src/gameassets/map/images/testBackground.png"; // Change String to Image or BufferedImage
        Tileset set = new Tileset(tileSetPath);
        MapPanel mapPanel = new MapPanel(testMap, set, backgroudImagePath);
        // For testing map in terminal view
        /*Tile[][] array = testMap.getMapArray();*/
        /* MapArray.printMapForTest(array, testMap.getMapWidth(), testMap.getMapHeight()); */


        // -------------------- Inventory model --------------------
        Inventory inventoryModel = new Inventory(40);
        InventoryBar inventoryBar = new InventoryBar();
        InventoryMenu iventoryMenu = new InventoryMenu();

        // -------------------- Inventory view --------------------
        inventoryBar.displayInventory(inventoryModel);
        iventoryMenu.displayInventory(inventoryModel);

        // -------------------- Inventory controller --------------------
        InventoryKeyController inventoryController = new InventoryKeyController(iventoryMenu);

        // Add view element to game panel
        gamePanel.addlayeredPanel(entityView, JLayeredPane.PALETTE_LAYER);
        gamePanel.addlayeredPanel(mapPanel, JLayeredPane.DEFAULT_LAYER);
        gamePanel.addlayeredPanel(inventoryBar, JLayeredPane.POPUP_LAYER);
        gamePanel.addlayeredPanel(iventoryMenu, JLayeredPane.DRAG_LAYER);

        // Set element in space of panel
        // x, y coordinates useless because we based to coordinate character in the model
        entityView.setBounds(0,0, CHARACTER_LENGHT, CHARACTER_LENGHT);
        mapPanel.setBounds(0,0, ApplicationWindow.getFrame().getWidth(),
                ApplicationWindow.getFrame().getHeight());

        int widthInventoryBar = 400;
        int heightInventoryBar = 75;
        inventoryBar.setBounds(ApplicationWindow.getFrame().getWidth() - widthInventoryBar,
                ApplicationWindow.getFrame().getHeight() - heightInventoryBar,
                widthInventoryBar,
                heightInventoryBar
        );

        int widthInventoryMenu = 50;
        int heightInventoryMenu = 50;
        iventoryMenu.setBounds(widthInventoryMenu,
                heightInventoryMenu,
                ApplicationWindow.getFrame().getWidth() - widthInventoryMenu * 2,
                ApplicationWindow.getFrame().getHeight() - heightInventoryMenu * 2
        );

        // Add controller to frame
        ApplicationWindow.getFrame().addKeyListener(inventoryController);
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
