package gameengine.application.view;

import gameengine.application.model.Camera;
import gameengine.utils.model.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * The game view composed of the Map Panel, Entity Panel and HUD Panel
 */
public class GamePanel extends CustomPanel {

    private JLayeredPane layeredPane; // Layer Pane to superpose the 3 panels
    private GameLayerPanel gameLayerPanel;

    /**
     *
     * @param pm
     * @param glp
     */
    public GamePanel(PanelMediator pm, GameLayerPanel glp) {
        super(pm);
        setBounds(0,0, Constants.MAP_LENGTH, Constants.MAP_HEIGHT);
        pm.setGamePanel(this);

        setLayout(new BorderLayout());

        // Create a JLayeredPane as the main container
        layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);
        gameLayerPanel = glp;
        layeredPane.add(this.gameLayerPanel, JLayeredPane.DEFAULT_LAYER);

        // Placing the different view components


    }
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public void render(){
        repaint();
    }
    public Camera getCamera(){
        return gameLayerPanel.getCamera();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameLayerPanel.paintComponent(g);
        // Calculate the offset for drawing based on the camera position
//        int offsetX = -cameraX;
//        int offsetY = -cameraY;
//        float playerX = entityView.getEntity().getCoordinates().getX();
//        float playerY = entityView.getEntity().getCoordinates().getY();

        // Draw the game world based on the camera position
        // Draws the map tiles
//        for (int y = 0; y < Constants.MAP_ROWS; y++) {
//            for (int x = 0; x < Constants.MAP_COLUMNS; x++) {
//                int worldX = x * Constants.SPRITE_DIM;
//                int worldY = y * Constants.SPRITE_DIM;
//
//                int screenX = worldX + cameraX;
//                int screenY = worldY + cameraY;
//
//                Tile currentTile = mapPanel.getLevel().getTileAtPos(y, x);
//                int currentTileIdentifier = currentTile.getTileId();
//                Image currentSprite = mapPanel.getSprites().getTileSprite(currentTileIdentifier);
//                //g.drawImage(currentSprite, worldX, worldY, null);
//                g.drawImage(currentSprite, screenX, screenY, null);
//                //    }
//                //}
//                // You can adjust the drawing coordinates by adding the offset
//
//                // Draw the player
//            }
//        }
    }
}
