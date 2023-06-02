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
	
	private Utils() {
		
	}
	
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
	
	public static Image getImage(String fileName) {
		return Toolkit.getDefaultToolkit().getImage(getImagePath(fileName));
	}
}
