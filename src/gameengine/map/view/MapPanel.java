package gameengine.map.view;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import gameengine.application.model.Camera;
import gameengine.map.model.Map;
import gameengine.map.model.Tile;
import gameengine.utils.model.Constants;

import static gameengine.utils.model.Constants.SPRITE_DIM;

/** This class defines the MapPanel object
 * @author Cédric Abdelbaki
 * @version 0.1
 */

public class MapPanel extends JPanel {

	// The map to draw
	private Map level;

	private Camera camera;
	public Map getLevel() {
		return level;
	}

	public Tileset getSprites() {
		return sprites;
	}

	// The tileset containing the sprites
	private Tileset sprites;

	// The map width
	private int width;

	// The map height
	private int height;


	// The first x coordinate in pixels
	private final static int FIRST_X_PIXEL = 0;

	// The first y coordinate in pixels
	private final static int FIRST_Y_PIXEL = 0;

	public int getMapX() {
		return mapX;
	}

	public int getMapY() {
		return mapY;
	}

	private int mapX = 0;
	private int mapY = 0;

	/** Initializes a panel object with a map, a tileset and a background image
	 * @param level The map to draw
	 * @param sprites The tileset containing the tiles sprites
s	 */
	public MapPanel(Map level, Tileset sprites, String backgroundPaths) {
		setOpaque(false);

		// Sets the attributes for the object
		this.level = level;
		this.sprites = sprites;

		// Stores the map width and height
		this.width = this.level.getMapWidth();
		this.height = this.level.getMapHeight();

		// Sets the panel dimensions
		this.setPreferredSize(new Dimension(this.width * SPRITE_DIM, this.height * SPRITE_DIM));
	}

	/**
	 * Sets the camera of the MapPanel
	 * @param c the camera
	 */
	public void setCamera(Camera c){
		camera = c;
	}

	/**
	 * Renders the map
	 * @param g the <code>Graphics</code> object to protect
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int cameraX = camera.getX();
		int cameraY = camera.getY();
		int screenWidth = Constants.SCREEN_WIDTH;
		int screenHeight = Constants.SCREEN_HEIGHT;

		int startTileX = Math.max(mapX, cameraX / SPRITE_DIM);
		int endTileX = Math.min(mapX + getWidth() / SPRITE_DIM + 1, (cameraX + screenWidth) / SPRITE_DIM + 1);
		int startTileY = Math.max(mapY, cameraY / SPRITE_DIM);
		int endTileY = Math.min(mapY + getHeight() / SPRITE_DIM + 1, (cameraY + screenHeight) / SPRITE_DIM + 1);

		for (int y = startTileY; y < endTileY && y < height; y++) {
			for (int x = startTileX; x < endTileX && x < width; x++) {
				Tile currentTile = level.getTileAtPos(y, x);
				int currentTileIdentifier = currentTile.getTileId();
				Image currentSprite = sprites.getTileSprite(currentTileIdentifier);

				int pixelX = x * SPRITE_DIM;
				int pixelY = y * SPRITE_DIM;

				g.drawImage(currentSprite, pixelX, pixelY, null);
			}
		}
	}
}

