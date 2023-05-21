import java.awt.CardLayout;
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

public class ApplicationWindow {

	JPanel a;
	JPanel b;
	JFrame frame;

	ApplicationWindow() {

		// Instanciation of Main Frame
		frame = new JFrame();
		frame.setSize(1920, 1080);
		frame.setTitle("Pixel Adventurer");
		frame.setResizable(false);
		frame.setVisible(true);

		CardLayout clayout = new CardLayout();

		// Instanciation of panel a
		a = new MenuPanel();

		// Instanciation of panel b
		b = new JPanel();
		b.setBackground(Color.blue);
		frame.setLayout(clayout);

		frame.add(a, "PANEL A");
		frame.add(b, "PANEL B");

	}

	public class MenuPanel extends JPanel {

		public MenuPanel() {
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
			PlayBtn.addActionListener(new PlayBtnListener());
			buttons.add(PlayBtn, gbc);
//			buttons.add(new JButton("Exit"), gbc);

			gbc.weighty = 1;
			add(buttons, gbc);
		}

	}

	public class PlayBtnListener implements ActionListener {
		public PlayBtnListener() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			frame.remove(a);
			frame.add(b);
			System.out.println("Clicked ! ");
			frame.repaint();
			frame.revalidate();
		}
	}

	public static void main(String[] args) {
		ApplicationWindow a = new ApplicationWindow();
	}

}
