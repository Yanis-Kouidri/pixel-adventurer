package gameengine.inventory.model;

import javax.swing.*;

public class Item {

    // Attributes :

    /**
     * The name of the item display in game
     */
    private final String name;

    /**
     * The visual of the item display in game
     */
    private final JLabel sprite;

    /**
     * A short description of the item that the player can see by clicking on the item (facultative)
     */
    private final String description;

    // Constructors :
    /**
     * A constructor with an empty description
     * @param name The item name
     * @param sprite The sprite of the item
     */
    public Item(String name, JLabel sprite) {
        this(name, sprite, "");
    }

    /**
     * A constructor with a description
     * @param name The item name
     * @param sprite The item sprite
     * @param description The item description
     */
    public Item(String name, JLabel sprite, String description) {
        this.name = name;
        this.sprite = sprite;
        this.description = description;
    }

    // Getters :

    public String getName() {
        return name;
    }

    public JLabel getSprite() {
        JLabel copySprite = new JLabel();
        Icon icon = sprite.getIcon();
        if (icon != null) {
            copySprite.setIcon(icon);
        }
        return copySprite;
    }

    public String getDescription() {
        return description;
    }
}
