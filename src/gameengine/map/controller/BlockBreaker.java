package gameengine.map.controller;

import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.InventoryFullException;
import gameengine.inventory.model.Item;
import gameengine.map.model.Map;
import gameengine.map.model.Tile;
import static gameengine.utils.model.Constants.BLOCK_LENGHT;
import gameengine.application.model.Camera;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * Determine the block to break, destroy it and add the block as an item to the inventory.
 * @author Jeremy Theret
 */
public class BlockBreaker extends MouseAdapter {

    private final Map map;

    private final Camera camera;

    private final Inventory inventory;

    private final Tile emptyTile;

    public BlockBreaker(Map map, Camera camera, Inventory inventory, Tile emptyTile) {
        this.map = map;
        this.camera = camera;
        this.inventory = inventory;
        this.emptyTile = emptyTile;
    }

    /**
     * Invoked when the mouse button has been clicked on the component
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        // For take the pick of mouse pointer
        int mouseOffsetY = 16;

        // Click coordinates
        int clickX = e.getX();
        int clickY = e.getY();

        // adjusted click coordinates with the block lenght
        int adjustedClickX = clickX / BLOCK_LENGHT;
        int adjustedClickY = (clickY - mouseOffsetY) / BLOCK_LENGHT;

        // adjusted camera coordinates with the block lenght
        int adjustedCameraX = camera.getX() / BLOCK_LENGHT;
        int adjustedCameraY = camera.getY() / BLOCK_LENGHT;

        // Block coordinates
        int blockX = adjustedClickX + adjustedCameraX;
        int blockY = adjustedClickY + adjustedCameraY;

        // Verified if blockX and blockY present in the map
        if (blockX >= 0 && blockX < map.getMapWidth() && blockY >= 0 && blockY < map.getMapHeight()) {

            // Verify if the block isn't empty block
            if (!Objects.equals(map.getTileAtPos(blockX, blockY).getTileName(), emptyTile.getTileName())) {
                // Add block in inventory
                Item item = new Item(map.getTileAtPos(blockX, blockY).getTileName());
                try {
                    inventory.add(item);
                } catch (InventoryFullException ex) {
                    System.out.println("Inventory is full.");
                }

                // Set block to empty block
                map.setTileAtPost(emptyTile, blockX, blockY);
            }
        }
    }
}

