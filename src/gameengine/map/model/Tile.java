package gameengine.map.model;

/** This class allows to instantiate Tile type objects
 * @author Cédric ABDELBAKI
 * @version 0.1
 */

public class Tile {

	// The tile identifier
	private String identifier;
	
	// The tile collision behavior
	private boolean collision;

	/** Initializes a tile object with an identifier
	 * @param identifier The desired tile identifier
	 * @param collision The desired value for the collision behavior boolean
	 */
	public Tile(String identifier, boolean collision) {
		try {
			this.identifier = identifier;
			this.collision = collision;
		} catch (Exception e) {
			System.out.println("Error trying to initialize a Tile object");
		}
	}

	/** Gets the tile identifier
	 * @return this.identifier The tile identifier
	 */
	public String getTileId() {
		return this.identifier;
	}

	/** Gets the collision behavior
	 * @return this.collision The value for the collision behavior boolean
	 */
	public boolean getCollisionBehaviour() {
		return this.collision;
	}
}
