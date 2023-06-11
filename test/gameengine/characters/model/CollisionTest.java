package gameengine.characters.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gameengine.map.model.Map;
import gameengine.map.model.MapType;
import gameengine.map.model.Tile;

public class CollisionTest {

	private static Character mainCharacter;
	private static Map map1, map2;
	
	@BeforeClass
	public static void setUp() {
		Tile solidBlock = new Tile(0, "solid", true);
		Tile emptyBlock = new Tile(1, "empty", false);
		
		Tile tileMatrix[][] = new Tile[6][6];
		
		MapType mapType = new MapType("collision Test", emptyBlock, solidBlock, null);
		
		//simulate the hitBox of a character at position x = 1, y = 2 and a size of 2 by 2
		mainCharacter = Character.createInstance(2.0f, 3.0f, 2.0f, 2.0f);
		
		//+++++++++++ Creation of a 6x6 Tile matrix with only solid and empty block
		//The lower floor of the map is filled with solid block
		for(int x = 0; x < 6; x++) {
			tileMatrix[x][5] = solidBlock;
		}
		
		//Fill the other floor with empty blocks
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 6; x++) {
				tileMatrix[x][y] = emptyBlock;
			}
		}
		
		//Addition of few solid blocks to test collisions for map1
		tileMatrix[0][4] = solidBlock;
		tileMatrix[5][4] = solidBlock;
		tileMatrix[2][1] = solidBlock;
		
		//creation of the map1
		map1 = new Map("collisionTest", mapType, 6, 6, 15.0, 0.1, tileMatrix);
		
		//Addition of few solid blocks to test collisions for map2
		tileMatrix[0][4] = emptyBlock;
		tileMatrix[5][4] = emptyBlock;
		tileMatrix[2][1] = emptyBlock;
		tileMatrix[0][3] = solidBlock;
		tileMatrix[5][3] = solidBlock;
		tileMatrix[3][1] = solidBlock;
		
		//creation of the map1
		map2 = new Map("collisionTest", mapType, 6, 6, 15.0, 0.1, tileMatrix);
		
		//Affichage de la map1
		System.out.println("Map num 1 :");
		displayMap(map1);
		System.out.println();
		System.out.println();
		
		//Affichage de la map1
		System.out.println("Map num 2 :");
		displayMap(map2);
		System.out.println();
		System.out.println();
		
		//set up Collisions with the map1
		Collisions.setMap(map1);
		
	}
	
	@After
	public void cleanUp()
	{
		mainCharacter.setLocation(2.0f, 3.0f);
	}
	
	private static void displayMap(Map map) {
		for(int y = 0; y < 6; y++) {
			for(int x = 0; x < 6; x++) {
				if(map.getTileAtPos(x, y).getTileName() == "solid") {
					System.out.print("S  ");
				}
				else {
					System.out.print("E  ");
				}
			}
			System.out.println();
		} 
	}
	
	/**
	 * this test is for the bottom left side of the character when he is moving left side.
	 * the character will move left 3 times and can't move a fourth time because of a collision.
	 */
	@Test
	public void collisionBottomLeft() {
		
		for(int i = 0; i < 3; i++) {
			//no collision detected during the first block travel
			assertEquals(Collisions.left(mainCharacter.getHitBox()), CollisionType.NONE);
			
			mainCharacter.moveLeft();
		}
		
		//a collision is detected
		assertEquals(Collisions.left(mainCharacter.getHitBox()), CollisionType.SOLID);
	}
	
	/**
	 * this test is for the bottom right side of the character when he is moving right side.
	 * the character will move left 3 times and can't move a fourth time because of a collision.
	 */
	@Test
	public void collisionBottomRight() {
		
		for(int i = 0; i < 3; i++) {
			//no collision detected during the first block travel
			assertEquals(Collisions.right(mainCharacter.getHitBox()), CollisionType.NONE);
			
			mainCharacter.moveRight();
		}
		
		//a collision is detected
		assertEquals(Collisions.right(mainCharacter.getHitBox()), CollisionType.SOLID);
	}
	
	/**
	 * this test is for the top side of the character when he is fjumping.
	 * the character will move left 3 times and can't move a fourth time because of a collision.
	 */
	@Test
	public void collisionTopLeft() {
		//no collisions in the actual position
		assertEquals(Collisions.top(mainCharacter.getHitBox()), CollisionType.NONE);

		mainCharacter.setLocation(2.0f, 2.0f);
		
		//collisions with the new location
		assertEquals(Collisions.top(mainCharacter.getHitBox()), CollisionType.SOLID);
	}

}
