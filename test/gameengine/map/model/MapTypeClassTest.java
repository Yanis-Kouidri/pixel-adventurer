package gameengine.map.model;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/** This JUnit class contains tests to check the MapType class methods
 * @author Cédric ABDELBAKI
 * @version 0.1
 */

public class MapTypeClassTest {

	// A first tile for testing purposes
	private Tile testEmptyTile;

	// A second tile for testing purposes
	private Tile testSurfaceTile;

	// A third tile for testing purposes
	private Tile testUndergroundTile;

	// A type for testing purposes
	private MapType testMapType;

	@Before
	public void setUp() throws Exception {
		testEmptyTile = new Tile(0, "testEmptyTile", false);
		testSurfaceTile = new Tile(1, "testSurfaceTile", true);
		testUndergroundTile = new Tile(2, "testUndergroundTile", true);
		testMapType = new MapType("testMapType", testEmptyTile, testSurfaceTile, testUndergroundTile);
	}

	// Tests if the name returned by the getTypeName() method is correct
	@Test
	public void testGetTypeName() {
		assertEquals("testMapType", testMapType.getTypeName());
	}

	// Tests if the tile returned by the getEmptyTile() method is correct
	@Test
	public void testGetEmptyTile() {
		assertEquals(testEmptyTile, testMapType.getEmptyTile());
	}

	// Tests if the tile returned by the getSurfaceTile() method is correct
	@Test
	public void testGetSurfaceTile() {
		assertEquals(testSurfaceTile, testMapType.getSurfaceTile());
	}

	// Tests if the tile returned by the getUndergroundTile() method is correct
	@Test
	public void testGetUndergroundTile() {
		assertEquals(testUndergroundTile, testMapType.getUndergroundTile());
	}
}

