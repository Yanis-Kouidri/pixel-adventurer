package gameengine.inventory.view;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class is created to respect the MVC by adding an external view for the item.
 * The model is well separate from the view.
 * @author Yanis Kouidri
 * @version 0.1
 */
public class ItemsView {

    // Attributes :

    /**
     * A tree map to associate each item name to his bounded icon
     */
    Map<String, ImageIcon> itemIconAssos;

    // Constructor :

    /**
     * Creation of the TreeMap
     */
    public ItemsView() {
        itemIconAssos = new TreeMap<>();
    }

    /**
     * Add a new item in the tree map or change his sprite if the name already exists
     * @param itemName name of the item to bound to a sprite (or change)
     * @param sprite visual of the item
     */
    public void addItem(String itemName, ImageIcon sprite) {
        itemIconAssos.put(itemName, sprite);
    }

    /**
     * Get a visual (sprite) by giving an item name.
     * @param itemName The name of the item that you want to get the sprite.
     * @return A JLabel with the sprite corresponding to your item name.
     * @throws NoSpriteFoundException Raised when the item name you give correspond to no sprite.
     */
    public JLabel getSprite(String itemName) throws NoSpriteFoundException {
        ImageIcon itemIcon = itemIconAssos.get(itemName);
        if (itemIcon == null) {
            throw new NoSpriteFoundException("Item " + itemName
                    + " doesn't have a sprite");
        }

        return new JLabel(itemIcon);
    }
}
