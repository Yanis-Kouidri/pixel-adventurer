package gameengine.application.view;
import gameengine.utils.model.Constants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** Displays the Main menu panel
 * @author Éric YU
 * @contributor Cédric ABDELBAKI
 * 				- Added : javadoc
 * 				- Added : menuBackground attribute
 * 				- Added : setFontSize() method
 * 				- Added : defineButtonsStyle() method
 * 				- Added : paintComponent() method
 * 				- Added : playSound() method
 * 				- Modified : javadoc
 * 				- Modified : MenuPanel() constructor
 * 				- Modified : BtnListener internal class
 * 				- Deleted : MenuButton class
 */
public class MenuPanel extends CustomPanel {

	// The menu background image
	private Image menuBackground;

	/** Creates a new menu panel with a logo, a background, a version, buttons and music 
	 * @param pm the related app window
	 */
	public MenuPanel(PanelMediator pm) {

		super(pm);
		pm.setMenuPanel(this);

		// Defines the menu panel borders, layout and size
		setBorder(new EmptyBorder(Constants.BORDER_CONST, Constants.BORDER_CONST, Constants.BORDER_CONST, Constants.BORDER_CONST));
		setLayout(new GridBagLayout());
		setPreferredSize(Constants.PANEL_DIMENSIONS);

		// Creates grid bag constraints
		GridBagConstraints gbc = new GridBagConstraints();

		// Defines grid bag constraints parameters
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1.0;

		// Plays the music theme
		playSound(Constants.THEME_PATH);

		// Creates the title label
		JLabel titleLabel = new JLabel();

		// Assigns a Pixel Adventurer logo to the label
		ImageIcon titleImage = new ImageIcon(Constants.LOGO_PATH);
		titleLabel.setIcon(titleImage);

		// Adds the title logo to the menu panel
		add(titleLabel, gbc);

		// Creates the version label
		JLabel versionLabel = new JLabel("Version: " + Constants.GAME_VERSION);

		// Sets the version font size
		Font versionFont = setFontSize(12);

		// Defines the version title text color and font
		versionLabel.setFont(versionFont);
		versionLabel.setForeground(Color.WHITE);

		// Modifies gbc parameters
		gbc.anchor = GridBagConstraints.SOUTHWEST;

        // Adds the version label to the menu panel
		add(versionLabel, gbc);

		// Creates the buttons panel
		JPanel buttonsPanel = new JPanel(new GridBagLayout());

		// Removes the buttons panel background color
		buttonsPanel.setOpaque(false);

		// The button to switch to the game panel
		JButton playBtn = new JButton("PLAY");

		// The button to switch to the commands panel
		JButton cmdsBtn = new JButton("COMMANDS");

		// The button to switch to the credits panel
		JButton cdtsBtn = new JButton("CREDITS");

		// The button to exit the program
		JButton quitBtn = new JButton("QUIT");

		// Adds listeners to the buttons
		playBtn.addMouseListener(new BtnListener());
		cmdsBtn.addMouseListener(new BtnListener());
		cdtsBtn.addMouseListener(new BtnListener());
		quitBtn.addMouseListener(new BtnListener());

		// Adds the buttons to the buttons panel
		buttonsPanel.add(playBtn, gbc);
		buttonsPanel.add(cmdsBtn, gbc);
		buttonsPanel.add(cdtsBtn, gbc);
		buttonsPanel.add(quitBtn, gbc);

		// Sets the buttons font size
		Font buttonsFont = setFontSize(24);

		// Defines the buttons panel style
		defineButtonsStyle(buttonsPanel, buttonsFont);

		// Modifies gbc parameters
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.weighty = 1;

		// Adds the buttons panel to the menu panel
		add(buttonsPanel, gbc);
	}

	/** Sets the font size as defined in parameters
	 * @param fontSize the desired font size
	 * @return a new Font object
	 */
	public Font setFontSize(int fontSize) {
		return new Font("Atari", Font.PLAIN, fontSize);
	}

	/** Defines the buttons style
	 * @author 
	 * @param buttonsPanel The JPanel containing the buttons
	 */
	public void defineButtonsStyle(JPanel buttonsPanel, Font buttonsFont) {

		// Sets a list of buttons components
		Component[] panelButtons = buttonsPanel.getComponents();

		// Loops on the buttons components
		for (Component currentButton : panelButtons) {

			// Checks if the button component is an instance of the JButton class
			if (currentButton instanceof JButton) {

				// Casts the currentButton component to the JButton type
				JButton button = (JButton) currentButton;

				// Sets the button font
				button.setFont(buttonsFont);

				// Sets the button text in black
				button.setForeground(Color.WHITE);

				// Removes the borders from the button
				button.setBorder(null);

				// Removes the background from the button
				button.setContentAreaFilled(false);

				// Aligns the buttons to the left
				button.setHorizontalAlignment(SwingConstants.LEFT);
			}
		}
	}

	// Draws the menu background image
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Tries to read the background image file
		try {
			this.menuBackground = ImageIO.read(new File(Constants.BCKGRND_PATH));
		} catch (IOException e) {
			System.err.println("Error trying to read the menu background image file" + e);
		}

		// Gets the panel width and height
		int width = getSize().width;
		int height = getSize().height;

		// Draws the menu background image at position 0,0 with the panel dimensions
		g.drawImage(menuBackground, 0, 0, width, height, this);
	}

	// Plays a sound whose file path is specified as a parameter
    public void playSound(String soundFilePath) {

    	// Tries to open and play the sound
        try {
            File audioFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
            audioClip.loop(-1);
        } catch (Exception e) {
            System.err.println("Error trying to read and play the audio file" + e);
        }
    }

    /** Implements the MouseListener methods to handle mouse behavior
     * @author
     */
    public class BtnListener implements MouseListener {

    	// Defines actions when a button is clicked
		@Override
		public void mouseClicked(MouseEvent e) {

			JFrame gameWindow = ApplicationWindow.getFrame();
			JButton clickedButton = (JButton) e.getSource();
			String buttonText = clickedButton.getActionCommand();

			// Switch case structure to select the action depending on the button clicked on
			switch (buttonText) {

			// Starts a new game
			case "PLAY":
				try {

					// Removes the menu panel from the window
					gameWindow.remove(MenuPanel.this);

					// Adds the game panel instead
					gameWindow.add(pm.getGamePanel());

					// Brings focus on the window
					gameWindow.toFront();
					gameWindow.requestFocus();

				} catch (NullPointerException exception) {
					System.out.print(exception.getMessage());
				}
				break;

			// Displays the commands menu
			case "COMMANDS":
				break;

			// Displays the game credits
			case "CREDITS":
				break;

			// Exits the game
			case "QUIT":
				gameWindow.dispose();
				break;
			}

			// Updates the game window after changes
			gameWindow.revalidate();
			gameWindow.repaint();
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
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
    }
}

