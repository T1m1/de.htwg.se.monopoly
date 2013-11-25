package de.htwg.monopoly.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayfieldTest {
	
	Playfield field;
	Player testplayer;
	IFieldObject testObject;

	@Before
	public void setUp() throws Exception {
		field = new Playfield();
		field.initialize();
		testplayer = new Player();
	}

	@Test
	public void testMovePlayer() {
		testplayer.setPosition(20);
		assertTrue(field.movePlayer(testplayer, 1));
		testplayer.setPosition(0);
		assertFalse(field.movePlayer(testplayer, 1));
	}

	@Test
	public void testGetCurrentField() {
		//TODO Object equal testing
	}

}
