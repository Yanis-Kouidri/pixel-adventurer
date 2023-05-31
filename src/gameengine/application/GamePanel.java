package gameengine.application;

import javax.swing.*;

import java.awt.*;

public class GamePanel extends CustomPanel {
    private static final long serialVersionUID = 1L;
	private GameLayerPanel gameLayerPanel;
    private HUDPanel hudPanel;
    private JLayeredPane layeredPane;

    public GamePanel(PanelMediator pm) {
        super(pm);
        pm.setGamePanel(this);
        setBackground(Color.BLUE);
        setLayout(new BorderLayout());

        // Create a JLayeredPane as the main container
        layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        // Create the game layer panel
        gameLayerPanel = new GameLayerPanel();
        layeredPane.add(gameLayerPanel, Integer.valueOf(0)); // Set lower layer index

        // Create the GUI panel
        hudPanel = new HUDPanel();
        layeredPane.add(hudPanel, Integer.valueOf(1)); // Set higher layer index
    }
    
    public void addGameLayerPanel(JPanel panel) {
    	// Il faut l'ajouter au GameLayerPanel et non au JLayeredPanel, c'est lui qui devra contenir tous nos JPanel
    	layeredPane.add(panel, Integer.valueOf(1));
    }

    private class HUDPanel extends JPanel {
        // Implement the HUD panel appearance and behavior (inventory, buttons, etc.)
        // ...
    }

}
