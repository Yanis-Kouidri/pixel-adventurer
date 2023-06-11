package gameengine.characters.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import gameengine.map.model.Map;
import gameengine.map.model.MapType;
import gameengine.map.model.Tile;

public class CollisionTest {
	
	private Character mainCharacter;
	private Map map;
	
	@BeforeAll
	public void setUp() {
		Tile solidBlock = new Tile(0, "solid", true);
		Tile emptyBlock = new Tile(1, "empty", false);
		
		Tile tileMatrix[][] = new Tile[6][6];
		
		MapType mapType = new MapType("collision Test", emptyBlock, solidBlock, null);
		
		//Create the character at position x = 1, y = 2
		mainCharacter = Character.createInstance(1.0f, 2.0f);
		
		//+++++++++++ Creation of a 6x6 Tile matrix with only solid and empty block
		
		//The lower floor of the map is filled with solid block
		for(int x = 0; x < 6; x++) {
			tileMatrix[5][x] = solidBlock;
		}
		
		//Fill the other floor with empty blocks
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 6; x++) {
				tileMatrix[y][x] = emptyBlock;
			}
		}
		
		//Addition of few solid blocks to test collisions
		tileMatrix[4][0] = solidBlock;
		tileMatrix[4][5] = solidBlock;
		tileMatrix[1][2] = solidBlock;
		
		//creation of the map
		map = new Map("collisionTest", mapType, 6, 6, 15.0, 0.1, tileMatrix);
		
	}
	
	@AfterAll
	public void cleanUp()
	{
		
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
