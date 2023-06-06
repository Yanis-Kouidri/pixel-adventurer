package gameengine.application.view;

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
	 * @param pm the related app window
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
		JButton playBtn = new MenuButton("PLAY", Color.BLUE);

		// The button to exit the program
		JButton exitBtn = new MenuButton("EXIT", Color.RED);

		// Adding listeners to the buttons
		playBtn.addActionListener(new BtnListener());
		exitBtn.addActionListener(new BtnListener());
		buttons.add(playBtn, gbc);
		buttons.add(exitBtn, gbc);

		gbc.weighty = 1;
		add(buttons, gbc);
	}

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

					// Focus frame for the KeyListeners work
					frame.toFront();
					frame.requestFocus();

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
