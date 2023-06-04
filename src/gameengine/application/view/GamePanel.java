package gameengine.application.view;

import gameengine.characters.view.EntityView;
import gameengine.map.view.MapPanel;
import gameengine.utils.model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

import static gameengine.utils.model.Constants.CHARACTER_LENGHT;

public class GamePanel extends CustomPanel {

    private JLayeredPane layeredPane;
    private EntityView entityView;
    private MapPanel mapPanel;

    private int cameraX; // The X pos of the camera
    private int cameraY; // The Y pos of the camera

    public GamePanel(PanelMediator pm, EntityView ev, MapPanel mp) {
        super(pm);
        pm.setGamePanel(this);
        setBackground(Color.BLUE);
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

        // Draw the game world based on the camera position

        // You can adjust the drawing coordinates by adding the offset

        // Draw the player
    }

}
