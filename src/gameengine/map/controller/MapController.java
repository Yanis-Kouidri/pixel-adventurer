package gameengine.map.controller;

import gameengine.map.view.MapPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapController implements KeyListener {

    private MapPanel mapPanel;

    public MapController(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        this.mapPanel.addKeyListener(this);
//        this.mapPanel.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Check the key pressed and move the map accordingly
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                mapPanel.moveMap(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                mapPanel.moveMap(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                mapPanel.moveMap(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                mapPanel.moveMap(1, 0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }

}
