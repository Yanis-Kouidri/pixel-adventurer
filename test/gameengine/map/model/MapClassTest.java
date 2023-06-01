package gameengine.map.model;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/** This JUnit class contain tests to check the Map class methods
 * @author Cédric ABDELBAKI
 * @version 0.1
 */

public class MapClassTest {

	// A first tile for testing purposes
	private Tile testEmptyTile;

	// A second tile for testing purposes
	private Tile testSurfaceTile;

	// A third tile for testing purposes
	private Tile testUndergroundTile;

	// A type for testing purposes
	private MapType testMapType;

	// A map for testing purposes
	private Map testMap;

	@Before
	public void setUp() throws Exception {
		testEmptyTile = new Tile(0, "testEmptyTile", false);
		testSurfaceTile = new Tile(1, "testSurfaceTile", true);
		testUndergroundTile = new Tile(2, "testUndergroundTile", true);
		testMapType = new MapType("testMapType", testEmptyTile, testSurfaceTile, testUndergroundTile);
		testMap = new Map("testMap", testMapType, 100, 50, 0.1, 0.1);
	}

	// Tests if the name returned by the getMapName() method is correct
	@Test
	public void testGetMapName() {
		assertEquals("testMap", testMap.getMapName());
	}

	// Tests if the type returned by the getMapType() method is correct
	@Test
	public void testGetMapType() {
		assertEquals(testMapType, testMap.getMapType());
	}

	// Tests if the width returned by the getMapWidth() method is correct
	@Test
	public void testGetMapWidth() {
		assertEquals(100, testMap.getMapWidth());
	}

	// Tests if the height returned by the getMapHeight() method is correct
	@Test
	public void testGetMapHeight() {
		assertEquals(50, testMap.getMapHeight());
	}

	// Ensures that the array returned by the getMapArray() method is not null
	@Test
	public void testGetMapArray() {
		assertNotNull(testMap.getMapArray());
	}

	// Tests if the tiles returned by the getTileAtPos() method are correct
	@Test
	public void testGetTileAtPos() {
		assertEquals(testEmptyTile, testMap.getTileAtPos(0, 0));
		assertEquals(testUndergroundTile, testMap.getTileAtPos(49, 49));
	}

	// Ensures that the tile is changed in the array after using the setTileAtPos() method
	@Test
	public void testSetTileAtPos() {
		testMap.setTileAtPost(testUndergroundTile, 0, 0);
		assertEquals(testUndergroundTile, testMap.getTileAtPos(0, 0));
	}	
}

