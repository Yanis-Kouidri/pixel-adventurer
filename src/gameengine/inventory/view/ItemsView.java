package gameengine.inventory.view;

import javax.swing.*;
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
    TreeMap<String, ImageIcon> itemIconAssos;

    // Constructor :

    /**
     * Creation of the TreeMap
     */
    public ItemsView() {
        itemIconAssos = new TreeMap<>();
    }

    /**
     * Add a new item in the tree map or change his sprite if the name already exist
     * @param itemName name of the item to bound to a sprite (or change)
     * @param sprite visual of the item
     */
    public void addItem(String itemName, ImageIcon sprite) {
        itemIconAssos.put(itemName, sprite);
    }

    public JLabel getSprite(String itemName) throws NoSpriteFoundException {
        ImageIcon itemIcon = itemIconAssos.get(itemName);
        if (itemIcon == null) {
            throw new NoSpriteFoundException("Item " + itemName
                    + " doesn't have a sprite");
        }

        return new JLabel(itemIcon);
    }
}