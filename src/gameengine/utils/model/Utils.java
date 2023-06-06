package gameengine.utils.model;

import java.awt.Image;
import java.awt.Toolkit;

// TODO : Ajouter le contrat et essayer de réaranger la classe.
/**
 * 
 * @author n7student
 *
 */
public class Utils {
	private final static String imagePath = "/src/gameassets/";
	private static String sourcePath = null;
	
	private static void getSourcePath() {
		sourcePath = System.getProperty("user.dir");
	}
	
	private static String getImagePath(String fileName) {
		String path = null;
		
		if(sourcePath == null) {
			getSourcePath();
		}
		
		path = sourcePath + imagePath + fileName;
		
		return path;
	}
	
	public static final Image getImage(String fileName) {
		return Toolkit.getDefaultToolkit().getImage(getImagePath(fileName));
	}
	
	public static int convertToPixel(float tileRatio) {
		return (int) tileRatio * Constants.BLOCK_LENGHT;
	}
	
	public static final int truncateFloatToInt(float f) {
		return (int) f;
	}
	
	public static final int ceilFloatToInt(float f) {
		return (int) Math.ceil(f);
	}
}
