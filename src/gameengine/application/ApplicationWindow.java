package gameengine.application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * The ApplicationWindow class create the application window where all the
 * graphics of the game will be displayed (singleton = only one instance of
 * ApplicationWindow can be create).
 * 
 * @author Eric YU
 */
public class ApplicationWindow {

	private static ApplicationWindow INSTANCE; // the ApplicationWindow instance.
	private static JFrame frame; // the window.

	/**
	 * ApplicationWindow constructor
	 */
	private ApplicationWindow(JPanel panel) {

		// instantiation of Main Frame
		frame = new JFrame();
		frame.setSize(1920, 1080);
		frame.setTitle("Pixel Adventurer");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit the program on close button press

		// instantiation of panel a
		frame.add(panel);
	}

	/**
	 * Retrieves the main frame
	 * 
	 * @return the frame of the application
	 */
	public static JFrame getFrame() {
		return frame;
	}

	/**
	 * createInstance to create an instance by calling the constructor.
	 * 
	 * @return ApplicationWindow
	 */
	public static ApplicationWindow createInstance(CustomPanel panel) {
		// if the instance doesn't exist, it create it
		if (INSTANCE == null) {
			INSTANCE = new ApplicationWindow(panel);
			panel.addAppWindow(INSTANCE);
		}

		return INSTANCE;
	}

	/**
	 * getInstance permit to get the instance of the ApplicationWindow.
	 * 
	 * @return ApplicationWindow.
	 */
	public static ApplicationWindow getInstance() {
		return INSTANCE;
	}

}