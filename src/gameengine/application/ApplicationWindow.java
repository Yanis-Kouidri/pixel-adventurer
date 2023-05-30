package src.gameengine.application;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Window displaying the game application
 * @author Eric YU
 */
public class ApplicationWindow {

	MenuPanel a; // MenuPanel used to show the main menu
	JPanel b; // Another panel
	JFrame frame; // Main frame used for the application

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
		ApplicationWindow a = new ApplicationWindow();
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

}