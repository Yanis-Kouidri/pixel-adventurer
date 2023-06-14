package gameengine.characters.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gameengine.Exceptions.NullMapException;
import gameengine.map.model.Map;
import gameengine.map.model.MapType;
import gameengine.map.model.Tile;

public class CollisionTest {

	private static MainCharacter mainCharacter;
	private static Map map1, map2;
	
	@BeforeClass
	public static void setUp() {
		Tile solidBlock = new Tile(0, "solid", true);
		Tile emptyBlock = new Tile(1, "empty", false);
		
		Tile tileMatrix1[][] = new Tile[6][6];
		Tile tileMatrix2[][] = new Tile[6][6];
		
		MapType mapType = new MapType("collision Test", emptyBlock, solidBlock, null);
		
		//simulate the hitBox of a character at position x = 1, y = 2 and a size of 2 by 2
		mainCharacter = MainCharacter.createInstance(2.0f, 3.0f, 2.0f, 2.0f);
		
		//+++++++++++ Creation of a 6x6 Tile matrix with only solid and empty block
		//The lower floor of the map is filled with solid block
		for(int x = 0; x < 6; x++) {
			tileMatrix1[x][5] = solidBlock;
			tileMatrix2[x][5] = solidBlock;
		}
		
		//Fill the other floor with empty blocks
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 6; x++) {
				tileMatrix1[x][y] = emptyBlock;
				tileMatrix2[x][y] = emptyBlock;
			}
		}
		
		//Addition of few solid blocks to test collisions for map1
		tileMatrix1[0][4] = solidBlock;
		tileMatrix1[5][4] = solidBlock;
		tileMatrix1[2][1] = solidBlock;
		
		//creation of the map1
		map1 = new Map("collisionTest", mapType, 6, 6, 15.0, 0.1, tileMatrix1);
		
		//Addition of few solid blocks to test collisions for map2
		tileMatrix2[0][3] = solidBlock;
		tileMatrix2[5][3] = solidBlock;
		tileMatrix2[3][1] = solidBlock;
		
		//creation of the map1
		map2 = new Map("collisionTest", mapType, 6, 6, 15.0, 0.1, tileMatrix2);
		
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
					System.out.print("X  ");
				}
				else {
					System.out.print("~  ");
				}
			}
			System.out.println();
		} 
	}
	
	
	
	//******************************** MAP1 TESTS *******************************
	//***************************************************************************
	
	/**
	 * this test is for the left bottom side of the character when he is moving left side.
	 * the character will move left 3 times and can't move a fourth time because of a collision.
	 */
	@Test
	public void collisionLeftBottom() {
		
		//set up Collisions with the map1
		Collisions.setMap(map1);
		
		for(int i = 0; i < 3; i++) {
			//no collision detected during the first block travel
			assertEquals(Collisions.left(mainCharacter.getHitBox()), CollisionType.NONE);
			
			mainCharacter.moveLeft();
		}
		
		//a collision is detected
		assertEquals(Collisions.left(mainCharacter.getHitBox()), CollisionType.SOLID);
		
		//the character move to the maximum he can
		mainCharacter.moveLeft();
		assertEquals(mainCharacter.getHitBox().getX(), 1.0f);
		
		//move left an other time to be sure that he is no going further
		mainCharacter.moveLeft();
		assertEquals(mainCharacter.getHitBox().getX(), 1.0f);
	}
	
	/**
	 * this test is for the right bottom side of the character when he is moving right side.
	 * the character will move left 3 times and can't move a fourth time because of a collision.
	 */
	@Test
	public void collisionRightBottom() {
		
		//set up Collisions with the map1
		Collisions.setMap(map1);
		
		for(int i = 0; i < 3; i++) {
			//no collision detected during the first block travel
			assertEquals(Collisions.right(mainCharacter.getHitBox()), CollisionType.NONE);
			
			mainCharacter.moveRight();
		}
		
		//a collision is detected
		assertEquals(Collisions.right(mainCharacter.getHitBox()), CollisionType.SOLID);
		
		//the character move to the maximum he can
		mainCharacter.moveRight();
		assertEquals(mainCharacter.getHitBox().getX(), 3.0f);
		
		//move right an other time to be sure that he is no going further
		mainCharacter.moveRight();
		assertEquals(mainCharacter.getHitBox().getX(), 3.0f);
	}
	
	/**
	 * this test is for the top left side of the character when he is jumping.
	 */
	@Test
	public void collisionTopLeft() {
		
		//set up Collisions with the map1
		Collisions.setMap(map1);
		
		//no collisions in the actual position
		assertEquals(Collisions.top(mainCharacter.getHitBox()), CollisionType.NONE);

		mainCharacter.setLocation(2.0f, 2.0f);
		
		//collisions with the new location
		assertEquals(Collisions.top(mainCharacter.getHitBox()), CollisionType.SOLID);

		//move left an other time to be sure that he is no going further
		mainCharacter.jump();
		assertEquals(mainCharacter.getHitBox().getY(), 2.0f);
	}
	
	
	
	//******************************** MAP2 TESTS *******************************
	//***************************************************************************
	
	/**
	 * this test is for the left top side of the character when he is moving left side.
	 * the character will move left 3 times and can't move a fourth time because of a collision.
	 */
	@Test
	public void collisionLeftTop() {
		
		//set up Collisions with the map2
		Collisions.setMap(map2);
		
		for(int i = 0; i < 3; i++) {
			//no collision detected during the first block travel
			assertEquals(Collisions.left(mainCharacter.getHitBox()), CollisionType.NONE);
			
			mainCharacter.moveLeft();
		}
		
		//a collision is detected
		assertEquals(Collisions.left(mainCharacter.getHitBox()), CollisionType.SOLID);
	}
	
	/**
	 * this test is for the right top side of the character when he is moving right side.
	 * the character will move left 3 times and can't move a fourth time because of a collision.
	 */
	@Test
	public void collisionRightTop() {
		
		//set up Collisions with the map2
		Collisions.setMap(map2);
		
		
		for(int i = 0; i < 3; i++) {
			//no collision detected during the first block travel
			assertEquals(Collisions.right(mainCharacter.getHitBox()), CollisionType.NONE);
			
			mainCharacter.moveRight();
		}
		
		//a collision is detected
		assertEquals(Collisions.right(mainCharacter.getHitBox()), CollisionType.SOLID);
	}
	
	/**
	 * this test is for the top right side of the character when he is jumping.
	 */
	@Test
	public void collisionTopRight() {
		
		//set up Collisions with the map2
		Collisions.setMap(map2);
		
		//no collisions in the actual position
		assertEquals(Collisions.top(mainCharacter.getHitBox()), CollisionType.NONE);

		mainCharacter.setLocation(2.0f, 2.0f);
		
		//collisions with the new location
		assertEquals(Collisions.top(mainCharacter.getHitBox()), CollisionType.SOLID);
	}
	
	/**
	 * this test is for the left middle side of the character when he is moving left side.
	 * the character will move left 3 times and can't move a fourth time because of a collision.
	 */
	@Test
	public void collisionLeftMiddle() {
		
		//set up Collisions with the map2
		Collisions.setMap(map2);
		
		mainCharacter.setLocation(2.0f, 2.0f);
		
		for(int i = 0; i < 3; i++) {
			//no collision detected during the first block travel
			assertEquals(Collisions.left(mainCharacter.getHitBox()), CollisionType.NONE);
			
			mainCharacter.moveLeft();
		}
		
		//a collision is detected
		assertEquals(Collisions.left(mainCharacter.getHitBox()), CollisionType.SOLID);
	}
	
	/**
	 * this test is for the right middle side of the character when he is moving right side.
	 * the character will move left 3 times and can't move a fourth time because of a collision.
	 */
	@Test
	public void collisionRightMiddle() {
		
		//set up Collisions with the map2
		Collisions.setMap(map2);
		
		mainCharacter.setLocation(2.0f, 2.0f);
		
		for(int i = 0; i < 3; i++) {
			//no collision detected during the first block travel
			assertEquals(Collisions.right(mainCharacter.getHitBox()), CollisionType.NONE);
			
			mainCharacter.moveRight();
		}
		
		//a collision is detected
		assertEquals(Collisions.right(mainCharacter.getHitBox()), CollisionType.SOLID);
	}
	
	/**
	 * this test is for the top right side of the character when he is jumping.
	 */
	@Test
	public void collisionTopMiddle() {
		
		//set up Collisions with the map2
		Collisions.setMap(map2);
		
		mainCharacter.setLocation(1.5f, 2.0f);
		
		assertEquals(Collisions.top(mainCharacter.getHitBox()), CollisionType.SOLID);
	}
	
	@Test(expected = NullMapException.class)
	public void nullMapExceptionTestTopSide() {
		//set up collision with a map of null
		Collisions.setMap(null);
		
		Collisions.top(mainCharacter.getHitBox());
	}
	
	@Test(expected = NullMapException.class)
	public void nullMapExceptionTestBottomSide() {
		//set up collision with a map of null
		Collisions.setMap(null);
		
		Collisions.bottom(mainCharacter.getHitBox());
	}
	
	@Test(expected = NullMapException.class)
	public void nullMapExceptionTestLeftSide() {
		//set up collision with a map of null
		Collisions.setMap(null);
		
		Collisions.left(mainCharacter.getHitBox());
	}
	
	@Test(expected = NullMapException.class)
	public void nullMapExceptionTestRightSide() {
		//set up collision with a map of null
		Collisions.setMap(null);
		
		Collisions.right(mainCharacter.getHitBox());
	}
}
