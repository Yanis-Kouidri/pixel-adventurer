package gameengine.map.model;
import java.util.Random;

/** This class contains methods to procedurally generate a 2D array representing a map
 * @author Cédric ABDELBAKI
 * @version 0.1
 */

public class MapArray {

	/** Procedurally generates a 2D array of tiles
	 * @param width The desired array width
	 * @param height The desired array height
	 * @param seed The desired seed
	 * @param amplitude The desired noise amplitude
	 * @param scale The desired noise scale
	 * @param type The desired map type
	 * @return map The procedurally generated 2D array of tiles
	 */
	public static Tile[][] generateMapArray(MapType type, int width, int height, long seed, double amplitude, double scale) {

		// Initializes an empty 2D array representing a map of tiles
		Tile[][] mapArray = new Tile[height][width];

		// Loops on the array tiles, calculates the groundLevel and fills the array
		try {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x ++) {
					double perlinNoise = computePerlinNoise(x * scale, seed);
					double groundLevel = perlinNoise * amplitude + height / 2.0;
					if (y == (int) groundLevel) {
						mapArray[y][x] = type.getSurfaceTile();
					} else if (y > groundLevel) {
						mapArray[y][x] = type.getUndergroundTile();
					} else {
						mapArray[y][x] = type.getEmptyTile();
					}
				}
			}
			return mapArray;
		} catch (Exception e) {
			System.out.println("Error trying to generate the 2D array");
			return null;
		}
	}

	/** Generate noise by implementing Ken Perlin's algorithm in a one-dimensional version
	 * @param xTimesScale The product of x and the scale
	 * @param seed A long value to randomly generate noise
	 * @return perlinNoise A randomly generated noise value
	 */
	public static double computePerlinNoise(double xTimesScale, long seed) {
		try {
			int lowerX = (int) xTimesScale;
			int greaterX = lowerX + 1;
			double decimalPart = xTimesScale - lowerX;
			double randomValue1 = generateRandomValue(lowerX, seed);
			double randomValue2 = generateRandomValue(greaterX, seed);
			double perlinNoise = interpolate(randomValue1, randomValue2, decimalPart);
			return perlinNoise;
		} catch (Exception e) {
			System.out.println("Error trying to compute Perlin noise");
			return 0.0;
		}
	}

	/** Performs the interpolation between two specified values
	 * @param randomValueA The first value for interpolation
	 * @param randomValueB The second value for interpolation
	 * @param decimalPart The decimal part of xTimesScale (computePerlinNoise method)
	 * @return interpolatedValue
	 */
	private static double interpolate(double randomValueA, double randomValueB, double decimalPart) {
		try {
			double scaledDecimalPart = decimalPart * Math.PI;
			double interpolationFactor = (1 - Math.cos(scaledDecimalPart)) * 0.5;
			double interpolatedValue = randomValueA * (1 - interpolationFactor) + randomValueB * interpolationFactor;
			return interpolatedValue;
		} catch (Exception e) {
			System.out.println("Error trying to interpolate values");
			return 0.0;
		}
	}

	/** Generates a random double value
	 * @param integer An integer to randomly generate a double
	 * @param seed The seed for procedural generation
	 * @return random.nextDouble() A random double
	 */
	private static double generateRandomValue(int integer, long seed) {
		try {
			Random random = new Random(integer * 1337 + seed);
			return random.nextDouble();
		} catch (Exception e) {
			System.out.println("Error trying to generate a random double");
			return 0.0;
		}
	}
	
	//FOR TESTING PURPOSES
    public static void printMapForTest(Tile[][] map, int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map[y][x].getTileId());
            }
            System.out.println();
        }
    }
}

