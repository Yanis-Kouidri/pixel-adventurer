package gameengine.characters.model;

import org.junit.*;
import static org.junit.Assert.*;

public class CharacterTest {
    public Character mainCharacter;
    public Character mainCharacterTest;
    @After
    public void mainCharacterDelete() throws ClassNotFoundException, NoSuchFieldException {
/*        Class<?> classe = Class.forName("Character");
        System.out.println(classe.getField("INSTANCE"));*/
    }

    @Test
    public void characterDefaultCoords() {
        mainCharacter = Character.createInstance();
        assertTrue(mainCharacter.getCoordinates().getX() == 0 && mainCharacter.getCoordinates().getY() == 0);
    }

    @Test
    public void characterOneInstance() {
        mainCharacter = Character.createInstance();
        mainCharacterTest = Character.createInstance();
        assertTrue(mainCharacter == mainCharacterTest);
    }

    @Test
    public void characterOneInstanceSameCoords() {
        mainCharacter = Character.createInstance(1,2);
        mainCharacterTest = Character.createInstance(1,2);
        assertTrue(mainCharacter == mainCharacterTest);
    }

    @Test
    public void characterOneInstanceDifferentCoords() {
        mainCharacter = Character.createInstance(1,2);
        mainCharacterTest = Character.createInstance(3,4);
        assert mainCharacter.getCoordinates() == mainCharacterTest.getCoordinates();
        assertTrue(mainCharacter == mainCharacterTest);
    }

    @Test
    public void characterCoords() {
        mainCharacter = Character.createInstance(1,2);
        assert mainCharacter.getCoordinates().getX() == 1 && mainCharacter.getCoordinates().getY() == 2;
    }

}