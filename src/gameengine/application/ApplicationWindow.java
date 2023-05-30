package src.gameengine.application;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The ApplicationWindow class create the application window where all the graphics of the game will be displayed (singleton = only one instance of ApplicationWindow can be create).
 * @author Eric YU
 */
public class ApplicationWindow {

	private static ApplicationWindow INSTANCE;		//the ApplicationWindow instance.
	private static JFrame frame;			//the window.
	private static JPanel a;
	private static JPanel b;
	/**
	 * ApplicationWindow constructor
	 */
	ApplicationWindow() {

		// instantiation of Main Frame
		frame = new JFrame();
		frame.setSize(1920, 1080);
		frame.setTitle("Pixel Adventurer");
		frame.setResizable(false);
		frame.setVisible(true);

		
		// instantiation of panel a
		a = new MenuPanel(this);
		// instantiation of panel b
		b = new JPanel();
		b.setBackground(Color.blue);
		
		// Adding the different panels to the layout
		frame.setLayout(new CardLayout());
		frame.add(a, "PANEL A");
		frame.add(b, "PANEL B");

	}

	/**
	 * Main program to render the application Window
	 * @param args : arguments used to 
	 */
	public static void main(String[] args) {
		ApplicationWindow a = ApplicationWindow.createInstance();
	}

	/**
	 * Retrieves the A component
	 * @return A panel
	 */
	public Component getA() {
		return a;
	}

	/**
	 * Retrieves the B component
	 * @return B panel
	 */
	public Component getB() {
		return b;
	}

	/**
	 * Retrieves the main frame
	 * @return the frame of the application
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * createInstance to create an instance by calling the constructor.
	 * @return ApplicationWindow
	 */
	public static ApplicationWindow createInstance() {
		//if the instance doesn't exist, it create it
		if(INSTANCE == null) {
			INSTANCE = new ApplicationWindow();
		}
		
		return INSTANCE;
	}
	
	/**
	 * getInstance permit to get the instance of the ApplicationWindow.
	 * @return ApplicationWindow.
	 */
	public static ApplicationWindow getInstance() {
		return INSTANCE;
	}

}