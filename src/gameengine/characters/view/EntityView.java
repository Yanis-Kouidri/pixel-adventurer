package gameengine.characters.view;

import java.awt.*;
import java.util.logging.Logger;


import javax.swing.JPanel;

import gameengine.characters.model.Entity;
import gameengine.utils.model.Coordinates;
import gameengine.utils.model.Utils;

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
	 * Draws the entity on the map.
	 * @param g the <code>Graphics</code> object to protect
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Coordinates entityPosition = Utils.convertFromTileToPixel(entity.getCoordinates());
		g.drawImage(image, (int) entityPosition.getX() , (int) entityPosition.getY(), CHARACTER_LENGHT, CHARACTER_LENGHT,this);
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

	/**
	 * Gets the entity linked to the view
	 * @return the player's character
	 */
	public Entity getEntity(){
		return entity;
	}
}
