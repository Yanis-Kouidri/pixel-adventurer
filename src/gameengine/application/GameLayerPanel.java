package gameengine.application;

import javax.swing.JPanel;

import gameengine.characters.model.MainCharacter;

public class GameLayerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainCharacter mainCharacter;
    
    public GameLayerPanel() {
		mainCharacter = MainCharacter.createInstance();
	}

	public MainCharacter getMainCharacter() {
		return mainCharacter;
	}
}
