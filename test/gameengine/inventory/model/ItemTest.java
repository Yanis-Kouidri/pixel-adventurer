package gameengine.inventory.model;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
    private ImageIcon lemonIcon;
    private JLabel lemonJlabel;
    private String lemonDesc;
    private String lemonName;

    @Before
    public void setUp() {
        lemonIcon = new ImageIcon("test/gameassets/item/lemon.png");
        lemonJlabel = new JLabel(lemonIcon);
        lemonDesc = "When life give you lemons...";
        lemonName = "Lemon";

        lemon = new Item(lemonName, lemonJlabel, lemonDesc);
    }

    @After
    public void tearDown() {
        lemonIcon = null;
        lemonJlabel = null;
        lemon = null;
        lemonName = null;
        lemonDesc = null;
    }

    @Test
    public void getName() {
        assertEquals(lemonName, lemon.getName());
    }

    @Test
    public void getSprite() {
        assertEquals(lemonJlabel.getIcon(), lemon.getSprite().getIcon());
    }

    @Test
    public void getDescription() {
        assertEquals(lemonDesc, lemon.getDescription());
    }
}
