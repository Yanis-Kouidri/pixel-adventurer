package gameengine.inventory.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class based on JUnit 4 to test Item class
 *
 * @author Yanis Kouidri
 * @version 0.1
 */
public class ItemTest {

    private Item lemon;
    private String lemonDesc;
    private String lemonName;

    @Before
    public void setUp() {
        lemonDesc = "When life give you lemons...";
        lemonName = "Lemon";

        lemon = new Item(lemonName, lemonDesc);
    }

    @After
    public void tearDown() {
        lemon = null;
        lemonName = null;
        lemonDesc = null;
    }

    @Test
    public void getName() {
        assertEquals(lemonName, lemon.getName());
    }


    @Test
    public void getDescription() {
        assertEquals(lemonDesc, lemon.getDescription());
    }
}
