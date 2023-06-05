package gameengine.application.view;

import gameengine.application.controller.CameraController;
import gameengine.application.model.Camera;
import gameengine.characters.view.EntityView;
import gameengine.map.model.Tile;
import gameengine.map.view.MapPanel;
import gameengine.utils.model.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static gameengine.utils.model.Constants.CHARACTER_LENGHT;
import static gameengine.utils.model.Constants.SPRITE_DIM;

public class GameLayerPanel extends CustomPanel {
    private MapPanel mapPanel; // View of the map
    private EntityView entityView; // View of Character
    private Camera camera;

    public GameLayerPanel(PanelMediator pm, EntityView entityView, MapPanel mapPanel) {
        super(pm);
        this.entityView = entityView;
        this.camera = new Camera(mapPanel.getLevel());
        this.mapPanel = mapPanel;
        JLayeredPane layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        layeredPane.add(mapPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(entityView, JLayeredPane.PALETTE_LAYER);

        setBounds(0,0,Constants.MAP_LENGTH, Constants.MAP_HEIGHT);

        entityView.setBounds(0,0, CHARACTER_LENGHT, CHARACTER_LENGHT);
        mapPanel.setBounds(0,0,Constants.MAP_LENGTH, Constants.MAP_HEIGHT);

        mapPanel.setCamera(camera);
    }

    public MapPanel getMapPanel(){
        return mapPanel;
    }
    public Camera getCamera(){
        return camera;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("src/gameassets/map/images/testBackground.png")), 0, 0, getWidth(), getHeight(), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Recreating panels");
        mapPanel.paintComponent(g.create(mapPanel.getMapX()-camera.getX(), mapPanel.getMapY()-camera.getY(), mapPanel.getWidth(), mapPanel.getHeight()));

    }
}
