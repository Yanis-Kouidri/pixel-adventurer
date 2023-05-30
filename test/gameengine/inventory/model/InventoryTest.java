package gameengine.inventory.model;

import gameengine.inventory.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory testInventory;
    private final String ITEM1NAME = "Sword";
    private final String ITEM2NAME = "Bow";
    private final String ITEM3NAME = "Potion";
    private final int NUMBER_OF_PLACE = 50;

    private Item item1, item2, item3;
    private final JLabel dummySprite = new JLabel();

    @BeforeEach
    void setUp() {
        testInventory = new Inventory(NUMBER_OF_PLACE);
        item1 = new Item(ITEM1NAME, dummySprite);
        item2 = new Item(ITEM2NAME, dummySprite);
        item3 = new Item(ITEM3NAME, dummySprite);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getNumberOfPlace() {
        Assertions.assertEquals(NUMBER_OF_PLACE, testInventory.getNumberOfPlace(),
                "The inventory as the wrong number of places");
    }

    @Test
    void getNumberOfItemsEmpty() {
        Assertions.assertEquals(0, testInventory.getNumberOfItems(),
                "The inventory should have 0 item");
    }

    // In an empty inventory
    @Test
    void isFull1() {
        Assertions.assertFalse(testInventory.isFull());
    }

    // In an empty inventory
    @Test
    void isEmpty1() {
        Assertions.assertTrue(testInventory.isEmpty());
    }


    @Test
    void testGetFirstEmptyPlaceEmpty() {
        try {
            assertEquals(0, testInventory.getFirstEmptyPlace());
        } catch (InventoryFullException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAddSimple() {
        int placeToAdd = 0;
        try {
            testInventory.add(placeToAdd, item1);
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(testInventory.getItemByIndex(placeToAdd),
                "This place should be not null and have the added item");
        assertEquals(item1.getName(), testInventory.getItemByIndex(placeToAdd).getName(),
                "The item1 should be at place " + placeToAdd);
        assertEquals(item1.getSprite(),
                testInventory.getItemByIndex(placeToAdd).getSprite(),
                "The item1 should be at place " + placeToAdd);

    }


    @Test
    void testRemoveSimple() {
        // Adding an item :
        int placeToAddAndRemove = 0;
        try {
            testInventory.add(placeToAddAndRemove, item1);
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(testInventory.getItemByIndex(placeToAddAndRemove),
                "This place should be not null and have the added item");

        // Removing item
        try {
            testInventory.remove(placeToAddAndRemove);
        } catch (NothingToRemoveException e) {
            throw new RuntimeException(e);
        }
        assertNull(testInventory.getItemByIndex(placeToAddAndRemove));
    }

    @Test
    void getItemByIndex() {
    }
}