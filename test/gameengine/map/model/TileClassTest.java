package gameengine.map.model;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/** This JUnit class contains tests to check the Tile class methods
 * @author Cédric ABDELBAKI
 * @version 0.1
 */

public class TileClassTest {

	// A tile without collisions for testing purposes
	private Tile tileWithoutCollisions;

	// A tile with collisions for testing purposes
	private Tile tileWithCollisions;

	@Before
	public void setUp() {
		tileWithoutCollisions = new Tile(0, "tileWithoutCollisions", false);
		tileWithCollisions = new Tile(1, "tileWithCollisions", true);
	}

	// Tests if the identifier returned by the getTileId() method is correct
	@Test
	public void testGetTileId() {
		assertEquals(0, tileWithoutCollisions.getTileId());
		assertEquals(1, tileWithCollisions.getTileId());
	}

	// Tests if the name returned by the getTileName() method is correct
	@Test
	public void testGetTileName() {
		assertEquals("tileWithoutCollisions", tileWithoutCollisions.getTileName());
		assertEquals("tileWithCollisions", tileWithCollisions.getTileName());
	}

	// Tests if the name returned by the getCollisionBehavior() method is correct
	@Test
	public void testGetCollisionBehavior() {
		assertFalse(tileWithoutCollisions.getCollisionBehaviour());
		assertTrue(tileWithCollisions.getCollisionBehaviour());
	}
}

