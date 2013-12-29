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
		field.initialize(10);
		testplayer = new Player();
		// initialize testObject;
	}

	@Test
	public void testMovePlayer() {
		testplayer.setPosition(6);
		field.movePlayer(testplayer, 1);
		testplayer.setPosition(1);
		field.movePlayer(testplayer, 1);
		field.movePlayer(testplayer, 88);
	}
	
	@Test
	public void testGetFieldSize() {
		assertNotEquals(6 , field.getfieldSize());
	}

	@Test
	public void testGetCurrentField() {
		assertEquals('l', field.getCurrentField(testplayer).getType());
		field.getFieldNameAtIndex(1);
		
	}
	
	@Test
	public void testNotMyStree2t() {
		
		Player andererPlayer = new Player("hhh", 'm', 2000);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		
		field.movePlayer(testplayer, 1);
		Street a = (Street) field.getCurrentField(testplayer);
		a.setOwner(andererPlayer);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
	}
	
	@Test
	public void testNotMyStreet() {
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
	}
	@Test
	public void testInitialize() {
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		
		field.movePlayer(testplayer, 1);
		Street a = (Street) field.getCurrentField(testplayer);
		a.setOwner(testplayer);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);

		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		field.appendInfo(field.getCurrentField(testplayer), testplayer);
		field.movePlayer(testplayer, 1);
		

		

	}

}
