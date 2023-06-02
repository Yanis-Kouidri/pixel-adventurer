package gameengine.characters.view;

import java.awt.*;
import java.util.logging.Logger;


import javax.swing.JPanel;

import gameengine.characters.model.Entity;

import static gameengine.utils.model.Constants.BLOCK_LENGHT;

/**
 * the view class for all entities objects.
 * @author n7student
 *
 */
public class EntityView extends JPanel{
	private final Logger LOGGER = Logger.getLogger(String.valueOf(this.getClass()));
	private Entity entity;		//the entity object to display
	private int width, height;	//the object displayed dimensions
	private Image image;		//the sprite to be displayed
	
	/**
	 * a constructor.
	 * @param entity
	 * @param width
	 * @param height
	 */
	public EntityView(Entity entity, Image image) {
		this.entity = entity;
		this.image = image;
	}
	

	/**
	 * paint add what need to be displayed.
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(image, 0, 0, this);
		LOGGER.info("mainCharacter Paint");
	}

	public void updateLocation() {
		float x = entity.getCoordinates().getX() * BLOCK_LENGHT;
		float y = entity.getCoordinates().getY() * BLOCK_LENGHT;
		this.setLocation(Math.round(x), Math.round(y));
	}
}
