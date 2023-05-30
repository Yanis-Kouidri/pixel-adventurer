package src.gameengine.application;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ApplicationWindow {

	MenuPanel a;
	JPanel b;
	JFrame frame;

	ApplicationWindow() {

		// Instanciation of Main Frame
		frame = new JFrame();
		frame.setSize(1920, 1080);
		frame.setTitle("Pixel Adventurer");
		frame.setResizable(false);
		frame.setVisible(true);

		
		// Instanciation of panel a
		a = new MenuPanel(this);
		// Instanciation of panel b
		b = new JPanel();
		b.setBackground(Color.blue);
		frame.setLayout(new CardLayout());

		frame.add(a, "PANEL A");
		frame.add(b, "PANEL B");

	}

	
	public static void main(String[] args) {
		ApplicationWindow a = new ApplicationWindow();
	}


	public Component getA() {
		// TODO Auto-generated method stub
		return a;
	}


	public Component getB() {
		// TODO Auto-generated method stub
		return b;
	}


	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}

}