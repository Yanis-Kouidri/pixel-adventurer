package gameengine.application.view;
import gameengine.application.model.CommandsMap;
import gameengine.utils.model.Constants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Displays the Main menu panel
 * @author Eric YU
 * @contributor Cédric ABDELBAKI
 * 				- Added : javadoc
 * 				- Added : menuBackground attribute
 * 				- Added : replacePanel() method
 * 				- Added : defineButtonsStyle() method
 * 				- Added : paintComponent() method
 * 				- Added : MouseListener methods
 * 				- Modified : javadoc
 * 				- Modified : MenuPanel() constructor
 * 				- Deleted : BtnListener internal class
 * 				- Deleted : MenuButton class
 * @version 0.3
 */
public abstract class MenuPanel extends CustomPanel implements MouseListener{

	// The menu background image
	private Image menuBackground;
  
	// The main GridBagConstraints object
	protected GridBagConstraints gbc;

	// The object containing the commands to use in the game
	protected CommandsMap gameCommands;

	/** Constructor : Allows to create a MenuPanel object
	 * @param pm the related app window
	 */
	public MenuPanel(PanelMediator pm) {
		super(pm);

		// Creates a CommandsMap object if it does not already exists
		if (gameCommands == null) {
			gameCommands = new CommandsMap();
		}

		// Tries to read the background image file
		try {
			this.menuBackground = ImageIO.read(new File(Constants.BCKGRND_PATH));
		} catch (IOException e) {
			System.err.println("Error trying to read the menu background image file" + e);
		}

		// Defines the panel borders and size
		setBorder(new EmptyBorder(Constants.BORDER_CONST, Constants.BORDER_CONST, Constants.BORDER_CONST, Constants.BORDER_CONST));
		setPreferredSize(Constants.PANEL_DIMENSIONS);

		// Defines the layout
		setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();

		// Creates and adds the Pixel Adventurer lol
		JLabel pixelAdventurerLogoLabel = new JLabel();
		ImageIcon pixelAdventurerLogoIcon = new ImageIcon(Constants.LOGO_PATH);
		pixelAdventurerLogoLabel.setIcon(pixelAdventurerLogoIcon);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1.0;
		add(pixelAdventurerLogoLabel, gbc);

		// Creates and adds the game version label
		JLabel versionLabel = new JLabel("Version: " + Constants.GAME_VERSION);
		Font versionFont = new Font("Atari", Font.PLAIN, 12);
		versionLabel.setFont(versionFont);
		versionLabel.setForeground(Color.WHITE);
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		add(versionLabel, gbc);
	}

	/** Defines the buttons style
	 * @author 
	 * @param button The JPanel containing the buttons
	 */
	public void defineButtonStyle(JButton button) {
		Font buttonFont = new Font("Atari", Font.PLAIN, 24);
		button.setFont(buttonFont);
		button.setForeground(Color.WHITE);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setHorizontalAlignment(SwingConstants.LEFT);
	}

	/** Removes a panel from the window and adds a new one
	 * @param gameWindow The window in which you want to change the panel
	 * @param panel The panel you want to add
	 */
	protected void replacePanel(JFrame gameWindow, CustomPanel panel) {
		try {
			// Removes the menu panel from the window
			gameWindow.remove(this);	
			gameWindow.add(panel);
			gameWindow.toFront();
			gameWindow.requestFocus();
		} catch (NullPointerException exception) {
			System.out.print(exception.getMessage());
		}
	}

	// Draws the menu background image
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getSize().width;
		int height = getSize().height;
		g.drawImage(menuBackground, 0, 0, width, height, this);
	}

	// Modifies the button text color to yellow when hovered
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton hoveredButton = (JButton) e.getSource();
		hoveredButton.setForeground(Color.decode("#FFDE59"));
	}

	// Modifies the button text color to white when exited
	@Override
	public void mouseExited(MouseEvent e) {
		JButton exitedButton = (JButton) e.getSource();
		exitedButton.setForeground(Color.WHITE);	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
