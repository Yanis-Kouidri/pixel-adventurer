package gameengine.map.view;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import java.awt.Image;
import static org.junit.Assert.*;

/** This JUnit class contains a test to check the Tileset class method
 * @author Cédric ABDELBAKI
 * @version 0.1
 */

public class TilesetClassTest {

	// A tileset for testing purposes
	private Tileset testTileset;

	// A tileset with an invalid path for testing purposes
	private Tileset invalidTileset;

	@Before
	public void setUp() throws IOException {
		testTileset = new Tileset("src/gameassets/map/tileset/testTileset.png");
		invalidTileset = new Tileset("src/invalid/path");
	}

	// Ensures that the getTileSprite() method returns a 32*32 non-null image
	@Test
	public void testGetTileSprite() {
		Image dirtSprite = testTileset.getTileSprite(3);
		assertNotNull(dirtSprite);
		assertEquals(32, dirtSprite.getWidth(null));
		assertEquals(32, dirtSprite.getHeight(null));
	}
}

