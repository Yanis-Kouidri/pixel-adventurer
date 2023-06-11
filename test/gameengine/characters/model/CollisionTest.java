package gameengine.characters.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import gameengine.map.model.Map;
import gameengine.map.model.MapType;
import gameengine.map.model.Tile;

public class CollisionTest {

	private Character mainCharacter;
	private Map map;
	
	@Before
	public void setUp() {
		Tile solidBlock = new Tile(0, "solid", true);
		Tile emptyBlock = new Tile(1, "empty", false);
		
		Tile tileMatrix[][] = new Tile[6][6];
		
		MapType mapType = new MapType("collision Test", emptyBlock, solidBlock, null);
		
		//simulate the hitBox of a character at position x = 1, y = 2 and a size of 2 by 2
		mainCharacter = Character.createInstance(1.0f, 2.0f, 2.0f, 2.0f);
		
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
		
		//Addition of few solid blocks to test collisions
		tileMatrix[0][4] = solidBlock;
		tileMatrix[5][4] = solidBlock;
		tileMatrix[2][1] = solidBlock;
		
		//creation of the map
		map = new Map("collisionTest", mapType, 6, 6, 15.0, 0.1, tileMatrix);
		displayMap();
		
		//set up Collisions with the map
		Collisions.setMap(map);
		
	}
	
	@AfterEach
	public void cleanUp()
	{
		mainCharacter.setLocation(1.0f, 2.0f);
	}
	
	private void displayMap() {
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

}
