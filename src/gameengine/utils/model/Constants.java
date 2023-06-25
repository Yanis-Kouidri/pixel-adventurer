package gameengine.utils.model;
import java.awt.Dimension;

/** Constants for the game
 * @author Jérémy THERET
 * @contributor Cédric ABDELBAKI
 * @version 0.2
 */
public class Constants {
    private Constants() {}

    public static final int BLOCK_LENGHT = 32;

    public static final int CHARACTER_LENGHT = 64;

    // The sprite dimension constant
    public static final int SPRITE_DIM = 32;

    public static final int MAP_COLUMNS = 200;
    public static final int MAP_ROWS = 60;
    public static final int MAP_LENGTH = MAP_COLUMNS*BLOCK_LENGHT;
    public static final int MAP_HEIGHT = MAP_COLUMNS*MAP_ROWS;

    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 720;

    public static final int SPAWN_WIDTH = SCREEN_WIDTH/2;
    public static final int SPAWN_HEIGHT = SCREEN_HEIGHT/2;
    
    /**
     * CONSTANTS FOR APPLICATION VIEW CLASSES
     */

    // The game version
 	public static final String GAME_VERSION = "Alpha 0.1";

 	// The path to the title logo
 	public static final String LOGO_PATH = "src/gameassets/application/images/pixelAdventurerLogo.png";

    // The path to the musical theme
	public static final String THEME_PATH = "src/gameassets/application/sounds/pixelAdventurerTheme.wav";

	// The path to the background image
	public static final String BCKGRND_PATH = "src/gameassets/application/images/menuBackground.png";

	// A constant for the menu panel dimensions
	public static final Dimension PANEL_DIMENSIONS = new Dimension(1920, 1080);

	// A constant for the menu panel empty borders
	public static final int BORDER_CONST = 50;

	// The path to the HTML file containing the credits
	public static final String CREDITS_FILE_PATH = "src/gameassets/application/files/credits.html";
}
