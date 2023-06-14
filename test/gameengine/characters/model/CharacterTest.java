package gameengine.characters.model;

import org.junit.*;
import java.lang.reflect.Field;
import java.util.Arrays;

import static gameengine.utils.model.Physics.GRAVITY;
import static gameengine.utils.model.Physics.NB_DEPLACEMENT_BLOCK;
import static org.junit.Assert.*;

public class CharacterTest {

    public final static double EPSILON = 0.001f;

    public MainCharacter mainCharacter;
    public MainCharacter mainCharacterTest;

    private void removeInstanceCharacter(MainCharacter chr)  {
        try {
            if (chr != null) {
                Field field = chr.getClass().getDeclaredField("instance");
                field.setAccessible(true);
                Object value = field.get(chr);
                field.set(value, null);
                field.setAccessible(false);
                //chr = null;
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private int naturalSum(int f) {
        if (f <= 1) {
            return 1;
        }
        else {
            return f + naturalSum(f - 1);
        }
    }

    @Before
    public void setUp() {
        mainCharacter = MainCharacter.createInstance();
    }

    @After
    public void cleanUp() {
        removeInstanceCharacter(mainCharacter);
        removeInstanceCharacter(mainCharacterTest);
    }

    // CharacterCreation
    @Test
    public void characterDefaultCoords() {
        cleanUp();
        assertNull(MainCharacter.getInstance());
        mainCharacter = MainCharacter.createInstance();
        assertTrue(mainCharacter.getCoordinates().getX() == 0
                && mainCharacter.getCoordinates().getY() == 0);
    }

    @Test
    public void characterOneInstance() {
        cleanUp();
        assertNull(MainCharacter.getInstance());

        mainCharacter = MainCharacter.createInstance();
        mainCharacterTest = MainCharacter.createInstance();
        assertSame(mainCharacter, mainCharacterTest);
    }

    @Test
    public void characterOneInstanceSameCoords() {
        cleanUp();
        assertNull(MainCharacter.getInstance());
        mainCharacter = MainCharacter.createInstance(1,2);
        mainCharacterTest = MainCharacter.createInstance(1,2);
        assertSame(mainCharacter, mainCharacterTest);
    }

    @Test
    public void characterOneInstanceDifferentCoords() {
        cleanUp();
        assertNull(MainCharacter.getInstance());
        mainCharacter = MainCharacter.createInstance(1,2);
        mainCharacterTest = MainCharacter.createInstance(3,4);
        assert mainCharacter.getCoordinates() == mainCharacterTest.getCoordinates();
        assertSame(mainCharacter, mainCharacterTest);
    }

    @Test
    public void characterCoords() {
        cleanUp();
        assertNull(MainCharacter.getInstance());
        mainCharacter = MainCharacter.createInstance(1,2);

        assertTrue(mainCharacter.getCoordinates().getX() == 1
                && mainCharacter.getCoordinates().getY() == 2);
    }

    // Character Deplacement
    @Test
    public void characterMoveRight() {
        assertNotNull(MainCharacter.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveRight();

        assertEquals(testX + NB_DEPLACEMENT_BLOCK, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY, mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveLeft() {
        assertNotNull(MainCharacter.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveLeft();

        assertEquals(testX - NB_DEPLACEMENT_BLOCK, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY, mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpOnce() {
        assertNotNull(MainCharacter.getInstance());

        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveUp();

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - NB_DEPLACEMENT_BLOCK + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpTwice() {
        assertNotNull(MainCharacter.getInstance());
        int howMany = 2;
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveUp();
        mainCharacter.moveUp();

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - (NB_DEPLACEMENT_BLOCK * howMany) + (GRAVITY * naturalSum(howMany)),
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpFiveTimes() {
        assertNotNull(MainCharacter.getInstance());
        int howMany = 5;
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        for (int i = 0; i < howMany; i++) {
            mainCharacter.moveUp();
        }

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - (NB_DEPLACEMENT_BLOCK * howMany) + (GRAVITY * naturalSum(howMany)),
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpTenTimes() {
        assertNotNull(MainCharacter.getInstance());
        int howMany = 10;
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        for (int i = 0; i < howMany; i++) {
            mainCharacter.moveUp();
        }

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - (NB_DEPLACEMENT_BLOCK * howMany) + (GRAVITY * naturalSum(howMany)),
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveRightMoveLeft() {
        assertNotNull(MainCharacter.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveLeft();
        mainCharacter.moveRight();

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY, mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveLeftMoveRight() {
        assertNotNull(MainCharacter.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveRight();
        mainCharacter.moveLeft();

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY, mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveLeftMoveUp() {
        assertNotNull(MainCharacter.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveLeft();
        mainCharacter.moveUp();

        assertEquals(testX - NB_DEPLACEMENT_BLOCK, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - NB_DEPLACEMENT_BLOCK + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpMoveLeft() {
        assertNotNull(MainCharacter.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveUp();
        mainCharacter.moveLeft();

        assertEquals(testX - NB_DEPLACEMENT_BLOCK, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - NB_DEPLACEMENT_BLOCK + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveRightMoveUp() {
        assertNotNull(MainCharacter.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveRight();
        mainCharacter.moveUp();

        assertEquals(testX + NB_DEPLACEMENT_BLOCK, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - NB_DEPLACEMENT_BLOCK + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpMoveRight() {
        assertNotNull(MainCharacter.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveUp();
        mainCharacter.moveRight();

        assertEquals(testX + NB_DEPLACEMENT_BLOCK, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - NB_DEPLACEMENT_BLOCK + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }
}