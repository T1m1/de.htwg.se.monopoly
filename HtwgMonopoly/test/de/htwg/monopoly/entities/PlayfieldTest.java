package de.htwg.monopoly.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.util.IMonopolyUtil;

public class PlayfieldTest {
	
	Playfield field;
	Player testplayer;
	IFieldObject testObject;

	@Before
	public void setUp() throws Exception {
		field = new Playfield();
		field.initialize(1);
		testplayer = new Player();
		// initialize testObject;
	}

	@Test
	public void testMovePlayer() {
		/* TODO: Wieso 2 ? */
		testplayer.setPosition(IMonopolyUtil.TEST_PLAYFIELD_SIZE);
		assertTrue(field.movePlayer(testplayer, 1));
		testplayer.setPosition(0);
		assertFalse(field.movePlayer(testplayer, 1));
	}
	
	@Test
	public void testGetFieldSize() {
		assertEquals(1,field.getfieldSize());
	}

	@Test
	public void testGetCurrentField() {
		assertEquals('l', field.getCurrentField(testplayer).getType());
	}

}
