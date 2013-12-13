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
		field.initialize(4);
		testplayer = new Player();
		// initialize testObject;
	}

	@Test
	public void testMovePlayer() {
		/* TODO: Wieso 2 ? */
		testplayer.setPosition(4);
		assertTrue(field.movePlayer(testplayer, 1));
		testplayer.setPosition(1);
		assertFalse(field.movePlayer(testplayer, 1));
	}
	
	@Test
	public void testGetFieldSize() {
		assertEquals(4 , field.getfieldSize());
	}

	@Test
	public void testGetCurrentField() {
		assertEquals('l', field.getCurrentField(testplayer).getType());
	}
	
	@Test
	public void testInitialize() {
		
	}

}
