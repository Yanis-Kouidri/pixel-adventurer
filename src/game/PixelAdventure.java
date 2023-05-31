package game;
import java.awt.Image;

import gameengine.application.ApplicationWindow;
import gameengine.application.GamePanel;
import gameengine.application.MenuPanel;
import gameengine.application.PanelMediator;
import gameengine.gameloop.GameLoop;


/**
 * The PixelAdventure class is the starting point of the application, it permit
 * to create a window and launch the game.
 * 
 * @author Thomas Gruner
 *
 */
//public class PixelAdventure extends GameLoop {
//	private Entity mainCharacter;		//the main character object
//	private EntityView entityView;		//the view that need to be displayed on the window
//	private Image image;
//
//	protected final Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
//
//	/**
//	 * a constructor.
//	 */
//	 public PixelAdventure() {
//		 image = Utils.getImage("mainCharacter.png");
//		 mainCharacter = Character.createInstance();						//we want to display the main character so we create it
//		 entityView = new EntityView(mainCharacter, 124, 124, image);		//we now specify that we want to create a view of this character
//		 GameWindow.createInstance(entityView);								//and we add it to the window
//
//	 }

public class PixelAdventure extends GameLoop {

    private GamePanel gamePanel;			// the view that need to be displayed on the window
    private MenuPanel menuPanel;
    private PanelMediator panelMediator;

    /**
     * a constructor.
     */
    public PixelAdventure() {
        panelMediator = new PanelMediator();
        gamePanel = new GamePanel(panelMediator);
        menuPanel = new MenuPanel(panelMediator);
        ApplicationWindow.createInstance(menuPanel); // and we add it to the window
    }

	@Override
	protected void processGameLoop() {
		while (isGameRunning()) {
			processInput(60);
			update();
			render();
		}
	}

	@Override
	protected void render() {
		//++++++++++++++++++++++++++++++ PROBLEME ICI
		
		//entityView.setLocation(mainCharacter.getCoordinates().getX(), mainCharacter.getCoordinates().getY());
	}
	
	protected void update() {
		//++++++++++++++++++++++++++++++ PROBLEME ICI
		
		//mainCharacter.moveRight();
		//logger.info(String.valueOf(mainCharacter.getCoordinates().getX()));
	}

}