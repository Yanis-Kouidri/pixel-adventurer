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
 * @contributor Cédric Abdelbaki
 * 				- Modified : MainMenuPanel panel creation
 */
public class Main {
    public static void main(String[] args) {
        PixelAdventure game = new PixelAdventure();
        game.run();
    }
}

class PixelAdventure extends GameLoop {

	// The main menu
    private MainMenuPanel mainMenuPanel;

    // The commands menu
    private CommandsPanel commandsPanel;

    // The credits panel
    private CreditsPanel creditsPanel;

  private static final float MAIN_CHARACTER_WIDTH = 2.0f;		//the width of the main character
	private static final float MAIN_CHARACTER_HEIGHT = 2.0f;	//the height of the main character

    private PanelMediator panelMediator;

    private Tile emptyTile;

    private GamePanel gamePanel;

    private MainCharacter mainCharacter;		//the main character object
    private EntityView entityView;		//the view that need to be displayed on the window
    private Image mainCharacterImage, backgroundImage;

    private CharacterController mainCharacterController;
    private MapPanel mapPanel;
    private CameraController cameraController;

    private Map testMap;

    private int updatePerSecond = 30;

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

        mainCharacter = MainCharacter.createInstance(MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);		//we want to display the main character so we create it
        // -------------------- Main Character model --------------------
        mainCharacter.setSpawn(mapPanel.getLevel());
        // -------------------- Main Character view --------------------
        mainCharacterImage = Utils.getImage("character/mainCharacter.png");
        entityView = new EntityView(mainCharacter, mainCharacterImage);		//we now specify that we want to create a view of this character

        // -------------------- Main Character controller --------------------
        mainCharacterController = new CharacterController(mainCharacter);

        // -------------------- Inventory model --------------------
        Inventory inventoryModel = new Inventory(40);
        InventoryBar inventoryBar = new InventoryBar();
        InventoryMenu inventoryMenu = new InventoryMenu();

        // -------------------- Inventory view --------------------
        ItemsView texturePack = new ItemsView();

        inventoryBar.displayInventory(inventoryModel, texturePack);
        inventoryMenu.displayInventory(inventoryModel, texturePack);

        // -------------------- Inventory controller --------------------
        InventoryKeyController inventoryController = new InventoryKeyController(inventoryMenu);


        // Set element in space of panel
        // x, y coordinates useless because we based to coordinate character in the model
        entityView.setBounds(0,0, Constants.CHARACTER_LENGHT, Constants.CHARACTER_LENGHT);
        mapPanel.setBounds(0,0, ApplicationWindow.getFrame().getWidth(),
                ApplicationWindow.getFrame().getHeight());


        int cellInveyory = 50;


        int widthInventoryBar = cellInveyory * InventoryBar.NB_OF_ITEMS_DISPLAY_IN_BAR;
        int heightInventoryBar = cellInveyory;
        inventoryBar.setBounds(
                ApplicationWindow.getFrame().getWidth() - widthInventoryBar,
                ApplicationWindow.getFrame().getHeight() - 75, // Review the origin of screen and modify
                widthInventoryBar,
                heightInventoryBar
        );

        int widthInventoryMenu = ApplicationWindow.getFrame().getWidth() -
                (InventoryMenu.NB_OF_COLS * cellInveyory);
        int heightInventoryMenu = InventoryMenu.NB_OF_ROWS * cellInveyory;
        inventoryMenu.setBounds(Math.round((float) (widthInventoryMenu) / 2),
                0,
                 widthInventoryMenu,
                heightInventoryMenu
        );
        
        GameLayerPanel gameLayerPanel = new GameLayerPanel(panelMediator,entityView, mapPanel);
        gamePanel = new GamePanel(panelMediator, gameLayerPanel);
        gamePanel.addlayeredPanel(inventoryBar, JLayeredPane.POPUP_LAYER);
        gamePanel.addlayeredPanel(inventoryMenu, JLayeredPane.DRAG_LAYER);

        cameraController = new CameraController(gamePanel.getCamera(), gameLayerPanel);

        // Add controller to framee
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
        gamePanel.render();
    }

    protected void update() {
        mainCharacterController.update();
        cameraController.update();
    }

    private MapPanel initMapPanel(){
        String tileSetPath = "src/gameassets/map/tileset/testTileset.png"; // Change String to Image or BufferedImage
        String backgroundImagePath = "src/gameassets/map/images/testBackground.png"; // Change String to Image or BufferedImage
        emptyTile = new Tile(0, "empty", false);
        Tile surfaceTile = new Tile(1, "grass", true);
        Tile undergroundTile = new Tile(2, "dirt", true);
        MapType testMapType = new MapType("testType", emptyTile, surfaceTile, undergroundTile);
        testMap = new Map("testMap", testMapType, Constants.MAP_COLUMNS, Constants.MAP_ROWS, 15.0, 0.1);
        Tile[][] array = testMap.getMapArray();
        MapArray.printMapForTest(array, testMap.getMapWidth(), testMap.getMapHeight());
        Tileset set = new Tileset(tileSetPath);
        System.out.println(surfaceTile.getTileName());
        return new MapPanel(testMap, set);
    }
}
