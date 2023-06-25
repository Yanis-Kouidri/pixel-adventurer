package gameengine.application.view;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public abstract class ScrollPanePanel extends MenuPanel{

	protected JScrollPane scrollPane;

	public ScrollPanePanel(PanelMediator pm) {
		super(pm);
	
		// Creates the commands menu JScrollPane
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.weighty = 4;
	}
	
    // Allows to return to the main menu by clicking on RETURN
	@Override
	public void mouseClicked(MouseEvent e) {
		JFrame gameWindow = ApplicationWindow.getFrame();
		JButton clickedButton = (JButton) e.getSource();
		String buttonText = clickedButton.getActionCommand();
		if (buttonText == "RETURN") {
			replacePanel(gameWindow, pm.getMainMenuPanel());
		}
			gameWindow.revalidate();
			gameWindow.repaint();
	}

}
