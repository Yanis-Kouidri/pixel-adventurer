package gameengine.application.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import gameengine.utils.model.Constants;

/** Allows to create a CreditsPanel object
 * @author Cédric ABDELBAKI
 * @version 0.1
 */
public class CreditsPanel extends ScrollPanePanel implements ActionListener {

	/** Constructor : creates a CreditsPanel object
	 * @param pm a PanelMediator object
	 */
	public CreditsPanel(PanelMediator pm) {
		super(pm);

		// Reads the text to display in a HTML file
		String creditsFilePath = Constants.CREDITS_FILE_PATH;
		String creditsText = null;
		try {
			creditsText = Files.readString(Path.of(creditsFilePath));
		} catch (IOException e) {
			System.err.println("Error trying to read the HTML file: " + e);
		}

		// Creates a label containing the credits text
		JLabel creditsLabel = new JLabel(creditsText);
		Font creditsFonts = new Font("Atari", Font.PLAIN, 20);
		creditsLabel.setFont(creditsFonts);
		creditsLabel.setForeground(Color.WHITE);

		// Creates the scroll pane to display the label
		super.scrollPane.setViewportView(creditsLabel);
		super.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        super.scrollPane.setPreferredSize(new Dimension(300, 300));
		add(super.scrollPane, gbc);

		// Creates the return button to return to the main menu
		JButton returnButton = new JButton("RETURN");
		returnButton.addMouseListener(this);
		gbc.weighty = 2;
		defineButtonStyle(returnButton);
		add(returnButton, gbc);

		// Calls the method that makes the credits scroll automatically
		startAutomaticScrolling();
	}

	/** Initialize and starts a timer for automatic scrolling
	 */
	private void startAutomaticScrolling() {
		Timer timer = new Timer(100, this);
		timer.start();	
	}

	// Manages the automatic scrolling
	@Override
	public void actionPerformed(ActionEvent e) {
	    JScrollBar automaticScrollBar = super.scrollPane.getVerticalScrollBar();
	    int currentValue = automaticScrollBar.getValue();
	    int maximumValue = automaticScrollBar.getMaximum() - automaticScrollBar.getVisibleAmount();
	    
	    if (currentValue < maximumValue) {
	        automaticScrollBar.setValue(currentValue + 1);
	    } else {
	        automaticScrollBar.setValue(automaticScrollBar.getMinimum());
	    }
	}
}
