package gameengine.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GamePanel extends CustomPanel {
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

/*        // Create the game layer panel
        gameLayerPanel = new GameLayerPanel();
        layeredPane.add(gameLayerPanel, JLayeredPane.DEFAULT_LAYER); // Set lower layer index

        // Create the GUI panel
        hudPanel = new HUDPanel();
        layeredPane.add(hudPanel, JLayeredPane.DEFAULT_LAYER); // Set higher layer index*/
    }

    public void addlayeredPanel(JPanel comp, Integer constraints) {
        layeredPane.add(comp, constraints);
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    private class HUDPanel extends JPanel {
        // Implement the HUD panel appearance and behavior (inventory, buttons, etc.)
        // ...
    }

    private class GameLayerPanel extends JPanel {
        // Implement the Game panel appearance
        // ...
    }

}
