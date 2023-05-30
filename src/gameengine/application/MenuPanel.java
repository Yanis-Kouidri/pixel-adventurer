package src.gameengine.application;

import java.awt.Color;
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
 * @author Eric YU
 *
 */
public class MenuPanel extends JPanel {
	ApplicationWindow appWindow; // The Application Window associated

	/**
	 * Creates a new menu panel with a small vertical menu and a title
	 * @param aW the related app window
	 */
		public MenuPanel(ApplicationWindow aW) {
			super();
			appWindow = aW;
			setBackground(Color.red);
			setBorder(new EmptyBorder(10, 10, 10, 10));
			setLayout(new GridBagLayout());

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.NORTH;

			add(new JLabel("Title"), gbc);

			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;

			JPanel buttons = new JPanel(new GridBagLayout());
			JButton PlayBtn = new JButton("PLAY");
//			JButton ExitBtn = new JButton("EXIT");
			PlayBtn.addActionListener(new BtnListener());
//            ExitBtn.addActionListener(new BtnListener());
			buttons.add(PlayBtn, gbc);
//			buttons.add(ExitBtn, gbc);

			gbc.weighty = 1;
			add(buttons, gbc);
		}
		
		/**
		 * Associate each button from the menu to an action
		 * @author n7student
		 *
		 */
		public class BtnListener implements ActionListener {
			
			public BtnListener() {
			}

			public void actionPerformed(ActionEvent e) {
				JFrame frame = appWindow.getFrame();
		        JButton o = (JButton)e.getSource();
		        String name = o.getActionCommand();

		        if (name == "PLAY")
		        {
		            frame.remove(appWindow.getA());
		            frame.add(appWindow.getB());
		        }
		        else{
		            System.out.println("> EXIT Clicked ! ");
		        }
		     
				frame.revalidate();
				frame.repaint();
			}

	}
}