package main;

import java.awt.*;

import gameengine.application.controller.CameraController;
import gameengine.application.view.*;
import gameengine.characters.controller.CharacterController;
import gameengine.characters.model.Character;
import gameengine.characters.view.EntityView;
import gameengine.gameloop.model.GameLoop;
import gameengine.map.model.Map;
import gameengine.map.model.MapArray;
import gameengine.map.model.MapType;
import gameengine.map.model.Tile;
import gameengine.map.view.MapPanel;
import gameengine.map.view.Tileset;
import gameengine.utils.model.Constants;
import gameengine.utils.model.Utils;

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
        panelMediator = new PanelMediator();
        menuPanel = new MenuPanel(panelMediator);

        mainCharacterImage = Utils.getImage("character/mainCharacter.png");

        mainCharacter = Character.createInstance();						//we want to display the main character so we create it
        entityView = new EntityView(mainCharacter, mainCharacterImage);		//we now specify that we want to create a view of this character
        mainCharacterController = new CharacterController(mainCharacter);
        mapPanel = initMapPanel();
        GameLayerPanel gameLayerPanel = new GameLayerPanel(panelMediator,entityView, mapPanel);
        gamePanel = new GamePanel(panelMediator, gameLayerPanel);
        cameraController = new CameraController(gamePanel.getCamera(),mainCharacterController, gameLayerPanel);
        ApplicationWindow.createInstance(menuPanel);
//        ApplicationWindow.getFrame().addKeyListener(mainCharacterController);
        ApplicationWindow.getFrame().addKeyListener(cameraController);
    }

    @Override
    protected void processGameLoop() {
        logger.info("Gameloop starts");
        while (isGameRunning()) {
            processInput(updatePerSecond);
            update(); // Model Update
            render(); // View update
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException ex) {
//            }
        }
    }

    @Override
    protected void render() {
        entityView.updateLocation();
        gamePanel.render();
    }

    protected void update() {
//        mainCharacterController.update();
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
        return new MapPanel(testMap, set, backgroundImagePath);
    }
}
