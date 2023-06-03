package gameengine.inventory.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item lemon;
    private ImageIcon lemonIcon;
    private JLabel lemonJlabel;
    private String lemonDesc;
    private String lemonName;

    @BeforeEach
    void setUp() {
        lemonIcon = new ImageIcon("test/gameassets/item/lemon.png");
        lemonJlabel = new JLabel(lemonIcon);
        lemonDesc = "When life give you lemons...";
        lemonName = "Lemon";

        lemon = new Item(lemonName, lemonJlabel, lemonDesc);
    }

    @AfterEach
    void tearDown() {
        lemonIcon = null;
        lemonJlabel = null;
        lemon = null;
        lemonName = null;
        lemonDesc = null;
    }

    @Test
    void getName() {
        assertEquals(lemonName, lemon.getName());
    }

    @Test
    void getSprite() {
        assertEquals(lemonJlabel, lemon.getSprite());
    }

    @Test
    void getDescription() {
        assertEquals(lemonDesc, lemon.getDescription());
    }
}