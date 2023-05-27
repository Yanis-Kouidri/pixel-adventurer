package game;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Logger;

import game.gameloop.GameLoop;
import gameengine.GameWindow;
import gameengine.Utils;
import gameengine.characters.model.Character;
import gameengine.characters.model.Entity;
import gameengine.characters.view.EntityView;


/**
 * The PixelAdventure class is the starting point of the application, it permit to create a window and launch the game.
 * @author Thomas Gruner
 *
 */
public class PixelAdventure extends GameLoop {
	private Entity mainCharacter;		//the main character object
	private EntityView entityView;		//the view that need to be displayed on the window
	private Image image;

	protected final Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

	/**
	 * a constructor.
	 */
	 public PixelAdventure() {
		 image = Utils.getImage("mainCharacter.png");
		 mainCharacter = Character.createInstance();						//we want to display the main character so we create it
		 entityView = new EntityView(mainCharacter, 124, 124, image);		//we now specify that we want to create a view of this character
		 GameWindow.createInstance(entityView);								//and we add it to the window

	 }

	@Override
	protected void processGameLoop() {
		while (isGameRunning()) {
			processInput();
			update();
			render();
		}
	}

	@Override
	protected void render() {
		entityView.setLocation(mainCharacter.getCoordinates().getX(), mainCharacter.getCoordinates().getY());

	}
	protected void update() {
		mainCharacter.moveRight();
		logger.info(String.valueOf(mainCharacter.getCoordinates().getX()));
	}

}
