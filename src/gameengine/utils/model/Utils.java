package gameengine.utils.model;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Utils is a class containing many methods used in the program but which is not part of a feature
 * @author Thomas Gruner
 *
 */
public class Utils {
	private final static String imagePath = "/src/gameassets/";		//the image path to get an image from the src folder
	private static String sourcePath = null;						//the path to go to the project folder
	
	/**
	 * a method that return the user path to the project folder
	 */
	private static void getSourcePath() {
		sourcePath = System.getProperty("user.dir");
	}
	
	/**
	 * a method returning the absolute path of the image 
	 * @param fileName
	 * @return String the image path
	 */
	private static String getImagePath(String fileName) {
		String path = null;
		
		if(sourcePath == null) {
			getSourcePath();
		}
		
		path = sourcePath + imagePath + fileName;
		
		return path;
	}
	
	/**
	 * a method to get the image
	 * @param fileName
	 * @return Image the image to get
	 */
	public static final Image getImage(String fileName) {
		return Toolkit.getDefaultToolkit().getImage(getImagePath(fileName));
	}
	
	/**
	 * convert a float value representing the number of blocks *to a pixel value
	 * @param tileRatio
	 * @return int the pixel value
	 */
	public static int convertToPixel(float tileRatio) {
		return (int) tileRatio * Constants.BLOCK_LENGHT;
	}
	
	/**
	 * a method to truncate a float
	 * @param f
	 * @return int the float value truncated
	 */
	public static final int truncateFloatToInt(float f) {
		return (int) f;
	}
	
	/**
	 * a method to ceil a float value
	 * @param f
	 * @return int the float value ceiled
	 */
	public static final int ceilFloatToInt(float f) {
		return (int) Math.ceil(f);
	}

	/**
	 * Converts coordinates in tile unit to pixel unit
	 * @param c coordinates to convert
	 * @return converted coordinates
	 */
	public static Coordinates convertFromTileToPixel(Coordinates c){
		return new Coordinates(c.getX()*Constants.BLOCK_LENGHT, c.getY()*Constants.BLOCK_LENGHT);
	}
}
