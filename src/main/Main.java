/**
 * The "main" package contains the entry point for the application and other related classes.
 * It provides the core functionality for running the program and initializing necessary components.
 */

package main;

import java.awt.*;

import gameengine.application.controller.CameraController;
import gameengine.application.view.*;
import gameengine.characters.controller.CharacterController;
import gameengine.characters.model.Character;
import gameengine.characters.view.EntityView;
import gameengine.gameloop.model.GameLoop;
import gameengine.inventory.controller.InventoryKeyController;
import gameengine.inventory.model.Inventory;
import gameengine.inventory.view.InventoryBar;
import gameengine.inventory.view.InventoryMenu;
import gameengine.inventory.view.ItemsView;
import gameengine.map.model.Map;
/*import gameengine.map.model.MapArray;*/
import gameengine.map.model.MapArray;
import gameengine.map.model.MapType;
import gameengine.map.model.Tile;
import gameengine.map.view.MapPanel;
import gameengine.map.view.Tileset;
import gameengine.utils.model.Constants;
import gameengine.utils.model.Utils;

import javax.swing.*;

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
    private MapPanel mapPanel;
    private CameraController cameraController;
    private int updatePerSecond = 30;

    /**
     * a constructor.
     */
    public PixelAdventure() {
        // Init the firsts elements to Application
        panelMediator = new PanelMediator();
        menuPanel = new MenuPanel(panelMediator);
        ApplicationWindow.createInstance(menuPanel);

        // -------------------- Map model --------------------
        MapPanel mapPanel = initMapPanel();
        // For testing map in terminal view
        /*Tile[][] array = testMap.getMapArray();*/
        /* MapArray.printMapForTest(array, testMap.getMapWidth(), testMap.getMapHeight()); */


        // -------------------- Main Character model --------------------
        mainCharacter = Character.createInstance();						//we want to display the main character so we create it
        mainCharacter.setSpawn(mapPanel.getLevel());
        // -------------------- Main Character view --------------------
        mainCharacterImage = Utils.getImage("character/mainCharacter.png");
        entityView = new EntityView(mainCharacter, mainCharacterImage);		//we now specify that we want to create a view of this character

        // -------------------- Main Character controller --------------------
        mainCharacterController = new CharacterController(mainCharacter);


        // -------------------- Inventory model --------------------
        Inventory inventoryModel = new Inventory(40);
        InventoryBar inventoryBar = new InventoryBar(inventoryModel);
        InventoryMenu iventoryMenu = new InventoryMenu(inventoryModel);

        // -------------------- Inventory view --------------------
        ItemsView texturePack = new ItemsView();

        inventoryBar.displayInventory(texturePack);
        iventoryMenu.displayInventory(texturePack);

        // -------------------- Inventory controller --------------------
        InventoryKeyController inventoryController = new InventoryKeyController(iventoryMenu);


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
        iventoryMenu.setBounds(Math.round((widthInventoryMenu) / 2),
                0,
                 widthInventoryMenu,
                heightInventoryMenu
        );
        
        GameLayerPanel gameLayerPanel = new GameLayerPanel(panelMediator,entityView, mapPanel);
        gamePanel = new GamePanel(panelMediator, gameLayerPanel);
        gamePanel.addlayeredPanel(inventoryBar, JLayeredPane.POPUP_LAYER);
        gamePanel.addlayeredPanel(iventoryMenu, JLayeredPane.DRAG_LAYER);

        ApplicationWindow.createInstance(menuPanel);

        cameraController = new CameraController(gamePanel.getCamera(), gameLayerPanel);

        // Add controller to frame
        ApplicationWindow.getFrame().addKeyListener(inventoryController);
        ApplicationWindow.getFrame().addKeyListener(mainCharacterController);
        ApplicationWindow.getFrame().addKeyListener(cameraController);
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
        Tile emptyTile = new Tile(0, "empty", false);
        Tile surfaceTile = new Tile(1, "grass", true);
        Tile undergroundTile = new Tile(2, "dirt", true);
        MapType testMapType = new MapType("testType", emptyTile, surfaceTile, undergroundTile);
        Map testMap = new Map("testMap", testMapType, Constants.MAP_COLUMNS, Constants.MAP_ROWS, 15.0, 0.1);
        Tile[][] array = testMap.getMapArray();
        MapArray.printMapForTest(array, testMap.getMapWidth(), testMap.getMapHeight());
        Tileset set = new Tileset(tileSetPath);
        System.out.println(surfaceTile.getTileName());
        return new MapPanel(testMap, set);
    }
}
