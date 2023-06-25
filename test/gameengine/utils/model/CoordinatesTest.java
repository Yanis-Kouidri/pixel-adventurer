package gameengine.utils.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinatesTest {

    @Test
    public void defaultConstructor() {
        Coordinates test = new Coordinates();
        assert test.getX() == 0 && test.getY() == 0;
    }

    @Test
    public void constructorWithXYEqualsZero() {
        Coordinates test = new Coordinates(0,0);
        assert test.getX() == 0 && test.getY() == 0;
    }

    @Test
    public void constructorWithXYPositif() {
        Coordinates test = new Coordinates(1,2);
        assert test.getX() == 1 && test.getY() == 2;
    }

    @Test
    public void constructorWithXYNegatif() {
        Coordinates test = new Coordinates(-1,-2);
        assert test.getX() == -1 && test.getY() == -2;
    }

    @Test
    public void constructorWithXNegatifYPositif() {
        Coordinates test = new Coordinates(1,-2);
        assert test.getX() == 1 && test.getY() == -2;
    }

    @Test
    public void constructorWithXPositifYNegatif() {
        Coordinates test = new Coordinates(1,-2);
        assert test.getX() == 1 && test.getY() == -2;
    }

    @Test
    public void testGetXPositif() {
        Coordinates test = new Coordinates(1,2);
        assert test.getX() == 1;
    }

    @Test
    public void testGetXNegatif() {
        Coordinates test = new Coordinates(-1,2);
        assert test.getX() == -1;
    }

    @Test
    public void testGetYPositif() {
        Coordinates test = new Coordinates(1,2);
        assert test.getY() == 2;
    }

    @Test
    public void testGetYNegatif() {
        Coordinates test = new Coordinates(1,-2);
        assert test.getY() == -2;
    }
}