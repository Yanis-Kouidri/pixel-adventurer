package gameengine.map.view;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import gameengine.map.model.Map;
import gameengine.map.model.Tile;
import static gameengine.utils.model.Constants.SPRITE_DIM;

/** This class defines the MapPanel object
 * @author Cédric Abdelbaki
 * @version 0.1
 */

public class MapPanel extends JPanel {

	// The map to draw
	private Map level;

	// The tileset containing the sprites
	private Tileset sprites;

	// The panel background image
	private Image background;

	// The map width
	private int width;

	// The map height
	private int height;


	// The first x coordinate in pixels
	private final static int FIRST_X_PIXEL = 0;

	// The first y coordinate in pixels
	private final static int FIRST_Y_PIXEL = 0;

	/** Initializes a panel object with a map, a tileset and a background image
	 * @param level The map to draw
	 * @param sprites The tileset containing the tiles sprites
	 * @param background The path to the desired background image
	 */
	public MapPanel(Map level, Tileset sprites, String backgroundPath) {

		// Sets the attributes for the object
		this.level = level;
		this.sprites = sprites;

		// Tries to open and store the background image file
		try {
			this.background = ImageIO.read(new File(backgroundPath));
		} catch (IOException e) {
			System.out.println("Error trying to read the background image file");
		}

		// Stores the map width and height
		this.width = this.level.getMapWidth();
		this.height = this.level.getMapHeight();

		// Sets the panel dimensions
		this.setPreferredSize(new Dimension(this.width * SPRITE_DIM, this.height * SPRITE_DIM));
	}

	// Draws the background and the tiles
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draws the background image
		g.drawImage(this.background, FIRST_X_PIXEL, FIRST_Y_PIXEL, this.getWidth(), this.getHeight(), null);

		// Draws the map tiles
		for (int y = 0; y < this.height; y ++) {
			for (int x = 0; x < this.width; x ++) {
				Tile currentTile = this.level.getTileAtPos(y, x);
				int currentTileIdentifier = currentTile.getTileId();
				Image currentSprite = this.sprites.getTileSprite(currentTileIdentifier);
				g.drawImage(currentSprite, x * SPRITE_DIM, y * SPRITE_DIM, null);
			}
		}
	}
}
