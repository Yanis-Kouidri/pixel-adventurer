package gameengine.characters.model;

import org.junit.*;
import java.lang.reflect.Field;
import java.util.Arrays;

import static gameengine.Physics.GRAVITY;
import static gameengine.Physics.SPEED;
import static org.junit.Assert.*;

public class CharacterTest {

    public final static double EPSILON = 0.001f;

    public Character mainCharacter;
    public Character mainCharacterTest;

    private void removeInstanceCharacter(Character chr)  {
        try {
            if (chr != null) {
                Field field = chr.getClass().getDeclaredField("INSTANCE");
                field.setAccessible(true);
                Object value = field.get(chr);
                field.set(value, null);
                field.setAccessible(false);
                chr = null;
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
        mainCharacter = Character.createInstance();
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
        assertNull(Character.getInstance());
        mainCharacter = Character.createInstance();
        assertTrue(mainCharacter.getCoordinates().getX() == 0
                && mainCharacter.getCoordinates().getY() == 0);
    }

    @Test
    public void characterOneInstance() {
        cleanUp();
        assertNull(Character.getInstance());

        mainCharacter = Character.createInstance();
        mainCharacterTest = Character.createInstance();
        assertSame(mainCharacter, mainCharacterTest);
    }

    @Test
    public void characterOneInstanceSameCoords() {
        cleanUp();
        assertNull(Character.getInstance());
        mainCharacter = Character.createInstance(1,2);
        mainCharacterTest = Character.createInstance(1,2);
        assertSame(mainCharacter, mainCharacterTest);
    }

    @Test
    public void characterOneInstanceDifferentCoords() {
        cleanUp();
        assertNull(Character.getInstance());
        mainCharacter = Character.createInstance(1,2);
        mainCharacterTest = Character.createInstance(3,4);
        assert mainCharacter.getCoordinates() == mainCharacterTest.getCoordinates();
        assertSame(mainCharacter, mainCharacterTest);
    }

    @Test
    public void characterCoords() {
        cleanUp();
        assertNull(Character.getInstance());
        mainCharacter = Character.createInstance(1,2);

        assertTrue(mainCharacter.getCoordinates().getX() == 1
                && mainCharacter.getCoordinates().getY() == 2);
    }

    // Character Deplacement
    @Test
    public void characterMoveRight() {
        assertNotNull(Character.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveRight();

        assertEquals(testX + SPEED, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY, mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveLeft() {
        assertNotNull(Character.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveLeft();

        assertEquals(testX - SPEED, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY, mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpOnce() {
        assertNotNull(Character.getInstance());

        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveUp();

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - SPEED + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpTwice() {
        assertNotNull(Character.getInstance());
        int howMany = 2;
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveUp();
        mainCharacter.moveUp();

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - (SPEED * howMany) + (GRAVITY * naturalSum(howMany)),
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpFiveTimes() {
        assertNotNull(Character.getInstance());
        int howMany = 5;
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        for (int i = 0; i < howMany; i++) {
            mainCharacter.moveUp();
        }

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - (SPEED * howMany) + (GRAVITY * naturalSum(howMany)),
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpTenTimes() {
        assertNotNull(Character.getInstance());
        int howMany = 10;
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        for (int i = 0; i < howMany; i++) {
            mainCharacter.moveUp();
        }

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - (SPEED * howMany) + (GRAVITY * naturalSum(howMany)),
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveRightMoveLeft() {
        assertNotNull(Character.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveLeft();
        mainCharacter.moveRight();

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY, mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveLeftMoveRight() {
        assertNotNull(Character.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveRight();
        mainCharacter.moveLeft();

        assertEquals(testX, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY, mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveLeftMoveUp() {
        assertNotNull(Character.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveLeft();
        mainCharacter.moveUp();

        assertEquals(testX - SPEED, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - SPEED + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpMoveLeft() {
        assertNotNull(Character.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveUp();
        mainCharacter.moveLeft();

        assertEquals(testX - SPEED, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - SPEED + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveRightMoveUp() {
        assertNotNull(Character.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveRight();
        mainCharacter.moveUp();

        assertEquals(testX + SPEED, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - SPEED + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }

    @Test
    public void characterMoveUpMoveRight() {
        assertNotNull(Character.getInstance());
        float testX = mainCharacter.getCoordinates().getX();
        float testY = mainCharacter.getCoordinates().getY();

        mainCharacter.moveUp();
        mainCharacter.moveRight();

        assertEquals(testX + SPEED, mainCharacter.getCoordinates().getX(), EPSILON);
        assertEquals(testY - SPEED + GRAVITY,
                mainCharacter.getCoordinates().getY(), EPSILON);
    }
}