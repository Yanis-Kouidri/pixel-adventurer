package gameengine.characters.view;

import java.awt.*;
import java.util.logging.Logger;


import javax.swing.JPanel;

import gameengine.characters.model.Entity;

import static gameengine.utils.model.Constants.BLOCK_LENGHT;
import static gameengine.utils.model.Constants.CHARACTER_LENGHT;

/**
 * the view class for all entities objects.
 * @author thomas
 *
 */
public class EntityView extends JPanel{
	private final Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
	private Entity entity;		//the entity object to display
	private Image image;		//the sprite to be displayed
	
	/**
	 * a constructor for create a EntityView with a Entity and image.
	 * @param entity
	 * @param image
	 */
	public EntityView(Entity entity, Image image) {
		setOpaque(false);
		this.entity = entity;
		this.image = image;

		// Transparency Image character
		setBackground( new Color(0, 0, 0, 0) );
	}
	

	/**
	 * paint add what need to be displayed.
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, CHARACTER_LENGHT, CHARACTER_LENGHT,this);
//		logger.info("mainCharacter Paint");
	}

	/**
	 * update the location in the screen with the character coordinates
	 * (temporally method)
	 */
	public void updateLocation() {
		float x = entity.getCoordinates().getX() * BLOCK_LENGHT;
		float y = entity.getCoordinates().getY() * BLOCK_LENGHT;
		this.setLocation(Math.round(x), Math.round(y));
	}

	public Entity getEntity(){
		return entity;
	}
}
