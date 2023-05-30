package gameengine.characters.model;

import org.junit.*;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CharacterTest {
    public Character mainCharacter;
    public Character mainCharacterTest;

    private void removeInstanceCharcter(Character chr) throws IllegalAccessException, NoSuchFieldException {
        if (chr != null) {
            Field field = chr.getClass().getDeclaredField("INSTANCE");
            field.setAccessible(true);
            Object value = field.get(chr);
            field.set(value, null);
            field.setAccessible(false);
        }
    }
    @After
    public void mainCharacterDelete() throws IllegalAccessException, NoSuchFieldException {
/*        Class<?> classe = Class.forName("Character");
        System.out.println(classe.getField("INSTANCE"));*/
        removeInstanceCharcter(mainCharacter);
        removeInstanceCharcter(mainCharacterTest);
        assertNull(Character.getInstance());

    }

    @Test
    public void characterDefaultCoords() {
        assertNull(Character.getInstance());
        mainCharacter = Character.createInstance();
        assertTrue(mainCharacter.getCoordinates().getX() == 0 && mainCharacter.getCoordinates().getY() == 0);
    }

    @Test
    public void characterOneInstance() {
        assertNull(Character.getInstance());

        mainCharacter = Character.createInstance();
        mainCharacterTest = Character.createInstance();
        assertTrue(mainCharacter == mainCharacterTest);
    }

    @Test
    public void characterOneInstanceSameCoords() {
        assertNull(Character.getInstance());
        mainCharacter = Character.createInstance(1,2);
        mainCharacterTest = Character.createInstance(1,2);
        assertTrue(mainCharacter == mainCharacterTest);
    }

    @Test
    public void characterOneInstanceDifferentCoords() {
        assertNull(Character.getInstance());
        mainCharacter = Character.createInstance(1,2);
        mainCharacterTest = Character.createInstance(3,4);
        assert mainCharacter.getCoordinates() == mainCharacterTest.getCoordinates();
        assertTrue(mainCharacter == mainCharacterTest);
    }

    @Test
    public void characterCoords() {
        assertNull(Character.getInstance());
        mainCharacter = Character.createInstance(1,2);

        assert mainCharacter.getCoordinates().getX() == 1 && mainCharacter.getCoordinates().getY() == 2;
    }

}