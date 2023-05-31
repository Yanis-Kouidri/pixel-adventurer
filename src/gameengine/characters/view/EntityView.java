package gameengine.characters.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import gameengine.characters.model.Entity;

/**
 * the view class for all entities objects.
 * @author n7student
 *
 */
public class EntityView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Entity entity;		//the entity object to display
	private int width, height;	//the object displayed dimensions
	private Image image;		//the sprite to be displayed
	
	/**
	 * a constructor.
	 * @param entity
	 * @param width
	 * @param height
	 */
	public EntityView(Entity entity, int width, int height, Image image) {
		this.entity = entity;
		this.width = width;
		this.height = height;
		this.image = image;
	}
	
	@Override
	/**
	 * paint component add what need to be displayed.
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		//g.drawRect(100, 100, width, height);
		g.drawImage(image, width, height, this);
	
	}
}
