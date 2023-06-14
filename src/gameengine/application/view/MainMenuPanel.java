package gameengine.application.view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gameengine.utils.model.Constants;

/** Allows to create a MainMenuPanel object
 * @author Cédric ABDELBAKI
 * @version 0.1
 */
public class MainMenuPanel extends MenuPanel {
	
	// The game musical theme
	protected GameSound gameTheme;

	/** Constructor : creates a MainMenuPanel object
	 * @param pm a PanelMediator object
	 */
	public MainMenuPanel(PanelMediator pm) {
		super(pm);
		
		if (gameTheme == null) {
			gameTheme = new GameSound(Constants.THEME_PATH);
			gameTheme.playClip(true);
		}

		// Creates the main menu buttons
		JButton playBtn = new JButton("PLAY");
		JButton cmdsBtn = new JButton("COMMANDS");
		JButton cdtsBtn = new JButton("CREDITS");
		JButton quitBtn = new JButton("QUIT");

		// Defines the buttons style
		defineButtonStyle(playBtn);
		defineButtonStyle(cmdsBtn);
		defineButtonStyle(cdtsBtn);
		defineButtonStyle(quitBtn);

		// Adds mouse listeners to the buttons
		playBtn.addMouseListener(this);
		cmdsBtn.addMouseListener(this);
		cdtsBtn.addMouseListener(this);
		quitBtn.addMouseListener(this);

		// Adds the buttons to the buttons panel
		JPanel buttonsPanel = new JPanel(new GridBagLayout());
		buttonsPanel.setOpaque(false);
		buttonsPanel.add(playBtn, gbc);
		buttonsPanel.add(cmdsBtn, gbc);
		buttonsPanel.add(cdtsBtn, gbc);
		buttonsPanel.add(quitBtn, gbc);
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.weighty = 1;
		add(buttonsPanel, gbc);
	}

    // Chooses an action depending on the button clicked
	@Override
	public void mouseClicked(MouseEvent e) {
		JFrame gameWindow = ApplicationWindow.getFrame();
		JButton clickedButton = (JButton) e.getSource();
		String buttonText = clickedButton.getActionCommand();
		switch (buttonText) {
			case "PLAY":
				gameTheme.stopClip();
				replacePanel(gameWindow, pm.getGamePanel());
				break;
			case "COMMANDS":
				replacePanel(gameWindow, pm.getCommandsPanel());
				break;
			case "CREDITS":
				replacePanel(gameWindow, pm.getCreditsPanel());
				break;
			case "QUIT":
				gameTheme.stopClip();
				gameWindow.dispose();
				break;
		}
			gameWindow.revalidate();
			gameWindow.repaint();
	}
}
