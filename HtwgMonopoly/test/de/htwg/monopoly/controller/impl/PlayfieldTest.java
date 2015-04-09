package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.controller.impl.Playfield;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.util.FieldType;
import de.htwg.monopoly.util.IMonopolyFields;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.PlayerIcon;

public class PlayfieldTest {

	private Playfield field;
	private Player testplayer;
	IFieldObject testObject;

	@Before
	public void setUp() throws Exception {
		field = new Playfield(IMonopolyUtil.FIELD_SIZE);
		testplayer = new Player("TestName", PlayerIcon.BITTEL);
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
		assertNotEquals(6, field.getfieldSize());
	}

	@Test
	public void testGetCurrentField() {
		assertEquals(FieldType.GO, field.getFieldOfPlayer(testplayer).getType());

	}

	@Test
	public void testNotMyStree2t() {

		Player andererPlayer =  new Player("TestName", PlayerIcon.BITTEL);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);

		field.movePlayer(testplayer, 1);
		Street a = (Street) field.getFieldOfPlayer(testplayer);
		a.setOwner(andererPlayer);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
	}

	@Test
	public void testNotMyStreet() {
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);

		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
	}

	@Test
	public void testInitialize() {
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);

		field.movePlayer(testplayer, 1);
		Street a = (Street) field.getFieldOfPlayer(testplayer);
		a.setOwner(testplayer);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);

		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 1);
		field.performActionAndAppendInfo(field.getFieldOfPlayer(testplayer),
				testplayer);
		field.movePlayer(testplayer, 20);

	}

	@Test
	public void testGetStack() {
		assertEquals(FieldType.CHANCE_STACK, field.getChanStack().getType());
		assertEquals(FieldType.COMMUNITY_STACK, field.getCommStack().getType());
	}

	@Test
	public void testMovePlayerTo() {
		field.movePlayerTo(testplayer, "Bsys Labor, nur zu Besuch");
		assertTrue(testplayer.isInPrison());
		testplayer.setInPrison(false);
		testplayer.setPosition(5);
		field.movePlayerTo(testplayer, IMonopolyFields.NAME[0]);
		assertEquals(0, testplayer.getPosition());
		field.movePlayerTo(testplayer, IMonopolyFields.NAME[1]);
		assertEquals(1, testplayer.getPosition());

		IFieldObject CurrentField = field.getFieldAtIndex(0);
		assertEquals(0, CurrentField.getPosition());
		field.movePlayerTo(testplayer, "frei");
		assertTrue(testplayer.hasPrisonFreeCard());
		testplayer.usePrisonFreeCard();
		assertFalse(testplayer.hasPrisonFreeCard());

	}

	@Test
	public void movePlayerWithNumber() {
		field.movePlayerTo(testplayer, IMonopolyFields.NAME[0]);

		field.movePlayerTo(testplayer, "1");
		assertEquals(1, testplayer.getPosition());
	}

	@Test(expected = AssertionError.class)
	public void secondTestMovePlayerTo() {
		field = new Playfield(0);
		field.movePlayerTo(testplayer, "go");
	}

}
