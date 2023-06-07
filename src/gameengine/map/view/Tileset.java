package gameengine.map.view;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import static gameengine.utils.model.Constants.SPRITE_DIM;

/** This class defines the Tileset object
 * @author Cédric Abdelbaki
 * @version 0.1
 */

public class Tileset {

	// The tileset
	private BufferedImage set;
	private final int nbTiles; // The number of tiles that can be extracted from the tileset
	private final Map<Integer, Image> cachedImages = new HashMap<>(); // The different tiles cached as images

	/** Instantiates a Tileset object
	 * @param tilesetPath The path to the tileset image
	 */
	public Tileset(String tilesetPath) {
		try {
			set = ImageIO.read(new File(tilesetPath));
		} catch (IOException e) {
			System.out.println("Error trying to read the tileset image file");
		}
		nbTiles = computeNumberTiles();
		createCache();
	}

	/** Gets a sprite in the tileset, using a tile identifier
	 * @param tileIdentifier The identifier of the tile
	 * @return sprite The tile sprite
	 */
	public Image getTileSprite(int tileIdentifier) {
		return cachedImages.get(tileIdentifier);
	}

	/**
	 * Creates a map of cached images to reuse for drawing.
	 * It uses the number of tiles computed by computeNumberTiles
	 * and iterates through the tileset to create the map.
	 *
	 * The key is the tile ID
	 * The value is the cached image
	 */
	public void createCache() {
		int numberOfColumns = set.getWidth() / SPRITE_DIM;
		for (int tileIdentifier = 0; tileIdentifier < nbTiles; tileIdentifier++) {
			int x = tileIdentifier / numberOfColumns;
			int y = tileIdentifier % numberOfColumns;

			Image sprite = set.getSubimage(y * SPRITE_DIM
					, x * SPRITE_DIM, SPRITE_DIM, SPRITE_DIM);

			cachedImages.put(tileIdentifier, sprite);
		}
	}

	/**
	 * Computes the number of tiles that can be extracted
	 * from the given tileset image.
	 * @return the number of tiles
	 */
	public int computeNumberTiles(){
		int numberOfColumns = set.getWidth() / SPRITE_DIM;
		int numberOfRows = set.getHeight() / SPRITE_DIM;
		return numberOfRows*numberOfColumns;
	}
}
