package gameengine.utils.model;
import java.awt.Dimension;

/** Stores all the constants that will be used in the application
 * @author Jérémy THERET
 * @contributor Cédric ABDELBAKI
 * 				- Added : javadoc
 * 				- Added : MenuPanel.java constants
 */

public class Constants {
    private Constants() {}

    public static final int BLOCK_LENGHT = 32;

    public static final int CHARACTER_LENGHT = 64;

    // MenuPanel.java

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
}

