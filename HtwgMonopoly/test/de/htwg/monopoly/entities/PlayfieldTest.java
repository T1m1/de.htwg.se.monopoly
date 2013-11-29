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
		field.initialize();
		testplayer = new Player();
		// initialize testObject;
	}

	@Test
	public void testMovePlayer() {
		testplayer.setPosition(IMonopolyUtil.TEST_PLAYFIELD_SIZE);
		assertTrue(field.movePlayer(testplayer, 1));
		testplayer.setPosition(0);
		assertFalse(field.movePlayer(testplayer, 1));
	}

	@Test
	public void testGetCurrentField() {
		assertTrue(field.getCurrentField(testplayer).getClass().equals(testObject.getClass()));
		// not working, because the playfield is not initialized yet. Also there are no IFieldObject-Objects. Not yet.
	}

}
