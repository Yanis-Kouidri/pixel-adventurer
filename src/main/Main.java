/**
 * The "main" package contains the entry point for the application and other related classes.
 * It provides the core functionality for running the program and initializing necessary components.
 */

package main;

import java.awt.*;

import gameengine.application.controller.CameraController;
import gameengine.application.view.*;
import gameengine.characters.controller.CharacterController;
import gameengine.characters.model.MainCharacter;
import gameengine.characters.model.Collisions;
import gameengine.characters.view.EntityView;
import gameengine.gameloop.model.GameLoop;
import gameengine.inventory.controller.InventoryKeyController;
import gameengine.inventory.model.Inventory;
import gameengine.inventory.view.InventoryBar;
import gameengine.inventory.view.InventoryMenu;
import gameengine.inventory.view.ItemsView;
import gameengine.map.controller.BlockBreaker;
import gameengine.map.model.Map;
import gameengine.map.model.MapArray;
import gameengine.map.model.MapType;
import gameengine.map.model.Tile;
import gameengine.map.view.MapPanel;
import gameengine.map.view.Tileset;
import gameengine.utils.model.Constants;
import gameengine.utils.model.Utils;

import javax.swing.*;

/**
 * The "main" package contains the entry point for the application and other related classes.
 * It provides the core functionality for running the program and initializing necessary components.
 * @author  Cédric Abdelbaki
 * 				- Modified : MainMenuPanel panel creation
 * @author Yanis Kouidri
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                PixelAdventure game = new PixelAdventure();
                game.run();
            }
        });

    }

}

class PixelAdventure extends GameLoop {

	// The main menu
    private MainMenuPanel mainMenuPanel;

    // The command menu
    private CommandsPanel commandsPanel;

    // The credits panel
    private CreditsPanel creditsPanel;

    private static final float MAIN_CHARACTER_WIDTH = 2.0f;		//the width of the main character
	private static final float MAIN_CHARACTER_HEIGHT = 2.0f;	//the height of the main character

    private final PanelMediator panelMediator;

    private Tile emptyTile;

    private final GamePanel gamePanel;

    /**
     * the main character object
     */
    private final MainCharacter mainCharacter;

    /**
     * the view that needs to be displayed on the window
     */
    private final EntityView entityView;
    private final Image mainCharacterImage;

    private final CharacterController mainCharacterController;

    private final CameraController cameraController;

    private Map testMap;

    private final static int updatePerSecond = 30;

    private Tileset set;

    /**
     * String uses to name the surface block
     */
    private static final String SURFACE_BLOCK = "gravel";

    /**
     * String uses to name the coat block (all block under the surface)
     */
    private static final String COAT_BLOCK = "rock";

    /**
     * a constructor.
     */
    public PixelAdventure() {
        // Init the firsts elements to Application
        panelMediator = new PanelMediator();
        mainMenuPanel = new MainMenuPanel(panelMediator);
        panelMediator.setMainMenuPanel(mainMenuPanel);
        commandsPanel = new CommandsPanel(panelMediator);
        panelMediator.setCommandsPanel(commandsPanel);
        creditsPanel = new CreditsPanel(panelMediator);
        panelMediator.setCreditsPanel(creditsPanel);
        ApplicationWindow.createInstance(mainMenuPanel);

        // -------------------- Map model --------------------
        MapPanel mapPanel = initMapPanel();
        // For testing map in terminal view
        /*Tile[][] array = testMap.getMapArray();*/
        /* MapArray.printMapForTest(array, testMap.getMapWidth(), testMap.getMapHeight()); */

        // -------------------- Main Character model --------------------
        mainCharacter = MainCharacter.createInstance(MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);		//we want
        // to display the main character,
        // so we create it
        mainCharacter.setSpawn(mapPanel.getLevel());
        // -------------------- Main Character view --------------------
        mainCharacterImage = Utils.getImage("character/mainCharacter.png");
        entityView = new EntityView(mainCharacter, mainCharacterImage);		//we now specify that we want to create a view of this character

        // -------------------- Main Character controller --------------------
        mainCharacterController = new CharacterController(mainCharacter);

        ItemsView texturePack = new ItemsView();

        // Get surface and coat block texture
        Image groundImage = set.getTileSprite(1);
        Image coatImage = set.getTileSprite(2);

        // Add them to the texture pack
        texturePack.addItem(SURFACE_BLOCK, new ImageIcon(groundImage));
        texturePack.addItem(COAT_BLOCK, new ImageIcon(coatImage));

        // -------------------- Inventory model --------------------
        Inventory inventoryModel = new Inventory(40);
        InventoryBar inventoryBar = new InventoryBar(inventoryModel, texturePack);
        InventoryMenu inventoryMenu = new InventoryMenu(inventoryModel, texturePack);

        // -------------------- Inventory view --------------------

        inventoryBar.displayInventory();
        inventoryMenu.displayInventory();

        inventoryModel.addObserver(inventoryBar);
        inventoryModel.addObserver(inventoryMenu);


        // -------------------- Inventory controller --------------------
        InventoryKeyController inventoryController = new InventoryKeyController(inventoryMenu);


        // Set element in space of panel
        // x, y coordinates useless because we based to coordinate character in the model
        entityView.setBounds(0,0, Constants.CHARACTER_LENGHT, Constants.CHARACTER_LENGHT);
        mapPanel.setBounds(0,0, ApplicationWindow.getFrame().getWidth(),
                ApplicationWindow.getFrame().getHeight());

        int cellInventory = 50;


        int widthInventoryBar = cellInventory * InventoryBar.NB_OF_ITEMS_DISPLAY_IN_BAR;
        inventoryBar.setBounds(
                ApplicationWindow.getFrame().getWidth() - widthInventoryBar,
                ApplicationWindow.getFrame().getHeight() - 75, // Review the origin of screen and modify
                widthInventoryBar,
                cellInventory
        );

        int widthInventoryMenu = ApplicationWindow.getFrame().getWidth() -
                (InventoryMenu.NB_OF_COLS * cellInventory);
        int heightInventoryMenu = InventoryMenu.NB_OF_ROWS * cellInventory;
        inventoryMenu.setBounds(Math.round((float) (widthInventoryMenu) / 2),
                0,
                 widthInventoryMenu,
                heightInventoryMenu
        );
        
        GameLayerPanel gameLayerPanel = new GameLayerPanel(panelMediator,entityView, mapPanel);
        gamePanel = new GamePanel(panelMediator, gameLayerPanel);
        gamePanel.addlayeredPanel(inventoryBar, JLayeredPane.POPUP_LAYER);
        gamePanel.addlayeredPanel(inventoryMenu, JLayeredPane.DRAG_LAYER);

        cameraController = new CameraController(gamePanel.getCamera());

        // Add controller to frame
        ApplicationWindow.getFrame().addKeyListener(inventoryController);
        ApplicationWindow.getFrame().addKeyListener(mainCharacterController);

        //setting up the map into the Collisions class as attribute
        Collisions.setMap(testMap);

        ApplicationWindow.getFrame().addKeyListener(cameraController);


        BlockBreaker blockBreaker = new BlockBreaker(testMap, gamePanel.getCamera(), inventoryModel, emptyTile);
        ApplicationWindow.getFrame().addMouseListener(blockBreaker);
    }

    @Override
    protected void processGameLoop() {
        logger.info("Game loop starts");
        while (isGameRunning()) {
            processInput(updatePerSecond);
            update(); // Model Update
            render(); // View update
        }
    }

    @Override
    protected void render() {
        entityView.updateLocation();
        gamePanel.render();
    }

    protected void update() {
        mainCharacterController.update();
        cameraController.update();
    }


    private MapPanel initMapPanel(){
        String tileSetPath = "src/gameassets/map/tileset/testTileset.png"; // Change String to Image or BufferedImage

        emptyTile = new Tile(0, "empty", false);
        Tile surfaceTile = new Tile(1, SURFACE_BLOCK, true);
        Tile undergroundTile = new Tile(2, COAT_BLOCK, true);

        MapType testMapType = new MapType("testType", emptyTile, surfaceTile, undergroundTile);
        testMap = new Map("testMap", testMapType, Constants.MAP_COLUMNS, Constants.MAP_ROWS, 15.0, 0.1);

        Tile[][] array = testMap.getMapArray();
        MapArray.printMapForTest(array, testMap.getMapWidth(), testMap.getMapHeight());
        set = new Tileset(tileSetPath);
        System.out.println(surfaceTile.getTileName());

        return new MapPanel(testMap, set);
    }
}
