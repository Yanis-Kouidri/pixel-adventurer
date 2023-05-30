package gameengine.inventory.model;

import javax.swing.*;

public class Item {

    // Attributes :
    private String name;
    private JLabel sprite;

    public Item(String name, JLabel sprite) {
        this.name = name;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public JLabel getSprite() {
        return sprite;
    }
}
