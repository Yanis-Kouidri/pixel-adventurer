package gameengine.inventory.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class based on Junit 5.8 to test Inventory class
 * @author Yanis Kouidri
 * @version 0.1
 */
class InventoryTest {


    /**
     * Adding a random number generator to randomise tests and cover more cases
     */
    private final Random randomGenerator = new Random();

    private Inventory testInventory;
    /**
     * If this value is too low, some tests could fail
     */
    private final int NUMBER_OF_PLACE = randomGenerator.nextInt(50, 100);

    private Item item1, item2, item3;
    private final JLabel dummySprite = new JLabel();

    @BeforeEach
    void setUp() {
        testInventory = new Inventory(NUMBER_OF_PLACE);
        String item1name = "Sword";
        item1 = new Item(item1name, dummySprite);
        String item2name = "Bow";
        item2 = new Item(item2name, dummySprite);
        String item3name = "Potion";
        item3 = new Item(item3name, dummySprite);
    }

    @AfterEach
    void tearDown() {
        testInventory = null; // Reset the reference to the object

        // Optionally, you can also reset the other variables used in the setup
        item1 = null;
        item2 = null;
        item3 = null;


    }

    @Test
    void constructorNullNbOfItem() {
        assertThrows(IllegalArgumentException.class, () -> new Inventory(0));
    }

    @Test
    void constructorNegativeNbOfItem() {
        assertThrows(IllegalArgumentException.class, () -> new Inventory(randomGenerator.nextInt(-100, 0)));
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

    @Test
    void getNumberOfItemsNotEmpty() {
        int nbOfItemAdd = 0; //The number of items add into the inventory
        try {
            testInventory.add(nbOfItemAdd++, item1);
            testInventory.add(nbOfItemAdd++, item2);
            testInventory.add(nbOfItemAdd++, item3);
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(nbOfItemAdd, testInventory.getNumberOfItems(),
                "The inventory should have" + nbOfItemAdd + "item");
    }

    // In an empty inventory
    @Test
    void isFullEmpty() {
        Assertions.assertFalse(testInventory.isFull());
    }

    @Test
    void isFullNotEmpty() {
        try {
            for (int i = 0 ; i < NUMBER_OF_PLACE - 1 ; i++)
                testInventory.add(testInventory.getFirstEmptyPlace(), item1);
        } catch (NotEmptyPlaceException | InventoryFullException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertFalse(testInventory.isFull());
    }

    @Test
    void isFulYes() {
        try {
            for (int i = 0 ; i < NUMBER_OF_PLACE; i++) { // Set the inventory full
                testInventory.add(testInventory.getFirstEmptyPlace(), item1);
            }
        } catch (NotEmptyPlaceException | InventoryFullException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(testInventory.isFull());
    }

    // In an empty inventory
    @Test
    void isEmptyYes() {
        Assertions.assertTrue(testInventory.isEmpty());
    }

    @Test
    void isEmptyNo() {
        try {
            testInventory.add(testInventory.getFirstEmptyPlace(), item1);
        } catch (NotEmptyPlaceException | InventoryFullException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertFalse(testInventory.isEmpty());
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
    void testGetFirstEmptyPlaceNotEmpty() {
        int nbOfItemAdd = 0; //The number of items added into the inventory
        final int NB_OF_ITEMS_TO_ADD = randomGenerator.nextInt(NUMBER_OF_PLACE) ;
        try {
            for (int i = 0 ; i < NB_OF_ITEMS_TO_ADD; i++) { // Set the inventory full
                testInventory.add(nbOfItemAdd++, item1);
            }
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }


        try {
            assertEquals(NB_OF_ITEMS_TO_ADD, testInventory.getFirstEmptyPlace());
        } catch (InventoryFullException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetFirstEmptyPlaceFull() {
        try {
            for (int i = 0 ; i < NUMBER_OF_PLACE; i++) { // Set the inventory full
                testInventory.add(i, item1);
            }
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }

        // Here, I except a InventoryFullException because my inventory is indeed full
        assertThrows(InventoryFullException.class, () -> testInventory.getFirstEmptyPlace());
    }

    @Test
    void AddSimple() {
        int placeToAdd = randomGenerator.nextInt(NUMBER_OF_PLACE);
        try {
            testInventory.add(placeToAdd, item1);
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(testInventory.getItemByIndex(placeToAdd),
                "This place should be not null and have the added item");
        assertEquals(item1.getName(), testInventory.getItemByIndex(placeToAdd).getName(),
                "The item1 should be at place " + placeToAdd);
        assertEquals(item1.getSprite().getIcon(),
                testInventory.getItemByIndex(placeToAdd).getSprite().getIcon(),
                "The item1 should be at place " + placeToAdd);

    }

    @Test
    void AddAtNotEmptyPlace() {
        int placeToAdd = randomGenerator.nextInt(NUMBER_OF_PLACE);
        try {
            testInventory.add(placeToAdd, item1);
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }

        // Here, I except a NotEmptyPlaceException because I have already added an Item at this place.
        assertThrows(NotEmptyPlaceException.class, () -> testInventory.add(placeToAdd, item1));

    }


    @Test
    void RemoveSimple() {
        // Adding an item :
        int placeToAddAndRemove = randomGenerator.nextInt(NUMBER_OF_PLACE);
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
    void RemoveEmpty() {
        assertThrows(NothingToRemoveException.class, () ->
                testInventory.remove(randomGenerator.nextInt(NUMBER_OF_PLACE)));
    }

    @Test
    void getItemByIndexEmpty() {
        assertNull(testInventory.getItemByIndex(randomGenerator.nextInt(NUMBER_OF_PLACE)));
    }

    @Test
    void getItemByIndexOutOfBound() {
        // I test the limit
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                testInventory.getItemByIndex(NUMBER_OF_PLACE));
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                testInventory.getItemByIndex(-1));
    }
}