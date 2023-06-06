package gameengine.application.view;

import gameengine.application.model.Camera;
import gameengine.utils.model.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * The game view composed of the GameLayerPanel (both the map and the entities) and HUD Panel.
 * @author Eric YU
 */
public class GamePanel extends CustomPanel {

    private JLayeredPane layeredPane; // Layer Pane to superpose both panels
    private GameLayerPanel gameLayerPanel; // The GameLayer on which entities and map will interact

    /**
     * Constructs a new Game Panel and adds it to the PanelMediator's list.
     * Layers its different panels with the gameLayer in the background and HUD on top of it.
     * @param pm the panelMediator
     * @param glp the gameLayerPanel
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

        // Adds the different layers to the JLayeredPane.
        layeredPane.add(this.gameLayerPanel, JLayeredPane.DEFAULT_LAYER);

    }

    /**
     * Calls the repaint method to manually re-render the view.
     * It is used in the gameloop.
     */
    public void render(){
        repaint();
    }

    /**
     * Gets the camera object of the gameLayerPanel.
     * @return the camera bounded to the gameLayerPanel
     */
    public Camera getCamera(){
        return gameLayerPanel.getCamera();
    }

    /**
     * Rendering logic. It first paints the gameLayerPanel then the HUDPanel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameLayerPanel.paintComponent(g);
    }
}
