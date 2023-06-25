package gameengine.map.model;
import org.junit.Test;
import static org.junit.Assert.*;

/** This JUnit class contains a test to check the MapArray class generateMapArray() method
 * @author Cédric ABDELBAKI
 * @version 0.1
 */

public class MapArrayClassTest {

	// Ensures that the generateMapArray() method returns a 50*50 2D array of non-null tiles
	@Test
	public void testGenerateMapArray() {

		// Creates the parameters, the tiles and the type to generate the array
		int testWidth = 50;
		int testHeight = 50;
		long testSeed = 42;
		double testAmplitude = 0.1;
		double testScale = 0.1;
		Tile testEmptyTile = new Tile(0, "testEmptyTile", false);
		Tile testSurfaceTile = new Tile(1, "testSurfaceTile", true);
		Tile testUndergroundTile = new Tile(3, "testUndergroundTile", true);
		MapType testType = new MapType("testType", testEmptyTile, testSurfaceTile, testUndergroundTile);

		// Generates the array
		Tile[][] testMapArray = MapArray.generateMapArray(testType, testWidth, testHeight, testSeed, testAmplitude, testScale);

		// Checks that width and height specified as parameters matches the array width and height
		assertEquals(50, testMapArray.length);
		assertEquals(50, testMapArray[0].length);

		// Ensures that all the tiles in the array have a value different from null
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
            	assertNotNull(testMapArray[x][y]);
            }
        }
	}
}
