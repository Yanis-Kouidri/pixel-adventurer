package gameengine.inventory.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;

import java.util.Random;


/**
 * Test class based on Junit 4 to test Inventory class
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryTest {


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

    @Before
    public void setUp() {
        testInventory = new Inventory(NUMBER_OF_PLACE);
        String item1name = "Sword";
        item1 = new Item(item1name, dummySprite);
        String item2name = "Bow";
        item2 = new Item(item2name, dummySprite);
        String item3name = "Potion";
        item3 = new Item(item3name, dummySprite);
    }

    @After
    public void tearDown() {
        testInventory = null; // Reset the reference to the object

        // Optionally, you can also reset the other variables used in the setup
        item1 = null;
        item2 = null;
        item3 = null;


    }

    @Test
    public void constructorNullNbOfItem() {
        assertThrows(IllegalArgumentException.class, () -> new Inventory(0));
    }

    @Test
    public void constructorNegativeNbOfItem() {
        assertThrows(IllegalArgumentException.class, () -> new Inventory(randomGenerator.nextInt(-100, 0)));
    }

    @Test
    public void getNumberOfPlace() {
        assertEquals("The inventory as the wrong number of places",
                NUMBER_OF_PLACE, testInventory.getNumberOfPlace());
    }

    @Test
    public void getNumberOfItemsEmpty() {
        assertEquals("The inventory should have 0 item",
                0, testInventory.getNumberOfItems());
    }

    @Test
    public void getNumberOfItemsNotEmpty() {
        int nbOfItemAdd = 0; //The number of items add into the inventory
        try {
            testInventory.add(nbOfItemAdd++, item1);
            testInventory.add(nbOfItemAdd++, item2);
            testInventory.add(nbOfItemAdd++, item3);
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }
        assertEquals("The inventory should have" + nbOfItemAdd + "item",
                nbOfItemAdd, testInventory.getNumberOfItems());
    }

    // In an empty inventory
    @Test
    public void isFullEmpty() {
        assertFalse(testInventory.isFull());
    }

    @Test
    public void isFullNotEmpty() {
        try {
            for (int i = 0 ; i < NUMBER_OF_PLACE - 1 ; i++)
                testInventory.add(testInventory.getFirstEmptyPlace(), item1);
        } catch (NotEmptyPlaceException | InventoryFullException e) {
            throw new RuntimeException(e);
        }
        assertFalse(testInventory.isFull());
    }

    @Test
    public void isFulYes() {
        try {
            for (int i = 0 ; i < NUMBER_OF_PLACE; i++) { // Set the inventory full
                testInventory.add(testInventory.getFirstEmptyPlace(), item1);
            }
        } catch (NotEmptyPlaceException | InventoryFullException e) {
            throw new RuntimeException(e);
        }
        assertTrue(testInventory.isFull());
    }

    // In an empty inventory
    @Test
    public void isEmptyYes() {
        assertTrue(testInventory.isEmpty());
    }

    @Test
    public void isEmptyNo() {
        try {
            testInventory.add(testInventory.getFirstEmptyPlace(), item1);
        } catch (NotEmptyPlaceException | InventoryFullException e) {
            throw new RuntimeException(e);
        }
        assertFalse(testInventory.isEmpty());
    }


    @Test
    public void testGetFirstEmptyPlaceEmpty() {
        try {
            assertEquals(0, testInventory.getFirstEmptyPlace());
        } catch (InventoryFullException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetFirstEmptyPlaceNotEmpty() {
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
    public void testGetFirstEmptyPlaceFull() {
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
    public void AddSimple() {
        int placeToAdd = randomGenerator.nextInt(NUMBER_OF_PLACE);
        try {
            testInventory.add(placeToAdd, item1);
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }
        assertNotNull("This place should be not null and have the added item",
                testInventory.getItemByIndex(placeToAdd));
        assertEquals("The item1 should be at place " + placeToAdd,
                item1.getName(), testInventory.getItemByIndex(placeToAdd).getName());
        assertEquals("The item1 should be at place " + placeToAdd,
                item1.getSprite().getIcon(),
                testInventory.getItemByIndex(placeToAdd).getSprite().getIcon());

    }

    @Test
    public void AddAtNotEmptyPlace() {
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
    public void RemoveSimple() {
        // Adding an item :
        int placeToAddAndRemove = randomGenerator.nextInt(NUMBER_OF_PLACE);
        try {
            testInventory.add(placeToAddAndRemove, item1);
        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }
        assertNotNull("This place should be not null and have the added item",
                testInventory.getItemByIndex(placeToAddAndRemove));

        // Removing item
        try {
            testInventory.remove(placeToAddAndRemove);
        } catch (NothingToRemoveException e) {
            throw new RuntimeException(e);
        }
        assertNull(testInventory.getItemByIndex(placeToAddAndRemove));
    }

    @Test
    public void RemoveEmpty() {
        assertThrows(NothingToRemoveException.class, () ->
                testInventory.remove(randomGenerator.nextInt(NUMBER_OF_PLACE)));
    }

    @Test
    public void getItemByIndexEmpty() {
        assertNull(testInventory.getItemByIndex(randomGenerator.nextInt(NUMBER_OF_PLACE)));
    }

    @Test
    public void getItemByIndexOutOfBound() {
        // I test the limit
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                testInventory.getItemByIndex(NUMBER_OF_PLACE));
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                testInventory.getItemByIndex(-1));
    }
}