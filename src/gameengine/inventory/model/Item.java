package gameengine.inventory.model;

/**
 * This class defines the notion of item, an object that the player can hold with at least a name and a visual
 * @author Yanis Kouidri
 * @version 0.1
 */
public class Item {

    // Attributes :

    /**
     * The name of the item display in game
     */
    private final String name;

    /**
     * A short description of the item that the player can see by clicking on the item (facultative)
     */
    private final String description;

    // Constructors :
    /**
     * A constructor with an empty description
     * @param name The item name
     */
    public Item(String name) {
        this(name, "");
    }

    /**
     * A constructor with a description
     * @param name The item name
     * @param description The item description
     */
    public Item(String name, String description) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.name = name;
        this.description = description;
    }

    // Getters :

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
