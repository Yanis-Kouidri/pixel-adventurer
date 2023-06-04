package gameengine.application.view;

import gameengine.characters.view.EntityView;
import gameengine.map.model.Tile;
import gameengine.map.view.MapPanel;
import gameengine.utils.model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

import static gameengine.utils.model.Constants.CHARACTER_LENGHT;
import static gameengine.utils.model.Constants.SPRITE_DIM;

/**
 * The game view composed of the Map Panel, Entity Panel and HUD Panel
 */
public class GamePanel extends CustomPanel {

    private JLayeredPane layeredPane; // Layer Pane to superpose the 3 panels
    private EntityView entityView; // View of the character
    private MapPanel mapPanel; // View of the map

    private int cameraX; // The X pos of the camera
    private int cameraY; // The Y pos of the camera

    /**
     *
     * @param pm the panelmediator used to switch between custom panels
     * @param ev the user's character
     * @param mp the map
     */
    public GamePanel(PanelMediator pm, EntityView ev, MapPanel mp) {
        super(pm);
        pm.setGamePanel(this);

        setLayout(new BorderLayout());
        entityView = ev;
        mapPanel = mp;

        // Create a JLayeredPane as the main container
        layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        layeredPane.add(entityView, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(mapPanel, JLayeredPane.DEFAULT_LAYER);

        // Camera instantiation
        this.cameraX=0;
        this.cameraY=0;

        // Placing the different view components
        entityView.setBounds(1000,50, CHARACTER_LENGHT, CHARACTER_LENGHT);
        mapPanel.setBounds(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);


    }
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Calculate the offset for drawing based on the camera position
        int offsetX = -cameraX;
        int offsetY = -cameraY;
        float playerX = entityView.getEntity().getCoordinates().getX();
        float playerY = entityView.getEntity().getCoordinates().getY();

        // Draw the game world based on the camera position
        // Draws the map tiles
        //for (int y = 0; y < Constants.MAP_ROWS; y ++) {
        //    for (int x = 0; x < Constants.MAP_COLUMNS; x ++) {
        //        int worldX = x*Constants.SPRITE_DIM;
        //        int worldY = y*Constants.SPRITE_DIM;

                //int screenX = worldX + cameraX;
                //int screenY = worldY + cameraY;

        //        Tile currentTile = mapPanel.getLevel().getTileAtPos(y, x);
        //        int currentTileIdentifier = currentTile.getTileId();
        //        Image currentSprite = mapPanel.getSprites().getTileSprite(currentTileIdentifier);
        //        g.drawImage(currentSprite, worldX, worldY, null);
                //g.drawImage(currentSprite, screenX, screenY, null);
        //    }
        //}
        // You can adjust the drawing coordinates by adding the offset

        // Draw the player
    }

}
