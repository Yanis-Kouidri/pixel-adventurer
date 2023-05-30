package src.gameengine.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Displays the Main menu panel
 * 
 * @author Eric YU
 *
 */
public class MenuPanel extends CustomPanel {

	/**
	 * Creates a new menu panel with a small vertical menu and a title
	 * 
	 * 
	 * @param aW the related app window
	 */
	public MenuPanel(PanelMediator pm) {
		super(pm);
		pm.setMenuPanel(this);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;

		JLabel title = new JLabel("Pixel Adventurer");
		title.setFont(new Font("Arial", Font.BOLD, 40));
		add(title, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel buttons = new JPanel(new GridBagLayout());

		// The button to switch to the game panel
		JButton PlayBtn = new MenuButton("PLAY", Color.BLUE);

		// The button to exit the program
		JButton ExitBtn = new MenuButton("EXIT", Color.RED);

		// Adding listeners to the buttons
		PlayBtn.addActionListener(new BtnListener());
		ExitBtn.addActionListener(new BtnListener());
		buttons.add(PlayBtn, gbc);
		buttons.add(ExitBtn, gbc);

		gbc.weighty = 1;
		add(buttons, gbc);
	}

	/**
	 * Associate each button from the menu to an action
	 * 
	 * @author n7student
	 *
	 */
	public class BtnListener implements ActionListener {

		public BtnListener() {
		}

		public void actionPerformed(ActionEvent e) {
			JFrame frame = aW.getFrame();
			JButton o = (JButton) e.getSource();
			String name = o.getActionCommand();

			if (name == "PLAY") {
				try {
					frame.remove(MenuPanel.this);
					frame.add(pm.getGamePanel());
				} catch (NullPointerException exception) {
					System.out.print(exception.getMessage());
				}
			} else {
				System.out.println("> EXIT Clicked ! ");
				frame.dispose();
			}

			frame.revalidate();
			frame.repaint();
		}

	}

	public class MenuButton extends JButton {
		MenuButton(String text) {
			super(text);
			setFont(new Font("Arial", Font.BOLD, 16)); // Set font
		}

		MenuButton(String text, Color c) {
			super(text);
			setFont(new Font("Arial", Font.BOLD, 16)); // Set font
			setForeground(c); // Set text color

		}
	}
}