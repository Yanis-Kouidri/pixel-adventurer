package gameengine.application.view;

import gameengine.application.model.Camera;
import gameengine.characters.view.EntityView;
import gameengine.map.view.MapPanel;
import gameengine.utils.model.Constants;
import gameengine.utils.model.Coordinates;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static gameengine.utils.model.Constants.*;

/**
 * Displays both the map and the character on the same panel.
 * It calculates how the view should be displayed based on the camera's coordinates
 * and sets the correct offset.
 * @author Eric YU
 */
public class GameLayerPanel extends CustomPanel {
    private MapPanel mapPanel; // View of the map
    private EntityView entityView; // View of Character
    private Camera camera; // Camera of the view

    /**
     * Constructs the GamePanel and adds it to PanelMediator's list of custom panels.
     * It will then add the EntityView of the character and the MapPanel and layer them.
     * The MapPanel will be the background (with value JLayeredPane.DEFAULT_LAYER) and
     * the entityView will be the foreground (value: JLayeredPane.PALETTE_LAYER)
     * @param pm
     * @param entityView
     * @param mapPanel
     */
    public GameLayerPanel(PanelMediator pm, EntityView entityView, MapPanel mapPanel) {
        super(pm);

        setBounds(0,0,Constants.MAP_LENGTH, Constants.MAP_HEIGHT);

        // Adding everything as the attributes
        this.entityView = entityView;
        this.camera = new Camera(mapPanel.getLevel());
        this.mapPanel = mapPanel;

        // Layering the different panels
        JLayeredPane layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        layeredPane.add(mapPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(entityView, JLayeredPane.PALETTE_LAYER);

        entityView.setBounds(0,0, CHARACTER_LENGHT, CHARACTER_LENGHT);
        mapPanel.setBounds(0,0,Constants.MAP_LENGTH, Constants.MAP_HEIGHT);

        Coordinates spawnPoint = mapPanel.getLevel().getSpawnPoint();
        mapPanel.setCamera(camera); // Adding the camera to the MapPanel

        // Setting the character spawnPoint

    }

    /**
     * Returns the Map View.
     * @return the mapPanel
     */
    public MapPanel getMapPanel(){
        return mapPanel;
    }

    /**
     * Returns the camera object.
     * @return the camera of the view
     */
    public Camera getCamera(){
        return camera;
    }

    /**
     * Renders the panel. It first tries to load the background image then
     * renders the MapPanel using the camera's offset.
     * @param g the <code>Graphics</code> object to protect
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Loading the background image
        try {
            g.drawImage(ImageIO.read(new File("src/gameassets/map/images/testBackground.png")), 0, 0, getWidth(), getHeight(), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // First renders the map offsetted by the camera
        mapPanel.paintComponent(g.create(mapPanel.getMapX()-camera.getX(), mapPanel.getMapY()-camera.getY(), mapPanel.getWidth(), mapPanel.getHeight()));
        entityView.paintComponent(g.create(SCREEN_WIDTH/2 - CHARACTER_LENGHT,SCREEN_HEIGHT/2-CHARACTER_LENGHT, CHARACTER_LENGHT, CHARACTER_LENGHT));
    }
}
