package gameengine.map.model;

/** This class allows to instantiate map type objects
 * @author Cédric ABDELBAKI
 * @version 0.1
 */

public class Map {

	// The name of the map object
	private String name;

	// The type of the map object
	private MapType type;

	// The width of the map object
	private int width;

	// The height of the map object
	private int height;

	// The seed for procedural generation
	private long seed;

	// The 2D array of tiles representing the map object
	private Tile[][] array;

	/** Initializes a map object with a name, a type, a width and a height
	 * @param name The desired map name
	 * @param type The desired map type
	 * @param width The desired map width
	 * @param height The desired map height
	 * @param amplitude The desired amplitude value for procedural generation
	 * @param scale The desired scale value for procedural generation
	 */
	public Map (String name, MapType type, int width, int height, double amplitude, double scale) {
		this.name = name;
		this.type = type;
		this.width = width;
		this.height = height;
		try {
			this.seed = System.currentTimeMillis();
			this.array = MapArray.generateMapArray(this.type, this.width, this.height, this.seed, amplitude, scale);
		} catch (Exception e) {
			System.out.println("Error while generating the map");
		}
	}
	
	/** Initializes a map object with a name, a type, a width, a height and a tile matrice
	 * @param name The desired map name
	 * @param type The desired map type
	 * @param width The desired map width
	 * @param height The desired map height
	 * @param amplitude The desired amplitude value for procedural generation
	 * @param scale The desired scale value for procedural generation
	 * @param tileMatrice the matrix of tiles
	 */
	public Map (String name, MapType type, int width, int height, double amplitude, double scale, Tile[][] tileMatrix) {
		this.name = name;
		this.type = type;
		this.width = width;
		this.height = height;
		array = tileMatrix;
	}

	/** Gets the map name
	 * @return this.name The map name
	 */
	public String getMapName() {
		return this.name;
	}

	/** Gets the map type
	 * @return this.type The map type
	 */
	public MapType getMapType() {
		return this.type;
	}

	/** Gets the map width
	 * @return this.width The map width
	 */
	public int getMapWidth() {
		return this.width;
	}

	/** Gets the map height
	 * @return this.height The map height
	 */
	public int getMapHeight() {
		return this.height;
	}

	/** Gets the map array
	 * @return this.array The map array
	 */
	public Tile[][] getMapArray() {
		return this.array;
	}

	/** Gets the tile at a specified position
	 * @return this.array[x][y] The tile at a given position
	 */
	public Tile getTileAtPos(int x, int y) {
		try {
			return this.array[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Specified parameters provoke an array index out of bounds error");
			return null;
		}
	}

	/** Sets a desired tile at a specified position 
	 * @param tileToSet The tile to set
	 * @param x The x-axis position for the tile in the array
	 * @param y The y-axis position for the tile in the array
	 */
	public void setTileAtPost(Tile tileToSet, int x, int y) {
		try {
			this.array[x][y] = null;
			this.array[x][y] = tileToSet;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Specified parameters provoke an array index out of bounds error");
		}
	}
}
