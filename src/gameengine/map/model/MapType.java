package gameengine.map.model;

/** This class allow to instantiate MapType type objects
 * @author Cédric ABDELBAKI
 * @version 0.1
 */

public class MapType {

	// The type name
	private String name;

	// The type empty tile
	private Tile emptyTile;

	// The type surface tile
	private Tile surfaceTile;

	// The type underground tile
	private Tile undergroundTile;

	/** Constructs a type that will be used by the map generator to know what tiles to use
	 * @param typeName The desired name for the type
	 * @param surfaceTile The desired surface tile for the type
	 * @param undergroundTile The desired underground tile for the type
	 */
	public MapType(String name, Tile emptyTile, Tile surfaceTile, Tile undergroundTile) {
		try {
			this.name = name;
			this.emptyTile = emptyTile;
			this.surfaceTile = surfaceTile;
			this.undergroundTile = undergroundTile;
		} catch (Exception e) {
			System.out.println("Error trying to initialize a MapType object");
		}
	}

	/** Gets the map type name
	 * @return this.typeName The map type name
	 */
	public String getTypeName() {
		return this.name;
	}

	/** Gets the empty tile for the map type
	 * @return this.emptyTile The empty tile
	 */
	public Tile getEmptyTile() {
		return this.emptyTile;
	}

	 /** Gets the surface tile for the map type
	 * @return this.surfaceTile The surface tile
	 */
	public Tile getSurfaceTile() {
		return this.surfaceTile;
	}

	/** Gets the underground tile for the map type
	 * @return this.undergroundTile The underground tile
	 */
	public Tile getUndergroundTile() {
		return this.undergroundTile;
	}
}

