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
    }
}
