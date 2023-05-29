package gameengine.map.view;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

/** This class defines the Tileset object
 * @author Cédric Abdelbaki
 * @version 0.1
 */

public class Tileset {

	// The tileset
	private BufferedImage set;

	/** Instantiates a Tileset object
	 * @param tilesetPath The path to the tileset image
	 */
	public Tileset(String tilesetPath) {
		try {
			set = ImageIO.read(new File(tilesetPath));
		} catch (IOException e) {
			System.out.println("Error trying to read the tileset image file");
		}
	}

	/** Gets a sprite in the tileset, using a tile identifier
	 * @param tileIdentifier The identifier of the tile
	 * @return sprite The tile sprite
	 */
	public Image getTileSprite(int tileIdentifier) {
		try {
			int numberOfColumns = set.getWidth() / MapPanel.SPRITE_DIM;
			int x = tileIdentifier / numberOfColumns;
			int y = tileIdentifier % numberOfColumns;
			Image sprite = set.getSubimage(y * MapPanel.SPRITE_DIM
					, x * MapPanel.SPRITE_DIM, MapPanel.SPRITE_DIM, MapPanel.SPRITE_DIM);
			return sprite;
		} catch (Exception e) {
			System.out.println("Error trying to get the sprite from the tileset");
			return null;
		}
	}
}

