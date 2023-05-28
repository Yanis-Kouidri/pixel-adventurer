package gameengine;

import java.awt.*;
import javax.swing.*;

import gameengine.characters.view.EntityView;

/**
 * The GameWindow class create the application window where all the graphics of the game will be displayed (singleton = only one instance of GameWindow can be create).
 * @author n7student
 *
 */
public final class GameWindow {
	
	private static GameWindow INSTANCE;		//the GameWindow instance.
	private static JFrame window;			//the window.
	private static Dimension dimension;		//the full screen dimension.
	
	/**
	 * a constructor.
	 */
	private GameWindow(EntityView entityView) {
		window = new JFrame();
		
		dimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		window.setSize(dimension);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		//exit the program on close button press
		window.add(entityView);		//we link the view to the window
		int nombreCases = 8;
		JPanel panel = new JPanel(new GridLayout(nombreCases, nombreCases));

		boolean couleurBlanche = true; // Indicateur pour alterner la couleur des cases

		for (int i = 0; i < nombreCases; i++) {
			for (int j = 0; j < nombreCases; j++) {
				JPanel casePanel = new JPanel();

				if (couleurBlanche) {
					casePanel.setBackground(Color.WHITE);
				} else {
					casePanel.setBackground(Color.BLACK);
				}

				panel.add(casePanel);

				couleurBlanche = !couleurBlanche; // Changer la couleur pour la prochaine case
			}

			if (nombreCases % 2 == 0) {
				couleurBlanche = !couleurBlanche; // Inverser la couleur pour la première case de la ligne suivante
			}
		}


		window.setVisible(true);
	}

	
	/**
	 * createInstance to create an instance by calling the constructor.
	 * @param entityView 		//the view that need to be displayed
	 * @return GameWindow
	 */
	public static GameWindow createInstance(EntityView entityView) {
		//if the instance doesn't exist, it create it
		if(INSTANCE == null) {
			INSTANCE = new GameWindow(entityView);
		}
		
		return INSTANCE;
	}

	/**
	 * getInstance permit to get the instance of the GameWindow.
	 * @return GameWindow.
	 */
	public static GameWindow getInstance() {
		return INSTANCE;
	}
}
